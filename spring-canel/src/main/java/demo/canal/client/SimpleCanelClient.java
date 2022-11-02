package demo.canal.client;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

public class SimpleCanelClient {

    public static void main(String[] args) {

        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("172.0.0.1", 11111),
                "test", "canal", "canal");
        int emptyCount = 0;
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            int totalEmptyCount = 120;
            while (emptyCount < totalEmptyCount) {
                Message message = connector.getWithoutAck(batchSize);
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (size == 0 || batchId == -1) {
                    emptyCount++;
                    System.out.println("empty count=" + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                } else {
                    emptyCount = 0;
                    printEntry(message.getEntries());
                }

                connector.ack(batchId);
//                connector.rollback(batchId);
            }
            System.out.println("empty too many times,exit.");
        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType().equals(CanalEntry.EntryType.TRANSACTIONBEGIN)
                    || entry.getEntryType().equals(CanalEntry.EntryType.TRANSACTIONEND)) {
                continue;
            }
            CanalEntry.RowChange rowChange = null;
            try {
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            CanalEntry.EventType eventType = rowChange.getEventType();

            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData data : rowChange.getRowDatasList()) {
                if (eventType.equals(CanalEntry.EventType.INSERT)) {
                    printColumn(data.getAfterColumnsList());
                } else if (eventType.equals(CanalEntry.EventType.DELETE)) {
                    printColumn(data.getBeforeColumnsList());
                } else {
                    System.out.println("-------&gt; before");
                    printColumn(data.getBeforeColumnsList());
                    System.out.println("-------&gt; after");
                    printColumn(data.getAfterColumnsList());
                }
            }
        }
    }

    private static void printColumn(List<CanalEntry.Column> columnsList) {
        for (CanalEntry.Column column : columnsList) {
            System.out.println("columnName = " + column.getName()
                    + ", columnVale = " + column.getValue()
                    + ", isUpdate = " + column.getUpdated() + "\n");
        }
    }

}

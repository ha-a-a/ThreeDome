package demo.nettyclient.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class EchoClientThirdChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("third client received: " + msg.toString(CharsetUtil.UTF_8));
        // 为什么这里的索引值是2？
        System.out.println("msg.refCnt():"+msg.refCnt());
        // channelRead0执行完毕后，SimpleChannelInboundHandler的模板方法会再一次进行释放release,即msg计数器-1，
        //这是就触发了IllegalReferenceCountException
        // msg引用计数器+1
        msg.retain();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks from client",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Netty client caught error");
        cause.printStackTrace();
        ctx.close();
    }
}
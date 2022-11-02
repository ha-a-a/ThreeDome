package demo.nettyclient.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientSecondChannelHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("second client received: " + msg.toString(CharsetUtil.UTF_8));
        // channelRead执行完毕后，SimpleChannelInboundHandler的模板方法会再一次进行释放release,即msg计数器-1，
        //这是就触发了IllegalReferenceCountException
        // msg引用计数器+1
        ctx.fireChannelRead(msg.retain());
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

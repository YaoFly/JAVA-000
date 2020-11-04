package netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * @description:
 * @author: yaohui.wang
 * @since: 2020/11/04 22:30
 */
public class NettyServerFilter extends SimpleChannelInboundHandler<FullHttpRequest> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        String nio = fullHttpRequest.headers().get("nio");
        if(nio !=null && nio.equals("wangyaohui")){

        }
    }
}

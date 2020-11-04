package netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @description:
 * @author: yaohui.wang
 * @since: 2020/10/28 10:20
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private NettyFilter filter;

    public NettyServerHandler(NettyFilter filter){
        this.filter = filter;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    private OkHttpClient client = new OkHttpClient();

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) {
        Request.Builder request = new Request.Builder()
                .get()
                .url("http://localhost:8081");
        filter.execute(request);
        Call call = client.newCall(request.build());
        String content;
        try ( Response response = call.execute()){
            if(response.isSuccessful()){
                content = response.message();
            }else{
                content = "后端系统调用调用异常";
            }
        } catch (IOException e) {
            content = "后端系统调用调用异常";
            e.printStackTrace();
        }
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes()));

        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN + "; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);

        channelHandlerContext.write(response);
    }
}

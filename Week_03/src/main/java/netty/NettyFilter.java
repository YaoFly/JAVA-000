package netty;

import okhttp3.Request;

public interface NettyFilter {
    public void execute(Request.Builder request);
}

package netty;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @description:
 * @author: yaohui.wang
 * @since: 2020/10/28 10:21
 */
public class NettyTest {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("http://localhost:8081")
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.isSuccessful()){
                System.out.println(response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

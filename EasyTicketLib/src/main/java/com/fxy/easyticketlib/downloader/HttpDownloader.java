package com.fxy.easyticketlib.downloader;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class HttpDownloader {
    private static OkHttpClient client;

    private HttpDownloader() {
    }

    private static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    private static OkHttpClient getProxyClient(String hostName, int port) {
        if (client == null) {
            client = new OkHttpClient
                    .Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostName, port)))
                    .build();
        }
        return client;
    }

    public static String getPage(NetRequest request) throws IOException {
        return getPage(request.getUrl(), request.getBody(), request.getCookie(), request.getHost(), request.getPort(), request.getContentType(), request.isIsProxy());
    }

    public static String getPage(String url,//请求的网页
                                 RequestBody body,//请求体
                                 String cookie,//cookie
                                 String host,//host
                                 int port,//port
                                 String contentType,//编码类型
                                 boolean isProxy//是否使用代理
    ) throws IOException {
        if (!isProxy) {
            client = getClient();
        } else {
            client = getProxyClient(host, port);
        }
        Request.Builder builder = new Request
                .Builder()
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .addHeader("connection", "Keep-Alive")
                .addHeader("accept","*/*");
        if (cookie != null) {
            builder.addHeader("cookie", cookie);
        }
        if (body != null) {
            builder.post(body);
        }
        Request request = builder.url(url).build();

        ResponseBody target = client.newCall(request).execute().body();
        if (target == null) {
            return "";
        } else {
            if (contentType == null) {
                return target.string();
            } else {
                return new String(target.bytes(), contentType);
            }
        }
    }
}

package com.fxy.easyticketlib.downloader;

import okhttp3.RequestBody;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class NetRequest {
    private String mUrl;
    private String mHost;
    private int mPort;
    private String mCookie;
    private String mContentType;
    private boolean mIsProxy;
    private RequestBody mBody;

    private NetRequest(String mUrl,
                       String mHost,
                       int mPort,
                       String mCookie,
                       String mContentType,
                       boolean mIsProxy,
                       RequestBody mBody) {
        this.mUrl = mUrl;
        this.mHost = mHost;
        this.mPort = mPort;
        this.mCookie = mCookie;
        this.mContentType = mContentType;
        this.mIsProxy = mIsProxy;
        this.mBody = mBody;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getHost() {
        return mHost;
    }

    public int getPort() {
        return mPort;
    }

    public String getCookie() {
        return mCookie;
    }

    public String getContentType() {
        return mContentType;
    }

    public boolean isIsProxy() {
        return mIsProxy;
    }

    public RequestBody getBody() {
        return mBody;
    }

    public static class Builder {
        private String url;
        private String host;
        private int port;
        private String cookie;
        private String contentType;
        private boolean isProxy;
        private RequestBody body;

        public Builder() {
            url = "";
            host = null;
            port = 0;
            cookie = "";
            contentType = "gbk";
            isProxy = false;
            body = null;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }


        public Builder setCookie(String cookie) {
            this.cookie = cookie;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setProxy(boolean proxy) {
            isProxy = proxy;
            return this;
        }

        public Builder setBody(RequestBody body) {
            this.body = body;
            return this;
        }

        public NetRequest build() {
            check();
            return new NetRequest(url, host, port, cookie, contentType, isProxy, body);
        }

        private void check() {
            if (url == null) {
                throw new RuntimeException("request must have url");
            }
            if (isProxy && host == null) {
                throw new RuntimeException("use proxy must have host");
            }
            if (host != null && port == 0) {
                throw new RuntimeException("host can't without a port");
            }
        }
    }
}

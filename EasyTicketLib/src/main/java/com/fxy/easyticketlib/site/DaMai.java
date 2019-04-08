package com.fxy.easyticketlib.site;

import com.fxy.easyticketlib.bean.Ticket;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class DaMai implements SiteStrategy {
    private int num = 1;
    @Override
    public String getSiteUrl(String search) {
        return "https://search.damai.cn/search.html?keyword="+search+"&spm=a2oeg.home.searchtxt.dsearchbtn";
    }

    @Override
    public int getPageNum(String page) {
        return 1;
    }

    @Override
    public void setPageNum(int num) {
        this.num = num;
    }

    @Override
    public List<Ticket> parse(String page) {

        return null;
    }

    @Override
    public String getContentType() {
        return "utf-8";
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getCookie() {
        return null;
    }

    @Override
    public RequestBody getRequestBody() {
        return new FormBody
                .Builder()
                .add("currPage", String.valueOf(num))
                .build();
    }

    @Override
    public boolean isProxy() {
        return false;
    }
}

package com.fxy.easyticketlib.site;

import com.fxy.easyticketlib.bean.Ticket;

import java.util.List;

import okhttp3.RequestBody;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 * 每个站点设置不同的策略
 */
public interface SiteStrategy {
    String getSiteUrl(String search);
    int getPageNum(String page);
    void setPageNum(int num);
    List<Ticket> parse(String page);
    String getContentType();
    String getHost();
    int getPort();
    String getCookie();
    RequestBody getRequestBody();
    boolean isProxy();
}

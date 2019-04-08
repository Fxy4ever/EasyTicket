package com.fxy.easyticketlib.scheduler;

import com.fxy.easyticketlib.bean.Ticket;
import com.fxy.easyticketlib.downloader.HttpDownloader;
import com.fxy.easyticketlib.downloader.NetRequest;
import com.fxy.easyticketlib.engine.TicketRequest;
import com.fxy.easyticketlib.site.SiteStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class TicketScheduler {
    private ExecutorService pool = Executors.newCachedThreadPool();

    public void submit(TicketRequest request, final Callback callback){

        final String search = request.getSearch();
        HashSet<SiteStrategy> sites = request.getSites();//获取所有站点

        for (final SiteStrategy site : sites) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        final List<Ticket> tickets = new ArrayList<>();
                        NetRequest net = new NetRequest.Builder()//用每个站点设置的不同策略包装网络请求reqeust
                                .setUrl(site.getSiteUrl(search))
                                .setContentType(site.getContentType())
                                .setBody(site.getRequestBody())
                                .setCookie(site.getCookie())
                                .setHost(site.getHost())
                                .setPort(site.getPort())
                                .setProxy(site.isProxy())
                                .build();

                        String page = HttpDownloader.getPage(net);
                        int maxNum = site.getPageNum(page);//通过每个站点设置的策略，解析一共有多少页

                        for (int i = 1; i <= maxNum; i++) {

                            site.setPageNum(i);//设置当前页数

                            final NetRequest every = new NetRequest.Builder()
                                    .setUrl(site.getSiteUrl(search))
                                    .setContentType(site.getContentType())
                                    .setBody(site.getRequestBody())
                                    .setCookie(site.getCookie())
                                    .setHost(site.getHost())
                                    .setPort(site.getPort())
                                    .setProxy(site.isProxy())
                                    .build();

                            pool.execute(new Runnable() {
                                @Override
                                public void run() {
                                    String everyPage = null;
                                    try {
                                        everyPage = HttpDownloader.getPage(every);//逐个请求每一页
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    if(everyPage!=null){
                                        tickets.addAll(site.parse(everyPage));//解析每一页的信息
                                        callback.onFinish(tickets);
                                    }else{
                                        callback.onError(new RuntimeException("Page is null"));
                                    }
                                }
                            });

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public interface Callback{
        void onFinish(List<Ticket> list);
        void onError(Throwable e);
    }
}

package com.fxy.easyticketlib.engine;

import com.fxy.easyticketlib.bean.Ticket;
import com.fxy.easyticketlib.scheduler.TicketScheduler;
import com.fxy.easyticketlib.site.SiteManager;
import com.fxy.easyticketlib.site.SiteStrategy;

import java.util.List;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class TicketProcesser {
    private static TicketScheduler scheduler = new TicketScheduler();

    public static void search(String search){
        scheduler.submit(new TicketRequest(search), new TicketScheduler.Callback() {
            @Override
            public void onFinish(List<Ticket> list) {
                System.out.println(list);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public static void addSite(SiteStrategy site){
        SiteManager.getINSTANCE().addSite(site);
    }
}

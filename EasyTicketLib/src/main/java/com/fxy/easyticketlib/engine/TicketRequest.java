package com.fxy.easyticketlib.engine;

import com.fxy.easyticketlib.site.SiteManager;
import com.fxy.easyticketlib.site.SiteStrategy;

import java.util.HashSet;
import java.util.List;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class TicketRequest {
    private String search;
    private HashSet<SiteStrategy> sites;

    public TicketRequest(String search) {
        this.search = search;
        sites = SiteManager.getINSTANCE().getSites();
    }

    public String getSearch() {
        return search;
    }

    public HashSet<SiteStrategy> getSites() {
        return sites;
    }
}

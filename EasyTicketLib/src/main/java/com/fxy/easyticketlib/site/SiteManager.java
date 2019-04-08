package com.fxy.easyticketlib.site;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class SiteManager {
    private HashSet<SiteStrategy> sites;

    private static SiteManager INSTANCE;

    public static SiteManager getINSTANCE(){
        if(INSTANCE == null){
            synchronized (SiteManager.class){
                if(INSTANCE == null){
                    INSTANCE = new SiteManager();
                }
            }
        }
        return INSTANCE;
    }

    private SiteManager() {
       sites = new LinkedHashSet<>();
       //sites.add(new DaMai());
       sites.add(new XiuDong());
    }

    public void addSite(SiteStrategy site){
        sites.add(site);
    }

    public void changeSite(HashSet<SiteStrategy> sites){
        this.sites = sites;
    }

    public HashSet<SiteStrategy> getSites() {
        return sites;
    }
}

package com.fxy.easyticketlib.site;

import com.fxy.easyticketlib.bean.Ticket;
import com.fxy.easyticketlib.downloader.HttpDownloader;
import com.fxy.easyticketlib.downloader.NetRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.RequestBody;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class XiuDong implements SiteStrategy {
    private int num = 1;

    @Override
    public String getSiteUrl(String search) {
        return "https://www.showstart.com/event/list?keyword="+search+"&isList=1&pageNo="+num;
    }

    @Override
    public int getPageNum(String page) {
        Document doc = Jsoup.parse(page);
        Elements links = doc.getElementsByAttributeValue("class","page");
        int max = 1;
        for (Element link : links) {
            String num = link.text().replaceAll("\\.","");
            String[] res = num.split(" ");
            max = Integer.valueOf(res[res.length-1]);
        }
        return max;
    }

    @Override
    public void setPageNum(int num) {
        this.num = num;
    }

    @Override
    public List<Ticket> parse(String page) {
        List<Ticket> res = new ArrayList<>();
        Document doc = Jsoup.parse(page);
        Element ul = doc.getElementsByClass("g-list-wrap justify MT30").first();
        if(ul == null)//没有结果直接返回
            return res;
        Elements li = ul.select("li");

        for (Element element : li) {
            Ticket ticket = new Ticket();

            Element a = element.select("a").first();

            String img = "";
            if(a!=null){
                img = a.getElementsByClass("g-img").select("img[original]").attr("original");
                String rawSite = element.select("a[href]").attr("href");
                String title = a.select("a[title]").attr("title");

                //获取详情网站，封面和名字
                ticket.setRawSite(rawSite);
                ticket.setPhoto(img);
                ticket.setTitle(title);

                ticket.setSite("秀动");
                try {
                    Document detail = Jsoup.parse(getInnerPage(rawSite));
                    Element e = detail.getElementsByClass("items-list").first();
                    Elements innerLi = e.getElementsByTag("li");

                    //获取五个属性
                    ticket.setTime(innerLi.get(0).text().substring(5));
                    ticket.setSinger(innerLi.get(1).text().substring(4));
                    ticket.setLocation(innerLi.get(2).text().substring(4));
                    ticket.setDetail(innerLi.get(3).text().substring(3).substring(0,innerLi.get(3).text().substring(3).length()-5));
                    ticket.setPhone(innerLi.get(4).text().substring(3));

                    //获取介绍
                    StringBuilder decBuilder = new StringBuilder();
                    for (Element dec : detail.getElementsByClass("dec").first().getElementsByTag("p")) {
                        decBuilder.append(dec.text()).append("\n");
                    }
                    ticket.setDec(decBuilder.toString());
                    //解析票价
                    List<Ticket.Price> prices = new ArrayList<>();
                    Elements priceLi =detail.getElementsByClass("ticket MT30").first().select("li");
                    for (Element element1 : priceLi) {//每一种票的情况
                        String kind = element1.text();
                        String stockNum = element1.attr("stocknum");
                        String starTime = element1.attr("starttime");
                        String endTime = element1.attr("endtime");
                        Ticket.Price price = new Ticket.Price();
                        price.setKind(kind);
                        price.setStartTime(starTime);
                        price.setEndTime(endTime);
                        price.setStockNum(stockNum);
                        prices.add(price);
                    }

                    ticket.setPrice(prices);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ticket.getTitle()!=null)
                 res.add(ticket);
        }
        return res;
    }

    public String getInnerPage(String rawSite) throws IOException {
        NetRequest inner = new NetRequest.Builder().setUrl(rawSite).setContentType("utf-8").build();
        return HttpDownloader.getPage(inner);
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
        return "apploadCookie=1; st_flpv=2019040788f4ac67552b618b8fab6d58f902f1b6; homecity=0; Hm_lvt_2c43fd2e9d23abe0e14a356c25051c2d=1554652796,1554694666; lcityId=0; o_s=https://www.showstart.com/event/list?keyword=%E9%87%8D%E5%BA%86; u_s=https://www.showstart.com/event/78743; Hm_lpvt_2c43fd2e9d23abe0e14a356c25051c2d=1554711757";
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public boolean isProxy() {
        return false;
    }
}

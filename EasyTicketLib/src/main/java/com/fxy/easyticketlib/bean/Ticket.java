package com.fxy.easyticketlib.bean;

import java.io.Serializable;
import java.util.List;

/**
 * create by:Fxymine4ever
 * time: 2019/4/8
 */
public class Ticket implements Serializable {
    private String singer;
    private String title;
    private String photo;
    private String location;
    private String time;
    private String phone;
    private String detail;
    private String rawSite;
    private String site;
    private List<Price> price;
    private String dec;

    public Ticket() {
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPhoto() {
        return photo;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRawSite() {
        return rawSite;
    }

    public void setRawSite(String rawSite) {
        this.rawSite = rawSite;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "singer='" + singer + '\'' + "\n"+
                ", title='" + title + '\'' + "\n"+
                ", photo='" + photo + '\'' + "\n"+
                ", location='" + location + '\'' + "\n"+
                ", time='" + time + '\'' + "\n"+
                ", phone='" + phone + '\'' + "\n"+
                ", detail='" + detail + '\'' + "\n"+
                ", rawSite='" + rawSite + '\'' + "\n"+
                ", site='" + site + '\'' + "\n"+
                ", price=" + price + "\n"+
                //", dec='" + dec + '\'' +
                '}'+"\n\n";
    }

    public static class Price{
        private String kind;
        private String stockNum;
        private String startTime;
        private String endTime;

        public Price() {
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getStockNum() {
            return stockNum;
        }

        public void setStockNum(String stockNum) {
            this.stockNum = stockNum;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "Price{" +
                    "kind='" + kind + '\'' +
                    ", stockNum='" + stockNum + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    '}';
        }
    }
}

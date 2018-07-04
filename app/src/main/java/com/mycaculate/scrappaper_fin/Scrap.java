package com.mycaculate.scrappaper_fin;

public class Scrap {
    private int id;
    private String scrap_date;
    private String scrap_text;
    private String scrap_ipt;

    public Scrap(int id, String scrap_date, String scrap_text, String scrap_ipt) {
        this.id = id;
        this.scrap_date = scrap_date;
        this.scrap_text = scrap_text;
        this.scrap_ipt = scrap_ipt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScrap_date() {
        return scrap_date;
    }

    public void setScrap_date(String scrap_date) {
        this.scrap_date = scrap_date;
    }

    public String getScrap_text() {
        return scrap_text;
    }

    public void setScrap_text(String scrap_text) {
        this.scrap_text = scrap_text;
    }

    public String getScrap_ipt() {
        return scrap_ipt;
    }

    public void setScrap_ipt(String scrap_ipt) {
        this.scrap_ipt = scrap_ipt;
    }
}

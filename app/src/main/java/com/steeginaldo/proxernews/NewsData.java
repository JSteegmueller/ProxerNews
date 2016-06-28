package com.steeginaldo.proxernews;

/**
 * Created by Janik on 27.06.2016.
 */
class NewsData {

    private String Titel;
    private String Datum;
    private String Zsm;
    private String url;

    public String getDatum() {
        return Datum;
    }

    public String getZsm() {
        return Zsm;
    }

    public String getTitel() {
        return Titel;
    }

    public String getUrl() {
        return url;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }

    public void setTitel(String titel) {
        Titel = titel;
    }

    public void setZsm(String zsm) {
        Zsm = zsm;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

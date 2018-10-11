package com.tinh.dev.applove.model;

public class NhatKi {
    private String date;
    private int anh;
    private String uri;
    private String nhatki;

    public NhatKi() {
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getNhatki() {
        return nhatki;
    }

    public void setNhatki(String nhatki) {
        this.nhatki = nhatki;
    }

    public NhatKi(String date, int anh, String uri, String nhatki) {

        this.date = date;
        this.anh = anh;
        this.uri = uri;
        this.nhatki = nhatki;
    }
}

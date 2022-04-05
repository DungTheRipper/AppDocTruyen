package com.example.metruyen.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Truyen implements Serializable {
    private String tenTruyen, linkBia,id,loaiTruyen;

    public Truyen(){

    }

    public Truyen(JSONObject o) throws JSONException {
        id = o.getString("id");
        tenTruyen = o.getString("tenTruyen");
        linkBia = o.getString("linkBia");
        loaiTruyen = o.getString("loaiTruyen");
    }

    public String getLoaiTruyen() {
        return loaiTruyen;
    }

    public void setLoaiTruyen(String loaiTruyen) {
        this.loaiTruyen = loaiTruyen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Truyen(String tenTruyen, String linkBia) {
        this.tenTruyen = tenTruyen;
        this.linkBia = linkBia;
    }

    public String getLinkBia() {
        return linkBia;
    }

    public void setLinkBia(String linkBia) {
        this.linkBia = linkBia;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }
}

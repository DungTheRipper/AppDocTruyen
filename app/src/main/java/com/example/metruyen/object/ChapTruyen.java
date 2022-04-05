package com.example.metruyen.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ChapTruyen implements Serializable {
    private String tenChap,noiDung;

    public ChapTruyen(){

    }

    public ChapTruyen(JSONObject o) throws JSONException {
        tenChap = o.getString("tenChap");
        noiDung = o.getString("textTruyen");
    }

    public ChapTruyen(String tenChap, String noiDung) {
        this.tenChap = tenChap;
        this.noiDung = noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiDung() {
        return noiDung;
    }



    public ChapTruyen(String tenChap) {
        this.tenChap = tenChap;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }
}

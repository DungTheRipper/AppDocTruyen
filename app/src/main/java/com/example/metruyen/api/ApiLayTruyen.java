package com.example.metruyen.api;

import android.os.AsyncTask;

import com.example.metruyen.interfaces.LayTruyenVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiLayTruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayTruyenVe layTruyenVe;

    public ApiLayTruyen(LayTruyenVe layTruyenVe) {
        this.layTruyenVe = layTruyenVe;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url("https://dungtheripper.000webhostapp.com/layTruyen.php")
                .build();
        data=null;
        try{
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data=body.string();
        }catch (IOException e){
            data=null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        this.layTruyenVe.loading(data);
    }
}

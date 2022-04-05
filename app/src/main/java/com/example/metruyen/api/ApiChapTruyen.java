package com.example.metruyen.api;

import android.os.AsyncTask;

import com.example.metruyen.ChapActivity;
import com.example.metruyen.interfaces.LayChapVe;
import com.example.metruyen.interfaces.LayTruyenVe;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class ApiChapTruyen extends AsyncTask<Void,Void,Void> {
    String data;
    LayChapVe layChapVe;
    String idTruyen;
    public ApiChapTruyen(LayChapVe layChapVe, String idTruyen) {
        this.idTruyen=idTruyen;
        this.layChapVe = layChapVe;
    }

    public ApiChapTruyen(ChapActivity layChapVe) {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url("https://dungtheripper.000webhostapp.com/layChap.php?id="+idTruyen)
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
        this.layChapVe.loading(data);
    }
}

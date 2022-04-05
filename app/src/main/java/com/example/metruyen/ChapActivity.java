package com.example.metruyen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.metruyen.adapter.ChapTruyenAdapter;
import com.example.metruyen.adapter.TruyenAdapter;
import com.example.metruyen.api.ApiChapTruyen;
import com.example.metruyen.api.ApiLayTruyen;
import com.example.metruyen.interfaces.LayChapVe;
import com.example.metruyen.object.ChapTruyen;
import com.example.metruyen.object.Truyen;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity implements LayChapVe {
    TextView txvTenTruyens;
    TextView txvLoaiTruyen;
    ImageView imgAnhTruyens;
    ImageView imgClose;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;
    Truyen truyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiChapTruyen(this,truyen.getId()).execute();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyen= (Truyen) b.getSerializable("truyen");
        arrChap=new ArrayList<>();
    }
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        txvLoaiTruyen = findViewById(R.id.txvLoaiTruyen);
        imgClose = findViewById(R.id.imgClose);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    }
    private void setUp(){
        lsvDanhSachChap.setAdapter(chapTruyenAdapter);
        txvTenTruyens.setText(truyen.getTenTruyen());
        txvLoaiTruyen.setText(truyen.getLoaiTruyen());
        Glide.with(this).load(truyen.getLinkBia()).into(imgAnhTruyens);
    }
    private void setClick(){
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        lsvDanhSachChap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChapTruyen chapTruyen =  arrChap.get(i);
                Bundle b = new Bundle();
                b.putSerializable("chap",chapTruyen);
                b.putSerializable("truyen",truyen);
                Intent intent = new Intent(ChapActivity.this, ReadChapActivity.class);
                intent.putExtra("data",b);
                intent.putExtra("datachap", b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loading(String data) {
        try{
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                ChapTruyen chapTruyen = new ChapTruyen(array.getJSONObject(i));
                arrChap.add(chapTruyen);
            }
            chapTruyenAdapter = new ChapTruyenAdapter(this,0,arrChap);
            lsvDanhSachChap.setAdapter(chapTruyenAdapter);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

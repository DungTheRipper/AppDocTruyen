package com.example.metruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.metruyen.adapter.TruyenAdapter;
import com.example.metruyen.api.ApiLayTruyen;
import com.example.metruyen.interfaces.LayTruyenVe;
import com.example.metruyen.object.ChapTruyen;
import com.example.metruyen.object.Truyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyenTim;
    TruyenAdapter adapter;
    ArrayList<Truyen> truyenArrayList;
    TextView edtTimTruyen;
    String tenTim;
    public static String convert(String str) {
        str = str.replaceAll("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
        str = str.replaceAll("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
        str = str.replaceAll("ì|í|ị|ỉ|ĩ", "i");
        str = str.replaceAll("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
        str = str.replaceAll("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
        str = str.replaceAll("ỳ|ý|ỵ|ỷ|ỹ", "y");
        str = str.replaceAll("đ", "d");

        str = str.replaceAll("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
        str = str.replaceAll("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
        str = str.replaceAll("Ì|Í|Ị|Ỉ|Ĩ", "I");
        str = str.replaceAll("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
        str = str.replaceAll("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
        str = str.replaceAll("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
        str = str.replaceAll("Đ", "D");
        return str;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }
    void init(){
        tenTim = "";
        Bundle b = getIntent().getBundleExtra("datatim");
        tenTim = (String) b.getSerializable("tentim");
        truyenArrayList = new ArrayList<>();
        adapter = new TruyenAdapter(this,0,truyenArrayList);
    };
    void anhXa(){
        gdvDSTruyenTim = findViewById(R.id.gdvDSTruyenTim);
        edtTimTruyen = findViewById(R.id.edtTimTruyen);
    };
    void setUp(){
        gdvDSTruyenTim.setAdapter(adapter);
        edtTimTruyen.setText(tenTim);
    };
    void setClick(){
        gdvDSTruyenTim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Truyen truyen = truyenArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyen);
                Intent intent = new Intent(SearchActivity.this, ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    };

    public void home(View view) {
        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loading(String data) {
        try{
            truyenArrayList.clear();
            JSONArray arr = new JSONArray(data);
            String tenSearch = tenTim.toUpperCase();
            for(int i=arr.length()-1;i>=0;i--){
                JSONObject o = arr.getJSONObject(i);
                Truyen t= new Truyen(o);
                String ten=convert(t.getTenTruyen().toUpperCase());
                String loai=convert(t.getLoaiTruyen().toUpperCase());
                if(ten.indexOf(tenSearch)>=0||loai.indexOf(tenSearch)>=0){
                    truyenArrayList.add(t);
                }
            }
            if(tenTim.equals("")) truyenArrayList.clear();
            adapter = new TruyenAdapter(this,0,truyenArrayList);
            gdvDSTruyenTim.setAdapter(adapter);
        }catch (JSONException e){

        }
    }
}

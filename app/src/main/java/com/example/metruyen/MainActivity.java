package com.example.metruyen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.metruyen.adapter.TruyenAdapter;
import com.example.metruyen.api.ApiChapTruyen;
import com.example.metruyen.api.ApiLayTruyen;
import com.example.metruyen.interfaces.LayChapVe;
import com.example.metruyen.interfaces.LayTruyenVe;
import com.example.metruyen.object.Truyen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gdvDSTruyen;
    TruyenAdapter adapter;
    ArrayList<Truyen> truyenArrayList;
    EditText edtTimKiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClick();
        new ApiLayTruyen(this).execute();
    }

    private void init() {
        truyenArrayList = new ArrayList<>();
        adapter = new TruyenAdapter(this,0,truyenArrayList);
    }

    private void anhXa() {
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiem = findViewById(R.id.edtTimKiem);
    }

    private void setUp() {
        gdvDSTruyen.setAdapter(adapter);
    }

    private void setClick() {
        gdvDSTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Truyen truyen = truyenArrayList.get(i);
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyen);
                Intent intent = new Intent(MainActivity.this, ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }


    @Override
    public void loading(String data) {
        try{
            truyenArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for(int i=arr.length()-1;i>=0;i--){
                JSONObject o = arr.getJSONObject(i);
                truyenArrayList.add(new Truyen(o));
            }
            adapter = new TruyenAdapter(this,0,truyenArrayList);
            gdvDSTruyen.setAdapter(adapter);
        }catch (JSONException e){

        }
    }


    public void reload(View view) {
        Toast.makeText(this,"Loading Data...",Toast.LENGTH_SHORT).show();
        new ApiLayTruyen(this).execute();
    }

    public void search(View view) {
        Bundle b = new Bundle();
        String s = edtTimKiem.getText().toString();
        b.putSerializable("tentim",s);
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        intent.putExtra("datatim",b);
        startActivity(intent);
    }
}

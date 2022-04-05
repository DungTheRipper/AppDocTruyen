package com.example.metruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.metruyen.object.ChapTruyen;
import com.example.metruyen.object.Truyen;

public class ReadChapActivity extends AppCompatActivity {
    TextView txvTenChuong;
    ImageView imgCloses;
    TextView txvNoiDung;
    ChapTruyen chapTruyen;
    Truyen truyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_chap);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        //chapTruyen = new ChapTruyen("Chuơng 1:","")
        Bundle b1 = getIntent().getBundleExtra("data");
        truyen= (Truyen) b1.getSerializable("truyen");
        Bundle b2 = getIntent().getBundleExtra("datachap");
        chapTruyen = (ChapTruyen) b2.getSerializable("chap");
    };
    private void anhXa(){
        txvTenChuong = findViewById(R.id.txvTenChuong);
        imgCloses = findViewById(R.id.imgCloses);
        txvNoiDung =  findViewById(R.id.txvNoiDung);
        txvNoiDung.setMovementMethod(new ScrollingMovementMethod());
    };
    private void setUp(){
        txvTenChuong.setText(chapTruyen.getTenChap());
        txvNoiDung.setText(chapTruyen.getNoiDung());
    };
    private void setClick(){
        imgCloses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putSerializable("truyen",truyen);
                Intent intent = new Intent(ReadChapActivity.this, ChapActivity.class);
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    };
}
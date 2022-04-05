package com.example.metruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.metruyen.R;
import com.example.metruyen.object.Truyen;

import java.util.ArrayList;
import java.util.List;

public class TruyenAdapter extends ArrayAdapter<Truyen> {
    private Context ct;
    private ArrayList<Truyen> arr;
    public TruyenAdapter(@NonNull Context context, int resource, @NonNull List<Truyen> objects) {
        super(context, resource, objects);
        this.ct=context;
        this.arr= new ArrayList<>(objects);
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen, null);
        }
        if(arr.size()>0){
            Truyen truyen = this.arr.get(position);
            TextView tenTenTruyen = convertView.findViewById(R.id.txvTenTruyen);
            ImageView imgAnhTruyen = convertView.findViewById(R.id.imgAnhTruyen);
            tenTenTruyen.setText(truyen.getTenTruyen());
            Glide.with(this.ct).load(truyen.getLinkBia()).into(imgAnhTruyen);
        }
        return convertView;
    }
}

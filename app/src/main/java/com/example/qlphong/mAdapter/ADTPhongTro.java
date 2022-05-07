package com.example.qlphong.mAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.qlphong.R;
import com.example.qlphong.mDB.DBPhongTro;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class ADTPhongTro extends ArrayAdapter<DBPhongTro> {
    private Activity context;
    private int layout;
    private List<DBPhongTro> list;
    DatabaseReference mData;

    public ADTPhongTro(@NonNull Context context, int resource, List<DBPhongTro> data) {
        super(context, resource, data);
        this.context = (Activity) context;
        this.layout = resource;
        this.list = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater flater = context.getLayoutInflater();
        View row = flater.inflate(layout, parent,false);
        TextView txt1=(TextView) row.findViewById(R.id.tvTenPhong);

        DBPhongTro phong = list.get(position);
        txt1.setText(phong.getName()==null?"":phong.getName().toString());
        return row;
    }


}



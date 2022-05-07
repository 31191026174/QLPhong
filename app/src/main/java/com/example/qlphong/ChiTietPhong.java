package com.example.qlphong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlphong.mDB.DBChuTro;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ChiTietPhong extends AppCompatActivity {

    EditText edtNguoitro,edtSodien,edtSonuoc;
    TextView tvThanhTien;
    Button btnTinh, btnXong, btnTB, btn_sua;
    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phong);

        edtNguoitro = (EditText) findViewById(R.id.edtNguoiThue);
        edtSodien = (EditText) findViewById(R.id.edtSoDien);
        edtSonuoc = (EditText) findViewById(R.id.edtSoNuoc);
        tvThanhTien = (TextView) findViewById(R.id.tvTongTien);
        btnTinh = (Button) findViewById(R.id.btnTinhTien);
        btnXong = (Button) findViewById(R.id.btnXong);
        btnTB = (Button) findViewById(R.id.btnThongbao);
        btn_sua =(Button)findViewById(R.id.btn_sua);

        Intent intent1 = getIntent();
        Bundle bundle = intent1.getBundleExtra("chitietphong");
        final String nguoithue = bundle.getString("nguoitro");
        final String giaphong = bundle.getString("giaphong");
        final String giadien = bundle.getString("giadien");
        final String gianuoc = bundle.getString("gianuoc");

        edtNguoitro.setText(nguoithue);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tiendien = Integer.parseInt(giadien)*Integer.parseInt(edtSodien.getText().toString());
                int tiennuoc = Integer.parseInt(gianuoc)*Integer.parseInt(edtSonuoc.getText().toString());

                tvThanhTien.setText(Integer.parseInt(giaphong)+tiendien+tiennuoc+" vnd");
            }
        });

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData = FirebaseDatabase.getInstance().getReference();
                Query query = mData.child("NguoiTro").orderByChild("username").equalTo(nguoithue);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        DBChuTro account = dataSnapshot.getValue(DBChuTro.class);
                        String no = account.getKeyid();
                        mData.child("NguoiTro").child(no).child("thongbao").setValue("Tiền tháng này là : "+ tvThanhTien.getText().toString());
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                finish();
            }
        });
        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tb= new Intent(ChiTietPhong.this,Thongbao.class);
                startActivity(tb);
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sua = new Intent(ChiTietPhong.this,suaPhong.class);
                startActivity(sua);
            }
        });





    }
}

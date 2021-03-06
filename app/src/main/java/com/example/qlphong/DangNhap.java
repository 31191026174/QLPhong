package com.example.qlphong;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.qlphong.mDB.DBChuTro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class DangNhap extends AppCompatActivity {

    DatabaseReference mData;
    FragmentManager fragmentManager;
    EditText editUsername, editPass;
    TextView txtDangKy;
    Button btnDangNhap;
    private static final String DATA = "data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        init();
        setClickDangNhap();
//        setLoadData();
    }

    private void init() {
        editUsername = (EditText)findViewById(R.id.editUsername);
        editPass = (EditText)findViewById(R.id.editPass);
        btnDangNhap = (Button)findViewById(R.id.btnDangNhap);

        fragmentManager = getSupportFragmentManager();
    }


    private void setClickDangNhap(){
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseApp.initializeApp();
                mData = FirebaseDatabase.getInstance().getReference();

                final String name = editUsername.getText().toString();
                final String pass = editPass.getText().toString();

                Query query = mData.child("ChuTro").orderByChild("username").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()==false) {
                            Toast.makeText(DangNhap.this, "Sai t??i kho???n ho???c m???t kh???u !", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        DBChuTro account = dataSnapshot.getValue(DBChuTro.class);
                        if(account.getUsername().equals(name) && account.getPassword().equals(pass)==false) {
                            Toast.makeText(DangNhap.this, "Sai t??i kho???n ho???c m???t kh???u !", Toast.LENGTH_SHORT).show();
                        } else if (account.getUsername().equals(name) && account.getPassword().equals(pass)){
                            String ten = account.getName();
                            String address = account.getAddess();
                            String phone = account.getPhone();
                            String tenDN = account.getUsername();
                            String matKhau = account.getPassword();
                            String homename = account.getTenphongtro();
                            String key = account.getKeyid();
                            Log.d("username", tenDN + " ten dang nhap");
                            mData.child("user").setValue(key);
                            //Th??ng b??o
                            Toast.makeText(DangNhap.this, "????ng nh???p th??nh c??ng !", Toast.LENGTH_SHORT).show();
             //               Toast.makeText(DangNhap.this, "Xin ch??o "+ ten + tenDN + matKhau + nhapLaiMK+ key, Toast.LENGTH_LONG).show();
                            SharedPreferences sharedPreferences = getSharedPreferences(DATA, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("name",ten !=null ? ten : "");
                            editor.putString("address",address !=null ? address : "");
                            editor.putString("phone",phone !=null ? phone : "");
                            editor.putString("username",tenDN !=null ? tenDN : "");
                            editor.putString("password",matKhau !=null ? matKhau : "");
                            editor.putString("home-name",homename !=null ? homename : "");
                            editor.putString("key",key !=null ? key : "");
                            editor.commit();

                            //get value for MainActivity
                            Intent intent = new Intent(DangNhap.this, MainActivity.class);
                            startActivity(intent);
                            Bundle bundle = new Bundle();
                            bundle.putString("ten", ten);
                            bundle.putString("address", address);
                            bundle.putString("phone", phone);
                            bundle.putString("tenDN", tenDN);
                            bundle.putString("matKhau", matKhau);
                            bundle.putString("homename", homename);
                            bundle.putString("key", key);
                            intent.putExtra("getTen", bundle);
                            startActivity(intent);

                            //get values for TaiKhoan
//                            Intent intent1 = new Intent(DangNhap.this, TaiKhoan.class);
//                            Bundle bundle1 = new Bundle();
//                            bundle1.putString("ten", ten);
//                            bundle1.putString("tenDN", tenDN);
//                            bundle1.putString("matKhau", matKhau);
//                            bundle1.putString("nhapLaiMK", nhapLaiMK);
//                            bundle1.putString("key", key);
//                            intent1.putExtra("getValues", bundle1);
//                            startActivity(intent1);

                        finish();
                        }
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

                //Set ??i???u ki???n
                if (name.length() == 0 && pass.length() == 0){
                    Toast.makeText(DangNhap.this, "Vui l??ng nh???p t??i kho???n v?? m???t kh???u !", Toast.LENGTH_SHORT).show();
                } else if (name.length()==0){
                    Toast.makeText(DangNhap.this, "Ch??a nh???p t??i kho???n !", Toast.LENGTH_SHORT).show();
                } else if (pass.length()==0){
                    Toast.makeText(DangNhap.this, "Ch??a nh???p m???t kh???u !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

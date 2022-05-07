package com.example.qlphong;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qlphong.mDB.DBChuTro;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NguoidungFragment extends Fragment {

    Button btnout, btnSua;
    TextView tvTen, tvSDT, tvaddress, tvUser, tvPass;
    CircleImageView img;
    ImageView imgAvatar;

    ArrayList<DBChuTro> arrChuTro = new ArrayList<>();
    DatabaseReference mData;
    String title;

    public NguoidungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_nguoidung, container, false);
        mData = FirebaseDatabase.getInstance().getReference();
        btnout = (Button) v.findViewById(R.id.btnDangxuat);

        tvTen = (TextView) v.findViewById(R.id.tvTen);
        tvaddress = (TextView) v.findViewById(R.id.tvAdress);
        tvSDT = (TextView) v.findViewById(R.id.tvSDT);
        tvUser = (TextView) v.findViewById(R.id.tvUser);
        tvPass = (TextView) v.findViewById(R.id.tvpass);
        imgAvatar = (ImageView) v.findViewById(R.id.imgAvatarProfile);
        img = (CircleImageView) v.findViewById(R.id.img);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        String address = sharedPreferences.getString("address","");
        String phone = sharedPreferences.getString("phone","");
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        String home_name = sharedPreferences.getString("home-name","");
        String key = sharedPreferences.getString("key","");

        tvTen.setText(name);
        tvaddress.setText(address);
        tvSDT.setText(phone);
        tvPass.setText(password);
        tvUser.setText(username);


        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DangNhap.class));
                getActivity().finish();
            }
        });


        title = this.getActivity().getTitle().toString(); //Lấy tiêu đề nếu muốn


//        mData.child("ChuTro").child(tvTen.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                try {
//                    DBChuTro dbChuTro=dataSnapshot.getValue(DBChuTro.class);
//                    dbChuTro.setKeyid(dataSnapshot.getKey());
//                    tvTen.setText(dbChuTro.getName());
//                    tvSDT.setText(dbChuTro.getPhone());
//                    tvaddress.setText(dbChuTro.getAddess());
//                    tvUser.setText(dbChuTro.getUsername());
//                    tvPass.setText(dbChuTro.getPassword());
////                    if(dbChuTro.getColor()!=null) {
////                        byte[] decodedString = Base64.decode(contact.getPicture(), Base64.DEFAULT);
////                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
////                        imgPicture.setImageBitmap(decodedByte);
////                    }
//                }
//                catch (Exception ex)
//                {
//                    Log.e("LOI_JSON",ex.toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        Query query = mData.child("ChuTro").orderByChild("username").equalTo(this.getActivity().getTitle().toString());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DBChuTro account = dataSnapshot.getValue(DBChuTro.class);
                tvTen.setText("dsdsdsdsđsdssd");
                tvSDT.setText(account.getPhone());
                tvaddress.setText(account.getAddess());
                tvUser.setText(account.getUsername());
                tvPass.setText(account.getPassword());
                if(account.getAvatar()!=null) {
                        byte[] decodedString = Base64.decode(account.getAvatar(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                        img.setImageBitmap(decodedByte);
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
//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mData.child("ChuTro").addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        DBChuTro ChuTro = dataSnapshot.getValue(DBChuTro.class);
//                        ChuTro.setKeyid(dataSnapshot.getKey());
////                      keysList.add(dataSnapshot.getKey());
//                        arrChuTro.add(ChuTro);
//
//                        for (int i= 0; i<arrChuTro.size(); i++){
//                            if (arrChuTro.get(i).getUsername()==title){
//                                tvTen.setText(arrChuTro.get(i).getName());}
//                        }
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });
        mData.child("ChuTro").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DBChuTro ChuTro = dataSnapshot.getValue(DBChuTro.class);
                ChuTro.setKeyid(dataSnapshot.getKey());
//                keysList.add(dataSnapshot.getKey());
                arrChuTro.add(ChuTro);

                for (int i= 0; i<arrChuTro.size(); i++){
                    if (arrChuTro.get(i).getName()==title){
                    tvTen.setText(arrChuTro.get(i).getName().toString());}
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
        return v;

    }

}

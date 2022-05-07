package com.example.qlphong;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.qlphong.mDB.PhieuDangKy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaocaoFragment extends Fragment {
    EditText edt_ho_ten, edt_ngay_sinh, edt_que_quan, edt_so_smnd, edt_noi_dang_ky_tam_tru, edt_so_cmnd_chu_tro;
    Button bt_dang_ky;


    public BaocaoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.dangky_tamtru, container, false);
        edt_ho_ten = v.findViewById(R.id.edt_ho_ten);
        edt_ngay_sinh = v.findViewById(R.id.edt_ngay_sinh);
        edt_que_quan = v.findViewById(R.id.edt_que_quan);
        edt_so_smnd = v.findViewById(R.id.edt_so_cmnd);
        edt_noi_dang_ky_tam_tru = v.findViewById(R.id.edt_noi_dang_ky_tam_tru);
        edt_so_cmnd_chu_tro = v.findViewById(R.id.edt_so_cmnd_chu_tro);
        bt_dang_ky = v.findViewById(R.id.bt_dang_ky);

        DatabaseReference mData;
        mData = FirebaseDatabase.getInstance().getReference();

        bt_dang_ky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("hoten",  "ho ten: " + edt_ho_ten.getText().toString());
//                Toast.makeText(getContext(), "Ho ten: " + edt_ho_ten.getText().toString(), Toast.LENGTH_SHORT).show();
                mData.child("PhieuDangKy").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PhieuDangKy phieuDangKy = new PhieuDangKy();
                        phieuDangKy.setHoTen(edt_ho_ten.getText().toString());
                        phieuDangKy.setNgaySinh(edt_ngay_sinh.getText().toString());
                        phieuDangKy.setQueQuan(edt_que_quan.getText().toString());
                        phieuDangKy.setSoCmnd(edt_so_smnd.getText().toString());
                        phieuDangKy.setNoiDangKyTamTru(edt_noi_dang_ky_tam_tru.getText().toString());
                        phieuDangKy.setGetSoCmndChuTro(edt_so_cmnd_chu_tro.getText().toString());
                        mData.child("PhieuDangKy").child(mData.child("PhieuDangKy").push().getKey()).setValue(phieuDangKy);
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        // Inflate the layout for this fragment
        return v;
    }

}

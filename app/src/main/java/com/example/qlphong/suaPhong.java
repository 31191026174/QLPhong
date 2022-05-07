package com.example.qlphong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class suaPhong extends AppCompatActivity {
    Button btnLuu;
    ImageButton btnCapture, btnChoose;
    ImageView imgMinhHoa;
    EditText edtTenPhong, edtGiaPhong, edtGiaDien, edtGiaNuoc, edtUserNguoiTro;
    DatabaseReference mData;
    Bitmap selectedBitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_phong);
    }
}

//        mData = FirebaseDatabase.getInstance().getReference();
// edtTenPhong = (EditText) findViewById(R.id.edtTenPhong1);
//        edtGiaPhong = (EditText) findViewById(R.id.edtGiaPhong1);
  //      edtGiaDien = (EditText) findViewById(R.id.edtGiadien1);
   //     edtGiaNuoc = (EditText) findViewById(R.id.edtGiaNuoc1);
    //    edtUserNguoiTro = (EditText) findViewById(R.id.edtUserNguoiTro1);
     //   btnCapture = (ImageButton) findViewById(R.id.btnCapture1);
     //   btnChoose = (ImageButton) findViewById(R.id.btnChoosePicture1);
     //   imgMinhHoa = (ImageView) findViewById(R.id.imgMinhHoa1);
//
  //      btnLuu = (Button) findViewById(R.id.btn_luusau);
//
//
  //      Intent intent1 = getIntent();
    //    Bundle bundle = intent1.getBundleExtra("chitietphong");
      //  final String nguoithue = bundle.getString("nguoitro");
        //final String giaphong = bundle.getString("giaphong");
        //final String giadien = bundle.getString("giadien");
        //final String gianuoc = bundle.getString("gianuoc");
        //btnCapture.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View view) {
           //     capturePicture();
            //}
       // });
        //btnChoose.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View view) {
          //      choosePicture();
           // }
        //});

       // btnLuu.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
         //       mData.child("abc").setValue("123");
         //       final String name = edtTenPhong.getText().toString();
         //       final String giaphong = edtGiaPhong.getText().toString();
          //      final String giadien = edtGiaDien.getText().toString();
          //      final String gianuoc = edtGiaNuoc.getText().toString();
          //      final String nguoithue = edtUserNguoiTro.getText().toString();



          //      final Query query = mData.child("PhongTro").orderByChild("name").equalTo(name);
          //      query.addListenerForSingleValueEvent(new ValueEventListener() {
          //          @Override
          //          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          //              DBPhongTro PhongTro = new DBPhongTro();
          //              PhongTro.setKeyid(mData.child("PhongTro").push().getKey());
          //              PhongTro.setName(name);
          //              PhongTro.setGiaphong(giaphong);
                       //
                        //đưa bitmap về base64string:
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            selectedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                            byte[] byteArray = byteArrayOutputStream .toByteArray();
//                            String imgeEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        //PhongTro.setAvatar(imgeEncoded);
                      //
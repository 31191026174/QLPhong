package com.example.qlphong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlphong.mDB.DBPhongTro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;


public class ThemPhongMoi extends AppCompatActivity {
    Button btnThem;
    ImageButton btnCapture, btnChoose;
    ImageView imgMinhHoa;
    EditText edtTenPhong, edtGiaPhong, edtGiaDien, edtGiaNuoc, edtUserNguoiTro;
    DatabaseReference mData;
    Bitmap selectedBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong);

        mData = FirebaseDatabase.getInstance().getReference();
        edtTenPhong = (EditText) findViewById(R.id.edtTenPhong1);
        edtGiaPhong = (EditText) findViewById(R.id.edtGiaPhong1);
        edtGiaDien = (EditText) findViewById(R.id.edtGiaDien);
        edtGiaNuoc = (EditText) findViewById(R.id.edtGiaNuoc1);
        edtUserNguoiTro = (EditText) findViewById(R.id.edtUserNguoiTro1);
        btnCapture = (ImageButton) findViewById(R.id.btnCapture1);
        btnChoose = (ImageButton) findViewById(R.id.btnChoosePicture1);
        imgMinhHoa = (ImageView) findViewById(R.id.imgMinhHoa1);

        btnThem = (Button) findViewById(R.id.btnThem);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturePicture();
            }
        });
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.child("abc").setValue("123");
                final String name = edtTenPhong.getText().toString();
                final String giaphong = edtGiaPhong.getText().toString();
                final String giadien = edtGiaDien.getText().toString();
                final String gianuoc = edtGiaNuoc.getText().toString();
                final String nguoithue = edtUserNguoiTro.getText().toString();



                final Query query = mData.child("PhongTro").orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(ThemPhongMoi.this, "phong ???? t???n t???i !", Toast.LENGTH_LONG).show();
                        } else {
                            DBPhongTro PhongTro = new DBPhongTro();
                            PhongTro.setKeyid(mData.child("PhongTro").push().getKey());
                            PhongTro.setName(name);
                            PhongTro.setGiaphong(giaphong);
                            PhongTro.setGiadien(giadien);
                            PhongTro.setGianuoc(gianuoc);
                            PhongTro.setNguoithue(nguoithue);
                            Intent intent = getIntent();
                            String value1 = intent.getStringExtra("title");
                            PhongTro.setChuphong(value1);

                            //????a bitmap v??? base64string:
//                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                            selectedBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                            byte[] byteArray = byteArrayOutputStream .toByteArray();
//                            String imgeEncoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                            //PhongTro.setAvatar(imgeEncoded);
                            mData.child("PhongTro").child(PhongTro.getKeyid()).setValue(PhongTro);

                            Toast.makeText(ThemPhongMoi.this, "Th??m th??nh c??ng", Toast.LENGTH_LONG).show();

                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }



    private void choosePicture() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 200);
    }

    private void capturePicture() {
        Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cInt,100);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
//x??? l?? l???y ???nh tr???c ti???p l??c ch???p h??nh:
            selectedBitmap = (Bitmap) data.getExtras().get("data");
            imgMinhHoa.setImageBitmap(selectedBitmap);
        } else if (requestCode == 200 && resultCode == RESULT_OK) {
            try {
//x??? l?? l???y ???nh ch???n t??? ??i???n tho???i:
                Uri imageUri = data.getData();
                selectedBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgMinhHoa.setImageBitmap(selectedBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

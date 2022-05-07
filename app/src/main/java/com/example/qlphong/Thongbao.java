package com.example.qlphong;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class Thongbao extends AppCompatActivity {
    Button b_gui,b_exit;
    EditText ed1,edtSodien,edtSonuoc;
    TextView noidung;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tb_sms);
        ed1 = (EditText) findViewById(R.id.edt_sdt);
        noidung = (TextView) findViewById(R.id.tv_nd);
        b_gui = (Button) findViewById(R.id.button4);
        b_exit = (Button) findViewById(R.id.button5);
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getBundleExtra("chitietphong");
        final String giaphong = bundle.getString("giaphong");
        final String giadien = bundle.getString("giadien");
        final String gianuoc = bundle.getString("gianuoc");
        b_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6=new Intent(Thongbao.this,ChiTietPhong.class);
                startActivity(i6);
                onStop();
            }
        });
        /*b_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager tn;
                int tiendien = Integer.parseInt(giadien)*Integer.parseInt(edtSodien.getText().toString());
                int tiennuoc = Integer.parseInt(gianuoc)*Integer.parseInt(edtSonuoc.getText().toString());
                String number = ed1.getText().toString();
                noidung.setText("tien phong thang nay cua ban la: "+ Integer.parseInt(giaphong)+tiendien+tiennuoc+" vnd");
                String content = noidung.setText(Integer.parseInt(giaphong)+tiendien+tiennuoc+" vnd");
                try {
                    tn = SmsManager.getDefault();
                    tn.sendTextMessage(number,null,content,null,null);
                    Toast.makeText(Thongbao.this,"Đã gửi thành công",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(Thongbao.this,"Gửi không thành công",Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }
}


package com.example.boilerchurch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import static android.Manifest.permission.CALL_PHONE;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    Button getInfo, setInfo;

    private static final int REQUEST_CALL = 1;
    String number = "89502466003";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInfo = (Button) findViewById(R.id.get_info);
        setInfo = (Button) findViewById(R.id.set_info);

        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    call();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        setInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boilerData();
            }
        });
    }

    public void call() throws InterruptedException {

        if (ContextCompat.checkSelfPermission(MainActivity.this, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[] {CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            Thread.sleep(4000);
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    call();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void boilerData() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            sendMessage();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.SEND_SMS},
                    100);
            sendMessage();
        }
    }



    private void sendMessage() {
        String phone = "tel:" + number;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, "Тестовое сообщение", null, null);
    }

    public void getNotify() {

    }
}
package com.example.boilerchurch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CommandActivity extends AppCompatActivity {

    private Button back, offWarning, forcedPower, choosePower, chooseTemp;
    private ImageView on, off;
    private EditText powerText, tempText;


    String value = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(CommandActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        on = (ImageView) findViewById(R.id.on_boiler);
        off = (ImageView) findViewById(R.id.off_boiler);
        offWarning = (Button) findViewById(R.id.off_warning);
        forcedPower = (Button) findViewById(R.id.forced_power);
        choosePower = (Button) findViewById(R.id.choose_power);
        chooseTemp = (Button) findViewById(R.id.choose_temp);
        powerText = (EditText) findViewById(R.id.power);
        tempText = (EditText) findViewById(R.id.temp);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on.setVisibility(View.INVISIBLE);
                off.setVisibility(View.VISIBLE);

                value = "ON";
                boilerData();
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on.setVisibility(View.VISIBLE);
                off.setVisibility(View.INVISIBLE);

                value = "OFF";
                boilerData();
            }
        });
        offWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "SBR";
                boilerData();
            }
        });
        forcedPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "RUR";
                boilerData();
            }
        });
        choosePower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value =
            }
        });



    }
    public void boilerData() {
        if (ContextCompat.checkSelfPermission(CommandActivity.this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            sendMessage(value);
        } else {
            ActivityCompat.requestPermissions(CommandActivity.this,
                    new String[] {Manifest.permission.SEND_SMS},
                    100);
            sendMessage(value);
        }
    }
    private void sendMessage(String message) {
        String phone = "tel:" + new MainActivity().number;
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, message, null, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendMessage(value);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
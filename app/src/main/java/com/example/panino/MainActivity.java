package com.example.panino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Button add;

    private ImageButton alert;

    SharedPreferences sharedpreferences;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=findViewById(R.id.addid);

        alert=findViewById(R.id.alertid);

        sharedpreferences = getSharedPreferences("num", Context.MODE_PRIVATE);

        final String a=sharedpreferences.getString("n1", "1");
        final String b=sharedpreferences.getString("n2", "2");
        final String c=sharedpreferences.getString("n3", "3");

        Log.d("checka", a+b+c);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }


        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(a, null, "There's an Emergency please reach out to me asap", null, null);

                SmsManager smsManager1 = SmsManager.getDefault();
                smsManager1.sendTextMessage(b, null, "There's an Emergency please reach out to me asap", null, null);

                SmsManager smsManager2 = SmsManager.getDefault();
                smsManager2.sendTextMessage(c, null, "There's an Emergency please reach out to me asap", null, null);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), ContactsPage.class);
                startActivity(intent);
            }
        });
    }
}
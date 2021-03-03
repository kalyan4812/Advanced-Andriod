package com.example.advancedandriod_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class Gcm extends AppCompatActivity {
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcm);
        tv1=findViewById(R.id.reg);
        tv2=findViewById(R.id.msg);
        //IntentFilter gcmfilter=new IntentFilter();
     //   gcmfilter.addAction("GCM_RECEIVED_ACTION");
       // registerClient();
    }

    // i need to do this...
}

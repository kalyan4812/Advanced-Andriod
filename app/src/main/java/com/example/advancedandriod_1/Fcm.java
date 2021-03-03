package com.example.advancedandriod_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class Fcm extends AppCompatActivity {
    GifImageView gif;

    //variables
    Animation topAnim, bottomAnim;
    Handler h = new Handler();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);
        gif = findViewById(R.id.gif);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);


        Runnable run=new Runnable() {
            @Override
            public void run() {
                bottomAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_animation);
                // gif.setAnimation(topAnim);
                // image.setAnimation(topAnim);
                gif.setAnimation(bottomAnim);
                h.postDelayed(this,5000);
            }
        };
       h.postDelayed(run,0);


    }
}
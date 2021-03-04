package com.example.advancedandriod_1.GRAPHICS;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Graphics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}

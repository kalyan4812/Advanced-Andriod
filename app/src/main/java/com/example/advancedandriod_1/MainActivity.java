package com.example.advancedandriod_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.advancedandriod_1.ALARAM_MANAGER.Alaram;
import com.example.advancedandriod_1.CameraX.Camerax;
import com.example.advancedandriod_1.EXOPLAYER.Exoplayer;
import com.example.advancedandriod_1.FIREBASE.FirebaseAuthentication;
import com.example.advancedandriod_1.FIREBASE.FirebaseDb;
import com.example.advancedandriod_1.GIF_ANIMATION.Image_Animation;
import com.example.advancedandriod_1.GRAPHICS.Graphics;
import com.example.advancedandriod_1.GoogleMaps.Googlemaps;
import com.example.advancedandriod_1.GooglePlaces.Googleplaces;
import com.example.advancedandriod_1.QUIZ_APP.Quiz;
import com.example.advancedandriod_1.RECYCLER_VIEW.Recycler;
import com.example.advancedandriod_1.RETROFIT.RetrofitConcept;
import com.example.advancedandriod_1.TOAST_LIBRARY.Toasty;
import com.example.advancedandriod_1.WEATHER_APP.WeatherData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void googlemaps(View view) {
        Intent i=new Intent(getApplicationContext(), Googlemaps.class);
        startActivity(i);
    }

    public void googleplaces(View view) {
        Intent i=new Intent(getApplicationContext(), Googleplaces.class);
        startActivity(i);
    }



    public void recylerview(View view) {
        Intent i=new Intent(getApplicationContext(), Recycler.class);
        startActivity(i);
    }

    public void firbaseauth(View view) {
        Intent i=new Intent(getApplicationContext(), FirebaseAuthentication.class);
        startActivity(i);
    }

    public void graphic(View view) {
        Intent i=new Intent(getApplicationContext(), Graphics.class);
        startActivity(i);
    }

    public void firebasedatabase(View view) {
        Intent i=new Intent(getApplicationContext(), FirebaseDb.class);
        startActivity(i);
    }

    public void fcm(View view) {
        Intent i=new Intent(getApplicationContext(), Image_Animation.class);
        startActivity(i);
    }

    public void exoplayer(View view) {
        Intent i=new Intent(getApplicationContext(), Exoplayer.class);
        startActivity(i);
    }

    public void weather(View view) {
        Intent i=new Intent(getApplicationContext(), WeatherData.class);
        startActivity(i);
    }

    public void customtoast(View view) {
        Intent i=new Intent(getApplicationContext(), Toasty.class);
        startActivity(i);
    }

    public void retrofit(View view) {
        Intent i=new Intent(getApplicationContext(), RetrofitConcept.class);
        startActivity(i);
    }

    public void alarams(View view) {
        Intent i=new Intent(getApplicationContext(), Alaram.class);
        startActivity(i);
    }

    public void quiz(View view) {
        Intent i=new Intent(getApplicationContext(), Quiz.class);
        startActivity(i);
    }

    public void camerax(View view) {
        Intent i=new Intent(getApplicationContext(), Camerax.class);
        startActivity(i);
    }
}

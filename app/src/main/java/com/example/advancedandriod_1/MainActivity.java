package com.example.advancedandriod_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.advancedandriod_1.GoogleMaps.Googlemaps;
import com.example.advancedandriod_1.GooglePlaces.Googleplaces;

import retrofit2.Retrofit;

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

    public void gcm(View view) {
        Intent i=new Intent(getApplicationContext(), Gcm.class);
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
        Intent i=new Intent(getApplicationContext(), Fcm.class);
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
}

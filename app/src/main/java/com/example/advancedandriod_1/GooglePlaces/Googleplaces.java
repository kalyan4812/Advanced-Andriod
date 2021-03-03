package com.example.advancedandriod_1.GooglePlaces;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.advancedandriod_1.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Googleplaces extends AppCompatActivity {
    LocationManager lm;
    double lati,longi;
    TextView tv1, tv2;
    EditText ed;
    ListView lv;
    InputStream is;
    String[] places;
    String[] vicinities;
    double[] platis;
   static ArrayList<GoogleplacesPojo> al=new ArrayList<>();
  static   Googleplaces googleplaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googleplaces);
        googleplaces=this;
        lv=findViewById(R.id.lv);
        StrictMode.ThreadPolicy st=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(st);
        tv1 = findViewById(R.id.lat);
        tv2 = findViewById(R.id.lg);
        ed=findViewById(R.id.place);
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED) {
            lm = (LocationManager) getApplicationContext(). getSystemService(Context.LOCATION_SERVICE);
            lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            // to get current one.,for every onesecond it will update.

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lati = location.getLatitude();
                    longi = location.getLongitude();
                    tv1.setText("LATITUDE:" + lati);
                    tv2.setText("LONGITUDE:" + longi);


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    // when gps is on it will be called.
                }

                @Override
                public void onProviderDisabled(String provider) {
                    // when gps is disabled it will be called
                }
            });

        }

        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 120);
        }

    }
    public void go(View view){
      Mytask task=new Mytask();
      task.execute();


    }
    public class Mytask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + lati + "," + longi + "&radius=1500&type=" + ed.getText().toString() + "&key=AIzaSyCiFY0UbrlNqnEzlXoVyNqPOInZq86zhRk");
                is = url.openStream();// by this we get respponse from url as input stream means data is in byte array format.
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            try {
                String msg = "";
                int i = is.read();
                while (i != -1) {
                    msg = msg + (char) i;
                    i = is.read();
                }
                System.out.println(msg);
                JSONObject jsonObject=new JSONObject(msg); // will give main json object.
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for(int k=0;k<jsonArray.length();k++){
                    JSONObject individualobjrct=jsonArray.getJSONObject(k);
                    String name=individualobjrct.getString("name");
                    String vicinity=individualobjrct.getString("vicinity");
                    JSONObject geometry=individualobjrct.getJSONObject("geometry");
                    JSONObject location=individualobjrct.getJSONObject("location");
                    double plati=location.getDouble("lat");
                    double plongi=location.getDouble("lng");
                    GoogleplacesPojo gp=new GoogleplacesPojo();
                    gp.setName(name);
                    gp.setVicinity(vicinity);
                    gp.setLati(plati);
                    gp.setLongi(plongi);
                    al.add(gp);
                }
                lv.setAdapter(new MyAdapter());

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 120:
                int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED) {
                    lm = (LocationManager) getApplicationContext(). getSystemService(Context.LOCATION_SERVICE);
                    lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            lati = location.getLatitude();
                            longi = location.getLongitude();
                            tv1.setText("LATITUDE:" + lati);
                            tv2.setText("LONGITUDE:" + longi);

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            // when gps is on it will be called.
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            // when gps is disabled it will be called
                        }
                    });

                }


        }
    }
}

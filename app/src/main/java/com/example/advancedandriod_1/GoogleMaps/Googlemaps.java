package com.example.advancedandriod_1.GoogleMaps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.icu.util.IndianCalendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.advancedandriod_1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class Googlemaps extends FragmentActivity implements OnMapReadyCallback {
    LocationManager lm;
    TextView tv1, tv2;
SupportMapFragment smp;
double lati,longi;
    MarkerOptions mo;
    int count=0;
Marker marker;
GoogleMap gmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemaps);

     smp=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapView);
     smp.getMapAsync(this);

        tv1 = findViewById(R.id.lat);
        tv2 = findViewById(R.id.lg);
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
                     mo=new MarkerOptions();
                    mo.position(new LatLng(lati,longi));
                    mo.title("YOUR LOCATION");


                            marker.setPosition(new LatLng(lati, longi));


                    gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati,longi),16));
                    gmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            try {
                                Geocoder gc = new Geocoder(getApplicationContext(),Locale.getDefault());
                                List<Address> adr = gc.getFromLocation(lati, longi, 1);
                                String adress = adr.get(0).getAddressLine(0);
                                String statename = adr.get(0).getAdminArea();
                                String countryname = adr.get(0).getCountryName();
                                String pincode = adr.get(0).getPostalCode();
                                String Code=adr.get(0).getCountryCode();
                                String k=adr.get(0).getLocality();
                                Toast.makeText(getApplicationContext(),k+"\n"+adress+"\n"+pincode+"\n"+Code+"\n"+statename+"\n"+countryname,Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                            return false;
                        }
                    });

                    // to add your own icon
                    //   mo.icon(BitmapDescriptorFactory.fromBitmap(R.drawable.your-img));
                    // to add satellite view use and also graphic feature in manifesto.
                //    gmap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); write it in onMapReady() method.
                  // lm.removeUpdates(this);

                  //  marker.remove();

                  //  gmap.addMarker(mo).setPosition(new LatLng(lati,longi));






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
                    // to get current one.,for every onesecond it will update.

                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                             lati = location.getLatitude();
                             longi = location.getLongitude();
                            tv1.setText("LATITUDE:" + lati);
                            tv2.setText("LONGITUDE:" + longi);

                            //onMapReady();
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap=googleMap;
        gmap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mo=new MarkerOptions();
        mo.position(new LatLng(20.5937,78.9629));
       // mo.title("YOUR LOCATION");
        marker=gmap.addMarker(mo);




        // to add your own icon
     //   mo.icon(BitmapDescriptorFactory.fromBitmap(R.drawable.your-img));
    }
}

package com.example.advancedandriod_1.RECYCLER_VIEW;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;

import com.example.advancedandriod_1.R;

import java.io.File;

public class Recycler extends AppCompatActivity {
RecyclerView rv;
 static   File f;
static String[] files;
static Recycler recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recycler=this;
        rv=findViewById(R.id.rev);

        int permissioncheck1 = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissioncheck1== PackageManager.PERMISSION_GRANTED && permissioncheck==PackageManager.PERMISSION_GRANTED){
             f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                     + "/Screenshot");
            files=f.list();
            RecyclerView.LayoutManager lm=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(lm);
            rv.setAdapter(new MyRecycleAdapter());
           // to get gallery view use LinearLAYOUTmANGER.HORIZONTAL.
           // for grid layout use
          //  StaggeredGridLayoutManager sg=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 120);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 120:
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
                int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if(permissioncheck1== PackageManager.PERMISSION_GRANTED && permissioncheck==PackageManager.PERMISSION_GRANTED){
                    f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/Screenshot");
                     files=f.list();
                    RecyclerView.LayoutManager lm=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
                    rv.setLayoutManager(lm);
                    rv.setAdapter(new MyRecycleAdapter());


                }
                break;

        }
    }
}

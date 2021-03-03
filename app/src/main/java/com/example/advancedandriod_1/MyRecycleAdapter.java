package com.example.advancedandriod_1;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.InputStream;

//import static com.example.advancedandriod_1.MyHolder.mh;
import static com.example.advancedandriod_1.Recycler.files;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyHolder> {
    String path= Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Screenshot";
  // String [] files=f.list();



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adapterlayout,parent,false);
        MyHolder myHolder=new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.advancedandriod_1.MyHolder holder, int position) {
        String newpath=path+files[position];
        final  File nf=new File(newpath);



      //  img.setImageBitmap(b);
        holder.img.setImageURI(Uri.fromFile(nf));
        holder.t1.setText(files[position]);
        holder.t2.setText(nf.length()+"bytes");
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nf.delete();
            }
        });
    }



    @Override
    public int getItemCount() {
     return files.length;
    }

}

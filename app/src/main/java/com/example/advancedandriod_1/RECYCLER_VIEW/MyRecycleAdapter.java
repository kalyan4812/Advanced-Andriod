package com.example.advancedandriod_1.RECYCLER_VIEW;

import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advancedandriod_1.R;

import java.io.File;

//import static com.example.advancedandriod_1.RECYCLER_VIEW.MyHolder.mh;
import static com.example.advancedandriod_1.RECYCLER_VIEW.Recycler.files;

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
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
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

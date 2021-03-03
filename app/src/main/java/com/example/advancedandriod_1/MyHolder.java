package com.example.advancedandriod_1;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView t1,t2;
    Button del;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        img=(ImageView) itemView.findViewById(R.id.img);
        t1=itemView.findViewById(R.id.tv1);
        t2=itemView.findViewById(R.id.tv2);
        del=itemView.findViewById(R.id.del);

    }
}

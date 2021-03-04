package com.example.advancedandriod_1.GRAPHICS;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.advancedandriod_1.R;

import pl.droidsonroids.gif.GifImageView;

public class MyView extends SurfaceView {
    SurfaceHolder sh;
    int movebaleX=50;
    android.os.Handler h=new Handler();
    public MyView(Context context) {
        super(context);
        sh=getHolder();
        sh.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas c=sh.lockCanvas();
                myDraw(c);
                sh.unlockCanvasAndPost(c);
                Runnable run=new Runnable() {
                    @Override
                    public void run() {
                        Canvas c=sh.lockCanvas();
                        myDraw(c);
                        sh.unlockCanvasAndPost(c);
                        h.postDelayed(this,500);
                    }
                };
                h.postDelayed(run,100);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    private void myDraw(Canvas c) {
        c.drawColor(Color.BLACK);
        Paint p=new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.FILL);
        c.drawCircle(450,350,50,p);
        //
        p.setColor(Color.CYAN);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(150,200,350,400,p);
        //
        c.drawText("HELLO EVERY ONE....",150,500,p);
        //
        Bitmap b= BitmapFactory.decodeResource(getResources(), R.drawable.horse);
        c.drawBitmap(b,50,550,null);
        //mvable image using handler.
    //    GifImageView gif=findViewById(R.id.gif);
        //BitmapDrawable bd=(BitmapDrawable) gif.getDrawable();
       // Bitmap bitmap=bd.getBitmap();
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.runninghorse);
      //  Bitmap b1= BitmapFactory.decodeResource(getResources(),R.drawable.gifhorse);

        c.drawBitmap(bitmap,movebaleX=movebaleX+10,650,null);

        // rotate image use cocos2-D-Android Framework is used.
        // useful url for graphics. WWW.edu4JAVA.com.



    }

}

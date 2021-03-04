package com.example.advancedandriod_1.ALARAM_MANAGER;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.advancedandriod_1.R;

import static com.example.advancedandriod_1.ALARAM_MANAGER.Alaram.notificationManager;

public class AlaramBroadcast extends BroadcastReceiver {
  //  NotificationManager   notificationManager=(NotificationManager)gets
  @Override
  public void onReceive(Context context, Intent intent) {

    NotificationCompat.Builder n = new NotificationCompat.Builder(context);
    n.setSmallIcon(R.drawable.ic_access_alarm_black_24dp);
    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_access_alarm_black_24dp);
    n.setLargeIcon(bitmap);
    n.setContentTitle("ALARAM!!!!!");
    //content text is inner most one.
    n.setSubText("ALARAM");
    n.setContentText("HELLO ITS TIME TO WAKE UP.......");
    n.setAutoCancel(true);
    n.setDefaults(NotificationCompat.DEFAULT_ALL);

    Uri ringtonepath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    n.setSound(ringtonepath);
    MediaPlayer mp = MediaPlayer.create(context, R.raw.alaram);
    mp.setLooping(true);
    mp.start();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      String channelId = "ANY_CHANNEL_NAME";
      NotificationChannel channel = new NotificationChannel(channelId, "GOOGLE Promotions", NotificationManager.IMPORTANCE_DEFAULT);
      notificationManager.createNotificationChannel(channel);
      n.setChannelId(channelId);
    }
    // for every notification there willbe a id ,if there is anotification with given id in notification bar,
    // then no new notification will appear ,just it will update.means for every unique id unique notification.
    //to get  same notification every time when we press the button ,use count variable count=0; and inplace of id use ++count.
    notificationManager.notify(44, n.build());
    // Intent in=new Intent(context,context.getClass());
    //   PendingIntent pendingIntent= PendingIntent.getActivity(context, 0, in, 0);


    mp = MediaPlayer.create(context, R.raw.alaram);
    mp.setLooping(true);
    mp.start();
  }
}


package com.fryanramzkhar.simplenotofication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtTitle, edtContext;
    public static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "IDN Channel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTitle = findViewById(R.id.edtTitle);
        edtContext = findViewById(R.id.edtContent);
    }

    public void sendNotification(View view) {
        //Menyetting Notifitcation Manager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuider = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_notification))
                .setContentTitle(edtTitle.getText().toString())
                .setContentText(edtContext.getText().toString())
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuider.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null){
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuider.build();
        if (notification != null){
            mNotificationManager.notify(NOTIFICATION_ID, notification);

        }


    }
}

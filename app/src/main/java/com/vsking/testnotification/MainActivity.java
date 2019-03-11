package com.vsking.testnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
public static final String CHANNEL_ID="Test Notification";
public static final int NOTIFICATION_ID=001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showNotification(View view) {

        createNotificationChannel();

        Intent intent=new Intent(this,ShowNotificationClickedActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_small_noti_icon);
        builder.setContentTitle("Testing vsking");
        builder.setContentText("This is the Notification body.....");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());

    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Testing vsking";
            String desc="This is the Notification body.....";
            int priority= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,name,priority);

            channel.setDescription(desc);
            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

        }
    }
}

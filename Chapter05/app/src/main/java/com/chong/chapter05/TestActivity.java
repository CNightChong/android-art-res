package com.chong.chapter05;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class TestActivity extends Activity implements OnClickListener {

    private static final String TAG = "TestActivity";

    private Button mButton1;
    private Button mButton2;

    private static int sId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            sId++;
            Intent intent = new Intent(this, DemoActivity_2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("chapter05")
                    .setContentText("this is notification.")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setTicker("hello world")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(sId, notification);
        } else if (v == mButton2) {
            sId++;
            Notification notification = new Notification();
            notification.icon = R.drawable.ic_launcher;
            notification.tickerText = "hello world";
            notification.when = System.currentTimeMillis();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            Intent intent = new Intent(this, DemoActivity_1.class);
            intent.putExtra("sid", "" + sId);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            System.out.println(pendingIntent);
            RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
            remoteViews.setTextViewText(R.id.msg, "chapter05: " + sId);
            remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);
            PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(this, DemoActivity_2.class), PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);
            notification.contentView = remoteViews;
            notification.contentIntent = pendingIntent;
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(sId, notification);
        }
    }

}

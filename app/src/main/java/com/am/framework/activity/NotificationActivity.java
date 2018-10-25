package com.am.framework.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.am.framework.R;
import com.am.framework.databinding.ActivityNotificationBinding;

public class NotificationActivity extends AppCompatActivity {
    private ActivityNotificationBinding mBinding;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = NotificationManagerCompat.from(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        mBinding.btnSimpleNotfication.setOnClickListener(view ->
                showSimpleNotification(NotificationActivity.this,
                        getString(R.string.notification_channel_name))
        );
    }


    private void showSimpleNotification(Context context, String channelId) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(10000, mBuilder.build());
    }

    private void showSimpleNotificationWithExtentedText(Context context, String channelId) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notification Title!")
                .setContentText("Notification  Short Body !")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line Era, aonides, et nuptia. Ecce, historia!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    }

}

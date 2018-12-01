package com.am.framework.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.am.framework.R;
import com.am.framework.broadcasts.MyBroadcastReceiver;
import com.am.framework.databinding.ActivityNotificationBinding;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

public class NotificationActivity extends AppCompatActivity {
    private static final String ACTION_SNOOZE = "snooze_action";
    private ActivityNotificationBinding mBinding;
    private NotificationManagerCompat notificationManager;
    private final int ID_SIMPLE_NOTIFICATION = 10000;
    private final int ID_SIMPLE_NOTIFICATION_EXTEND = 10001;
    private static final int ID_NOTIFICATION_BTN = 10002;
    private static final int ID__NOTIFICATION_WITH_PROGRESS_BAR = 10003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = NotificationManagerCompat.from(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        mBinding.btnSimpleNotfication.setOnClickListener(view ->
                showSimpleNotification(NotificationActivity.this,
                        getString(R.string.notification_channel_name))
        );
        mBinding.btnSimpleNotificationWithExtendedText.setOnClickListener(view ->
                showSimpleNotificationWithExtendedText(NotificationActivity.this,
                        getString(R.string.notification_channel_name)));

        mBinding.btnNotifcationButton.setOnClickListener(view ->
                showNotificationWithBtn(NotificationActivity.this,
                        getString(R.string.notification_channel_name)));
        mBinding.btnNotificationWithProgressBar.setOnClickListener(view ->
                showNotificationWithProgressBar(NotificationActivity.this,
                        getString(R.string.notification_channel_name)));
        mBinding.btnMessagingStyleNotifcation.setOnClickListener(view ->
                showNotificationWithMessagingStyle(NotificationActivity.this,
                        getString(R.string.notification_channel_name)));
    }


    private void showSimpleNotification(Context context, String channelId) {

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notification Title!")
                .setContentText("Notification  Short Body !")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(ID_SIMPLE_NOTIFICATION, mBuilder.build());
    }

    private void showSimpleNotificationWithExtendedText(Context context, String channelId) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Notification Title!")
                .setContentText("Notification  Short Body !")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line Era, aonides, et nuptia. Ecce, historia!"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(ID_SIMPLE_NOTIFICATION_EXTEND, mBuilder.build());
    }


    private void showNotificationWithBtn(Context context, String channelId) {
        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, channelId);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(snoozePendingIntent)
                .addAction(R.drawable.ic_cheer_active, getString(R.string.snooze),
                        snoozePendingIntent);

        notificationManager.notify(ID_NOTIFICATION_BTN, mBuilder.build());

    }

    private void showNotificationWithProgressBar(Context context, String channelId) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_notification)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        // Issue the initial notification with zero progress
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 70;
        mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        notificationManager.notify(ID__NOTIFICATION_WITH_PROGRESS_BAR, mBuilder.build());

        // Do the job here that tracks the progress.
        // Usually, this should be in a
        // worker thread
        // To show progress, update PROGRESS_CURRENT and update the notification with:
        // mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
        // notificationManager.notify(notificationId, mBuilder.build());

        // When done, update the notification one more time to remove the progress bar
//        mBuilder.setContentText("Download complete")
//                .setProgress(0, 0, false);
        notificationManager.notify(ID__NOTIFICATION_WITH_PROGRESS_BAR, mBuilder.build());
    }


    private void showNotificationWithMessagingStyle(Context context, String channelId) {

//        Notification mBuilder = new NotificationCompat.Builder(context, channelId)
//                .setSmallIcon(R.drawable.ic_notification)
//                .setStyle(new NotificationCompat.MessagingStyle("Me")
//                        .setConversationTitle("Team lunch")
//                        .addMessage("Hi", 1540506741, null) // Pass in null for user.
//                        .addMessage("What's up?", 1540506741, "Coworker")
//                        .addMessage("Not much", 1540506741, null)
//                        .addMessage("How about lunch?", 1540506741, "Coworker"))
//                .build();
//
//
//        notificationManager.notify(ID__NOTIFICATION_WITH_PROGRESS_BAR, mBuilder);
    }
}


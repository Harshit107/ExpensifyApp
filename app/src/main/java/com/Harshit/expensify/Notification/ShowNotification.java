package com.Harshit.expensify.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.Harshit.expensify.Helper.MessageData;
import com.Harshit.expensify.MainActivity;
import com.Harshit.expensify.R;

public class ShowNotification {

    public static void showNotification(Context context, NotificationCompat.Builder builder) {

        Intent launchIntent = new Intent(context, MainActivity.class);
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Log.d("SMS_RECEIVE", "Trying to send Notification");
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission( context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Log.d("SMS_RECEIVE", "Error");
            return;
        }
        int notificationId = (int) System.currentTimeMillis();
        notificationManager.notify(notificationId, builder.build());
        new Handler().postDelayed(() -> notificationManager.cancel(notificationId), 10000);

    }

    public static void buildNotificationForMessageData(Context context, MessageData messageData) {
        NotificationCompat.Builder builder =  buildNotification(context, messageData.getBankName(),messageData.getAmount()+"");
        showNotification(context, builder);
    }

    public static void buildCustomNotification(Context context, String title, String message) {
        NotificationCompat.Builder builder =  buildNotification(context, title, message);
        showNotification(context, builder);

    }

    private static NotificationCompat.Builder buildNotification(Context context, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        return builder;
    }



}

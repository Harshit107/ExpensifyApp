package com.Harshit.expensify.Receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.Harshit.expensify.Helper.ExpenseHelper;
import com.Harshit.expensify.MainActivity;
import com.Harshit.expensify.R;

public class SmsReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private static final String KEYWORD_DEBIT = "debit";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SMS_RECEIVE", "Calling");
        if (intent.getAction().equals(SMS_RECEIVED_ACTION)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null && pdus.length > 0) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
                    String smsBody = smsMessage.getMessageBody();
                    if (smsBody.toLowerCase().contains(KEYWORD_DEBIT)) {
                        ExpenseHelper.handleSMS(context, smsBody);

                    }
                }
            }
        }
    }



}


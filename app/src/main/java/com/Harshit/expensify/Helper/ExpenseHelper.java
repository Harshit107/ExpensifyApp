package com.Harshit.expensify.Helper;

import android.content.Context;
import android.os.Bundle;

import com.Harshit.expensify.Notification.ShowNotification;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpenseHelper {

    public static void handleSMS(Context context, String smsBody) {
        MessageData messageData = processSMS(smsBody);
        ShowNotification.showNotification(context, messageData);

    }
    public static MessageData processSMS(String message) {
        String bank = "";
        String vpa = "";
        double amount = 0.0;
        String date = "";

        Pattern bankPattern = Pattern.compile("(\\S+)\\s+Bank:");
        Pattern vpaPattern = Pattern.compile("VPA\\s(\\S+@[^\\s\\)\\(\\d]+)");
        Pattern amountPattern = Pattern.compile("Rs (\\d+\\.\\d+)");
        Pattern datePattern = Pattern.compile("on\\s(\\d{2}-\\d{2}-\\d{2})");

        // Extract bank, VPA, amount, and date information using regular expressions
        Matcher bankMatcher = bankPattern.matcher(message);
        Matcher vpaMatcher = vpaPattern.matcher(message);
        Matcher dateMatcher = datePattern.matcher(message);

        try {
            Matcher amountMatcher = amountPattern.matcher(message);
            if (amountMatcher.find()) {
                amount = Double.parseDouble(amountMatcher.group(1));
            }
        } catch (NumberFormatException e) {
            // Handle exception if amount cannot be parsed
            amount = 0.0;
        }

        if (bankMatcher.find()) {
            bank = bankMatcher.group(1);
        }
        if (vpaMatcher.find()) {
            vpa = vpaMatcher.group(1);
        }
        if (dateMatcher.find()) {
            date = dateMatcher.group(1);
        }

        // Handle cases where a value is not present in the input string

        if (bank != null && bank.isEmpty()) {
            bank = "";
        }
        if (vpa != null && vpa.isEmpty()) {
            vpa = "";
        }
        if (date != null && date.isEmpty()) {
            date = "";
        }
        return new MessageData(bank,amount,date,vpa,"");

    }


}

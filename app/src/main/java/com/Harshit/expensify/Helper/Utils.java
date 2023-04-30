package com.Harshit.expensify.Helper;

import android.content.Context;
import android.content.Intent;

import com.Harshit.expensify.MainActivity;

public class Utils {

    public static void moveActivity(Context fromClass, Class<?> toClass) {
        Intent it = new Intent(fromClass, toClass);
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        fromClass.startActivity(it);
    }
}

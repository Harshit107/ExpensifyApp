package com.Harshit.expensify.Helper;

import android.content.Context;

import androidx.annotation.NonNull;

import com.Harshit.expensify.Notification.ShowNotification;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ExpenseDatabase {

    public static void addNewExpenseToDataBase(Context context, MessageData messageData) {
        if(messageData.getAmount() <= 0 )
            return;
        FirebaseUser user = FirebaseHelper.isLogin();
        if(user == null)
            return;

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("amount",messageData.getAmount());
        hm.put("description",messageData.getVPA_address());
        hm.put("createdAt",System.currentTimeMillis());
        hm.put("note",messageData.getBankName());

        DatabaseReference expenseRef = getExpenseRef(user.getUid()).push();
        expenseRef.setValue(hm).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                ShowNotification.buildCustomNotification(context, "Success", "");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ShowNotification.buildCustomNotification(context, "Failed", "");
            }
        });

    }

    public static DatabaseReference getExpenseRef(String uid) {

        return FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("expense");

    }

}

package com.Harshit.expensify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Harshit.expensify.Helper.FirebaseHelper;
import com.Harshit.expensify.Helper.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseHelper.isLogin() == null)
            Utils.moveActivity(MainActivity.this,SmsPage.class);

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 FirebaseAuth.getInstance().signOut();
                Utils.moveActivity(MainActivity.this, SmsPage.class);
            }
        });



    }
}
package com.Harshit.expensify.Helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseHelper {

    public static FirebaseUser isLogin() {
        return FirebaseAuth.getInstance().getCurrentUser();

    }


}

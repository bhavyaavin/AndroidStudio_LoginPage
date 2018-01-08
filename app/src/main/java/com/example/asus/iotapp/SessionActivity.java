package com.example.asus.iotapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Asus on 2017-11-09.
 */

public class SessionActivity {

    SharedPreferences SP;
    SharedPreferences.Editor editor;
    Context context;

    public SessionActivity(Context context){
        this.context = context;
        SP = context.getSharedPreferences("SecureLogin", Context.MODE_PRIVATE);
        editor = SP.edit();
    }

    public void sessionSignedIn(boolean SignedIn){
        editor.putBoolean("SignedInMode", SignedIn);
        editor.commit();

    }
    public void sessionstoreCredentials(boolean storecredentials){
        editor.putBoolean("SignedInMode", storecredentials);
        editor.commit();

    }


    public boolean signedIn(){
        return SP.getBoolean("SignedInMode", false);
    }
}

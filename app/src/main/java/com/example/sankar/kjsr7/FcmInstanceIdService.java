package com.example.sankar.kjsr7;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by SANKAR on 2/23/2018.
 */

public class FcmInstanceIdService extends FirebaseInstanceIdService {
@Override
public void onTokenRefresh()
    {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.sankar.kjsr7withappserver.fcm_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("com.example.sankar.kjsr7withappserver.fcm_token", recent_token );
        editor.commit();
    }
}

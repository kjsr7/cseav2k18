package com.example.sankar.kjsr7;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_REGNO = "regno";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHNO = "phno";

   // private static final String KEY_USERNAME = "";

    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

public boolean userLogin(String regno, String name, String email, String phno)
{
    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,   Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(KEY_REGNO,regno);
    editor.putString(KEY_NAME,name);
    editor.putString(KEY_EMAIL, email   );
    editor.putString(KEY_PHNO, phno   );

    //editor.putString();
    editor.apply();
    return true;
}
public boolean isLoggedIn()
{
    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,   Context.MODE_PRIVATE);
    if(sharedPreferences.getString(KEY_REGNO, null)!=null)
    {
        return true;
    }
    return false;
}
public boolean logout()
{
    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,   Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.clear();
    editor.apply();
    return true;
}
public String getregno()
{
    SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    return sharedPreferences.getString(KEY_REGNO,null);
}
    public String getname()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME,null);
    }
    public String getemail()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL,null);
    }
    public String getphno()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PHNO,null);
    }

}
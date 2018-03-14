package com.example.sankar.kjsr7;

import android.app.VoiceInteractor;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by SANKAR on 2/23/2018.
 */

public class MySingleton {
    private static MySingleton mInstance;
    private static Context mCtx;
    private RequestQueue requestQueue;
    private MySingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }
    private RequestQueue getRequestQueue()
    {
    if(requestQueue == null)
    {
        requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
    }
    return requestQueue;

    }
    public static synchronized MySingleton getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }
    public<T> void addToRequestQueue(Request<T> request)
    {
        getRequestQueue().add(request);
    }
}

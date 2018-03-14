package com.example.sankar.kjsr7;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SANKAR on 3/8/2018.
 */

public class MRegRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://kjsr.000webhostapp.com/mreg.php";
    private Map<String, String> params;

    public MRegRequest(String tn,String fmr,String smr, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("tn", tn);
        params.put("fmr", fmr);
        params.put("smr", smr);
       // params.put("tmr", tmr);
        //params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

package com.example.sankar.kjsr7;

/**
 * Created by SANKAR on 3/8/2018.
 */

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SANKAR on 2/24/2018.
 */

public class RegRequestc extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://kjsr.000webhostapp.com/regc.php";
    private Map<String, String> params;

    public RegRequestc(String username, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("regno", username);
        //params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
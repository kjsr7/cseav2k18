package com.example.sankar.kjsr7;

/**
 * Created by SANKAR on 2/22/2018.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://kjsr.000webhostapp.com/Register2.php";
    private Map<String, String> params;

    public RegisterRequest(String regno, String name, String email, String password, String phno, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("regno", regno);
        params.put("name",  name);
        params.put("emailid", email);

        params.put("password", password);
        params.put("phno", phno);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

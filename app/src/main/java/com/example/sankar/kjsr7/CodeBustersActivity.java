package com.example.sankar.kjsr7;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class CodeBustersActivity extends AppCompatActivity {
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_busters);   final String regno = SharedPrefManager.getInstance(this).getregno();
        b = findViewById(R.id.button3cb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //             final String username = etUsername.getText().toString();
                //           final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                //String name = jsonResponse.getString("name");
                                AlertDialog.Builder builder = new AlertDialog.Builder(CodeBustersActivity.this);
                                builder.setMessage("Successfully Registered")
                                        //.setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CodeBustersActivity.this);
                                builder.setMessage("Already Registered")
                                        //.setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegRequestcb regRequest = new RegRequestcb(regno,responseListener);
                //RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
                //queue.add(regRequest);
                RequestHandler.getInstance(CodeBustersActivity.this).addToRequestQueue(regRequest);

            }
        });
    }
}

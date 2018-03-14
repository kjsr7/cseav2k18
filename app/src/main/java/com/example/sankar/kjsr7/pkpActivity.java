package com.example.sankar.kjsr7;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class pkpActivity extends AppCompatActivity {
Button b;
int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkp); EditText pass = findViewById(R.id.p1);
       final EditText pass7 = findViewById(R.id.p3);

        final TextView err = (TextView) findViewById(R.id.errpkp);

        final String regno = SharedPrefManager.getInstance(this).getregno();
        b = findViewById(R.id.s3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ans = pass7.getText().toString();
                err.setVisibility(View.VISIBLE);
                if (ans.equals("pokehunt2k18")) {
                    m = 1;
                    err.setText("correct .Please Wait!");
                } else {
                    err.setText("incorrect Passcode");

                }


                //             final String username = etUsername.getText().toString();
                //           final String password = etPassword.getText().toString();

                // Response received from the server
                if(m==1) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    //String name = jsonResponse.getString("name");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(pkpActivity.this);
                                    builder.setMessage("Successfully Earned a point")
                                            //.setNegativeButton("Retry", null)
                                            .create()
                                            .show();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(pkpActivity.this);
                                    builder.setMessage("You have already earned a point !")
                                            //.setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    pkpRequest regRequest = new pkpRequest(regno, ans, responseListener);
                    //RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
                    //queue.add(regRequest);
                    RequestHandler.getInstance(pkpActivity.this).addToRequestQueue(regRequest);
                }
            }
        });
    }
}


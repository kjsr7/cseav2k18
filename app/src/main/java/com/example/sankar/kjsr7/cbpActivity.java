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

public class cbpActivity extends AppCompatActivity {
Button b;
int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cb);

       final EditText pass3 = findViewById(R.id.p2);
        //final String ans = pass3.getText().toString();
        final String regno = SharedPrefManager.getInstance(this).getregno();
        final TextView err = (TextView) findViewById(R.id.textView7);
        ;
        b = findViewById(R.id.s2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = pass3.getText().toString();
                err.setVisibility(View.VISIBLE);
                if (ans.equals("codebusters2k18")) {
                    m = 1;
                    err.setText("correct. Please Wait!");
                } else {
                    err.setText("incorrect Passcode");

                }


                //             final String username = etUsername.getText().toString();
                //           final String password = etPassword.getText().toString();

                // Response received from the server
if(m==1){
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            err.setVisibility(View.GONE);
                            if (success) {
                                //String name = jsonResponse.getString("name");
                                AlertDialog.Builder builder = new AlertDialog.Builder(cbpActivity.this);
                                builder.setMessage("Successfully Earned a point")
                                        //.setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(cbpActivity.this);
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

                cbpRequest regRequest = new cbpRequest(regno, ans, responseListener);
                //RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
                //queue.add(regRequest);
                RequestHandler.getInstance(cbpActivity.this).addToRequestQueue(regRequest);

            }
        }
        });
    }

}


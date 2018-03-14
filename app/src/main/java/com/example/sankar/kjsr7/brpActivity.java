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

public class brpActivity extends AppCompatActivity {
Button b;
int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brp);
        final EditText pass = findViewById(R.id.p1);
       // final String
        final String regno = SharedPrefManager.getInstance(this).getregno();
        final TextView err = (TextView) findViewById(R.id.err12);
        b = findViewById(R.id.s1);
       // if (m == 1) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   String ans = pass.getText().toString();
                    err.setVisibility(View.VISIBLE);

                    if (ans.equals("bullrun2k18"))
                    {m = 1;err.setText("correct .Please Wait!");}
                    else {
                        err.setText("incorrect Passcode");

                    }

                    if(m==1){
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                err.setText(response);
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");

                                if (success) {
                                    //String name = jsonResponse.getString("name");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(brpActivity.this);
                                    builder.setMessage("Successfully Earned a point")
                                            //.setNegativeButton("Retry", null)
                                            .create()
                                            .show();

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(brpActivity.this);
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

                    brpRequest regRequest = new brpRequest(regno, ans, responseListener);
                    //RequestQueue queue = Volley.newRequestQueue(EventActivity.this);
                    //queue.add(regRequest);
                    RequestHandler.getInstance(brpActivity.this).addToRequestQueue(regRequest);
                       }

                }
            });
      //  }
    }
}


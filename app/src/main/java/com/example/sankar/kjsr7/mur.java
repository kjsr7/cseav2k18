package com.example.sankar.kjsr7;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class mur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mur);
        EditText etn = (EditText)findViewById(R.id.editText9);
        EditText esm = (EditText)findViewById(R.id.editText8);
        final TextView err = (TextView)findViewById(R.id.error6);
        final TextView info = (TextView)findViewById(R.id.textView5);
     //   EditText etm = (EditText)findViewById(R.id.editText10);
        final String tn = etn.getText().toString();
        final String fmr = SharedPrefManager.getInstance(this).getregno();
        final String smr = esm.getText().toString();
      //  final String tmr = etm.getText().toString();
        Button b = (Button) findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m=1;
                    if(fmr.equals(smr))
                    {
                        m=0;
                        err.setVisibility(View.VISIBLE);
                        err.setText("Please Enter a Different Regno of your team Mate");
                    }
                //final String username = etUsername.getText().toString();
                //final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  progressDialog.dismiss();
                        try {
                                info.setText(response);
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                //String name = jsonResponse.getString("name");
                                //String email = jsonResponse.getString("email");
                                //String phno = jsonResponse.getString("phno");
                                // int age = jsonResponse.getInt("age");
                                // String rno = jsonResponse.getString("regno");
                              //  SharedPrefManager.getInstance(getApplicationContext()).
                                //        userLogin(username, name, email, phno);

  //                              Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                                //intent.putExtra("name", name);
                                //intent.putExtra("regno", username);

                                //  intent.putExtra("age", age);
                                // intent.putExtra("username", username);
                                Toast.makeText(getApplicationContext(),"Successfully Registered your Team",Toast.LENGTH_LONG).show();
                            //    MainActivity.this.startActivity(intent);
                               // finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mur.this);
                                builder.setMessage("Already Registered")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                    if(m==1) {

                        MRegRequest loginRequest = new MRegRequest(tn, fmr, smr, responseListener);
                        //  RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                        //  queue.add(loginRequest);
                        RequestHandler.getInstance(mur.this).addToRequestQueue(loginRequest);
                    }

            }
        });






    }
}

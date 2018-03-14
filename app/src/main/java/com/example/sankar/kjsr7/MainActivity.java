package com.example.sankar.kjsr7;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this,UserAreaActivity.class));
            return;
        }
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        final TextView nh = (TextView)findViewById(R.id.textView2);

        final Button bLogin = (Button) findViewById(R.id.bSignIn);
        progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");


        nh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Contact JaiShankar: 9963009225")
                        //.setNegativeButton("Retry", null)
                        .create()
                        .show();

            }
        });
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterAcitivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  progressDialog.dismiss();
                        try {
                         //   nh.setText(response);

                            JSONObject jsonResponse = new JSONObject(response);
                          //  Log.i("error", "onResponse: " + jsonResponse);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                           //     String regno = jsonResponse.getString("regno");
                               String name = jsonResponse.getString("name");
                                String email = jsonResponse.getString("email");
                                String phno = jsonResponse.getString("phno");
                               // int age = jsonResponse.getInt("age");
                               // String rno = jsonResponse.getString("regno");
                              // String email = "jai";
                              //  String phno  = "phno";
                                SharedPrefManager.getInstance(getApplicationContext()).
                                        userLogin(username, name, email, phno);

                                Intent intent = new Intent(MainActivity.this, UserAreaActivity.class);
                                //intent.putExtra("name", name);
                                //intent.putExtra("regno", username);

                              //  intent.putExtra("age", age);
                               // intent.putExtra("username", username);
                                Toast.makeText(getApplicationContext(),"Successfully Logged In",Toast.LENGTH_LONG).show();
                                MainActivity.this.startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
              //  RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
              //  queue.add(loginRequest);
                RequestHandler.getInstance(MainActivity.this).addToRequestQueue(loginRequest);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.ABOUT:
                //SharedPrefManager.getInstance(this).logout();
                //finish();
                startActivity(new Intent(this, creditsActivity.class));        break;


        }
        return true;

    }


}

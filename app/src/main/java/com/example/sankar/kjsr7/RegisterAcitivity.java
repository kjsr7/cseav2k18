package com.example.sankar.kjsr7;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import org.w3c.dom.Text;

public class RegisterAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acitivity);

        final EditText phno1 = (EditText) findViewById(R.id.editText5);
        final EditText etName = (EditText) findViewById(R.id.editText2);
        final EditText etUsername = (EditText) findViewById(R.id.editText);
        final EditText email1 = (EditText) findViewById(R.id.editText3);
        final TextView e = (TextView) findViewById(R.id.textView);


        final EditText etPassword = (EditText) findViewById(R.id.editText4);
        final EditText cetPassword = (EditText) findViewById(R.id.editText6);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                final String regno = etUsername.getText().toString();
                final String email = email1.getText().toString();
                final String phno = phno1.getText().toString();
                //final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();
                final String cpassword = cetPassword.getText().toString();
                String er = e.getText().toString();
                int m = 1;
                if (password.equals(cpassword)) {

                } else {
                    m = 0;
                    e.setText("*passwords did not match*");


                }
                if (regno.length() != 6) {
                    m = 0;
                    e.setText("*please enter valid Registration Number*");

                }
                if(   !(email.contains("@")) || !(email.endsWith(".com"))  )
                {
                    m=0;
                    e.setText("*please enter valid Email Id*");
                }
                if(phno.length()!=10)
                {
                    m=0;
                    e.setText("*please enter valid Phone Number*");
                }



                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //Log.i("error", "onResponse: " + jsonResponse);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                              //  Toast.makeText(getApplicationContext(this), "Successfully Registered", Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(RegisterAcitivity.this, MainActivity.class);
                                RegisterAcitivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAcitivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                if(m==1)
                {
                RegisterRequest registerRequest = new RegisterRequest(regno, name, email, password, phno, responseListener);
               // RequestQueue queue = Volley.newRequestQueue(RegisterAcitivity.this);
                //queue.add(registerRequest);
                    //
                    RequestHandler.getInstance(RegisterAcitivity.this).addToRequestQueue(registerRequest);
                    }

            }});


}
}
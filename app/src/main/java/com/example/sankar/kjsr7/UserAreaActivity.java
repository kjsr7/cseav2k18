package com.example.sankar.kjsr7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UserAreaActivity extends AppCompatActivity {
    String app_server_url  = "http://kjsr.000webhostapp.com/fcm_insert.php";
    Button br,b2,cb,pt,c,pk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        //Intent intent = getIntent();
        //String name = intent.getStringExtra("name");
        //final String rno = intent.getStringExtra("regno");

      //  String username = intent.getStringExtra("username");
      //  int age = intent.getIntExtra("age", -1);
        String name = SharedPrefManager.getInstance(this).getname();
        final String rno = SharedPrefManager.getInstance(this).getregno();

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
   //     EditText etUsername = (EditText) findViewById(R.id.etUsername);
      //  EditText etAge = (EditText) findViewById(R.id.etAge);

        // Display user details
        String message = "Hie " + name + " ! CSEA welcomes you";
        tvWelcomeMsg.setText(message);
        br = findViewById(R.id.br);
        cb = findViewById(R.id.cb);
        pt = findViewById(R.id.pt);
        pk = findViewById(R.id.pk);
        c = findViewById(R.id.c);



        b2 = findViewById(R.id.button5);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.sankar.kjsr7withappserver.fcm_pref", Context.MODE_PRIVATE);
          final String token  =sharedPreferences.getString("com.example.sankar.kjsr7withappserver.fcm_token", "");
        StringRequest stringRequest  = new StringRequest(Request.Method.POST, app_server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {
            @Override
            protected Map<String, String > getParams() throws AuthFailureError
            {
                Map <String, String > params = new HashMap<String, String>();
                params.put("fcm_token", token);

                return params;
            }
        }
        ;
        MySingleton.getmInstance(UserAreaActivity.this).addToRequestQueue(stringRequest );


                // etUsername.setText(username);
                // etAge.setText(age + "");

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this,EventActivity.class);
            //    i.putExtra("regno",rno);
                startActivity(i);
            }
        });
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (UserAreaActivity.this, CodeBustersActivity.class);
                startActivity(i);
            }
        });
        pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this, ptActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this,ExtraActivity.class);
                //    i.putExtra("regno",rno);
                startActivity(i);
            }
        });
        pk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this, pkActivity.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserAreaActivity.this,cActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
 getMenuInflater().inflate(R.menu.menu, menu);
    return true;}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
switch(item.getItemId())
{
    case R.id.menuLogout:
        SharedPrefManager.getInstance(this).logout();
        finish();
        startActivity(new Intent(this, MainActivity.class));        break;
    case R.id.ABOUT:
        //SharedPrefManager.getInstance(this).logout();
       // finish();
        startActivity(new Intent(this, creditsActivity.class));        break;

}
return true;

    }
}

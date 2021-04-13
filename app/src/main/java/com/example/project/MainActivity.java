package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("json", Context.MODE_PRIVATE);

    }

    public void signUp_func(View view) {
        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(i);
    }

    public void pay_func(View view) {
        Intent i = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(i);
    }

    public void login_func(View view) {
        TextInputLayout tt = (TextInputLayout) findViewById(R.id.email_layout);
        String name = tt.getEditText().getText().toString();
        Toast.makeText(this,   "Welcome "+name, Toast.LENGTH_LONG).show();
//        String name_gotten = get("user", "id", "1");
//        Log.i("myjson", name_gotten);
        Intent i = new Intent(MainActivity.this, Home.class);
        startActivity(i);

    }

    public void forgot_pass(View view) {
    }


//    public String get(String table, String filter, String key){
//
//
//
//        try {
//            RequestQueue queue = Volley.newRequestQueue(this);
//            String url ="https://kfupm-intelligent-parkings.azurewebsites.net/api/v1/"+table.toLowerCase().replaceAll(" ", "")+"/list?"+filter+"="+key.toLowerCase();
//
//            // Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
//
//                @Override
//                public void onResponse(String response) {
//                    try {
//                        JSONArray jsonArr = new JSONArray(response);
//                        JSONObject jo = (JSONObject)jsonArr.get(0);
//                        SharedPreferences.Editor myEdit = sp.edit();
//
////                        myEdit.putString("jo", jo.getString("name"));
//                        myEdit.putString("jo", jo.getString("name"));
//                        myEdit.apply();
//                        Log.i("Line 86 myjson", sp.getString("jo", "not found"));
//
//                    } catch (Exception e) {
//                        Log.e("JSONError", e.getMessage());
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                    usernameEditText.setText("That didn't work!");
//                }
//            });
//
//
//
//            // Add the request to the RequestQueue.
//            queue.add(stringRequest);
//
//        }catch (Exception e){
//            Log.e("Error", e.getMessage());
//        }
//
//
//        return getJO();
//    }

    public String getJO(){
        return sp.getString("jo", "not found");
    }
}


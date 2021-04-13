package com.example.project;
// Database link for users info
// https://kfupm-intelligent-parkings.azurewebsites.net/api/v1/user
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

// This must be added in app > manifests:
// <uses-permission
//        android:name="android.permission.INTERNET" />


public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    // for this func, you must add this in the dependencies:
    //    implementation 'com.android.volley:volley:1.2.0'
    public void registerFunc(View view) {
        String fName = ((EditText)findViewById(R.id.firstName)).getText().toString().split(" ")[0];
//        if(fName.equals("")){
//            Toast.makeText(this, "Please enter your full name", Toast.LENGTH_LONG).show();
//        }
        String lName = ((EditText)findViewById(R.id.firstName)).getText().toString().split(" ")[1];
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();
        String phone = ((EditText)findViewById(R.id.number)).getText().toString();

//        if(!fName.equals("") ){
//
//        }
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            String URL = "https://kfupm-intelligent-parkings.azurewebsites.net/api/v1/user/new";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("fName", fName);
            jsonBody.put("lName", lName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("phone", phone);
            final String requestBody = jsonBody.toString();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    Log.e("VOLLEY", requestBody);
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
            // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            requestQueue.add(stringRequest);
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Intent i = new Intent(MainActivity2.this, MainActivity.class);
//        startActivity(i);
    }




//    fun registerFunc(view: View?) {
////        val tt = findViewById<View>(R.id.username_layout) as TextInputLayout
////        val name = tt.editText!!.text.toString()
//        Toast.makeText(this, "You have signed up successfully", Toast.LENGTH_LONG).show()
////        val i = Intent(this@MainActivity, signUp::class.java)
//        val i = Intent(this@MainActivity2, MainActivity::class.java)
//        startActivity(i)
//    }

    public void login_page(View view) {
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(i);
    }

}
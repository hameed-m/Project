package com.example.project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Home extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private Geocoder geocoder;
    EditText startdate_time_in;
    EditText enddate_time_in;
    private static final String Tag= "MapsActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        geocoder = new Geocoder(this);
        startdate_time_in=findViewById(R.id.start_date_time_input);
        enddate_time_in=findViewById(R.id.end_date_time_input);
        startdate_time_in.setInputType(InputType.TYPE_NULL);
        enddate_time_in.setInputType(InputType.TYPE_NULL);



        startdate_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialogStart(startdate_time_in);
            }
        });
        enddate_time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialogEnd(enddate_time_in);
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapv);
        mapFragment.getMapAsync(this);

        AutoCompleteTextView lot_auto_complete = (AutoCompleteTextView) findViewById(R.id.lot_list);


        // Parking Lots Request // start
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://kfupm-intelligent-parkings.azurewebsites.net/api/v1/parkinglot/list";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){

                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray ja = new JSONArray(response);
                        String[] items = new String[ja.length()];
                        for(int i = 0; i < ja.length(); i++){
                            items[i] = ((JSONObject)ja.get(i)).getString("name");
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, items);
                        lot_auto_complete.setAdapter(adapter);
                        lot_auto_complete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override

                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                                String g = ((TextView) view).getText().toString();
//                                LatLng sydney = new LatLng(0.0, 0.0);
//                                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in saudi"));
//                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f));


                                AutoCompleteTextView n = ((AutoCompleteTextView) findViewById(R.id.parking_list));
                                n.setEnabled(true);
                                n.setText("id = " + position);

                            }
                        });



                    } catch (Exception e) {
                        Log.e("JSONError", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    usernameEditText.setText("That didn't work!");
                }
            });



            // Add the request to the RequestQueue.
            queue.add(stringRequest);

        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }

        // Parking Lots Request // end






        AutoCompleteTextView area = (AutoCompleteTextView) findViewById(R.id.lot_list);

        //val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
//        String[] items = {"A", "B", "C"};
//        String[][] A = {{"1", "2", "3"}, {"5", "6", "7"}, {"8", "9", "10"}};
//        double[][] cor={{24.468675, 39.611064},{26.364734, 50.112031},{26.364734, 45.112031}};
////        String[] B= {"5","6","7"};
////        String[] C= {"8","9","10"};

        //val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, items);
//        //(textField.editText as? AutoCompleteTextView)?.setAdapter(adapter)
//        area.setAdapter(adapter);
        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        area.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String g = ((TextView) view).getText().toString();
                LatLng sydney = new LatLng(0.0, 0.0);
                // Toast.makeText(this," "+cor[position][0]+","+cor[position][1],Toast.LENGTH_LONG).show();
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in saudi"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f));


                AutoCompleteTextView n = ((AutoCompleteTextView) findViewById(R.id.parking_num_List));
                n.setEnabled(true);
                n.setText(g+" "+position+" "+0.0+","+0.0);
//                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, new String());
//                n.setAdapter(adapter);
            }
        });


    }
    public void check_out_func(View view) {
        //Toast.makeText(this,"start end",Toast.LENGTH_LONG).show();
        Intent i = new Intent(Home.this,setUpParkingLot.class);
        startActivity(i);


    }
    private void showDateTimeDialogStart(final EditText startdate_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");

                        startdate_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(Home.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(Home.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void showDateTimeDialogEnd(final EditText enddate_time_in) {
        final Calendar calendar=Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);

                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd HH:mm");

                        enddate_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(Home.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),false).show();
            }
        };

        new DatePickerDialog(Home.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(26.364734, 50.112031);
        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in saudi"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f));
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d(Tag, "onmapclick: " +latLng.toString());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses.size()>0){
                Address address = addresses.get(0);
                String streetAdress = address.getAddressLine(0);
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
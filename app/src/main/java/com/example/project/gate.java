package com.example.project;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class gate extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    Button btnClick;
    TextView text;
    private GoogleMap mMap;
    private Geocoder geocoder;
    public String pos;
    private static final String Tag= "gate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);
        geocoder = new Geocoder(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnClick=(Button)findViewById(R.id.save);
        text= (TextView)findViewById(R.id.text);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("the gate coordenates are: "+ pos);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
    }
    public void onMapClick(LatLng latLng) {
        Log.d(Tag, "onmapclick: " +latLng.toString());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addresses.size()>0){
                Address address = addresses.get(0);
                String streetAdress = address.getAddressLine(0);
                pos = latLng.latitude+" "+latLng.longitude;
                mMap.addMarker(new MarkerOptions().position(latLng));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
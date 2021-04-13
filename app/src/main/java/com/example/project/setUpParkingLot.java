package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class setUpParkingLot extends AppCompatActivity {
    Button coordB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_parking_lot);
        coordB=(Button)findViewById(R.id.coordG);
        coordB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(setUpParkingLot.this,gate.class);
                startActivity(i);
            }
        });
    }

}
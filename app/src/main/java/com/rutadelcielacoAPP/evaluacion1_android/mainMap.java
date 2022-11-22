package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class mainMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        new Handler(). postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mainMap.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);
    }
}
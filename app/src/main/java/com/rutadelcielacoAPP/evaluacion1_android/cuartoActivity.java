package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class cuartoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarto);
    }

    public void volverHome(View view){
        Intent volverHome = new Intent(this,SegundoActvity.class);
        startActivity(volverHome);
    }
}
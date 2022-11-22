package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class regristroActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button singup, singin;
    BDHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regristro);

        username = findViewById(R.id.Username2);
        password = findViewById(R.id.Password2);
        repassword = findViewById(R.id.repassword2);

        singup = findViewById(R.id.singup2);
        singin = findViewById(R.id.singin2);
        DB = new BDHelper(this);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                Toast.makeText(regristroActivity.this, "Se requiere completar los campos", Toast.LENGTH_SHORT).show();

                }else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = DB.insertData(user,pass);
                            if(insert == true){
                                Toast.makeText(regristroActivity.this, "Registro completado", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(regristroActivity.this, "Registro fallido, intente nuevamente", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(regristroActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        }
}



    /*
    public void volverIniciosesion(View view){
        Intent volverIniciosesion = new Intent(this,MainActivity.class);
        startActivity(volverIniciosesion);
    }*/

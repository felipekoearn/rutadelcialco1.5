package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button singin;
    BDHelper DB;

    public void formulario(View view){
        Intent formulario = new Intent(this,regristroActivity.class);
        startActivity(formulario);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_Evaluacion1_Android);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        singin = findViewById(R.id.singin1);

        DB = new BDHelper(this);

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this, "complete todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Toast.makeText(MainActivity.this,"inicio de sesion completado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),SegundoActvity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "inicio de sesion fallido", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}





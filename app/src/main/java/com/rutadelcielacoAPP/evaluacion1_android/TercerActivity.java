package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TercerActivity extends AppCompatActivity {

    private EditText txtid, txtnom;
    private Button btnbus, btnmod, btnreg, btneli;
    private ListView lvDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);

        txtid   = (EditText) findViewById(R.id.txtid);
        txtnom  = (EditText) findViewById(R.id.txtnom);
        btnbus  = (Button)   findViewById(R.id.btnbus);
        btnmod  = (Button)   findViewById(R.id.btnmod);
        btnreg  = (Button)   findViewById(R.id.btnreg);
        btneli  = (Button)   findViewById(R.id.btneli);
        lvDatos = (ListView) findViewById(R.id.lvDatos);

        botonBuscar();
        botonModificar();
        botonRegistrar();
        botonEliminar();
        listarAlimentos();
    }

    private void botonBuscar(){
        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(TercerActivity.this, "Digite el ID del alimento a buscar", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alimentos.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x: snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    res = true;
                                    ocultarTeclado();
                                    txtnom.setText(x.child("nombre").getValue().toString());
                                    break;
                                }
                            }

                            if(res == false){
                                ocultarTeclado();
                                Toast.makeText(TercerActivity.this, "ID ("+aux+") No encontrdo", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }//cierra el if/else inicial
            }
        });

    }//cierra el metodo boton buscar





    private void botonModificar(){
        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(TercerActivity.this, "Escriba los datos faltantes Para Actualizar", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alimentos.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res2 = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("nombre").getValue().toString().equals(nom)){
                                    res2 = true;
                                    ocultarTeclado();
                                    Toast.makeText(TercerActivity.this, "El nombre ("+nom+") Ya Existe! \nImposible Modificar", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }

                            if(res2 == false){
                                String aux = Integer.toString(id);
                                boolean res = false;
                                for(DataSnapshot x : snapshot.getChildren()){
                                    if(x.child("id").getValue().toString().equals(aux)){
                                        res = true;
                                        ocultarTeclado();
                                        x.getRef().child("nombre").setValue(nom);
                                        txtid.setText("");
                                        txtnom.setText("");
                                        listarAlimentos();
                                        break;
                                    }
                                }

                                if(res == false){
                                    ocultarTeclado();
                                    Toast.makeText(TercerActivity.this, "ID("+aux+")No Encontrado. \nImposible localizar... ", Toast.LENGTH_SHORT).show();
                                    txtid.setText("");
                                    txtnom.setText("");
                                }
                            }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }//cierra el if else inicial
            }
        });
    }





    private void botonRegistrar(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtid.getText().toString().trim().isEmpty() || txtnom.getText().toString().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(TercerActivity.this, "Escriba los datos faltantes", Toast.LENGTH_SHORT).show();

                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    String nom = txtnom.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alimentos.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("id").getValue().toString().equals(aux)){
                                    res = true;
                                    ocultarTeclado();
                                    Toast.makeText(TercerActivity.this,"Error... el ID("+aux+") Ya Existe!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }

                            boolean res2 = false;
                            for(DataSnapshot x : snapshot.getChildren()){
                                if(x.child("nombre").getValue().toString().equals(nom)){
                                    res2 = true;
                                    ocultarTeclado();
                                    Toast.makeText(TercerActivity.this,"Error... el Nombre("+nom+") Ya Existe!", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }

                            if(res == false && res2 == false){
                                Alimentos ali = new Alimentos(id, nom);
                                dbref.push().setValue(ali);
                                ocultarTeclado();
                                Toast.makeText(TercerActivity.this, "Alimento registrado correctamente", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }//cierra el if else inicial
            }
        });
    }//cierra el metodo boton registrar


    private void listarAlimentos(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbref = db.getReference(Alimentos.class.getSimpleName());

        ArrayList<Alimentos> listalim = new ArrayList<Alimentos>();
        ArrayAdapter<Alimentos> adap = new ArrayAdapter<Alimentos>(TercerActivity.this, android.R.layout.simple_list_item_1, listalim);
        lvDatos.setAdapter(adap);

        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Alimentos ali = snapshot.getValue(Alimentos.class);
                listalim.add(ali);
                adap.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adap.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Alimentos ali = listalim.get(i);
                AlertDialog.Builder a = new AlertDialog.Builder(TercerActivity.this);
                a.setCancelable(true);
                a.setTitle("alimento seleccionado");
                String msg = "ID: " + ali.getId() + "\n\n";
                msg += "NOMBRE: " + ali.getNombre();

                a.setMessage(msg);
                a.show();
            }
        });

    }//cierra el metodo listarAlimentos



    private void botonEliminar(){
        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(TercerActivity.this, "Digite el ID del alimento a ELIMINAR", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbref = db.getReference(Alimentos.class.getSimpleName());

                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            final boolean[] res = {false};
                            for(DataSnapshot x: snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){

                                    AlertDialog.Builder a = new AlertDialog.Builder(TercerActivity.this);
                                    a.setCancelable(false);
                                    a.setTitle("pregunta");
                                    a.setMessage("Esta seguro de eliminar este registro ?");

                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            res[0] = true;
                                            ocultarTeclado();
                                            x.getRef().removeValue();
                                            listarAlimentos();

                                        }
                                    });

                                    a.show();
                                    break;

                                }
                            }

                            if(res[0] == false){
                                ocultarTeclado();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }//cierra el if/else inicial
            }
        });
    }//cierra el metodo botoneliminar


    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.


}
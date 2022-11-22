package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SegundoActvity extends AppCompatActivity {

    RecyclerView rv1;
    String[]nombres={"celiaco : Reacción inmunológica ante la ingesta de gluten, una proteína presente en el trigo, la cebada y el centeno.\n" +
            "Con el tiempo, la reacción inmunológica al ingerir gluten genera una inflamación que daña el revestimiento del intestino delgado y produce complicaciones médicas. También dificulta la absorción de algunos nutrientes (malabsorción).\n" +
            "El síntoma típico es la diarrea. Otros síntomas incluyen distensión abdominal, fatiga, niveles bajos de hemoglobina (anemia) y osteoporosis. Muchas personas no presentan síntomas.\n" +
            "El tratamiento principal consiste en una dieta estricta libre de gluten que pueda controlar los síntomas y promover la curación del intestino.","intolerante a la lactosa: ncapacidad de digerir por completo el azúcar (lactosa) de los lácteos.\n" +
            "La intolerancia a la lactosa suele estar ocasionada por la deficiencia en el cuerpo de una enzima llamada lactasa.\n" +
            "Los síntomas incluyen calambres abdominales, distensión abdominal y diarrea.\n" +
            "El tratamiento se basa en evitar los productos lácteos, consumir productos libres de lactosa o tomar suplementos de lactasa.\n","alergia al trigo: La alergia al trigo es una reacción alérgica a los alimentos que contienen trigo. Las reacciones alérgicas pueden ser consecuencia del consumo de trigo y también, en algunos casos, de la inhalación de harina de trigo.\n" +
            "\n" +
            "El tratamiento principal para la alergia al trigo es evitar el trigo, pero eso no siempre es tan fácil como parece. El trigo se encuentra en muchos alimentos, incluidos algunos que no sospecharías, como la salsa de soja, el helado y los perritos calientes. Es posible que se necesiten medicamentos para controlar las reacciones alérgicas si comes trigo por accidente."};
    int[]fotos={R.drawable.celiaca,R.drawable.lactosa,R.drawable.trigo};

    //atributos de spinner
    TextView estado;
    Spinner comboEnfermedades;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        rv1 = findViewById(R.id.rv1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv1.setLayoutManager(linearLayoutManager);

        rv1.setAdapter(new AdaptadorEnfermedad());

        estado = (TextView) findViewById(R.id.etiSeleccion);
        comboEnfermedades = (Spinner) findViewById(R.id.idSpinner);

        //Instacia de la array list de las opciones de enfemedades
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_enfe,android.R.layout.simple_spinner_item);

        // agregando el adaptador al comboenfermedades
        comboEnfermedades.setAdapter(adapter);
    }

    //metodo que nos permite entrar a la app
    public void Salir(View view){
        Intent Salir = new Intent(this,MainActivity.class);
        startActivity(Salir);
    }
    //metodo que nos permite entrar a la galeria
    public void Galeria(View view){
        Intent Galeria = new Intent(this,TercerActivity.class);
        startActivity(Galeria);
    }

    //metodo que nos permite entrar a la galeria
    public void Rutas(View view){
        Intent Rutas = new Intent(this,cuartoActivity.class);
        startActivity(Rutas);
    }

    //metodo que nos permite entrar a los mapas
    public void rutasMapas(View view){
        Intent rutasMapas = new Intent(this,mainMap.class);
        startActivity(rutasMapas);
    }


    private class AdaptadorEnfermedad extends RecyclerView.Adapter<AdaptadorEnfermedad.AdaptadorEnfermedadHolder> {
        @NonNull
        @Override
        public AdaptadorEnfermedadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorEnfermedadHolder(getLayoutInflater().inflate(R.layout.layaout_tarjeta,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorEnfermedadHolder holder, int position) {
            holder.imprimir(position);

        }

        @Override
        public int getItemCount() {
            return nombres.length;
        }

        class AdaptadorEnfermedadHolder extends RecyclerView.ViewHolder{
            ImageView iv1;
            TextView tv1;


            public AdaptadorEnfermedadHolder(@NonNull View itemView) {
                super(itemView);
                iv1=itemView.findViewById(R.id.imageView);
                tv1=itemView.findViewById(R.id.tvnombre);
            }

            public void imprimir(int position) {
                iv1.setImageResource(fotos[position]);
                tv1.setText(nombres[position]);
            }
        }
    }
}
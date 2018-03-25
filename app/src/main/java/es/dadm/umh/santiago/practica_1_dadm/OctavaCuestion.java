package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by santi on 20/03/2018.
 */

public class OctavaCuestion extends AppCompatActivity {


    private final String OBJETO_SERIALIZADO = "PARAM1";
    private Cuestionario cuestiones;

    private ProgressBar barra;
    private TextView usuario;
    private Button anterior;
    private Button siguiente;
    private Spinner opciones;

    String[] versiones;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.octava_cuestion);
        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        usuario=(TextView)findViewById(R.id.tv_nombre);
        opciones=(Spinner)findViewById(R.id.s_pregunta8);
        anterior=(Button)findViewById(R.id.b_anterior);
        siguiente=(Button)findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(80);

        versiones=opciones.getResources().getStringArray(R.array.lista_versiones);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,versiones);
        opciones.setAdapter(adapter);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrocederActividad();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siguienteActividad();
            }
        });
    }

    public void retrocederActividad(){

        String cadenaSerializadaJSON;
        Gson gson = new Gson();
        Intent i = new Intent(this, SeptimaCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void siguienteActividad(){
        cuestiones.setRespuesta8(opciones.getSelectedItem().toString());

        String cadenaSerializadaJSON;

        Intent i = new Intent(this, NovenaCuestion.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }
}

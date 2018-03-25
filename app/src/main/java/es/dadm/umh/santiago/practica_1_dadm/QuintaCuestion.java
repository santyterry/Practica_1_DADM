package es.dadm.umh.santiago.practica_1_dadm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

public class QuintaCuestion extends AppCompatActivity{

    private final String OBJETO_SERIALIZADO = "PARAM1";
    Cuestionario cuestiones;

    private ProgressBar barra;
    private TextView usuario;
    private Button anterior;
    private Button siguiente;
    private Spinner opciones;

    String[] versiones;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quinta_cuestion);

        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        usuario=(TextView)findViewById(R.id.tv_nombre);
        opciones=(Spinner)findViewById(R.id.s_pregunta5);
        anterior=(Button)findViewById(R.id.b_anterior);
        siguiente=(Button)findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(50);

        versiones=opciones.getResources().getStringArray(R.array.lista_wearables);
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
        Intent i = new Intent(this, CuartaCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void siguienteActividad(){
        cuestiones.setRespuesta5(opciones.getSelectedItem().toString());

        String cadenaSerializadaJSON;

        Intent i = new Intent(this, SextaCuestion.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }
}

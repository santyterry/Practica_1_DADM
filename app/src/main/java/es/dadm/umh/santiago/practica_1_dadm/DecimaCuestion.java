package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by santi on 21/03/2018.
 */

public class DecimaCuestion extends AppCompatActivity{

    private final String OBJETO_SERIALIZADO = "PARAM1";
    private Cuestionario cuestiones;

    private ProgressBar barra;
    private TextView usuario;
    private NumberPicker respuesta;
    private Button anterior;
    private Button siguiente;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decima_cuestion);
        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        usuario=(TextView)findViewById(R.id.tv_nombre);
        respuesta=(NumberPicker)findViewById(R.id.np_pregunta10);
        anterior=(Button)findViewById(R.id.b_anterior);
        siguiente=(Button)findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(100);

        respuesta.setMaxValue(2010);
        respuesta.setMinValue(2006);
        respuesta.setWrapSelectorWheel(false);

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
        Intent i = new Intent(this, NovenaCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void siguienteActividad(){
        cuestiones.setRespuesta10(String.valueOf(respuesta.getValue()));

        String cadenaSerializadaJSON;

        Intent i = new Intent(this, ResultadoCuestionario.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);


    }
}




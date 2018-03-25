package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;


public class ResultadoCuestionario extends AppCompatActivity{

    private final String OBJETO_SERIALIZADO = "PARAM1";
    private Cuestionario cuestiones;

    private ProgressBar barra;
    private TextView resultado;
    private NumberPicker respuesta;
    private Button anterior;
    private Button siguiente;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado_cuestionario);
        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);


        resultado=(TextView)findViewById(R.id.tv_resultado);
        //resultado.setText(cuestiones.comprobarRespuestas()+"/10");
    }


}



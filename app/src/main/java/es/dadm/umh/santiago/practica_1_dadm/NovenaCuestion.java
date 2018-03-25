package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;

public class NovenaCuestion extends AppCompatActivity {

    private final String OBJETO_SERIALIZADO = "PARAM1";
    private Cuestionario cuestiones;
    private ProgressBar barra;
    private TextView usuario;
    private ToggleButton cambio;
    private Button anterior;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novena_cuestion);

        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra = (ProgressBar) findViewById(R.id.pb_progreso);
        usuario = (TextView) findViewById(R.id.tv_nombre);
        cambio=(ToggleButton)findViewById(R.id.tb_respuesta);
        anterior = (Button) findViewById(R.id.b_anterior);
        siguiente = (Button) findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(90);

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
        Intent i = new Intent(this, OctavaCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void siguienteActividad(){

        if(cambio.isChecked()){
            cuestiones.setRespuesta8("Verdadero");

        }
        else{
            cuestiones.setRespuesta8("Falso");

        }

        String cadenaSerializadaJSON;

        Intent i = new Intent(this, DecimaCuestion.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);


    }

}

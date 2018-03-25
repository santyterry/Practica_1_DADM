package es.dadm.umh.santiago.practica_1_dadm;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

public class SeptimaCuestion extends AppCompatActivity {
    private final String OBJETO_SERIALIZADO = "PARAM1";
    Cuestionario cuestiones;
    private int progreso=0;
    private ProgressBar barra;
    private TextView usuario, numero;
    private Button anterior;
    private Button siguiente;
    private SeekBar barraBusqueda;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.septima_cuestion);

        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        usuario=(TextView)findViewById(R.id.tv_nombre);
        numero=(TextView)findViewById(R.id.tv_sbNum);
        barraBusqueda=(SeekBar)findViewById(R.id.sb_pregunta7);
        anterior=(Button)findViewById(R.id.b_anterior);
        siguiente=(Button)findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(70);

        barraBusqueda.setMax(10);
        numero.setText(""+progreso);

        barraBusqueda.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progreso=i;
                numero.setText(""+progreso);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        Intent i = new Intent(this, SextaCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void siguienteActividad(){
        cuestiones.setRespuesta7(String.valueOf(progreso));

        String cadenaSerializadaJSON;

        Intent i = new Intent(this, OctavaCuestion.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }
}


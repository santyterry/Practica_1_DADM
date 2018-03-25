package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;


public class SegundaCuestion extends AppCompatActivity{

    private final String OBJETO_SERIALIZADO = "PARAM1";

    private Cuestionario cuestiones;

    private ProgressBar barra;
    private TextView usuario;
    private Button anterior;
    private Button siguiente;
    private EditText respuesta;

    String r;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_cuestion);

        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        usuario=(TextView)findViewById(R.id.tv_nombre);
        respuesta=(EditText)findViewById(R.id.et_pregunta2_respuesta);
        anterior=(Button)findViewById(R.id.b_anterior);
        siguiente=(Button)findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(20);

        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrocederActividadd();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobar();
            }
        });

    }

    public void retrocederActividadd(){

        String cadenaSerializadaJSON;
        Gson gson = new Gson();
        Intent i = new Intent(this, PrimeraCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void comprobar(){
        inicializar();

        if(r.isEmpty()){
            respuesta.setError(getString(R.string.error_opcion));
        }
        else{
            siguienteActividad(siguiente);
        }
    }

    public void inicializar(){
        r=respuesta.getText().toString().trim();
    }

    public void siguienteActividad(View view){
        String cadenaSerializadaJSON;
        cuestiones.setRespuesta2(r);

        Intent i = new Intent(this, TerceraCuestion.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }
}

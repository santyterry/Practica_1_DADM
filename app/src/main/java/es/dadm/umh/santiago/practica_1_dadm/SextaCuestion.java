package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;


public class SextaCuestion extends AppCompatActivity{



    private final String OBJETO_SERIALIZADO = "PARAM1";
    private Cuestionario cuestiones;

    private ProgressBar barra;
    private TextView usuario;
    private CheckBox op1,op2,op3,op4,op5;
    private Button anterior;
    private Button siguiente;

    private ArrayList<String> respuestas =new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sexta_cuestion);

        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        usuario=(TextView)findViewById(R.id.tv_nombre);
        op1=(CheckBox)findViewById(R.id.cb_opcion1);
        op2=(CheckBox)findViewById(R.id.cb_opcion2);
        op3=(CheckBox)findViewById(R.id.cb_opcion3);
        op4=(CheckBox)findViewById(R.id.cb_opcion4);
        op5=(CheckBox)findViewById(R.id.cb_opcion5);
        anterior=(Button)findViewById(R.id.b_anterior);
        siguiente=(Button)findViewById(R.id.b_siguiente);

        usuario.setText(cuestiones.getNombre());
        barra.setProgress(60);

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

    public void opcionesSelecionadas(){
        if(op1.isChecked()){
            respuestas.add(op1.getText().toString());
        }
        if(op2.isChecked()){
            respuestas.add(op2.getText().toString());
        }
        if(op3.isChecked()){
            respuestas.add(op3.getText().toString());
        }
        if(op4.isChecked()){
            respuestas.add(op4.getText().toString());
        }
        if(op5.isChecked()){
            respuestas.add(op5.getText().toString());
        }
    }

    public void retrocederActividad(){

        String cadenaSerializadaJSON;
        Gson gson = new Gson();
        Intent i = new Intent(this, QuintaCuestion.class);

        cadenaSerializadaJSON=gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }

    public void siguienteActividad(){

        opcionesSelecionadas();

        if(respuestas.size()==0){
            Toast.makeText(this,getString(R.string.error_opcion),Toast.LENGTH_SHORT).show();
        }
        else{
            cuestiones.setRespuesta6(respuestas);
            String cadenaSerializadaJSON;

            Intent i = new Intent(this, SeptimaCuestion.class);
            Gson gson = new Gson();
            cadenaSerializadaJSON = gson.toJson(cuestiones);
            i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
            startActivity(i);
        }
    }
}

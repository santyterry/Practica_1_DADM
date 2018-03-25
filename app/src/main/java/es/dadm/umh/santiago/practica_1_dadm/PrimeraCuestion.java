package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class PrimeraCuestion extends AppCompatActivity{

    private final String OBJETO_SERIALIZADO = "PARAM1";

    //Componentes de la actividad y variables
    private String nomb;
    private TextView usuario;
    private ProgressBar barra;
    private RadioGroup grupo;
    private RadioButton opcion;
    private Cuestionario cuestiones;
    private Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primera_cuestion);

        //Creamos el objeto Cuestionario donde se almacenará todas las respuestas del usuario
        cuestiones = new Cuestionario();
        //Asignamos al componente el id del componente de la actividadd
        barra=(ProgressBar)findViewById(R.id.pb_progreso);
        grupo=(RadioGroup)findViewById(R.id.rg_opciones1);
        usuario =(TextView)findViewById(R.id.tv_nombre);
        siguiente=(Button)findViewById(R.id.b_siguiente);
        //Recibimos los datos de la otra actividad
        Intent intent = getIntent();
        String jsonObj = intent.getStringExtra(OBJETO_SERIALIZADO);

        Gson gson = new Gson();
        cuestiones = gson.fromJson(jsonObj, Cuestionario.class);

        //Asignamos el nombre y el progreso de la barra
        usuario.setText(cuestiones.getNombre());
        barra.setProgress(10);

        //Evento onClick
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_boton=grupo.getCheckedRadioButtonId();
                opcion=(RadioButton)findViewById(id_boton);

                //Comprobamos si ha seleccionado una respuesta
                if(grupo.getCheckedRadioButtonId()==-1){
                    //Error
                    comprobacionRadio();
                }
                else {
                    //OK!
                    siguienteActividad();
                }
            }
        });

    }

    public void comprobacionRadio(){
        Toast.makeText(this,getString(R.string.error_opcion),Toast.LENGTH_SHORT).show();
    }

    public void siguienteActividad(){

        //Envio de datos a la siguiente activida y serializaión del objeto
        String opcionp, cadenaSerializadaJSON;
        Gson gson = new Gson();
        Intent i=new Intent(this, SegundaCuestion.class);

        opcionp=opcion.getText().toString().trim();
        cuestiones.setRespuesta1(opcionp);

        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);

        startActivity(i);

    }


}

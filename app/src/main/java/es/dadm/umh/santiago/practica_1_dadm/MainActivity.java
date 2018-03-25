package es.dadm.umh.santiago.practica_1_dadm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private final String OBJETO_SERIALIZADO = "PARAM1";

    Cuestionario cuestiones;
    //Declaración de elemento que encontramos en el activity
    private EditText nom_usu;
    private Button comenzar;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignación del elemento a partir del fichero de recursos R
        nom_usu=(EditText)findViewById(R.id.et_nombre);
        comenzar=(Button)findViewById(R.id.b_comenzar);

        cuestiones = new Cuestionario();

        //Evento OnClick para el boton
        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobacion();
            }
        });
    }

    //Creacion del menu
    @Override
    public boolean onCreateOptionsMenu(Menu miMenu){
        getMenuInflater().inflate(R.menu.menu_acerca_de,miMenu);

        return true;
    }

    //Dependiendo que opcion del menu elija el usuario hacemos algo distinto
    @Override
    public boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id=opcion_menu.getItemId();

        if(id==R.id.acercaDe){
            Toast.makeText(getApplicationContext(), getString(R.string.acerca_de), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }

    //Comprobamos que el usuario ha escrito su nombre
    public void comprobacion(){
        inicializar();

        if(nombre.isEmpty()){
            nom_usu.setError(getString(R.string.error_nombre));
        }
        else{
            siguienteActividad(comenzar);
        }
    }

    //Inicializamos el valor introducido en nom_usu
    public void inicializar(){
        nombre=nom_usu.getText().toString().trim();
    }

    //En el caso de OK! saltamos a la siguiente activity
    public void siguienteActividad(View view){
        String cadenaSerializadaJSON;
        cuestiones.setNombre(nombre);
        Intent i = new Intent(this, PrimeraCuestion.class);
        Gson gson = new Gson();
        cadenaSerializadaJSON = gson.toJson(cuestiones);
        i.putExtra(OBJETO_SERIALIZADO,cadenaSerializadaJSON);
        startActivity(i);
    }


}

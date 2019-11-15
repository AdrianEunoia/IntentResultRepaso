package com.adrian.intentresultrepaso;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button idbuttonlanzar;
    private CheckBox idchecknombre, idcheckedad;
    final static String TAG_1 = "Envio";
    final static int REQ_CODE = 1;
    final static int RES_CODE_OK_EDAD = 1;
    final static int RES_CODE_OK_NOMBRE = 0;
    final static int RES_CODE_FAIL = 1;
    private int flagPregunta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
    }
    private void instancias(){
        idbuttonlanzar = this.findViewById(R.id.idbuttonlanzar);
        idcheckedad = this.findViewById(R.id.idcheckedad);
        idchecknombre = this.findViewById(R.id.idchecknombre);
    }
    private void acciones(){
        idbuttonlanzar.setOnClickListener(this);
        idchecknombre.setOnClickListener(this);
        idcheckedad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idbuttonlanzar:
                if (idcheckedad.isChecked() || idchecknombre.isChecked()){
                    System.out.println("Lanzando petición");
                    Intent intentEnvio = new Intent(getApplicationContext(),SecondActivity.class);
                    intentEnvio.putExtra(TAG_1,flagPregunta);
                    startActivityForResult(intentEnvio,REQ_CODE);
                }else{
                    System.out.println("Debes checkear...");
                }
                break;
            case R.id.idcheckedad:
                flagPregunta=1;
                if(idchecknombre.isChecked()){
                    idchecknombre.setChecked(false);
                    System.out.println(flagPregunta);
                }
                break;
            case R.id.idchecknombre:
                flagPregunta=2;
                if(idcheckedad.isChecked()){
                    idcheckedad.setChecked(false);
                    System.out.println(flagPregunta);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if (requestCode ==REQ_CODE && resultCode ==RES_CODE_OK_EDAD){
            int edadDevuelva = (int) data.getExtras().get(SecondActivity.TAG_RES);
            Toast.makeText(getApplicationContext(), "La edad devuelta es de "+edadDevuelva+" años.", Toast.LENGTH_LONG).show();
        }else if(requestCode ==REQ_CODE && resultCode ==RES_CODE_OK_NOMBRE){
            String nombreDevuelto = (String) data.getExtras().get(SecondActivity.TAG_RES);
            Toast.makeText(getApplicationContext(), "El nombre devuelto es de "+nombreDevuelto+".", Toast.LENGTH_LONG).show();
        }
    }
}

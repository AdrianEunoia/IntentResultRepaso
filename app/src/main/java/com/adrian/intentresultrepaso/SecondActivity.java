package com.adrian.intentresultrepaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ideditnombre, ideditedad;
    private Button idbuttondevolver;
    final static String TAG_RES = "Resultado";
    int flagRecuperado = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        instancias();
        acciones();
        recuperarDatos();
    }
    private void instancias(){
        idbuttondevolver = this.findViewById(R.id.idbuttondevolver);
        ideditedad = this.findViewById(R.id.ideditedad);
        ideditnombre = this.findViewById(R.id.ideditnombre);
        ideditnombre.setEnabled(false);
        ideditedad.setEnabled(false);
    }
    private void acciones(){
        idbuttondevolver.setOnClickListener(this);
    }
    private void recuperarDatos() {
        if (getIntent().getExtras() != null){
            flagRecuperado = (int) getIntent().getExtras().get(MainActivity.TAG_1);
            if(flagRecuperado == 1){
                ideditedad.setEnabled(true);
            }else if(flagRecuperado == 2){
                ideditnombre.setEnabled(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idbuttondevolver:
            if(flagRecuperado == 1 && ideditedad.length()>0){
                int edadDevuelta = Integer.parseInt(ideditedad.getText().toString());
                Intent intentDevuelta = new Intent();
                intentDevuelta.putExtra(TAG_RES,edadDevuelta);
                setResult(MainActivity.RES_CODE_OK_EDAD,intentDevuelta);
                finish();
            }else if(flagRecuperado == 2 && ideditnombre.length()>0){
                String nombreDevuelto = ideditnombre.getText().toString();
                Intent intentDevuelta = new Intent();
                intentDevuelta.putExtra(TAG_RES,nombreDevuelto);
                setResult(MainActivity.RES_CODE_OK_NOMBRE,intentDevuelta);
                finish();
            }else{
                System.out.println("Se ha producido un fallo");
                setResult(MainActivity.RES_CODE_FAIL);
            }
                break;
        }
    }
}

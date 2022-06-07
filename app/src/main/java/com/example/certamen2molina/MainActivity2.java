package com.example.certamen2molina;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {
Fragment fragmentListaP, fragmentListaR;
FragmentTransaction transaccion;
View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fragmentListaP= new ListaPlantasFragment();
        fragmentListaR= new ListaRecoleccionFragment();
    }
    public void onClick(View view){
        int var= view.getId();
        switch(var) {
            case R.id.btnListaPlantas:
                transaccion = getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.ContenedorDeFrag, fragmentListaP);
                transaccion.addToBackStack(null);
                transaccion.commit();
                break;
            case R.id.btnListaRecolecciones:
                transaccion = getSupportFragmentManager().beginTransaction();
                transaccion.replace(R.id.ContenedorDeFrag, fragmentListaR);
                transaccion.addToBackStack(null);
                transaccion.commit();
                break;

        }
    }
}
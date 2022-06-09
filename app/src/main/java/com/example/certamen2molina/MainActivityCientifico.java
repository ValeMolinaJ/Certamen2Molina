package com.example.certamen2molina;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

/* Valentina Molina Jara
    19.987.243-5 */
public class MainActivityCientifico extends AppCompatActivity {
EditText rut, nombres, apellidos;
RadioGroup sexo;
RadioButton Fem,Masc;
BDMolina BDM;
List<classRecoleccion> listarRecoleccion;
List<classCientifico> listarCientifico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cientifico);
        rut =(EditText)findViewById(R.id.edRut);
        nombres =(EditText)findViewById(R.id.edNombres);
        apellidos =(EditText)findViewById(R.id.edApellidos);
        Fem =(RadioButton) findViewById(R.id.rdFem);
        Masc=(RadioButton) findViewById(R.id.rdMasc);
        sexo=(RadioGroup)findViewById(R.id.rdSexo);
        BDM =new BDMolina(this);
        listarRecoleccion = BDM.listarRecoleccion();
        listarCientifico = BDM.listarclassCientificos();
    }
    public void Guardar(View v) {
        try{
            if (!rut.getText().toString().equals("") && !nombres.getText().toString().equals("") &&
                    !apellidos.getText().toString().equals("") && (Fem.isChecked() || (Masc.isChecked()))) {
                String obtRut;
                String obtNombres;
                String obtApellidos;
                String sexo = "";
                boolean siGuarda;
                boolean rutDuplicado = false;
                obtRut = rut.getText().toString();
                obtNombres = nombres.getText().toString();
                obtApellidos = apellidos.getText().toString();
                if (Fem.isChecked()) {
                    sexo = "Femenino";
                } else if (Masc.isChecked()) {
                    sexo = "Masculino";
                }
                for (int i = 0; i < listarCientifico.size(); i++){
                    if(obtRut.equals(listarCientifico.get(i).getRut())){
                        rutDuplicado = true;
                    }
                }
                if(!rutDuplicado) {
                    siGuarda = BDM.insertarDatosC(obtRut, obtNombres, obtApellidos, sexo);
                    if (siGuarda == true) {
                        Toast.makeText(this, "Datos ingresados correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "No se han ingresado los datos", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(this, "Este rut ya se ha registrado...", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
            } Limpiar();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void Editar(View v) {
        if (!rut.getText().toString().equals("") && !nombres.getText().toString().equals("") &&
                !apellidos.getText().toString().equals("") && (Fem.isChecked() || (Masc.isChecked()))) {
            String obtRut;
            String obtNombres;
            String obtApellidos;
            String sexo = "";
            boolean siEdita;
            try {
                obtRut = rut.getText().toString();
                obtNombres = nombres.getText().toString();
                obtApellidos = apellidos.getText().toString();
                if (Fem.isChecked()) {
                    sexo = "Femenino";
                } else if (Masc.isChecked()) {
                    sexo = "Masculino";
                }
                siEdita = BDM.editarDatosC(obtRut, obtNombres, obtApellidos, sexo);
                if (siEdita == true) {
                    Toast.makeText(this, "Datos modificados correctamente", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al modificar los datos", Toast.LENGTH_LONG).show();
            }
        } else  {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Eliminar(View v) {
        if (!rut.getText().toString().equals("")) {
            String obtRut;
            boolean validate = false;
            try {
                obtRut = rut.getText().toString();
                for (int i = 0; i < listarRecoleccion.size(); i++){
                    if(obtRut.equals(listarRecoleccion.get(i).getRutCientifico())){
                        validate = true;
                    }
                }
                if(!validate) {
                    new AlertDialog.Builder(MainActivityCientifico.this)
                            .setTitle("Eliminación de cientifico.")
                            .setMessage("¿Desea eliminar al cientifico?")
                            .setPositiveButton("Sí",
                                    new DialogInterface.OnClickListener() {
                                        @TargetApi(11)
                                        public void onClick(DialogInterface dialog, int id) {
                                            boolean siElimino;
                                            siElimino = BDM.eliminarDatosC(obtRut);
                                            if (siElimino) {
                                                Toast.makeText(MainActivityCientifico.this, "¡Se han eliminado los datos!", Toast.LENGTH_SHORT).show();
                                                dialog.cancel();
                                                Limpiar();
                                            }
                                        }
                                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @TargetApi(11)
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(MainActivityCientifico.this, "No se ha realizado la eliminación", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            }).show();
                }else {
                    Toast.makeText(MainActivityCientifico.this, "No se puede eliminar este cientifico. Pertenece a una recolección", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(this, "Error al Eliminar los datos", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Buscar(View v) {
        if (!rut.getText().toString().equals("")) {
            String obtRut;
            String obtNombres;
            String obtApellidos;
            String sexo = "";
            obtRut = rut.getText().toString();
            obtNombres = nombres.getText().toString();
            obtApellidos = apellidos.getText().toString();
            classCientifico obtdatos = new classCientifico();
                obtdatos = BDM.buscarDatos(obtRut);
            if (obtdatos != null) {
                rut.setText(obtdatos.getRut());
                nombres.setText(obtdatos.getNombres());
                apellidos.setText(obtdatos.getApellidos());
                sexo=obtdatos.getSexo();
                if(sexo.equals("Femenino")){
                Fem.setChecked(true);
                Masc.setChecked(false);
                }
                if(sexo.equals("Masculino")){
                Masc.setChecked(true);
                Fem.setChecked(false);
                }
            } else {
                Toast.makeText(this, "No se encuentran los datos", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public  void Limpiar(){
        rut.setText("");
        nombres.setText("");
        apellidos.setText("");
        sexo.clearCheck();
    }
    public void LimpiarBtn(View view){
        Limpiar();
    }
}
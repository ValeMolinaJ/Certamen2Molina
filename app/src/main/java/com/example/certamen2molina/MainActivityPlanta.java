package com.example.certamen2molina;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/* Valentina Molina Jara
    19.987.243-5 */
public class MainActivityPlanta extends AppCompatActivity implements View.OnLongClickListener {
EditText edCodPlanta,edNomPlanta,edNomCientP,edDescP;
Button btnAbrirCamara;
ImageView imgPlant;
BDMolina BDM;
Intent intentPlant;
Bitmap bmpPlant;
final static int cons=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_planta);
        init();
    }
    private void init(){
        edCodPlanta=(EditText) findViewById(R.id.edCodPlanta);
        edNomPlanta=(EditText) findViewById(R.id.edNomPlanta);
        edNomCientP=(EditText) findViewById(R.id.edNomCientP);
        edDescP=(EditText) findViewById(R.id.edDescP);
        imgPlant=(ImageView) findViewById(R.id.imgPlant);
        btnAbrirCamara=(Button)findViewById(R.id.btnAbrirCamara);
        btnAbrirCamara.setOnLongClickListener(this);
        BDM =new BDMolina(this);
    }
    @Override
    public boolean onLongClick(View view) {
        int idFoto;
        idFoto = view.getId();
        switch (idFoto) {
            case R.id.btnAbrirCamara:
                //ABRE CAMARA
                intentPlant = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentPlant, cons);
                break;
        }
        return false;
    }
    protected void onActivityResult(int requesCode, int resultCode, Intent data)
    {
        super.onActivityResult(requesCode, resultCode, data);
        if (resultCode== Activity.RESULT_OK)
        {
            Bundle ext=data.getExtras();
            bmpPlant=(Bitmap)ext.get("data");
            imgPlant.setImageBitmap(bmpPlant);
        }
    }
    public void Guardar(View v){
        if (!edCodPlanta.getText().toString().equals("") && !edNomPlanta.getText().toString().equals("") &&
                !edNomCientP.getText().toString().equals("") && !edDescP.getText().toString().equals("")){
            int obtCodPlanta;
            String obtNomPlanta;
            String obtnNomCientP;
            String obtDesc;
            boolean siGuarda;
            obtCodPlanta=Integer.parseInt(edCodPlanta.getText().toString());
            obtNomPlanta=edNomPlanta.getText().toString();
            obtnNomCientP=edNomCientP.getText().toString();
            obtDesc=edDescP.getText().toString();
            ByteArrayOutputStream stream= new ByteArrayOutputStream();
            bmpPlant.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte [] byteArray = stream.toByteArray();
            siGuarda=BDM.insertarDatosP(obtCodPlanta,obtNomPlanta,obtnNomCientP,byteArray,obtDesc);
            if (siGuarda == true)
            {
                Toast.makeText(this, "Datos ingresados correctamente", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, "No se han ingresado los datos", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        } Limpiar();
    }
    public void Editar(View v){
        if (!edCodPlanta.getText().toString().equals("") && !edNomPlanta.getText().toString().equals("") &&
                !edNomCientP.getText().toString().equals("") && !edDescP.getText().toString().equals("")){
            int obtCodPlanta;
            String obtNomPlanta;
            String obtnNomCientP;
            String obtDesc;
            boolean siEdita;
            try {
                obtCodPlanta=Integer.parseInt(edCodPlanta.getText().toString());
                obtNomPlanta=edNomPlanta.getText().toString();
                obtnNomCientP=edNomCientP.getText().toString();
                obtDesc=edDescP.getText().toString();
                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                bmpPlant.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte [] byteArray = stream.toByteArray();
                siEdita=BDM.editarDatosP(obtCodPlanta,obtNomPlanta,obtnNomCientP,byteArray,obtDesc);
                if (siEdita == true)
                {
                    Toast.makeText(this, "Datos modificados correctamente", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this, "No se han modificado los datos", Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Toast.makeText(this, "Error al modificar los datos", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Eliminar(View v) {
        if (!edCodPlanta.getText().toString().equals("")) {
            int obtCodPlanta;
            try {
                obtCodPlanta = Integer.parseInt(edCodPlanta.getText().toString());
                new AlertDialog.Builder(MainActivityPlanta.this)
                        .setTitle("Eliminación de planta.")
                        .setMessage("¿Desea eliminar la planta?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    @TargetApi(11)
                                    public void onClick(DialogInterface dialog, int id) {
                                        Boolean siElimino;
                                        siElimino = BDM.eliminarDatosP(obtCodPlanta);
                                        if (siElimino) {
                                            Toast.makeText(MainActivityPlanta.this, "¡Se han eliminado los datos!", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                            Limpiar();
                                        }
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivityPlanta.this, "No se ha realizado la eliminación", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }).show();

            }catch (Exception e){
                Toast.makeText(this, "Error al Eliminar los datos", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Buscar(View v){
        if (!edCodPlanta.getText().toString().equals("")) {
            int codPlantinta;
            String obtNomPlanta;
            String obtnNomCientP;
            String obtDesc;
            Bitmap fotoPlanta;
            codPlantinta=Integer.parseInt(edCodPlanta.getText().toString());
            obtNomPlanta=edNomPlanta.getText().toString();
            obtnNomCientP=edNomCientP.getText().toString();
            obtDesc=edDescP.getText().toString();
            classPlanta obtDatosP = new classPlanta();
            obtDatosP = BDM.buscarDatosP(codPlantinta);
            if (obtDatosP != null) {
                fotoPlanta = BitmapFactory.decodeByteArray(obtDatosP.getImgPlanta(), 0, obtDatosP.getImgPlanta().length);
                imgPlant.setImageBitmap(fotoPlanta);
                edNomPlanta.setText(obtDatosP.getNombrePlanta());
                edNomCientP.setText(obtDatosP.getNomCientificoP());
                edDescP.setText(obtDatosP.getDescripcion());
            } else {
                Toast.makeText(this, "No se Encuentran Datos", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public  void Limpiar(){
        edCodPlanta.setText("");
        edNomPlanta.setText("");
        edNomCientP.setText("");
        edDescP.setText("");
        imgPlant.setImageResource(R.drawable.fondo6);
    }
    public void LimpiarBtn(View view){
        Limpiar();
    }
}
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/* Valentina Molina Jara
    19.987.243-5 */
public class MainActivityRecoleccion extends AppCompatActivity implements View.OnLongClickListener {
    EditText edIdentificador, edRegistro, edComentario, edLong, edLat;
    Button btnAbrirC;
    ImageView imgLugar;
    Spinner spCodC, spRutC;
    BDMolina BDM;
    Intent intentReco;
    Bitmap bmpReco;
    List<classPlanta> lista_codigos;
    List<classCientifico> lista_Rut;
    List<classRecoleccion> listaReco;
    String obRut;
    Integer obCod;
    final static int cons = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recoleccion);
        edIdentificador = (EditText) findViewById(R.id.edIdentificador);
        edRegistro = (EditText) findViewById(R.id.edRegistro);
        edComentario = (EditText) findViewById(R.id.edComentario);
        edLong = (EditText) findViewById(R.id.edLong);
        edLat = (EditText) findViewById(R.id.edLat);
        imgLugar = (ImageView) findViewById(R.id.imgLugar);
        btnAbrirC = (Button) findViewById(R.id.btnAbrirCamara);
        btnAbrirC.setOnLongClickListener(this);
        BDM = new BDMolina(this);
        spRutC = (Spinner) findViewById(R.id.spRutC);
        spCodC = (Spinner)findViewById(R.id.spCodPlanta);
        lista_codigos = BDM.listarclassPlanta();
        listaReco = BDM.listarRecoleccion();
        lista_Rut = BDM.listarclassCientificos();
        LlenarSpinnerRut();
        LlenarSpinnerCod();
        spRutC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                obRut = adapterView.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spCodC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                obCod = Integer.parseInt(adapterView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onLongClick(View view) {
        int idFoto;
        idFoto = view.getId();
        switch (idFoto) {
            case R.id.btnAbrirCamara:
                //ABRE CAMARA
                intentReco = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentReco, cons);
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
            bmpReco=(Bitmap)ext.get("data");
            imgLugar.setImageBitmap(bmpReco);
        }
    }
    public void LlenarSpinnerCod() {
        ArrayList<Integer> codPlantas = new ArrayList<Integer>();
        lista_codigos = new ArrayList<>();
        lista_codigos = BDM.listarclassPlanta();
        if (lista_codigos != null) {
            for (int i = 0; i < lista_codigos.size(); i++) {
                codPlantas.add(lista_codigos.get(i).getCodPlanta());
            }
            spCodC.setAdapter(new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, codPlantas));

        } else {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
    public void LlenarSpinnerRut() {
        ArrayList<String> rutC = new ArrayList<String>();
        lista_Rut = new ArrayList<classCientifico>();
        lista_Rut = BDM.listarclassCientificos();
        if (lista_Rut != null) {
            lista_Rut = BDM.listarclassCientificos();
            for (int i = 0; i < lista_Rut.size(); i++) {
                rutC.add(lista_Rut.get(i).getRut());
            }
            spRutC.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rutC));

        } else {
            Toast.makeText(this, "No hay datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Guardar(View view){
        try {
            if (!edIdentificador.getText().toString().equals("") && !edRegistro.getText().toString().equals("") &&
                    !edComentario.getText().toString().equals("")) {
                int obtIdentificador, obtIdent;
                String obtRegistro, obtComentario, obtRutC;
                float obtLongitud, obtLatitud;
                Boolean siGuarda;
                Boolean identificadorDuplicado = false;
                obtIdentificador = Integer.parseInt(edIdentificador.getText().toString());
                //obtIdent = Integer.parseInt(edIdentificador.getText().toString());
                obtRegistro = edRegistro.getText().toString();
                obtComentario = edComentario.getText().toString();
                obtLongitud = Float.parseFloat(edLong.getText().toString());
                obtLatitud = Float.parseFloat((edLat.getText().toString()));
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmpReco.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                if(listaReco != null){
                    for (int i = 0; i < listaReco.size(); i++) {
                        if (obtIdentificador == (listaReco.get(i).getIdentificador())) {
                            identificadorDuplicado = true;
                        }
                    }
                }
                if (!identificadorDuplicado) {
                    siGuarda = BDM.insertarDatosR(obtIdentificador, obtRegistro, obCod, obRut, obtComentario, byteArray, obtLongitud, obtLatitud);
                    if (siGuarda == true) {
                        Toast.makeText(this, "Datos ingresados correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "No se han ingresado los datos", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "El identificador ya ha sido ingresado...", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
            } Limpiar();
        } catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void Editar(View view){
        if (!edIdentificador.getText().toString().equals("") && !edRegistro.getText().toString().equals("") &&
                !edComentario.getText().toString().equals("") && !edLong.getText().toString().equals("") && !edLat.getText().toString().equals("")){
            int obtIdent;
            String obtFechaReg, obtComentario;
            Float obtLong, obtLat;
            boolean siEdita;
            try {
                obtIdent=Integer.parseInt(edIdentificador.getText().toString());
                obtFechaReg=edRegistro.getText().toString();
                obtComentario=edComentario.getText().toString();
                obtLong=Float.parseFloat(edLong.getText().toString());
                obtLat=Float.parseFloat((edLat.getText().toString()));
                ByteArrayOutputStream stream= new ByteArrayOutputStream();
                bmpReco.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte [] byteArray = stream.toByteArray();
                siEdita=BDM.editarDatosR(obtIdent,obtFechaReg,obCod,obRut,obtComentario,byteArray,obtLong,obtLat);
                if (siEdita == true)
                {
                    Toast.makeText(this, "Datos modificados correctamente", Toast.LENGTH_LONG).show();
                    Limpiar();
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
    public void Eliminar(View view){
        if (!edIdentificador.getText().toString().equals("")) {
            int obIden;
            try {
                obIden = Integer.parseInt(edIdentificador.getText().toString());
                new AlertDialog.Builder(MainActivityRecoleccion.this)
                        .setTitle("Eliminación de recolección.")
                        .setMessage("¿Desea eliminar la recolección?")
                        .setPositiveButton("Sí",
                                new DialogInterface.OnClickListener() {
                                    @TargetApi(11)
                                    public void onClick(DialogInterface dialog, int id) {
                                        boolean siElimino;
                                        siElimino=BDM.eliminarDatosR(obIden);
                                        if (siElimino) {
                                            Toast.makeText(MainActivityRecoleccion.this, "¡Se han eliminado los datos!", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                            Limpiar();
                                        }
                                    }
                                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivityRecoleccion.this, "No se ha realizado la eliminación", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }).show();
            }catch (Exception e){
                Toast.makeText(this, "Error al Eliminar los datos", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Buscar(View view){
        if (!edIdentificador.getText().toString().equals("")) {
            int ident, obtCodPlantita;
            String obtRegistro, obtComentario, obtRutC;
            float obtLongitud;
            float obtLatitud;
            Bitmap fotoL;
            ident=Integer.parseInt(edIdentificador.getText().toString());
            obtRegistro = edRegistro.getText().toString();
            obtComentario = edComentario.getText().toString();
            classRecoleccion obtDatosR = new classRecoleccion();
            obtDatosR = BDM.buscarDatosR(ident);
            if (obtDatosR != null) {
                fotoL = BitmapFactory.decodeByteArray(obtDatosR.getFotoLugar(), 0, obtDatosR.getFotoLugar().length);
                imgLugar.setImageBitmap(fotoL);
                edRegistro.setText(obtDatosR.getFechaReg());
                edComentario.setText(obtDatosR.getComentario());
                edLong.setText(String.valueOf(obtDatosR.getLongitud()));
                edLat.setText(String.valueOf(obtDatosR.getLatitud()));

            } else {
                Toast.makeText(this, "No se Encuentran Datos", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Faltan datos", Toast.LENGTH_LONG).show();
        }
    }
    public void Limpiar(){
        edIdentificador.setText("");
        edComentario.setText("");
        edRegistro.setText("");
        edLong.setText("");
        edLat.setText("");
        imgLugar.setImageResource(R.drawable.plantita);
    }
    public void LimpiarBtn(View view){
        Limpiar();
    }
}
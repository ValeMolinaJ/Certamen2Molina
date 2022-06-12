package com.example.certamen2molina;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
/* Valentina Molina Jara
    19.987.243-5 */
public class ArrayAdapterPlanta extends ArrayAdapter<classPlanta> {
    View vistaP;

    public ArrayAdapterPlanta(@NonNull Context context, int resource, @NonNull List<classPlanta> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vistaP = convertView;
        Bitmap foto;
        if(null==convertView){
            vistaP = inflater.inflate(R.layout.lista_plantas, parent, false);
        }
        TextView codP = (TextView)vistaP.findViewById(R.id.txtCodPlant);
        TextView nombreP = (TextView)vistaP.findViewById(R.id.txtNombreP);
        TextView nomPc = (TextView)vistaP.findViewById(R.id.txtNomCp);
        ImageView imagen = (ImageView)vistaP.findViewById(R.id.imgListaP);
        TextView desc = (TextView)vistaP.findViewById(R.id.txtDesc);

        classPlanta item = getItem(position);
        codP.setText("Codigo: "+String.valueOf(item.getCodPlanta()));
        nombreP.setText("Nombre: "+item.getNombrePlanta());
        nomPc.setText("Nombre cientifico: "+item.getNomCientificoP());
        foto = BitmapFactory.decodeByteArray(item.getImgPlanta(), 0, item.getImgPlanta().length);
        imagen.setImageBitmap(foto);
        desc.setText("Descripcion: "+item.getDescripcion());
        return vistaP;
    }
}

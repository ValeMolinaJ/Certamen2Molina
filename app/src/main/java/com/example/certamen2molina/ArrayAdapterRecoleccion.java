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
public class ArrayAdapterRecoleccion extends ArrayAdapter<classRecoleccion> {
View v;

    public ArrayAdapterRecoleccion(@NonNull Context context, int resource, @NonNull List<classRecoleccion> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = convertView;
        Bitmap foto;
        if(null==convertView){
            v = inflater.inflate(R.layout.lista_recolecciones, parent, false);
        }
        ImageView imagen = (ImageView) v.findViewById(R.id.imglistaR);
        TextView ident = (TextView) v.findViewById(R.id.txtIdent);
        TextView fecha = (TextView) v.findViewById(R.id.txtFechaReg);
        TextView planta = (TextView) v.findViewById(R.id.txtPlant);
        TextView cientifico = (TextView) v.findViewById(R.id.txtCientifico);
        TextView comentario = (TextView) v.findViewById(R.id.txtComent);
        TextView longitud = (TextView) v.findViewById(R.id.txtLong);
        TextView latitud = (TextView) v.findViewById(R.id.txtLat);

        classRecoleccion item = getItem(position);
        ident.setText("Codigo: "+String.valueOf(item.getIdentificador()));
        fecha.setText("Fecha: "+item.getFechaReg());
        planta.setText("Codigo Planta: "+String.valueOf(item.getCodPlanta()));
        cientifico.setText("Rut Cientifico: "+item.getRutCientifico());
        comentario.setText("Comentario: "+item.getComentario());
        longitud.setText("Longitud: "+String.valueOf(item.getLongitud()));
        latitud.setText("Latitud: "+String.valueOf(item.getLatitud()));

        foto = BitmapFactory.decodeByteArray(item.getFotoLugar(), 0, item.getFotoLugar().length);
        imagen.setImageBitmap(foto);

        return v;
    }
}

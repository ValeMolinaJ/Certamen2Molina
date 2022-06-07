package com.example.certamen2molina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/* Valentina Molina Jara
    19.987.243-5 */
public class BDMolina extends SQLiteOpenHelper {
    public BDMolina(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BDMolina(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public BDMolina(Context context) {
        super(context, "basecertamen", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE cientificosMolina" +
                "(rut TEXT  PRIMARY KEY, nombres TEXT, apellidos TEXT, sexo TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE plantasMolina" +
                "(codPlanta INT PRIMARY KEY, nombrePlanta TEXT, nomCientificoP TEXT, imgPlanta BLOB, descripcion TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE recoleccionMolina" +
                "(identificador INT PRIMARY KEY, fechaReg TEXT, codPlantita INT, rutCientifico TEXT ," +
                "comentario TEXT, fotoLugar BLOB, longitud REAL, latitud REAL, FOREIGN KEY (codPlantita) REFERENCES plantasMolina (codPlanta)," +
                "FOREIGN KEY (rutCientifico) REFERENCES cientificosMolina (rut))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*----------------------CIENTIFICOS------------------------------*/
    //Método para crear un registro.
    public boolean insertarDatosC(String rut, String nombres, String apellidos, String sexo) {
        boolean sw1 = true;
        //Abre la BD en modo escritura.
        SQLiteDatabase db = getWritableDatabase();
        //Verifica si la bd existe.
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("rut", rut);
            valores.put("nombres", nombres);
            valores.put("apellidos", apellidos);
            valores.put("sexo", sexo);
            try {
                db.insert("cientificosMolina", "", valores);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            onCreate(db);
        }
        return sw1;
    }

    //Método para editar un registro.
    public boolean editarDatosC(String rut, String nombres, String apellidos, String sexo) {
        boolean sw1 = true;
        //Abre la BD en modo escritura.
        SQLiteDatabase db = getWritableDatabase();
        //Verifica si la bd existe.
        if (db != null) {
            //se guarda el contenido del registro a grabar en objeto valores
            ContentValues valores = new ContentValues();
            valores.put("nombres", nombres);
            valores.put("apellidos", apellidos);
            valores.put("sexo", sexo);
            try {
                String[] args = new String[]{String.valueOf(rut)};
                //ACTUALIZA REGISTRO DE LA TABLA CON LOS VALORES
                db.update("cientificosMolina", valores, "RUT=?", args);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            sw1 = false;
        }
        return sw1;
    }

    //Método para eliminar un registro
    public boolean eliminarDatosC(String rut) {
        boolean sw1 = true;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String[] args = new String[]{String.valueOf(rut)};
                db.delete("cientificosMolina", "RUT=?", args);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            //onCreate(db);
            sw1 = false;
        }
        return sw1;
    }

    //Método para buscar un registro
    public classCientifico buscarDatos(String rutC) {
        SQLiteDatabase db = getReadableDatabase();
        classCientifico datos = new classCientifico();
        try {
            Cursor c = db.rawQuery("SELECT * FROM cientificosMolina WHERE RUT=" + rutC, null);
            if (c.moveToFirst()) {
                datos = new classCientifico(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                this.close();
                c.close();
                return datos;
            } else {
                this.close();
                c.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*----------------------PLANTAS------------------------------*/
    //Método para insertar datos de la planta.
    public boolean insertarDatosP(int codPlanta, String nombrePlanta, String nomCientificoP, byte[] imgPlanta, String descripcion) {
        boolean sw1 = true;
        //Abre la BD en modo escritura.
        SQLiteDatabase db = getWritableDatabase();
        //Verifica si la bd existe.
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("codPlanta", codPlanta);
            valores.put("nombrePlanta", nombrePlanta);
            valores.put("nomCientificoP", nomCientificoP);
            valores.put("imgPlanta", imgPlanta);
            valores.put("descripcion", descripcion);
            try {
                db.insert("plantasMolina", "imgPlanta", valores);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            onCreate(db);
        }
        return sw1;
    }

    //Método para editar datos de la planta.
    public boolean editarDatosP(int codPlanta, String nombrePlanta, String nomCientificoP, byte[] imgPlanta, String descripcion) {
        boolean sw1 = true;
        //Abre la BD en modo escritura.
        SQLiteDatabase db = getWritableDatabase();
        //Verifica si la bd existe.
        if (db != null) {
            //se guarda el contenido del registro a grabar en objeto valores
            ContentValues valores = new ContentValues();
            valores.put("nombrePlanta", nombrePlanta);
            valores.put("nomCientificoP", nomCientificoP);
            valores.put("imgPlanta", imgPlanta);
            valores.put("descripcion", descripcion);
            try {
                String[] args = new String[]{String.valueOf(codPlanta)};
                //ACTUALIZA REGISTRO DE LA TABLA CON LOS VALORES
                db.update("plantasMolina", valores, "CODP=?", args);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            sw1 = false;
        }
        return sw1;
    }

    //Método para eliminar datos de la planta.
    public boolean eliminarDatosP(int codPlanta) {
        boolean sw1 = true;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String[] args = new String[]{String.valueOf(codPlanta)};
                db.delete("plantasMolina", "codPlanta=?", args);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            sw1 = false;
        }
        return sw1;
    }

    //Método para buscar una planta.
    public classPlanta buscarDatosP(int codPlanta) {
        SQLiteDatabase db = getReadableDatabase();
        classPlanta datos = new classPlanta();
        try {
            Cursor c = db.rawQuery("SELECT * FROM plantasMolina WHERE codPlanta=" + codPlanta, null);
            if (c.moveToFirst()) {
                datos = new classPlanta(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3), c.getString(4));
                this.close();
                c.close();
                return datos;
            } else {
                this.close();
                c.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*----------------------RECOLECCIÓN------------------------------*/
    //Método para insertar una Recolección.
    public boolean insertarDatosR(int identificador, String fechaReg, int codPlant, String rutCientifico,
                                  String comentario, byte[] fotoLugar, float longitud, float latitud) {
        boolean sw1 = true;
        //Abre la BD en modo escritura.
        SQLiteDatabase db = getWritableDatabase();
        //Verifica si la bd existe.
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("identificador", identificador);
            valores.put("fechaReg", fechaReg);
            valores.put("codPlantita", codPlant);
            valores.put("rutCientifico", rutCientifico);
            valores.put("comentario", comentario);
            valores.put("fotoLugar", fotoLugar);
            valores.put("longitud", longitud);
            valores.put("latitud", latitud);
            try {
                db.insert("recoleccionMolina", "fotoLugar", valores);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            onCreate(db);
        }
        return sw1;
    }

    //Método para editar una Recolección.
    public boolean editarDatosR(int identificador, String fechaReg, int codPlant, String rutCientifico, String comentario, byte[] fotoLugar, float longitud, float latitud) {
        boolean sw1 = true;
        //Abre la BD en modo escritura.
        SQLiteDatabase db = getWritableDatabase();
        //Verifica si la bd existe.
        if (db != null) {
            //se guarda el contenido del registro a grabar en objeto valores
            ContentValues valores = new ContentValues();
            valores.put("fechaReg", fechaReg);
            valores.put("codPlantita", codPlant);
            valores.put("rutCientifico", rutCientifico);
            valores.put("comentario", comentario);
            valores.put("fotoLugar", fotoLugar);
            valores.put("longitud", longitud);
            valores.put("latitud", latitud);
            try {
                String[] args = new String[]{String.valueOf(identificador)};
                //ACTUALIZA REGISTRO DE LA TABLA CON LOS VALORES
                db.update("recoleccionMolina", valores, "identificador=?", args);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            sw1 = false;
        }
        return sw1;
    }

    //Método para eliminar una Recolección.
    public boolean eliminarDatosR(int identificador) {
        boolean sw1 = true;
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            try {
                String[] args = new String[]{String.valueOf(identificador)};
                db.delete("recoleccionMolina", "identificador=?", args);
                db.close();
            } catch (Exception e) {
                db.close();
                sw1 = false;
            }
        } else {
            sw1 = false;
        }
        return sw1;
    }

    //Método para buscar una recolección.
    public classRecoleccion buscarDatosR(int identificador) {
        SQLiteDatabase db = getReadableDatabase();
        classRecoleccion datos = new classRecoleccion();
        try {
            Cursor c = db.rawQuery("SELECT * FROM recoleccionMolina WHERE identificador=" + identificador, null);
            if (c.moveToFirst()) {
                datos = new classRecoleccion(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getString(4),
                        c.getBlob(5), c.getFloat(6), c.getFloat(7));
                this.close();
                c.close();
                return datos;
            } else {
                this.close();
                c.close();
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    //Método que lista los códigos.
    public List<classPlanta> listarclassPlanta() {
        SQLiteDatabase bdm = getReadableDatabase();
        if (bdm != null) {
            List<classPlanta> lista_codigos = new ArrayList<classPlanta>();
            Cursor c = bdm.rawQuery("SELECT * FROM plantasMolina", null);
            if (c.moveToFirst()) {
                do {
                    classPlanta objCodP = new classPlanta(c.getInt(0), c.getString(1), c.getString(2), c.getBlob(3), c.getString(4));
                    lista_codigos.add(objCodP);
                } while (c.moveToNext());
                bdm.close();
                c.close();
                return lista_codigos;
            } else
                return null;
        } else
            return null;
    }
    //Método que lista los rut de cientificos.
    public List<classCientifico> listarclassCientificos() {
        SQLiteDatabase bdm = getReadableDatabase();
        if (bdm != null) {
            List<classCientifico> lista_Rut = new ArrayList<classCientifico>();
            Cursor c = bdm.rawQuery("SELECT * FROM cientificosMolina", null);
            if (c.moveToFirst()) {
                do {
                    classCientifico objRut = new classCientifico(c.getString(0), c.getString(1), c.getString(2), c.getString(3));
                    lista_Rut.add(objRut);
                } while (c.moveToNext());
                bdm.close();
                c.close();
                return lista_Rut;
            } else
                return null;
        } else
            return null;
    }
    public List<classRecoleccion> listarRecoleccionRut(String rut) {
        SQLiteDatabase db = getReadableDatabase();
        if (db != null) {
            List<classRecoleccion> listarecolec = new ArrayList<classRecoleccion>();
            Cursor c = db.rawQuery("SELECT * FROM recoleccionMolina WHERE rutCientifico='" + rut + "'", null);
            if (c.moveToFirst()) {
                do {
                    classRecoleccion r = new classRecoleccion(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getString(4), c.getBlob(5), c.getFloat(6), c.getFloat(7));
                    listarecolec.add(r);
                } while (c.moveToNext());
                db.close();
                c.close();
                return listarecolec;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}

package com.example.certamen2molina;
/* Valentina Molina Jara
    19.987.243-5 */
public class classRecoleccion {
    private int identificador;
    private String fechaReg;
    private int codPlanta;
    private String rutCientifico;
    private String comentario;
    private byte[] fotoLugar;
    private float longitud;
    private float latitud;

    public classRecoleccion() {
    }

    public classRecoleccion(int identificador, String fechaReg, int codPlanta, String rutCientifico, String comentario, byte[] fotoLugar, float longitud, float latitud) {
        this.identificador = identificador;
        this.fechaReg = fechaReg;
        this.codPlanta = codPlanta;
        this.rutCientifico = rutCientifico;
        this.comentario = comentario;
        this.fotoLugar = fotoLugar;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public int getIdentificador() {
        return identificador;
    }
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }
    public String getFechaReg() {
        return fechaReg;
    }
    public void setFechaReg(String fechaReg) {
        this.fechaReg = fechaReg;
    }
    public int getCodPlanta() {
        return codPlanta;
    }
    public void setCodPlanta(int codPlanta) {
        this.codPlanta = codPlanta;
    }
    public String getRutCientifico() {
        return rutCientifico;
    }
    public void setRutCientifico(String rutCientifico) {
        this.rutCientifico = rutCientifico;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public byte[] getFotoLugar() {
        return fotoLugar;
    }
    public void setFotoLugar(byte[] fotoLugar) {
        this.fotoLugar = fotoLugar;
    }
    public float getLongitud() {
        return longitud;
    }
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
    public float getLatitud() {
        return latitud;
    }
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }
}

package com.example.certamen2molina;
/* Valentina Molina Jara
    19.987.243-5 */
public class classPlanta {
    private int codPlanta;
    private String nombrePlanta;
    private String nomCientificoP;
    private byte[] imgPlanta;
    private String descripcion;

    public classPlanta() {
    }

    public classPlanta(int codPlanta, String nombrePlanta, String nomCientificoP, byte[] imgPlanta, String descripcion) {
        this.codPlanta = codPlanta;
        this.nombrePlanta = nombrePlanta;
        this.nomCientificoP = nomCientificoP;
        this.imgPlanta = imgPlanta;
        this.descripcion = descripcion;
    }
    public int getCodPlanta() {
        return codPlanta;
    }
    public void setCodPlanta(int codPlanta) {
        this.codPlanta = codPlanta;
    }
    public String getNombrePlanta() {
        return nombrePlanta;
    }
    public void setNombrePlanta(String nombrePlanta) {
        this.nombrePlanta = nombrePlanta;
    }
    public String getNomCientificoP() {
        return nomCientificoP;
    }
    public void setNomCientificoP(String nomCientificoP) {
        this.nomCientificoP = nomCientificoP;
    }
    public byte[] getImgPlanta() {
        return imgPlanta;
    }
    public void setImgPlanta(byte[] imgPlanta) {
        this.imgPlanta = imgPlanta;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


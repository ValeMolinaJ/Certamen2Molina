package com.example.certamen2molina;
/* Valentina Molina Jara
19.987.243-5 */
public class classCientifico {
    private String rut;
    private String nombres;
    private String apellidos;
    private String sexo;

    public classCientifico() {
    }

    public classCientifico(String rut, String nombres, String apellidos, String sexo) {
        this.rut = rut;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String toString(){
        return nombres;
    }
}

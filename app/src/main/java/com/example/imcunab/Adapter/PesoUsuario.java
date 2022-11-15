package com.example.imcunab.Adapter;

public class PesoUsuario {
    private String Id;
    private String Fecha;
    private String Altura;
    private String Peso;
    private String Imc;

    public PesoUsuario(){}

    public PesoUsuario(String id, String fecha, String altura, String peso, String imc) {
        Id = id;
        Fecha = fecha;
        Altura = altura;
        Peso = peso;
        Imc = imc;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getAltura() {
        return Altura;
    }

    public void setAltura(String altura) {
        Altura = altura;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getImc() {
        return Imc;
    }

    public void setImc(String imc) {
        Imc = imc;
    }

    @Override
    public String toString() {
        return "PesoUsuario{" +
                "Id='" + Id + '\'' +
                ", Fecha='" + Fecha + '\'' +
                ", Altura='" + Altura + '\'' +
                ", Peso='" + Peso + '\'' +
                ", Imc='" + Imc + '\'' +
                '}';
    }
}

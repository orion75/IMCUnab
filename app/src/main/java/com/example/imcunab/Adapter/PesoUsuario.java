package com.example.imcunab.Adapter;

public class PesoUsuario {
    private String Id;
    private String Fecha;
    private String Altura;
    private String Peso;

    public PesoUsuario(){}

    public PesoUsuario(String id, String fecha, String altura, String peso) {
        Id = id;
        Fecha = fecha;
        Altura = altura;
        Peso = peso;
    }

    @Override
    public String toString() {
        return "PesoUsuario{" +
                "Id='" + Id + '\'' +
                ", Fecha='" + Fecha + '\'' +
                ", Altura='" + Altura + '\'' +
                ", Peso='" + Peso + '\'' +
                '}';
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
}

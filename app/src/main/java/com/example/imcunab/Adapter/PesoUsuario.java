package com.example.imcunab.Adapter;

public class PesosUsuario {
    private String id;
    private String fecha;
    private String altura;
    private String peso;

    public PesosUsuario(String id, String fecha, String altura, String peso) {
        this.id = id;
        this.fecha = fecha;
        this.altura = altura;
        this.peso = peso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "PesosUsuario{" +
                "id='" + id + '\'' +
                ", fecha='" + fecha + '\'' +
                ", altura='" + altura + '\'' +
                ", peso='" + peso + '\'' +
                '}';
    }
}

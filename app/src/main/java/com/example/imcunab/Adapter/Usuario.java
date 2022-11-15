package com.example.imcunab.Adapter;

public class Usuario {
    private String FechaNacimiento;
    private String Nombres;
    private String Apellidos;

    public Usuario() {}

    public Usuario(String fechaNacimiento, String nombres, String apellidos) {
        FechaNacimiento = fechaNacimiento;
        Nombres = nombres;
        Apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String nombres) {
        Nombres = nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "FechaNacimiento='" + FechaNacimiento + '\'' +
                ", Nombres='" + Nombres + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                '}';
    }
}

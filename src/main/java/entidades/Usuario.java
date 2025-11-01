package entidades;

import java.util.ArrayList;

public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private ArrayList<Perfil> perfil;
    private Suscripcion suscripcion;
    private Empleado empleado;

    public Usuario(String nombreUsuario, String contrasena, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Perfil> getPerfil() {
        return new ArrayList<>(this.perfil);
    }
    public void addPerfil(Perfil perfil) {
        this.perfil.add(perfil);
    }
    public void removePerfil(Perfil perfil) {
        this.perfil.remove(perfil);
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }
    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }
}

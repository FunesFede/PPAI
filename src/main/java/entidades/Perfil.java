package entidades;

import java.util.ArrayList;

public class Perfil {
    private String descripcion;
    private String nombre;
    private ArrayList<Permiso> permisos;

    public Perfil(String descripcion, String nombre, ArrayList<Permiso> permisos) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.permisos = permisos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Permiso> getPermisos() {
        return permisos;
    }
    public void addPermisos(Permiso permiso) {
        this.permisos.add(permiso);
    }
    public void removePermisos(Permiso permiso) {
        this.permisos.remove(permiso);
    }
}

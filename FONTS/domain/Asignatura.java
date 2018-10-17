package domain;

import java.util.ArrayList;

import domain.Sesion;
import domain.Grupo;

/** Imports **/

public class Asignatura {

    /** Atributos **/

    private String id;
    private String nombre;
    private ArrayList sesiones;
    private ArrayList grupos;

    /** Constructoras **/

    public Asignatura(String id, String nom) {
        this.id = id;
        this.nombre = nom;
        this.sesiones = new ArrayList();
        this.grupos = new ArrayList();
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addSesion(Sesion sesion) {
        this.sesiones.add(sesion);
    }

    public void eliminarSesion(Integer posicion) {
        this.sesiones.remove(posicion);
    }

    public void addGrupo(Grupo grupo) {
        this.grupos.add(grupo);
    }

    public void eliminarGrupo(Integer posicion) {
        this.grupos.remove(posicion);
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public ArrayList getSesiones() {
        return this.sesiones;
    }

    public ArrayList getGrupos() {
        return this.grupos;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }

}
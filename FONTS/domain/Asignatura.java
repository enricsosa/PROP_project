package domain;

import java.util.ArrayList;

import domain.Sesion;
import domain.Grupo;

/** Imports **/

public class Asignatura {

    /** Atributos **/

    private string id;
    private string nombre;
    private ArrayList sesiones;
    private ArrayList grupos;

    /** Constructoras **/

    public Asignatura(string id, string nom) {
        this.id = id;
        this.nombre = nom;
        this.sesiones = new ArrayList();
        this.grupos = new ArrayList();
    }

    /** Métodos públicos **/

    public void setId(string id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addSesion(Session sesion) {
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

    public string getId() {
        return this.id;
    }

    public string getNombre() {
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
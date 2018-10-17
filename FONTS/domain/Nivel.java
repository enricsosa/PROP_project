package domain;

/** Imports **/

import java.util.Map;
import java.util.HashMap;

import domain.Asignatura;

public class Nivel {

    /** Atributos **/

    private String nombre;
    private Map<String, Asignatura> asignaturas;

    /** Constructoras **/

    public Nivel(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new HashMap<String, Asignatura>();
    }

    /** Métodos públicos **/

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getNombre(), asignatura);
    }

    public void replaceAsignatura(Asignatura asignatura) {
        this.asignaturas.replace(asignatura.getNombre(), asignatura);
    }

    public void eliminarAsignatura(String nombre) {
        this.asignaturas.remove(nombre);
    }

    /** Consultoras **/

    public String getNombre() {
        return this.nombre;
    }

    public Map<String, Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public Asignatura getAsignatura(String nom) {
        return this.asignaturasget(nom);
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }


}
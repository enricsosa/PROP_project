package domain;

/** Imports **/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Nivel {

    /** Atributos **/

    private String nombre;
    private Map<String, Asignatura> asignaturas;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Nivel(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new HashMap<String, Asignatura>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /** Métodos públicos **/

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    public void replaceAsignatura(Asignatura asignatura) {
        this.asignaturas.replace(asignatura.getId(), asignatura);
    }

    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    /** Consultoras **/

    public String getNombre() {
        return this.nombre;
    }

    public Map<String, Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public Asignatura getAsignatura(String nom) {
        return this.asignaturas.get(nom);
    }

    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.asignaturas.get(asignatura.getId()) != null);
    }

    public Boolean tieneAsignatura(String id) {
        return (this.asignaturas.get(id) != null);
    }

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    public Restriccion getRestriccion(int i) {
        return this.restricciones.get(i);
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }


}
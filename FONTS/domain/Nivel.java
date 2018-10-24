package domain;

/** Imports **/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Nivel {

    /** Atributos **/

    private String nombre;
    private Map<String, Asignatura> asignaturas;
    private ArrayList restricciones;

    /** Constructoras **/

    public Nivel(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new HashMap<String, Asignatura>();
        this.restricciones = new ArrayList();
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

    public void setRestricciones(ArrayList restricciones) {
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

    public ArrayList getRestricciones() {
        return this.restricciones;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }


}
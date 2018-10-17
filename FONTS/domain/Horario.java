package domain;

/** Imports **/

import java.util.ArrayList;

import domain.Asignacion;

public class Horario {

    /** Atributos **/

    private String id;
    private ArrayList assignaciones;

    /** Constructoras **/

    public Horario(String id) {
        this.id = id;
        this.assignaciones = new ArrayList();
    }

    /** Métodos públicos **/

    public void setId() {
        this.id = id;
    }

    public void addAsignacion(Asignacion asignacion) {
        this.assignaciones.add(asignacion);
    }

    public void removeAsignacion(Integer posicion) {
        this.assignaciones.remove(posicion);
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public ArrayList getAssignaciones() {
        return this.assignaciones;
    }

    /** Métodos redefinidos **/

}
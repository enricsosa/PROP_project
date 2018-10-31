package domain;

/** Imports **/

import java.util.ArrayList;

public class Horario {

    /** Atributos **/

    private String id;
    private ArrayList<Asignacion> assignaciones;

    /** Constructoras **/

    public Horario(String id) {
        this.id = id;
        this.assignaciones = new ArrayList<Asignacion>();
    }

    /** Métodos públicos **/

    public void setId() {
        this.id = id;
    }

    public void setAssignaciones(ArrayList<Asignacion> assignaciones) {
        this.assignaciones = assignaciones;
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

    public ArrayList<Asignacion> getAssignaciones() {
        return this.assignaciones;
    }

    /** Métodos redefinidos **/

}
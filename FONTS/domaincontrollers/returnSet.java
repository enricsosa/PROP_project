package domaincontrollers;

import domain.Asignacion;

import java.util.ArrayList;

/** Imports **/

public class returnSet {

    /** Atributos **/

    private Boolean validez;
    private ArrayList<Asignacion> asignaciones;

    /** Constructoras **/

    public returnSet(Boolean validez, ArrayList<Asignacion> asignaciones) {
        this.validez = validez;
        this.asignaciones = asignaciones;
    }

    /** Metodos públicos **/

    /** Consultoras **/

    public Boolean getValidez() {
        return this.validez;
    }

    public ArrayList<Asignacion> getAsignaciones() {
        return this.asignaciones;
    }

    /** Métodos redefinidos **/

}

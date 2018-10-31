package domaincontrollers;

import domain.Asignacion;

import java.util.ArrayList;

/** Imports **/

public class ReturnSet {

    /** Atributos **/

    private Boolean validez;
    private ArrayList<Asignacion> asignaciones;

    /** Constructoras **/

    public ReturnSet(Boolean validez, ArrayList<Asignacion> asignaciones) {
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
package domaincontrollers;

import domain.Asignacion;
import domain.Horario;

import java.util.ArrayList;

/** Imports **/

public class ReturnSet {

    /** Atributos **/

    private Boolean validez;
    private ArrayList<Asignacion> asignaciones;
    private Horario horario;

    /** Constructoras **/

    public ReturnSet(Boolean validez, ArrayList<Asignacion> asignaciones) {
        this.validez = validez;
        this.asignaciones = asignaciones;
        this.horario = null;
    }

    public ReturnSet(Boolean validez, Horario horario) {
        this.validez = validez;
        this.asignaciones = null;
        this.horario = horario;
    }

    /** Metodos públicos **/

    /** Consultoras **/

    public Boolean getValidez() {
        return this.validez;
    }

    public ArrayList<Asignacion> getAsignaciones() {
        return this.asignaciones;
    }

    public Horario getHorario() {
        return this.horario;
    }

    /** Métodos redefinidos **/

}
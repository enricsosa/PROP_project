package domaincontrollers;

import domain.Asignacion;
import domain.Horario;
import domain.Ocupaciones;

import java.util.ArrayList;

/** Imports **/

public class ReturnSet {

    /** Atributos **/

    private Boolean validez;
    private Ocupaciones ocupaciones;
    private Horario horario;

    /** Constructoras **/

    public ReturnSet(Boolean validez, Ocupaciones ocupaciones) {
        this.validez = validez;
        this.ocupaciones = ocupaciones;
        this.horario = null;
    }

    public ReturnSet(Boolean validez, Horario horario) {
        this.validez = validez;
        this.ocupaciones = null;
        this.horario = horario;
    }

    /** Metodos públicos **/

    /** Consultoras **/

    public Boolean getValidez() {
        return this.validez;
    }

    public Ocupaciones getOcupaciones() {
        return this.ocupaciones;
    }

    public Horario getHorario() {
        return this.horario;
    }

    /** Métodos redefinidos **/

}
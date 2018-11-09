package domaincontrollers;

import domain.Horario;
import domain.Ocupaciones;

/** Imports **/

public class ReturnSet {

    /** Atributos **/

    private Boolean validez;
    private Ocupaciones ocupaciones;
    private Horario horario;
    private int horaIni;
    private int horaFin;

    /** Constructoras **/

    public ReturnSet(Boolean validez) {
        this.validez = validez;
    }

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

    public ReturnSet(int horaIni, int horaFin) {
        this.horaIni = horaIni;
        this.horaFin = horaFin;
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

    public int getHoraIni() {
        return this.horaIni;
    }

    public int getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

}
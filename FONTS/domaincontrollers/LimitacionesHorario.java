package domaincontrollers;

import java.util.Arrays;

/** Imports **/

public class LimitacionesHorario {

    /** Atributos **/

    private Boolean[] diasLibres;
    private Integer horaIni;
    private Integer horaFin;

    /** Constructoras **/

    public LimitacionesHorario() {
        this.diasLibres = new Boolean[7];
        Arrays.fill(this.diasLibres, Boolean.FALSE);
        this.horaIni = 0;
        this.horaFin = 24;
    }

    public LimitacionesHorario(Boolean[] diasLibres, Integer horaIni, Integer horaFin) {
        this.diasLibres = diasLibres;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setDiasLibres(Boolean[] diasLibres) {
        this.diasLibres = diasLibres;
    }

    public void setDiaLibre(Integer dia, Boolean b) {
        this.diasLibres[dia - 1] = b;
    }

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Boolean[] getDiasLibres() {
        return diasLibres;
    }

    public Boolean esDiaLibre(int dia) {
        return this.diasLibres[dia - 1];
    }

    public Integer getHoraIni() {
        return horaIni;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    /** Métodos redefinidos **/
}

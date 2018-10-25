package domain;

/** Imports **/

import java.time.Clock;

public class Asignacion {

    /** Atributos **/

    private Integer horaIni;
    private Integer DiaSemana;
    private Aula aula;
    private Clase clase;

    /** Constructoras **/

    public Asignacion(Integer horaIni, Integer diaSemana, Aula aula, Clase clase) {
        this.horaIni = horaIni;
        this.DiaSemana = diaSemana;
        this.aula = aula;
        this.clase = clase;
    }

    /** Métodos públicos **/

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public void setDiaSemana(Integer diaSemana) {
        DiaSemana = diaSemana;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    /** Consultoras **/

    public Integer getHoraIni() {
        return this.horaIni;
    }

    public Integer getDiaSemana() {
        return this.DiaSemana;
    }

    public Integer getHoraFin() {
        return this.horaIni + this.clase.getSesion().getDuracion();
    }

    public Aula getAula() {
        return this.aula;
    }

    public Clase getClase() {
        return this.clase;
    }

    /** Métodos redefinidos **/

}
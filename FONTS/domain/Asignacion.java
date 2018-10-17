package domain;

/** Imports **/

import java.time.Clock;

import domain.Clase;
import domain.Aula;

public class Asignacion {

    /** Atributos **/

    private Clock horaIni;
    private Integer DiaSemana;
    private Aula aula;
    private Clase clase;

    /** Constructoras **/

    public Asignacion(Clock horaIni, Integer diaSemana, Aula aula, Clase clase) {
        this.horaIni = horaIni;
        this.DiaSemana = diaSemana;
        this.aula = aula;
        this.clase = clase;
    }

    /** Métodos públicos **/

    public void setHoraIni(Clock horaIni) {
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

    public Clock getHoraIni() {
        return this.horaIni;
    }

    public Integer getDiaSemana() {
        return this.DiaSemana;
    }

    public Aula getAula() {
        return this.aula;
    }

    public Clase getClase() {
        return this.clase;
    }

    /** Métodos redefinidos **/

}
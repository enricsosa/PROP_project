package domain;

/** Imports **/

import java.util.Arrays;

public class Ocupaciones {

    /** Atributos **/

    private Dia[] dias;

    /** Constructoras **/

    public Ocupaciones() {
        this.dias = new Dia[7];
        Arrays.fill(this.dias, new Dia());
    }

    public Ocupaciones(Ocupaciones oldOcupaciones) {
        this.dias = new Dia[7];
        for (int i = 0; i < 7; ++i) {
            this.dias[i] = new Dia(oldOcupaciones.getDia(i));
        }
    }

    /** Metodos publicos **/

    public void setDias(Dia[] dias) {
        this.dias = dias;
    }

    public void addAsignacion (Asignacion asignacion) {
        if (asignacion.tieneNivel()) {
            this.getDia(asignacion.getDiaSemana()).addNivel(asignacion.getNivel());

            for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
                this.getDia(asignacion.getDiaSemana()).getHora(hora).addNivel(asignacion.getNivel());
            }
        }
        this.getDia(asignacion.getDiaSemana()).addAsignatura(asignacion.getAsignatura());
        this.getDia(asignacion.getDiaSemana()).addAula(asignacion.getAula());
        this.getDia(asignacion.getDiaSemana()).addGrupo(asignacion.getGrupo());
        this.getDia(asignacion.getDiaSemana()).addSubGrupo(asignacion.getSubGrupo());
        this.getDia(asignacion.getDiaSemana()).addRestricciones(asignacion.getRestricciones());

        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAsignatura(asignacion.getAsignatura());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAula(asignacion.getAula());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addGrupo(asignacion.getGrupo());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addSubGrupo(asignacion.getSubGrupo());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addRestricciones(asignacion.getRestricciones());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAsignacion(asignacion);
        }
    }

    /** Consultoras **/

    public Dia[] getDias() {
        return this.dias;
    }

    public Dia getDia(int dia) {
        return this.dias[dia - 1];
    }

    public Hora getHora(int dia, int hora) {
        return this.dias[dia -1].getHora(hora);
    }

    /** Métodos redefinidos **/

}

package domain;

/** Imports **/

import java.util.Map;
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
        for (int i = 1; i <= 7; ++i) {
            this.dias[i - 1] = new Dia(oldOcupaciones.getDia(i));
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
        //this.getDia(asignacion.getDiaSemana()).addRestricciones(asignacion.getRestricciones());

        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAsignatura(asignacion.getAsignatura());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAula(asignacion.getAula());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addGrupo(asignacion.getGrupo());
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addSubGrupo(asignacion.getSubGrupo());
            //this.getDia(asignacion.getDiaSemana()).getHora(hora).addRestricciones(asignacion.getRestricciones());
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

    @Override
    public String toString() {
        String text = "";
        for (int dia = 1; dia <= 7; ++dia) {
            if (this.getDia(dia).getAsignaturas().size() > 0) {
                text += (spacer() + "\n" + spacer() + "\n" + getNombreDia(dia) + "\n");
                for (int hora = 0; hora < 24; ++hora) {
                    if (this.getHora(dia, hora).getAsignaciones().size() > 0) {
                        text += (spacer() + "\n" + getHoraCompleta(hora) + "-" + getHoraCompleta(hora + 1) + "\n" + lspacer() + "\n");
                        for (Map.Entry<String, Asignacion> entry : this.getHora(dia, hora).getAsignaciones().entrySet()) {
                            text += (entry.getValue().toString() + "\n");
                        }
                    }
                }
            }
        }
        text += spacer();
        return text;
    }

    /** Otros **/

    static String getNombreDia(int dia) {
        if (dia == 1) return "Lunes";
        else if (dia == 2) return "Martes";
        else if (dia == 3) return "Miercoles";
        else if (dia == 4) return "Jueves";
        else if (dia == 5) return "Viernes";
        else if (dia == 6) return "Sábado";
        else return "Domingo";
    }

    static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }

    static String spacer() {
        return "----------------------------------------------------------------------------------------------";
    }

    static String lspacer() {
        return "..............................................................................................";
    }
}

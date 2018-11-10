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

    @Override
    public String toString() {
        String text = "Limitaciones Horario:\n-Hora Ini: " + this.horaIni.toString() +"\n-Hora Fin: " + getHoraCompleta(this.horaFin)
                    + "\nDias Libres: ";
        for (int i = 0; i < 7; ++i) {
            if (this.diasLibres[i]) {
                text += getNombreDia(i + 1);
                if (i != 6) text += ", ";
            }
        }
        return text;
    }

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
}

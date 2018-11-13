/**LimitacionesHorario*/

/**Imports*/

package domaincontrollers;

import java.util.Arrays;

import domain.Aux;

/**
 * LimitacionesHorario mantiene información sobre la franja de trabajo y los dias que no se da Clase.
 * @author  Daniel Martín
 * @see     Arrays
 * @see     Aux
 */
public class LimitacionesHorario {

    /**Atributos*/

    /**Información de los dias que no se da Clase.*/
    private Boolean[] diasLibres;
    /**Hora a partir de la cual se da Clase.*/
    private Integer horaIni;
    /**Hora hasta la cual se da Clase.*/
    private Integer horaFin;

    /**Constructoras*/

    /**Constructora de LimitacionesHorario sin Resricciones.*/
    public LimitacionesHorario() {
        this.diasLibres = new Boolean[7];
        Arrays.fill(this.diasLibres, Boolean.FALSE);
        this.horaIni = 0;
        this.horaFin = 24;
    }

    /**
     * Constructora de la clase LimitacionesHorario indicando diasLibres, horaIni y horaFin.
     * @param diasLibres    Array de Boolean que se asigna a diasLibres.
     * @param horaIni       hora que se asigna a horaIni.
     * @param horaFin       hora que se asigna a horaFin.
     */
    public LimitacionesHorario(Boolean[] diasLibres, Integer horaIni, Integer horaFin) {
        this.diasLibres = diasLibres;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo Array de Boolean a diasLibres
     * @param diasLibres    Nuevo Array de Boolean que se asigna a diasLibres.
     */
    public void setDiasLibres(Boolean[] diasLibres) {
        this.diasLibres = diasLibres;
    }

    /**
     * Asigna un nuevo Boolean al dia indicado.
     * @param dia   dia de la semana al que se asigna el nuevo valor.
     * @param b     Boolean que se asigna al dia indicado.
     */
    public void setDiaLibre(Integer dia, Boolean b) {
        this.diasLibres[dia - 1] = b;
    }

    /**
     * Asigna un nuevo valor a horaIni.
     * @param horaIni   Nuevo valor que se asigna a horaIni.
     */
    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * Asigna un nuevo valor a horaFin.
     * @param horaFin   Nuevo valor que se asigna a horaFin.
     */
    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /**Consultoras*/

    /**
     * Devuelve un Array de Boolean con los dias libres.
     * @return  Array de Boolean con los dias libres.
     */
    public Boolean[] getDiasLibres() {
        return diasLibres;
    }

    /**
     * Devuelve si es DiaLibre el dia indicado.
     * @param dia   dia del que se quiere saber si es DiaLibre.
     * @return      true si es DiaLibre el dia indicado, false en caso contrario.
     */
    public Boolean esDiaLibre(int dia) {
        return this.diasLibres[dia - 1];
    }

    /**
     * Devuelve el valor de horaIni.
     * @return  Valor de horaIni.
     */
    public Integer getHoraIni() {
        return horaIni;
    }

    /**
     * Devuelve el valor de horaFin.
     * @return  Valor de horaFin.
     */
    public Integer getHoraFin() {
        return horaFin;
    }

    /**Métodos redefinidos*/

    /**
     * Convierte LimitacionesHorario a String con su información.
     * @return  String con la información de LimitacionesHorario.
     */
    @Override
    public String toString() {
        String text = "Limitaciones Horario:\n-Hora Ini: " + Aux.getHoraCompleta(this.horaIni) +"\n-Hora Fin: " + Aux.getHoraCompleta(this.horaFin)
                    + "\nDias Libres: ";
        for (int i = 0; i < 7; ++i) {
            if (this.diasLibres[i]) {
                text += Aux.getNombreDia(i + 1);
                if (i != 6) text += ", ";
            }
        }
        return text;
    }
}

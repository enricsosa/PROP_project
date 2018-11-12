/**Ocupaciones*/

/**Imports*/

package domain;

import java.util.Map;
import java.util.Arrays;

/**
 * Contiene la información de lo que ocurre las 24 horas de los 7 dias de una semana.
 * @author  Daniel Martín
 * @see     Map
 * @see     Arrays
 */
public class Ocupaciones {

    /**Atributos*/

    /**Conjunto de dias de una semana.*/
    private Dia[] dias;

    /**Constructoras*/

    /**Constructora básica de Ocupaciones.*/
    public Ocupaciones() {
        this.dias = new Dia[7];
        Arrays.fill(this.dias, new Dia());
    }

    /**
     * Constructora de la clase Ocupaciones que copia la información de otra Ocupaciones.
     * @param oldOcupaciones    Ocupaciones de la que se copia la información.
     */
    public Ocupaciones(Ocupaciones oldOcupaciones) {
        this.dias = new Dia[7];
        for (int i = 1; i <= 7; ++i) {
            this.dias[i - 1] = new Dia(oldOcupaciones.getDia(i));
        }
    }

    /**Metodos publicos*/

    /**
     * Añade una Asignación a Ocupaciones.
     * @param asignacion    Asignación que se quiere añadir a Ocupaciones
     */
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

    /**Consultoras*/

    /**
     * Devuelve el Array de Dia que contiene la información de Ocupaciones.
     * @return  Array de Dia que contiene la información de Ocupaciones.
     */
    public Dia[] getDias() {
        return this.dias;
    }

    /**
     * Devuelve el Dia de Ocupaciones correspondiente al dia dado.
     * @param dia   Dia que se quiere obtener.
     * @return      Dia correspondiente al numero del dia dado.
     */
    public Dia getDia(int dia) {
        return this.dias[dia - 1];
    }

    /**
     * Devuelve la Hora hora del Dia dia de Ocupaciones.
     * @param dia   Dia de la Hora que se quiere obtener.
     * @param hora  Hora que se quiere obtener.
     * @return      Hora hora del Dia dia de ocupaciones.
     */
    public Hora getHora(int dia, int hora) {
        return this.dias[dia -1].getHora(hora);
    }

    /** Métodos redefinidos **/

    /**
     * Convierte Ocupaciones a un String con la información que contiene.
     * @return  String con la información que contiene Ocupaciones.
     */
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

    /**
     * Obtiene el String con el nombre del dia de la Semana que corresponde a un int representando un dia.
     * @param dia   dia de la semana del que se quiere obtener el nombre.
     * @return      String con el nombre de dia.
     */
    static String getNombreDia(int dia) {
        if (dia == 1) return "Lunes";
        else if (dia == 2) return "Martes";
        else if (dia == 3) return "Miercoles";
        else if (dia == 4) return "Jueves";
        else if (dia == 5) return "Viernes";
        else if (dia == 6) return "Sábado";
        else return "Domingo";
    }

    /**
     * Tranforma un int representando una hora a un String que representa la hora de forma normativa.
     * @param hora  hora que se quiere convertir a String.
     * @return      Devuelve el String representando la hora introducida.
     */
    static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }

    /**
     * Devuelve un String con una linea de guiones.
     * @return  String con una linea de guiones.
     */
    static String spacer() {
        return "----------------------------------------------------------------";
    }

    /**
     * Devuelve un String con una linea de puntos.
     * @return  String con una linea de puntos.
     */
    static String lspacer() {
        return "................................................................";
    }
}

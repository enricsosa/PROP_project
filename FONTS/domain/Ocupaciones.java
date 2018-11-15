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
    public void addAsignacion(Asignacion asignacion) {
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAsignacion(asignacion);
        }
    }

    /**
     * Elimina una Asignación de Ocupaciones.
     * @param asignacion    Asignación que se quiere eliminar de Ocupaciones
     */
    public void eliminarAsignacion(Asignacion asignacion) {
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            this.getDia(asignacion.getDiaSemana()).getHora(hora).eliminarAsignacion(asignacion.generateKey());
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
                text += (Aux.spacer() + "\n" + Aux.spacer() + "\n" + Aux.getNombreDia(dia) + "\n");
                for (int hora = 0; hora < 24; ++hora) {
                    if (this.getHora(dia, hora).getAsignaciones().size() > 0) {
                        text += (Aux.spacer() + "\n" + Aux.getHoraCompleta(hora) + "-" + Aux.getHoraCompleta(hora + 1) + "\n" + Aux.lspacer() + "\n");
                        for (Map.Entry<String, Asignacion> entry : this.getHora(dia, hora).getAsignaciones().entrySet()) {
                            text += (entry.getValue().toString() + "\n");
                        }
                    }
                }
            }
        }
        text += Aux.spacer();
        return text;
    }
}

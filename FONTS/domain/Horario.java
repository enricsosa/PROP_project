/**Horario*/

/**Imports*/

package domain;

import java.util.Arrays;
import java.util.Map;

/**
 * Horario contiene las Asignaciones que lo definen y una id que lo identifica.
 * @author  Daniel Martín
 * @see     Arrays
 */
public class Horario {

    /**Atributos*/

    /**Código identificador del Horario.*/
    private String id;
    /**Conjunto de dias de una semana.*/
    private Dia[] dias;

    /**Constructoras*/

    /**
     * Contructora básica de la clase Horario.
     * @param id id que se asignará al Horario.
     */
    public Horario(String id) {
        this.id = id;
        this.dias = new Dia[7];
        for (int i = 0; i < 7; ++i) this.dias[i] = new Dia();
    }

    /**
     * Constructora de la clase Horario copia de otro Horario.
     * @param oldHorario Horario del que se copia la información del nuevo Horario.
     */
    public Horario(Horario oldHorario) {
        this.id = oldHorario.getId();
        this.dias = new Dia[7];
        for (int i = 1; i <= 7; ++i) {
            this.dias[i - 1] = new Dia(oldHorario.getDia(i));
        }
    }

    /**Métodos públicos*/

    /**
     * Añade una Asignación a Horario.
     * @param asignacion    Asignación que se quiere añadir a Horario.
     */
    public void addAsignacion(Asignacion asignacion) {
        //System.out.println(asignacion.getHoraIni());
        //System.out.println(asignacion.getHoraFin());
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            //System.out.println("Se añade a " +  Aux.getHoraCompleta(hora) + " de " + Aux.getNombreDia(asignacion.getDiaSemana()));

            this.getDia(asignacion.getDiaSemana()).getHora(hora).addAsignacion(asignacion);
        }
    }

    /**
     * Elimina una Asignación de Horario.
     * @param asignacion    Asignación que se quiere eliminar de Horario.
     */
    public void eliminarAsignacion(Asignacion asignacion) {
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            this.getHora(asignacion.getDiaSemana(), hora).eliminarAsignacion(asignacion);
        }
    }

    /**
     * Asigna una nueva id a Horario.
     * @param id Nueva id que se asignará a Horario.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna una nuevos Dias a Horario.
     * @param dias Dias que se asigna a Horario.
     */
    public void setDias(Dia[] dias) {
        this.dias = dias;
    }

    /**Consultoras*/

    /**
     * Devuelve la id del Horario en forma de String.
     * @return id del Horario.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve Dias del Horario.¡
     * @return Dias de Horario.
     */
    public Dia[] getDias() {
        return this.dias;
    }

    /**
     * Devuelve el Dia de Horario correspondiente al dia dado.¡
     * @param dia Dia que se quiere obtener.
     * @return Dia correspondiente al numero del dia dado.
     */
    public Dia getDia(int dia) {
        return this.dias[dia - 1];
    }

    /**
     * Devuelve la Hora hora del Dia dia de Horario.¡
     * @param dia  Dia de la Hora que se quiere obtener.
     * @param hora Hora que se quiere obtener.
     * @return Hora hora del Dia dia de Horario.
     */
    public Hora getHora(int dia, int hora) {
        return (this.dias[dia - 1]).getHora(hora);
    }

    /**Métodos redefinidos*/

    /**
     * Convierte Horario a un String con la información que contiene.
     * @return String con la información que contiene Horario.
     */
    @Override
    public String toString() {
        String text = "Horario: " +this.id +"\n";
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
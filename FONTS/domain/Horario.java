/**Horario*/

/**Imports*/

package domain;

/**
 * Horario contiene unas Ocupaciones que contiene las Asignaciones que lo definen y una id que lo identifica.
 * @æuthor Daniel Martín
 */
public class Horario {

    /**Atributos*/

    /**Código identificador del Horario.*/
    private String id;
    /**Ocupaciones que contiene las Asignaciones del Horario.*/
    private Ocupaciones ocupaciones;

    /**Constructoras*/

    /**
     * Contructora básica de la clase Horario.
     * @param id    id que se asignará al Horario.
     */
    public Horario(String id) {
        this.id = id;
        this.ocupaciones = new Ocupaciones();
    }

    /**
     * Constructora de la clase Horario con Ocupaciones.
     * @param id            id que se asignará al Horario.
     * @param ocupaciones   Ocupaciones que se asignará a Horario.
     */
    public Horario(String id, Ocupaciones ocupaciones) {
        this.id = id;
        this.ocupaciones = ocupaciones;
    }

    /**
     * Constructora de la clase Horario copia de otro Horario.
     * @param oldHorario    Horario del que se copia la información del nuevo Horario.
     */
    public Horario(Horario oldHorario) {
        this.id = oldHorario.getId();
        this.ocupaciones = new Ocupaciones(oldHorario.getOcupaciones());
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva id a Horario.
     * @param id    Nueva id que se asignará a Horario.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna una nueva Ocupaciones a Horario.
     * @param ocupaciones   Ocupaciones que se asigna a Horario.
     */
    public void setOcupaciones(Ocupaciones ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    /**Consultoras*/

    /**
     * Devuelve la id del Horario en forma de String.
     * @return  id del Horario.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve Ocupaciones del Horario.
     * @return  Ocupaciones de Horario.
     */
    public Ocupaciones getOcupaciones() {
        return this.ocupaciones;
    }

    /**
     * Devuelve el Dia dia de ocupaciones.
     * @param dia   Dia de ocupaciones que se quiere obtener.
     * @return      Dia asociado al numero dia de ocupaciones
     */
    public Dia getDia(int dia) {
        return this.ocupaciones.getDia(dia);
    }

    /**
     * Devuelve la Hora hora deel Dia dia de ocupaciones.
     * @param dia   Dia de ocupaciones de la Hora que se quiere obtener.
     * @param hora  Hora de dia que se quiere obtener.
     * @return      Hora asociada al Dia dia en la Hora hora.
     */
    public Hora getHora(int dia, int hora) {
        return this.ocupaciones.getHora(dia, hora);
    }

    /**Métodos redefinidos*/

    /**
     * Convierte Horario a un String que contiene toda su información.
     * @return  String con la información de Horario.
     */
    @Override
    public String toString() {
        return "Horario: " + this.id + "\n" + this.ocupaciones.toString();
    }

}
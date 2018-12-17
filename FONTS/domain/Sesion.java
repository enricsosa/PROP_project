/**Sesion*/

/**Imports*/

package domain;

import domaincontrollers.Aux;

/**
 * Sesion representa cada una de las sesiones semanales que realiza un alumno matricuylado en una Asignatura.
 * @author  Daniel Martín
 */
public class Sesion {

    /**Atributos*/

    /**Duracion en horas de la Sesion.*/
    private Integer duracion;
    /**TipoClase de la Sesion.*/
    private TipoClase tipo;
    /**Asignatura a la que pertenece la Sesion.*/
    private Asignatura asignatura;

    /**Constructoras*/

    /**
     * Constructora de la clase Sesion.
     * @param duracion      valor que se asigna al atributo duracion.
     * @param tipo          TipoClase que se asigna al atributo tipo.
     * @param asignatura    Asignatura que se asigna al atributo asignatura.
     */
    public Sesion(Integer duracion, TipoClase tipo, Asignatura asignatura) {
        this.duracion = duracion;
        this.tipo = tipo;
        this.asignatura = asignatura;
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva duracion a Sesion.
     * @param duracion  Nueva duración que se asigna a Sesion.
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * Asigna una nuevo TipoClase a Sesion.
     * @param tipo  Nuevo TipoClase que se asigna a Sesion.
     */
    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    /**
     * Asigna una nueva Asignatura a Sesion.
     * @param asignatura    Nueva Asignatura que se asigna a Sesion.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**Consultoras*/

    /**
     * Devuelve la duración de la Sesión en forma de Integer.
     * @return  duración de la Sesión en forma de Integer.
     */
    public Integer getDuracion() {
        return this.duracion;
    }

    /**
     * Devuelve el TipoClase de Sesion.
     * @return  TipoClase de Sesion.
     */
    public TipoClase getTipo() {
        return this.tipo;
    }

    /**
     * Devuelve la Asignatura de Sesion.
     * @return  Asignatura de Sesion.
     */
    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    /**Métodos redefinidos*/

    /**
     * Convierte Sesion a String que contiene su información.
     * @return  String que contiene la información de Sesion.
     */
    @Override
    public String toString() {
        return "Sesion:\n-Asignatura: " + this.asignatura.getId() + "\n-Tipo: " + Aux.strTipo(this.tipo) + "\n-Duracion: "
                + this.duracion + " horas";
    }

}
/**Asignacion*/

/**Imports*/

package domain;

import java.util.ArrayList;

/**
 * Asignacion contiene la informacion relativa a una Clase que se da en una Aula a una hora y dia determinados.
 * @author  Daniel Martín
 * @see     ArrayList
 */
public class Asignacion {

    /**Atributos*/

    /**Hora de inicio de la Asignacion.*/
    private Integer horaIni;
    /**Representa el dia de la semana en el cual se da la Asignacion.*/
    private Integer diaSemana;
    /**Representa el Aula donde se da la Asignacion.*/
    private Aula aula;
    /**Representa la Clase que se realiza en la Asignacion.*/
    private Clase clase;
    //** Arraylist que contiene todas la restricciones de los actores involucrados en la Asignacion.*/
    //private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Constructora de la clase Asignacion.
     * @param horaIni   hora a la que empieza la Asignacion.
     * @param diaSemana dia de la semana en que se produce la Asignacion.
     * @param aula      Aula en la que se produce la Asignacion.
     * @param clase     Clase que se realiza en la Asignacion.
     */
    public Asignacion(Integer horaIni, Integer diaSemana, Aula aula, Clase clase) {
        this.horaIni = horaIni;
        this.diaSemana = diaSemana;
        this.aula = aula;
        this.clase = clase;
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(clase.getRestricciones());
    }

    /**
     * Constructora que copia el contenido de otra Asignacion.
     * @param oldAsignacion Asignacion de la que se copia la información.
     */
    public Asignacion(Asignacion oldAsignacion) {
        this.horaIni = oldAsignacion.getHoraIni();
        this.diaSemana = oldAsignacion.getDiaSemana();
        this.aula = oldAsignacion.getAula();
        this.clase = oldAsignacion.getClase();
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(oldAsignacion.getRestricciones());
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva horaIni a Asignacion.
     * @param horaIni   Nueva horaIni que se asignará a la Asignacion.
     */
    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * Asigna un nuevo diaSemana a Asignacion.
     * @param diaSemana Nuevo diaSemana que se asignará a la Asignacion.
     */
    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    /**
     * Asigna una nueva Aula a Asignacion.
     * @param aula  Nueva Aula que se asignará a la Asignacion.
     */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**
     * Asigna una nueva Clase a Asignacion.
     * @param clase Nueva Clase que se asignará a la Asignacion.
     */
    public void setClase(Clase clase) {
        this.clase = clase;
    }

    ///**
     //* Asigna un nuevo conjunto de Restricciones a la Asignacion.
     //* @param restricciones Conjunto de Restricciones que se asignará a la Asignación.
    ////*/
    //public void setRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones = restricciones;
    //}

    //**
     //* Añade una Restriccion a las Restricciones de la Asignacion.
     //* @param restriccion   Restriccion que se añade a la Asignacion.
     //*/
    //public void addRestriccion(Restriccion restriccion) {
      //  this.restricciones.add(restriccion);
    //}

    //**
     //* Añade un conjunto de Restricciones a las Restricciones de la Asignacion.
     //* @param restricciones Conjunto de Restriccones que se añade a la Asignacion.
     //*/
    //public void addRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones.addAll(restricciones);
    //}

    ///**
     //* Elimina una Restriccion de la Asignacion en función de su posición.
    //* @param posicion  Posición de la Restrccion que se quiere eliminar.
     //*/
    //public void eliminarRestriccion(int posicion) {
      //  this.restricciones.remove(posicion);
    //}

    /**
     * Comprueva que todas las restricciones de una Asignacion se cumplen dado un Horario.
     * @param horario    Horario en función de las cuales se evaluan la restricciones.
     * @return           Devuelve true si se cumplen todas las Restricciones, false en caso contrario.
     */
    public Boolean comprobarRestricciones(Horario horario) {
        return this.clase.comprobarRestricciones(this.diaSemana, this.horaIni, horario);
    }

    /**
     * Comprueba que no existe conflicto entre SubGrupo y Aula involucrados.
     * @return  Devuelve true si no hay conflicto de plazas, false en caso contrario.
     */
    public Boolean noCabeSubGrupo() {
        return (this.clase.getPlazas() > this.aula.getPlazas());
    }

    /**
     * Genera una clave única para la Asignacion.
     * @return  Devuelve un String con la clave única de la Asignación.
     */
    public String generateKey() {
        return (this.clase.getAsignatura().getId()+this.clase.getSubGrupo().getIdCompleta()+this.getAula().getId())+Aux.strTipo(this.clase.getTipoSesion());
    }

    /**Consultoras*/

    /**
     * Devuelve un Integer con el valor de horaIni.
     * @return  Devuelve el atributo horaIni.
     */
    public Integer getHoraIni() {
        return this.horaIni;
    }

    /**
     * Devuelve un Integer con el valor de diaSemana.
     * @return  Devuelve el atributo diaSemana.
     */
    public Integer getDiaSemana() {
        return this.diaSemana;
    }

    /**
     * Devuelve un Integer con la hora a la que finaliza la Asignacion.
     * @return  Devuelve un la hora a la que finaliza la Asignacion.
     */
    public Integer getHoraFin() {
        return this.horaIni + this.clase.getSesion().getDuracion();
    }

    /**
     * Devuelve el Aula donde se realiza la Asignacion.
     * @return  Devuelve el atributo aula
     */
    public Aula getAula() {
        return this.aula;
    }

    /**
     * Devuelve el Grupo del Subgrupo asociado a la Clase de la Asignacion.
     * @return  Devuleve el Grupo involucrado en la Asignacion.
     */
    public Grupo getGrupo() {
        return this.clase.getGrupo();
    }

    /**
     * Devuelve el Subgrupo asociado a la Clase de la Asignacion.
     * @return  Devuelve el Subgrupo involucrado en la Asignacion.
     */
    public SubGrupo getSubGrupo() {
        return this.clase.getSubGrupo();
    }

    /**
     * Devuelve la Clase que se realiza en la Asignación.
     * @return  Devuelve el atributo clase.
     */
    public Clase getClase() {
        return this.clase;
    }

    /**
     * Devuelve las Restricciones de la Asignacion.
     * @return  Devuelve el atributo restricciones.
     */
    public ArrayList<Restriccion> getRestricciones() {
        return this.clase.getRestricciones();
    }

    /**
     * Devuelve una Restriccion de la Asignacion en función de su posición.
     * @param posicion  Posicion de la Restriccion dentro del Arraylist de Restricciones.
     * @return          Devuelve la Restricion que se encuentra en la posicion dada.
     */
    public Restriccion getRestriccion(int posicion) {
        return this.clase.getRestriccion(posicion);
    }

    /**
     * Devuelve la Asignatura asociada al Grupo del SubGrupo asociado a la Clase de la Asignación.
     * @return  Devuelve la Asignatura asociada a la Asignacion.
     */
    public Asignatura getAsignatura() {
        return this.clase.getAsignatura();
    }

    /**
     * Devuelve si la Asignatura asociada a la Asignacion tiene un Nivel asociado.
     * @return  Devuelve true si la Asignatura asociada a la Asignacion tiene nivel, false en caso contrario.
     */
    public Boolean tieneNivel() {
        return this.getAsignatura().tieneNivel();
    }

    /**
     * Devuelve el Nivel asociado a la Asignatura que interviene en la Asignacion.
     * @return  Devuelve el nivel que interviene en la Asignacion.
     */
    public Nivel getNivel() {
        return this.getAsignatura().getNivel();
    }

    /**
     * Devuelve un String con la información detallada sobre la Asignacion.
     * @return Devuelve una versión detallada de la Asignacion.
     */
    public String toStringCompleto() {
        String t, tipo, hi, hf;
        hi = "";
        hf = "";
        if (this.horaIni < 10) hi = "0";
        if (this.getHoraFin() < 10) hf = "0";
        if (this.clase.getSesion().getTipo() == TipoClase.Laboratorio) {
            t = "L";
            tipo = "Laboratorio";
        }
        else if (this.clase.getSesion().getTipo() == TipoClase.Teoria) {
            t = "T";
            tipo = "Teoria";
        }
        else {
            t = "P";
            tipo = "Problemas";
        }
        return this.clase.getSubGrupo().getGrupo().getAsignatura().getId() + " " + this.clase.getSubGrupo().getIdCompleta()
                + " " + t + "\n    Asignatura: " + this.clase.getSubGrupo().getGrupo().getAsignatura().getNombre()
                + "\n    Grupo: " + this.clase.getSubGrupo().getIdCompleta() + "\n    Aula: " + this.aula.getId()
                + "Tipo: " + tipo + "\n    HoraIni: " + hi + this.horaIni.toString() + ":00\n    HoraFin: " + hf
                + this.getHoraFin() + ":00\n";
    }

    /**Métodos redefinidos*/

    /**
     * Convierte la Asignacion en String.
     * @return  Devuelve un String con la información de la Asignacion.
     */
    @Override
    public String toString() {
        String tipo;
        String nivel = "";
        if (this.tieneNivel()) nivel = " (" + this.getNivel().getNombre() + ")";
        if (this.clase.getTipoSesion() == TipoClase.Teoria) tipo = "T";
        else if (this.clase.getTipoSesion() == TipoClase.Laboratorio) tipo = "L";
        else tipo = "P";
        return (this.getAsignatura().getId() + nivel + " " + this.getSubGrupo().getIdCompleta() + " " + tipo + " [" + this.getAula().getId() + "]");
    }

}
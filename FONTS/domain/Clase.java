/**Clase*/

/**Imports*/

package domain;

import java.util.ArrayList;

/**
 * Clase contiene la información de una Sesión asociada a un SubGrupo.
 * @author  Daniel Martín
 * @see     ArrayList
 */
public class Clase {

    /**Atributos*/

    /**Subgrupo que realiza la Clase.*/
    private SubGrupo subGrupo;
    /**Sesion que se realiza en la Clase*/
    private Sesion sesion;
    //**Conjunto de Restricciones formado por las Restricciones de la Asignatura y el Nivel (si tiene) del Grupo.*/
    //private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Constructora de la clase Clase.
     * @param subGrupo  Subgrupo que se asigna a la Clase.
     * @param sesion    Sesion que se da en la Clase.
     */
    public Clase(SubGrupo subGrupo, Sesion sesion) {
        this.subGrupo = subGrupo;
        this.sesion = sesion;
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(subGrupo.getGrupo().getRestricciones());
        //this.restricciones.addAll(subGrupo.getAsignatura().getRestricciones());
        //if (subGrupo.getAsignatura().tieneNivel()) this.restricciones.addAll(subGrupo.getAsignatura().getNivel().getRestricciones());
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo SubGrupo a la Clase.
     * @param subGrupo  SubGrupo que se quiere asignar a la Clase.
     */
    public void setSubGrupo(SubGrupo subGrupo) {
        this.subGrupo = subGrupo;
    }

    /**
     * Asigna una nueva Sesion a la Clase.
     * @param sesion    Sesion que se quiere asignar a la Clase.
     */
    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    //**
    // * Asigna un nuevo conjunto de Restricciones a Clase.
    // * @param restricciones Nuevo conjunto de Restricciones que se asignará a Clase.
    // */
    //public void setRestricciones(ArrayList<Restriccion> restricciones) {
    //    this.restricciones = restricciones;
    //}

    //**
    // * Añade una Restriccion al conjunto de Restricciones de Clase.
    // * @param restriccion   Restriccion que se añade a las Restricciones de la Clase.
    // */
    //public void addRestriccion(Restriccion restriccion) {
    //    this.restricciones.add(restriccion);
    //}

    //**
    // * ELimina una Restriccion en funcion de su posicion dentro de las Restricciones de la Clase.
    // * @param posicion  posicion de la Restriccion que se quiere eliminar dentro de las Restricciones de la Clase.
     //*/
    //public void eliminarRestriccion(Integer posicion) {
    //    this.restricciones.remove(posicion);
    //}

    /**
     * Convierte la Clase a un String que contiene su información de forma resumida.
     * @return  Devuelve un String con la información de la Clase resumida.
     */
    public String toStringResumido() {
        return "Clase: " + this.getAsignatura().getId() + " " + this.getSubGrupo().getIdCompleta() + " "
                + this.getSubGrupo().getTipo().toString() + " " + this.getSesion().getDuracion().toString();
    }

    /**
     * Comprueva que todas las restricciones de una Clase se cumplen dadas un Horario, dia y horaIni.
     * @param dia           dia en el cual se evaluan las Restricciones.
     * @param horaIni       horaIni con la cual se evauan las restricciones.
     * @param horario       Horario en función del cual se evaluan la restricciones.
     * @return              Devuelve true si se cumplen todas las Restricciones, false en caso contrario.
     */
    public Boolean comprobarRestricciones(int dia, int horaIni, Horario horario) {
        ArrayList<Restriccion> restricciones = this.getRestricciones();
        for (int i = 0; i < restricciones.size(); ++i) {
            if (!(restricciones.get(i).comprobarRestriccion(this, dia, horaIni, horario))) return false;
        }
        return true;
    }


    /**Consultoras*/

    /**
     * Devuelve el SubGrupo de la Clase.
     * @return  SubGrupo de la Clase.
     */
    public SubGrupo getSubGrupo() {
        return this.subGrupo;
    }

    /**
     * Devuelve la Sesion de la Clase.
     * @return  Sesion de la Clase.
     */
    public Sesion getSesion() {
        return this.sesion;
    }

    /**
     * Devuelve el conjunto de Restricciones de la Clase.
     * @return  Restricciones de la Clase.
     */
    public ArrayList<Restriccion> getRestricciones() {
        ArrayList<Restriccion> restricciones = new ArrayList<Restriccion>();
        restricciones.addAll(subGrupo.getAsignatura().getRestricciones());
        if (subGrupo.getAsignatura().tieneNivel()) restricciones.addAll(subGrupo.getAsignatura().getNivel().getRestricciones());
        return restricciones;
    }

    /**
     * Devuelve una Restriccion de la Clase en funcion de una posicion dada.
     * @param posicion  posicion de la Restriccion dentro de las Restricciones de la Clase.
     * @return          Restriccion situada en la posicion indicada de las Restricciones de la Clase.
     */
    public Restriccion getRestriccion(int posicion) {
        return this.getRestricciones().get(posicion);
    }

    /**
     * Devuelve las plazas del SubGrupo de la Clase.
     * @return  plazas del SubGrupo de la Clase.
     */
    public int getPlazas() {
        return this.subGrupo.getPlazas();
    }

    /**
     * Devuelve el Grupo del SubGrupo de la Clase.
     * @return  Grupo del SubGrupo de la Clase.
     */
    public Grupo getGrupo() {
        return this.subGrupo.getGrupo();
    }

    /**
     * Devuelve el TipoClase de la Sesion de la Clase.
     * @return  TipoClase de la Sesion de la Clase.
     */
    public TipoClase getTipoSesion() {
        return this.sesion.getTipo();
    }

    /**
     * Devuelve la Asignatura del Grupo del SubGrupo de la Clase.
     * @return  Asignatura del Grupo del SubGrupo de la Clase.
     */
    public Asignatura getAsignatura() {
        return this.subGrupo.getAsignatura();
    }

    /**
     * Indica si la Asignatura de la Clase tiene Nivel.
     * @return  true si la Asignatura de la Clase tiene Nivel, false en caso contrario.
     */
    public Boolean tieneNivel() {
        return this.getAsignatura().tieneNivel();
    }

    /**
     * Devuelve el Nivel de la Asignatura del Grupo del SubGrupo de la Clase.
     * @return  Nivel de la Asignatura del Grupo del SubGrupo de la Clase.
     */
    public Nivel getNivel() {
        return this.getAsignatura().getNivel();
    }

    /**
     * Devuelve la duracion de la Sesion de la Clase.
     * @return  duracion de la Sesion de la Clase.
     */
    public Integer getDuracion() {
        return this.sesion.getDuracion();
    }

    /**Métodos redefinidos*/

    /**
     * Convierte la Clase a un String que contiene su información.
     * @return Devuelve un String con la información de la Clase.
     */
    @Override
    public String toString() {
        return ("Clase:\n-Asignatura: " + this.getAsignatura().getId() + "\n-Grupo: " + this.getGrupo().getId() + "\n-SubGrupo: "
                + this.getSubGrupo().getId() + "\n-" + this.sesion.toString());
    }

}
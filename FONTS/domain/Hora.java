/**Hora*/

/**Imports*/

package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Hora contiene la información de lo que sucede una Hora dentro de Dia.
 * @author  Daniel Martín
 * @see HashMap
 * @see Map
 */
public class Hora {

    /**Atributos*/

    /**Asignaciones que transcurren en Hora.*/
    private Map<String, Asignacion> asignaciones;
    //private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**Constructora básica de la clase Hora.*/
    public Hora() {
        this.asignaciones = new HashMap<String, Asignacion>();
        //this.restricciones = new ArrayList<Restriccion>();
    }

    /**
     * Constructora de la clase Hora que genera una copia de otra Hora.
     * @param oldHora   Hora de la cual se copian los datos.
     */
    public Hora(Hora oldHora) {
        this.asignaciones = new HashMap<String, Asignacion>();
        this.asignaciones.putAll(oldHora.asignaciones);
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(oldHora.restricciones);
    }

    /**Metodos publicos*/

    /**
     * Asigna un nuevo conjunto de Asignaciones a una Hora.
     * @param asignaciones  Nuevo conjunto de Asignaciones que se asigna a Hora.
     */
    public void setAsignaciones(Map<String, Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    /**
     * Añade una Asignacion al conjunto de Grupos de Hora.
     * @param asignacion    Asignacion que se quiere añadir a Hora.
     */
    public void addAsignacion(Asignacion asignacion) {
        this.asignaciones.putIfAbsent(asignacion.generateKey(), asignacion);
    }

    //public void replaceAsignacion(Asignacion asignacion) {
    //    this.asignaciones.replace(asignacion.generateKey(), asignacion);
    //}

    /**
     * Elimina una Asignacion de Hora a partir de su key.
     * @param key   key de la Asignacion que se quiere eliminar.
     */
    public void eliminarAsignacion(String key) {
        this.asignaciones.remove(key);
    }

    //public void setRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones = restricciones;
    //}

    //public void addRestriccion(Restriccion restriccion) {
      //  this.restricciones.add(restriccion);
    //}

    //public void addRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones.addAll(restricciones);
    //}

    //public void eliminarRestriccion(Integer posicion) {
      //  this.restricciones.remove(posicion);
    //}

    /**Consultoras*/

    /**
     * Devuelve los Niveles de Hora.
     * @return  ArrayList con los Niveles de Hora.
     */
    public Map<String, Nivel> getNiveles() {
        Map<String, Nivel> niveles = new HashMap<String, Nivel>();
        for (Map.Entry<String, Asignacion> entry : this.asignaciones.entrySet()) {
            if (entry.getValue().tieneNivel()) niveles.putIfAbsent(entry.getValue().getNivel().getNombre(), entry.getValue().getNivel());
        }
        return niveles;
    }

    /**
     * Devuelve un Nivel de Hora dado su nombre.
     * @param nombre    nombre del nivel que se quiere obtener.
     * @return          Nivel de Hora con el nombre indicado.
     */
    public Nivel getNivel(String nombre) {
        return this.getNiveles().get(nombre);
    }

    /**
     * Indica si en Hora hay un Nivel.
     * @param nivel Nivel que queremos saber si tiene Hora.
     * @return      true si dia tiene nivel, false en caso contrario.
     */
    public Boolean tieneNivel(Nivel nivel) {
        return (this.getNiveles().get(nivel.getNombre()) != null);
    }

    /**
     * Devuelve el conjunto de Asignaturas que tiene Hora.
     * @return  ArrayList de Asignaturas que tiene Hora.
     */
    public Map<String, Asignatura> getAsignaturas() {
        Map<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();
        for (Map.Entry<String, Asignacion> entry : this.asignaciones.entrySet()) {
            asignaturas.putIfAbsent(entry.getValue().getAsignatura().getId(), entry.getValue().getAsignatura());
        }
        return asignaturas;
    }

    /**
     * Devuelve una Asignatura con el nombre indicado.
     * @param id    nombre de la Asignatura que se quiere obtener.
     * @return          Devuelve la Asignatura con le nombre indicado.
     */
    public Asignatura getAsignatura(String id) {
        return this.getAsignaturas().get(id);
    }

    /**
     * Indica si asignatura se encuentra en Hora.
     * @param asignatura    Asignatura que se quiere comprovar si está en Hora.
     * @return              true si asignatura se encuentra en Hora, false en caso contrario.
     */
    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.getAsignaturas().get(asignatura.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Aulas que tiene Hora.
     * @return  ArrayList con las Aulas de Hora.
     */
    public Map<String, Aula> getAulas() {
        Map<String, Aula> aulas = new HashMap<String, Aula>();
        for (Map.Entry<String, Asignacion> entry : this.asignaciones.entrySet()) {
            aulas.putIfAbsent(entry.getValue().getAula().getId(), entry.getValue().getAula());
        }
        return aulas;
    }

    /**
     * Devuelve un Aula de Hora a partir de la id de la misma.
     * @param id    id del Aula que se quiere obtener.
     * @return      Aula de Hora con id igual al id dado.
     */
    public Aula getAula(String id) {
        return this.getAulas().get(id);
    }

    /**
     * Indica si una Aula se encuentra entre las Aulas de Hora.
     * @param aula  Aula de la que se quiere saber si está en Hora.
     * @return      true si aula se encuentra entre las Aulas de Hora, false en caso contrario.
     */
    public Boolean tieneAula(Aula aula) {
        return (this.getAulas().get(aula.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Grupos que tiene Hora.
     * @return  ArrayList que contiene los Grupos de Hora.
     */
    public Map<String, Grupo> getGrupos() {
        Map<String, Grupo> grupos = new HashMap<String, Grupo>();
        for (Map.Entry<String, Asignacion> entry : this.asignaciones.entrySet()) {
            grupos.putIfAbsent(entry.getValue().getGrupo().getId() + entry.getValue().getGrupo().getAsignatura().getId(), entry.getValue().getGrupo());
        }
        return grupos;
    }

    //public Grupo getGrupo(String id) {
      //  return this.grupos.get(id);
    //}

    /**
     * Indica si una Grupo se encuentra entre las Grupos de Hora.
     * @param grupo Grupo del que se quiere saber si está en Hora.
     * @return      true si grupo se encuentra entre los grupos de Hora, false en caso contrario.
     */
    public Boolean tieneGrupo(Grupo grupo) {
        return (this.getGrupos().get(grupo.getId() + grupo.getAsignatura().getId()) != null);
    }

    /**
     * Indica si Hora tiene un grupo con la id de grupo de asignatura.
     * @param asignatura    Asignatura de la cual es el grupo que se quiere comprovar si tiene Hora.
     * @param grupo         Grupo cuya id tiene el grupo que se quiere comprovar si tiene Hora.
     * @return              true si Hora tiene un grupo con la id de grupo de asignatura, false en caso contrario.
     */
    public Boolean tieneGrupo(Asignatura asignatura, Grupo grupo) {
        return (this.getGrupos().get(grupo.getId() + asignatura.getId()) != null);
    }

    /**
     * Devuelve el conjunto de SubGrupos que tiene Hora.
     * @return  ArrayList que contiene los SubGrupos de Hora.
     */
    public Map<String, SubGrupo> getSubGrupos() {
        Map<String, SubGrupo> subGrupos = new HashMap<String, SubGrupo>();
        for (Map.Entry<String, Asignacion> entry : this.asignaciones.entrySet()) {
            subGrupos.putIfAbsent(entry.getValue().getSubGrupo().getIdCompleta() + entry.getValue().getSubGrupo().getAsignatura().getId() + Aux.strTipo(entry.getValue().getSubGrupo().getTipo()), entry.getValue().getSubGrupo());
        }
        return subGrupos;
    }

    //public SubGrupo getSubGrupo(String id) {
    //    return this.subGrupos.get(id);
    //}

    //public SubGrupo getSubGrupo(SubGrupo subGrupo) {
     //   return this.subGrupos.get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()));
    //}

    /**
     * Indica si un SubGrupo se encuentra entre las SubGrupos de Hora.
     * @param subGrupo  SubGrupo del que se quiere saber si está en Hora.
     * @return          true si subGrupo se encuentra entre los subGrupos de Hora, false en caso contrario.
     */
    public Boolean tieneSubGrupo(SubGrupo subGrupo) {
        return (this.getSubGrupos().get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + Aux.strTipo(subGrupo.getTipo())) != null);
    }

    /**
     * Devuelve el conjunto de Asignaciones que tiene Hora.
     * @return  ArrayList que contiene las Asignaciones de Hora.
     */
    public Map<String, Asignacion> getAsignaciones() {
        return this.asignaciones;
    }

    /**
     * Devuelve una Asignacion de Hora a partir de la key de la misma.
     * @param key   id de la Asignacion que se quiere obtener.
     * @return      Asignacion de Hora con key igual a la key dada.
     */
    public Asignacion getAsignacion(String key) {
        return this.asignaciones.get(key);
    }

    //**
     //* Indica si una Asignacion se encuentra entre las Asignaciones de Hora.
     //* @param asignacion  Asignacion de la que se quiere saber si está en Hora.
     //* @return            true si asignacion se encuentra entre las Asignaciones de Hora, false en caso contrario.
     //*/
    //public Boolean tieneAsignacion(Asignacion asignacion) {
      //  return (this.asignaciones.get(asignacion.generateKey()) != null);
    //}

    //public ArrayList<Restriccion> getRestricciones() {
     //   return restricciones;
    //}

    //public Restriccion getRestriccion(int i) {
      //  return this.restricciones.get(i);
    //}

    /**Métodos redefinidos*/

}

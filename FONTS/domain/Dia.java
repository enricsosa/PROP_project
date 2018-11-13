/**Dia*/

/**Imports*/

package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Dia contiene la información de lo que sucede un dia dentro de Ocupaciones.
 * @author  Daniel Martín
 * @see     Arrays
 * @see     HashMap
 * @see     Map
 */
public class Dia {

    /**Atributos*/

    /**Niveles de los que se da Clase ese en Dia.*/
    private Map<String, Nivel> niveles;
    /**Asignaturas de las que se da Clase ese en Dia.*/
    private Map<String, Asignatura> asignaturas;
    /**Aulas en las que se da Clase ese en Dia.*/
    private Map<String, Aula> aulas;
    /**Grupos que dan Clase ese en Dia.*/
    private Map<String, Grupo> grupos;
    /**SubGrupos que dan Clase ese en Dia.*/
    private Map<String, SubGrupo> subGrupos;
    //private ArrayList<Restriccion> restricciones;
    /**Horas que pertenecen al Dia*/
    private Hora[] horas;

    /**Constructoras*/

    /**Constructora básica de la clase Dia.*/
    public Dia() {
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.grupos = new HashMap<String, Grupo>();
        this.subGrupos = new HashMap<String, SubGrupo>();
        //this.restricciones = new ArrayList<Restriccion>();
        this.horas = new Hora[24];
        Arrays.fill(this.horas, new Hora());
    }

    /**
     * Constructora de la clase Dia que genera una copia de otro dia.
     * @param oldDia    Dia del cual se copian los datos.
     */
    public Dia(Dia oldDia) {
        this.niveles = new HashMap<String, Nivel>();
        this.niveles.putAll(oldDia.niveles);
        this.asignaturas = new HashMap<String, Asignatura>();
        this.asignaturas.putAll(oldDia.asignaturas);
        this.aulas = new HashMap<String, Aula>();
        this.aulas.putAll(oldDia.aulas);
        this.grupos = new HashMap<String, Grupo>();
        this.grupos.putAll(oldDia.grupos);
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.subGrupos.putAll(oldDia.subGrupos);
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(oldDia.restricciones);
        this.horas = new Hora[24];
        for (int i = 0; i < 24; ++i) {
            this.horas[i] = new Hora(oldDia.getHora(i));
        }
    }

    /**Metodos publicos*/

    /**
     * Asigna un nuevo conjunto de Niveles a un Dia.
     * @param niveles   Nuevo conjunto de Niveles que se asigna a Dia.
     */
    public void setNiveles(Map<String, Nivel> niveles) {
        this.niveles = niveles;
    }

    /**
     * Añade un Nivel a los niveles de Dia.
     * @param nivel Nivel que se quiere añadir a Dia.
     */
    public void addNivel(Nivel nivel) {
        this.niveles.putIfAbsent(nivel.getNombre(), nivel);
    }

    //public void replaceNivel(Nivel nivel) {
    //  this.niveles.replace(nivel.getNombre(), nivel);
    //}

    /**
     * Elimina un Nivel con el nombre introducido de Dia.
     * @param nombre    nombre del nivel que se quiere eliminar.
     */
    public void eliminarNivel(String nombre) {
        this.niveles.remove(nombre);
    }

    /**
     * Asigna un nuevo conjunto de Asignaturas a un Dia.
     * @param asignaturas   Nuevo conjunto de Asignaturas que se asigna a Dia.
     */
    public void setAsignaturas(Map<String, Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * Añade una Asignatura a las Asignaturas de Dia.
     * @param asignatura    Asignatura que se quiere añadir a Dia.
     */
    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    //public void replaceAsignatura(Asignatura asignatura) {
    //  this.asignaturas.replace(asignatura.getId(), asignatura);
    //}

    /**
     * Se elimina la Asignatura con la id dada del conjunto de Asignaturas de Dia.
     * @param id    id de la Asignatura que se quiere eliminar.
     */
    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    /**
     * Asigna un nuevo conjunto de Aulas a un Dia.
     * @param aulas Nuevo conjunto de Aulas que se asigna a Dia.
     */
    public void setAulas(Map<String, Aula> aulas) {
        this.aulas = aulas;
    }

    /**
     * Añade una Aula al conjunto de Aulas de un Dia.
     * @param aula  Aula que se quiere añadir a Dia.
     */
    public void addAula(Aula aula) {
        this.aulas.putIfAbsent(aula.getId(), aula);
    }

    //public void replaceAula(Aula aula) {
    //  this.aulas.replace(aula.getId(), aula);
    //}

    /**
     * Elimina un Aula del conjunto de Aulas de Dia a partir de su id.
     * @param id    id del Aula que se quiere eliminar de Dia.
     */
    public void eliminarAula(String id) {
        this.aulas.remove(id);
    }

    /**
     * Asigna un nuevo conjunto de Grupos a un Dia.
     * @param grupos    Nuevo conjunto de Grupos que se asigna a Dia.
     */
    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    /**
     * Añade un Grupo al conjunto de Grupos de Dia.
     * @param grupo Grupo que se quiere añadir a Dia.
     */
    public void addGrupo(Grupo grupo) {
        this.grupos.putIfAbsent(grupo.getId() + grupo.getAsignatura().getId(), grupo);
    }

    //public void replaceGrupo(Grupo grupo) {
    //  this.grupos.replace(grupo.getId() + grupo.getAsignatura().getId(), grupo);
    //}

    /**
     * Elimina un Grupo de Dia a partir de su key.
     * @param key   key del Grupo que se quiere eliminar.
     */
    public void eliminarGrupo(String key) {
        this.grupos.remove(key);
    }

    /**
     * Asigna un nuevo conjunto de Subgrupos a un Dia.
     * @param subGrupos Nuevo conjunto de SubGrupos que se asigna a Dia.
     */
    public void setSubGrupos(Map<String, SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
    }

    /**
     * Añade un SubGrupo al conjunto de SubGrupos de Dia.
     * @param subGrupo  SubGrupo que se quiere añadir a Dia.
     */
    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + Aux.strTipo(subGrupo.getTipo()), subGrupo);
    }

    //public void replaceSubGrupo(SubGrupo subGrupo) {
    //  this.subGrupos.replace(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()), subGrupo);
    //}

    /**
     * Dada una key elimina un SubGrupo de Dia.
     * @param key   key del SubGrupo que se quiere eliminar.
     */
    public void eliminarSubGrupo(String key) {
        this.subGrupos.remove(key);
    }

    //public void setRestricciones(ArrayList<Restriccion> restricciones) {
        //this.restricciones = restricciones;
    //}

    //public void addRestriccion(Restriccion restriccion) {
    //    this.restricciones.add(restriccion);
    //}

    //public void addRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones.addAll(restricciones);
    //}

    //public void eliminarRestriccion(Integer posicion) {
      //  this.restricciones.remove(posicion);
    //}

    /**
     * Asigna un Array de Horas a Dia.
     * @param horas Array de Horas que se quiere asignar a Dia.
     */
    public void setHoras(Hora[] horas) {
        this.horas = horas;
    }

    /**
     * Asigna una Hora a la posicion i de un Array de Horas.
     * @param hora  Hora que se quiere asignar a la posicion i.
     * @param i     Posicion en el Array a la que se quiere asignar Hora.
     */
    public void setHora(Hora hora, int i) {
        this.horas[i] = hora;
    }

    /**Consultoras*/

    /**
     * Devuelve los Niveles de Dia.
     * @return  ArrayList con los Niveles de Dia.
     */
    public Map<String, Nivel> getNiveles() {
        return niveles;
    }

    /**
     * Indica si en Dia hay un Nivel.
     * @param nivel Nivel que queremos saber si tiene Dia.
     * @return      true si dia tiene nivel, false en caso contrario.
     */
    public Boolean tieneNivel(Nivel nivel) {
        return (this.niveles.get(nivel.getNombre()) != null);
    }

    /**
     * Devuelve un Nivel de Dia a partir de su nombre.
     * @param nombre    nombre del nivel que se queire obtener.
     * @return          Devuelve el Nivel con el nombre indicado.
     */
    public Nivel getNivel(String nombre) {
        return this.niveles.get(nombre);
    }

    /**
     * Devuelve el conjunto de Asignaturas que tiene Dia.
     * @return  ArrayList de Asignaturas que tiene Dia.
     */
    public Map<String, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    /**
     * Devuelve una Asignatura con el nombre indicado.
     * @param nombre    nombre de la Asignatura que se quiere obtener.
     * @return          Devuelve la Asignatura con le nombre indicado.
     */
    public Asignatura getAsignatura(String nombre) {
        return this.asignaturas.get(nombre);
    }

    /**
     * Indica si asignatura se encuentra en Dia.
     * @param asignatura    Asignatura que se quiere comprovar si está en Dia.
     * @return              true si asignatura se encuentra en Dia, false en caso contrario.
     */
    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.asignaturas.get(asignatura.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Aulas que tiene Dia.
     * @return  ArrayList con las Aulas de Dia.
     */
    public Map<String, Aula> getAulas() {
        return aulas;
    }

    /**
     * Devuelve un Aula de Dia a partir de la id de la misma.
     * @param id    id del Aula que se quiere obtener.
     * @return      Aula de Dia con id igual al id dado.
     */
    public Aula getAula(String id) {
        return this.aulas.get(id);
    }

    /**
     * Indica si una Aula se encuentra entre las Aulas de Dia.
     * @param aula  Aula de la que se quiere saber si está en Dia.
     * @return      true si aula se encuentra entre las Aulas de Dia, false en caso contrario.
     */
    public Boolean tieneAula(Aula aula) {
        return (this.aulas.get(aula.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Grupos que tiene Dia.
     * @return  ArrayList que contiene los Grupos de Dia.
     */
    public Map<String, Grupo> getGrupos() {
        return grupos;
    }

    /**
     * Indica si una Grupo se encuentra entre las Grupos de Dia.
     * @param grupo Grupo del que se quiere saber si está en Dia.
     * @return      true si grupo se encuentra entre los grupos de Dia, false en caso contrario.
     */
    public Boolean tieneGrupo(Grupo grupo) {
        return (this.grupos.get(grupo.getId() + grupo.getAsignatura().getId()) != null);
    }

    /**
     * Devuelve el conjunto de SubGrupos que tiene Dia.
     * @return  ArrayList que contiene los SubGrupos de Dia.
     */
    public Map<String, SubGrupo> getSubGrupos() {
        return subGrupos;
    }

    /**
     * Indica si un SubGrupo se encuentra entre las SubGrupos de Dia.
     * @param subGrupo  SubGrupo del que se quiere saber si está en Dia.
     * @return          true si subGrupo se encuentra entre los subGrupos de Dia, false en caso contrario.
     */
    public Boolean tieneSubGrupo(SubGrupo subGrupo) {
        return (this.subGrupos.get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + Aux.strTipo(subGrupo.getTipo())) != null);
    }

    //public ArrayList<Restriccion> getRestricciones() {
      //  return restricciones;
    //}

    //public Restriccion getRestriccion(int i) {
      //  return this.restricciones.get(i);
    //}

    /**
     * Devuelve el Array de Horas que tiene Dia.
     * @return  Array con las Horas de Dia.
     */
    public Hora[] getHoras() {
        return horas;
    }

    /**
     * Devuelve la Hora que se encuentra en una posición del Array de Horas de Dia.
     * @param hora  Posición de la Hora que se quiere obtener dentro del Array de Horas de Dia.
     * @return      Hora que se enceuntra en la posición hora dentro del Array de Horas de Dia.
     */
    public Hora getHora(int hora) {
        return this.horas[hora];
    }

    /**Métodos redefinidos*/

}
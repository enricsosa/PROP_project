/**Hora*/

/**Imports*/

package domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Hora contiene la información de lo que sucede una Hora dentro de Dia.
 * @æuthor  Daniel Martín
 * @see HashMap
 * @see Map
 */
public class Hora {

    /**Atributos*/

    /**Niveles de los que se da Clase ese en Hora.*/
    private Map<String, Nivel> niveles;
    /**Asignaturas de las que se da Clase ese en Hora.*/
    private Map<String, Asignatura> asignaturas;
    /**Aulas en las que se da Clase ese en Hora.*/
    private Map<String, Aula> aulas;
    /**Grupos que dan Clase ese en Hora.*/
    private Map<String, Grupo> grupos;
    /**SubGrupos que dan Clase ese en Hora.*/
    private Map<String, SubGrupo> subGrupos;
    /**Asignaciones que transcurren en Hora.*/
    private Map<String, Asignacion> asignaciones;
    //private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**Constructora básica de la clase Hora.*/
    public Hora() {
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.grupos = new HashMap<String, Grupo>();
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.asignaciones = new HashMap<String, Asignacion>();
        //this.restricciones = new ArrayList<Restriccion>();
    }

    /**
     * Constructora de la clase Hora que genera una copia de otra Hora.
     * @param oldHora   Hora de la cual se copian los datos.
     */
    public Hora(Hora oldHora) {
        this.niveles = new HashMap<String, Nivel>();
        this.niveles.putAll(oldHora.niveles);
        this.asignaturas = new HashMap<String, Asignatura>();
        this.asignaturas.putAll(oldHora.asignaturas);
        this.aulas = new HashMap<String, Aula>();
        this.aulas.putAll(oldHora.aulas);
        this.grupos = new HashMap<String, Grupo>();
        this.grupos.putAll(oldHora.grupos);
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.subGrupos.putAll(oldHora.subGrupos);
        this.asignaciones = new HashMap<String, Asignacion>();
        this.asignaciones.putAll(oldHora.asignaciones);
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(oldHora.restricciones);
    }

    /**Metodos publicos*/

    /**
     * Asigna un nuevo conjunto de Niveles a una Hora.
     * @param niveles   Nuevo conjunto de Niveles que se asigna a Hora.
     */
    public void setNiveles(Map<String, Nivel> niveles) {
        this.niveles = niveles;
    }

    /**
     * Añade un Nivel a los niveles de Hora.
     * @param nivel Nivel que se quiere añadir a Hora.
     */
    public void addNivel(Nivel nivel) {
        this.niveles.putIfAbsent(nivel.getNombre(), nivel);
    }

    //public void replaceNivel(Nivel nivel) {
    //    this.niveles.replace(nivel.getNombre(), nivel);
    //}

    /**
     * Elimina un Nivel con el nombre introducido de Hora.
     * @param nombre    nombre del nivel que se quiere eliminar.
     */
    public void eliminarNivel(String nombre) {
        this.niveles.remove(nombre);
    }

    /**
     * Asigna un nuevo conjunto de Asignaturas a una Hora.
     * @param asignaturas   Nuevo conjunto de Asignaturas que se asigna a Hora.
     */
    public void setAsignaturas(Map<String, Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    /**
     * Añade una Asignatura a las Asignaturas de Hora.
     * @param asignatura    Asignatura que se quiere añadir a Hora.
     */
    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    //public void replaceAsignatura(Asignatura asignatura) {
    //  this.asignaturas.replace(asignatura.getId(), asignatura);
    //}

    /**
     * Se elimina la Asignatura con la id dada del conjunto de Asignaturas de Hora.
     * @param id    id de la Asignatura que se quiere eliminar.
     */
    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    /**
     * Asigna un nuevo conjunto de Aulas a una Hora.
     * @param aulas Nuevo conjunto de Aulas que se asigna a Hora.
     */
    public void setAulas(Map<String, Aula> aulas) {
        this.aulas = aulas;
    }

    /**
     * Añade una Aula al conjunto de Aulas de un Hora.
     * @param aula  Aula que se quiere añadir a Hora.
     */
    public void addAula(Aula aula) {
        this.aulas.putIfAbsent(aula.getId(), aula);
    }

    //public void replaceAula(Aula aula) {
    //    this.aulas.replace(aula.getId(), aula);
    //}

    /**
     * Elimina un Aula del conjunto de Aulas de Hora a partir de su id.
     * @param id    id del Aula que se quiere eliminar de Hora.
     */
    public void eliminarAula(String id) {
        this.aulas.remove(id);
    }

    /**
     * Asigna un nuevo conjunto de Grupos a una Hora.
     * @param grupos    Nuevo conjunto de Grupos que se asigna a Hora.
     */
    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    /**
     * Añade un Grupo al conjunto de Grupos de Hora.
     * @param grupo Grupo que se quiere añadir a Hora.
     */
    public void addGrupo(Grupo grupo) {
        this.grupos.putIfAbsent(grupo.getId() + grupo.getAsignatura().getId(), grupo);
    }

    //public void replaceGrupo(Grupo grupo) {
    //    this.grupos.replace(grupo.getId() + grupo.getAsignatura().getId(), grupo);
    //}

    /**
     * Elimina un Grupo de Hora a partir de su key.
     * @param key   key del Grupo que se quiere eliminar.
     */
    public void eliminarGrupo(String key) {
        this.grupos.remove(key);
    }

    /**
     * Asigna un nuevo conjunto de Subgrupos a una Hora.
     * @param subGrupos Nuevo conjunto de SubGrupos que se asigna a Hora.
     */
    public void setSubGrupos(Map<String, SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
    }

    /**
     * Añade un SubGrupo al conjunto de SubGrupos de Hora.
     * @param subGrupo  SubGrupo que se quiere añadir a Hora.
     */
    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()), subGrupo);
    }

    //public void replaceSubGrupo(SubGrupo subGrupo) {
    //   this.subGrupos.replace(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()), subGrupo);
    //}

    /**
     * Dada una key elimina un SubGrupo de Hora.
     * @param key   key del SubGrupo que se quiere eliminar.
     */
    public void eliminarSubGrupo(String key) {
        this.subGrupos.remove(key);
    }

    /**
     * Asigna un nuevo conjunto de Asignaciones a una Hora.
     * @param asignaciones  Nuevo conjunto de Asignaciones que se asigna a Hora.
     */
    public void setAsignaciones(Map<String, Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    /**
     * Convierte un TipoClase a un String con su forma abreviada.
     * @param tipo  TipoClase que se quiere convertir.
     * @return  Devuelve un String con la forma abreviada del TipoClase.
     */
    static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
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
        return niveles;
    }

    /**
     * Devuelve un Nivel de Hora dado su nombre.
     * @param nombre    nombre del nivel que se quiere obtener.
     * @return          Nivel de Hora con el nombre indicado.
     */
    public Nivel getNivel(String nombre) {
        return this.niveles.get(nombre);
    }

    /**
     * Indica si en Hora hay un Nivel.
     * @param nivel Nivel que queremos saber si tiene Hora.
     * @return      true si dia tiene nivel, false en caso contrario.
     */
    public Boolean tieneNivel(Nivel nivel) {
        return (this.niveles.get(nivel.getNombre()) != null);
    }

    /**
     * Devuelve el conjunto de Asignaturas que tiene Hora.
     * @return  ArrayList de Asignaturas que tiene Hora.
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
     * Indica si asignatura se encuentra en Hora.
     * @param asignatura    Asignatura que se quiere comprovar si está en Hora.
     * @return              true si asignatura se encuentra en Hora, false en caso contrario.
     */
    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.asignaturas.get(asignatura.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Aulas que tiene Hora.
     * @return  ArrayList con las Aulas de Hora.
     */
    public Map<String, Aula> getAulas() {
        return aulas;
    }

    /**
     * Devuelve un Aula de Hora a partir de la id de la misma.
     * @param id    id del Aula que se quiere obtener.
     * @return      Aula de Hora con id igual al id dado.
     */
    public Aula getAula(String id) {
        return this.aulas.get(id);
    }

    /**
     * Indica si una Aula se encuentra entre las Aulas de Hora.
     * @param aula  Aula de la que se quiere saber si está en Hora.
     * @return      true si aula se encuentra entre las Aulas de Hora, false en caso contrario.
     */
    public Boolean tieneAula(Aula aula) {
        return (this.aulas.get(aula.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Grupos que tiene Hora.
     * @return  ArrayList que contiene los Grupos de Hora.
     */
    public Map<String, Grupo> getGrupos() {
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
        return (this.grupos.get(grupo.getId() + grupo.getAsignatura().getId()) != null);
    }

    /**
     * Indica si Hora tiene un grupo con la id de grupo de asignatura.
     * @param asignatura    Asignatura de la cual es el grupo que se quiere comprovar si tiene Hora.
     * @param grupo         Grupo cuya id tiene el grupo que se quiere comprovar si tiene Hora.
     * @return
     */
    public Boolean tieneGrupo(Asignatura asignatura, Grupo grupo) {
        return (this.grupos.get(grupo.getId() + asignatura.getId()) != null);
    }

    /**
     * Devuelve el conjunto de SubGrupos que tiene Hora.
     * @return  ArrayList que contiene los SubGrupos de Hora.
     */
    public Map<String, SubGrupo> getSubGrupos() {
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
        return (this.subGrupos.get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo())) != null);
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

    /**
     * Indica si una Asignacion se encuentra entre las Asignaciones de Hora.
     * @param asignacion  Asignacion de la que se quiere saber si está en Hora.
     * @return            true si asignacion se encuentra entre las Asignaciones de Hora, false en caso contrario.
     */
    public Boolean tieneAsignacion(Asignacion asignacion) {
        return (this.asignaciones.get(asignacion.generateKey()) != null);
    }

    //public ArrayList<Restriccion> getRestricciones() {
     //   return restricciones;
    //}

    //public Restriccion getRestriccion(int i) {
      //  return this.restricciones.get(i);
    //}

    /**Métodos redefinidos*/

}

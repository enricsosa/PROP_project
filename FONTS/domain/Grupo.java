/**Grupo*/

/**Imports*/

package domain;

//import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Grupo pertence a una Asignatura y tiene un conjunto de SubGrupos.
 * @author  Daniel Martín
 * @see     Map
 * @see     HashMap
 * @see     Collectors
 */
public class Grupo {

    /**Atributos*/

    /**Código identificador del Grupo dentro de su Asignatura.*/
    private String id;
    /**Asignatura a la que pertenece el Grupo.*/
    private Asignatura asignatura;
    /**Conjunto de SubGrupos del Grupo*/
    private Map<String, SubGrupo> subGrupos;
    //private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Constructora de la clase Grupo.
     * @param id            id que se asignará a Grupo.
     * @param asignatura    Asignatura que se asignará a Grupo.
     */
    public Grupo(String id, Asignatura asignatura) {
        this.id = id;
        this.asignatura = asignatura;
        this.subGrupos = new HashMap<String, SubGrupo>();
        //this.restricciones = new ArrayList<Restriccion>();
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo id a Grupo.
     * @param id    Nuevo id que se asignará a Grupo.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna una nueva Asignatura a Grupo.
     * @param asignatura    Nueva Asignatura que se asignará a Grupo.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Asigna un nuevo conjunto de SubGrupos a Grupo.
     * @param subGrupos Conjunto de SubGrupos que se asignarán a Grupo.
     */
    public void setSubGrupos(Map<String, SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
    }

    /**
     * Añade un SubGrupo al conjunto de SubGrupos de Grupo.
     * @param subGrupo  SubGrupo que se añade a los SubGrupos de Grupo.
     */
    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getKey(), subGrupo);
    }

    //public void replaceSubGrupo(SubGrupo subGrupo) {
    //    this.subGrupos.replace(subGrupo.getKey(), subGrupo);
    //}

    /**
     * Se elimina de los SubGrupos de Grupo el SubGrupo con la key indicada.
     * @param key   key identificadora del SubGrupo que se quiere eliminar del Grupo.
     */
    public void eliminarSubGrupo(String key) {
        this.subGrupos.remove(key);
    }

    //public void setRestricciones(ArrayList<Restriccion> restricciones) {
    //    this.restricciones = restricciones;
    //}

    //public void addRestriccion(Restriccion restriccion) {
    //    this.restricciones.add(restriccion);
    //}

    //public void eliminarRestriccion(Integer posicion) {
    //    this.restricciones.remove(posicion);
    //}

    /**Consultoras*/

    /**
     * Devuelve la id del Grupo.
     * @return  String con la id del Grupo.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve la Asignatura a la que pertenece el Grupo.
     * @return  Asignatura del Grupo.
     */
    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    /**
     * Devuelve el conjunto de SubGrupos de Grupo.
     * @return  Map con los SubGrupos de Grupo.
     */
    public Map<String, SubGrupo> getSubGrupos() {
        return this.subGrupos;
    }

    /**
     * Devuelve todos los Subgrupos de Teoria del Grupo
     * @return  Map con los SubGrupos de Teoria del Grupo.
     */
    public Map<String, SubGrupo> getSubGruposTeoria() {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == TipoClase.Teoria)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve todos los Subgrupos de Laboratorio del Grupo
     * @return  Map con los SubGrupos de Laboratorio del Grupo.
     */
    public Map<String, SubGrupo> getSubGruposLaboratorio() {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == TipoClase.Laboratorio)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve todos los Subgrupos de Problemas del Grupo
     * @return  Map con los SubGrupos de Problemas del Grupo.
     */
    public Map<String, SubGrupo> getSubGruposProblemas() {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == TipoClase.Problemas)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve el SubGrupo de Grupo con la key indicada.
     * @param key   key del SubGrupo que queremos obtener.
     * @return      SubGrupo de Grupo con la key indicada.
     */
    public SubGrupo getSubGrupo(String key) {
        return this.subGrupos.get(key);
    }

    //public SubGrupo getSubGrupo(SubGrupo subGrupo) {
      //  return this.subGrupos.get(subGrupo.getKey());
    //}

    //public ArrayList<Restriccion> getRestricciones() {
    //    return this.restricciones;
    //}

    /**
     * Indica si la Asigantura a la que pertenece Grupo pertenece a un Nivel.
     * @return  true si la Asigantura a la que pertenece Grupo pertenece a un Nivel, false en caso contrario.
     */
    public Boolean tieneNivel() {
        return this.asignatura.tieneNivel();
    }

    /**
     * Devuelve el Nivel de la Asignatura a la que pertence Grupo.
     * @return  Nivel del Grupo.
     */
    public Nivel getNivel() {
        return this.asignatura.getNivel();
    }

    /**Métodos redefinidos*/

    /**
     * Convierte Grupo a un String con su información.
     * @return  String con la información de Grupo.
     */
    @Override
    public String toString() {
        String text = "Grupo: " + this.id + "\n-Asignatura: " + this.asignatura.getId();
        text += "\n-SubGrupos:";
        for (Map.Entry<String, SubGrupo> entry : this.subGrupos.entrySet()) text += ("\n-- " + entry.getValue().toString());
        //text += "\n-Restricciones:";
        //for (int i = 0; i < this.restricciones.size(); ++i) text += ("\n-- " + this.restricciones.get(i).toString());
        return text;
    }

}
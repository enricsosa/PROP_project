/**Asignatura*/

/**Imports*/

package domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Asignatura contiene la informacion relativa a una Asignatura de un PlanEstudios.
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Map
 * @see     HashMap
 */
public class Asignatura {

    /**Atributos*/

    /**Codigo identifcador de la Asignatura.*/
    private String id;
    /**Nombre completo de la Asignatura.*/
    private String nombre;
    /**PlanEstudios al cual pertence la Asignatura.*/
    private PlanEstudios planEstudios;
    /**Nivel al cual pertenece la Asignatura.*/
    private Nivel nivel;
    /**Conjunto de Sesiones que se dan en la Asignatura.*/
    private ArrayList<Sesion> sesiones;
    /**Conjunto de Grupos que tiene la Asignatura.*/
    private Map<String, Grupo> grupos;
    /**Conjunto de Restricciones de la Asignatura.*/
    private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Constructora básica de Asignatura.
     * @param id            id de la Asignatura.
     * @param nom           nombre completo de la Asignatura
     * @param planEstudios  PlanEstudios al que pertenece la Asignatura.
     */
    public Asignatura(String id, String nom, PlanEstudios planEstudios) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.sesiones = new ArrayList<Sesion>();
        this.grupos = new HashMap<String, Grupo>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /**
     * Contructora básica de Asignatura con Nivel.
     * @param id            id de la Asignatura.
     * @param nom           nombre completo de la Asignatura
     * @param planEstudios  PlanEstudios al que pertenece la Asignatura.
     * @param nivel         Nivel al que pertenece la Asignatura.
     */
    public Asignatura(String id, String nom, PlanEstudios planEstudios, Nivel nivel) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.nivel = nivel;
        this.sesiones = new ArrayList<Sesion>();
        this.grupos = new HashMap<String, Grupo>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /**
     * Contructora de Asignatura con Sesiones y Grupos.
     * @param id            id de la Asignatura.
     * @param nom           nombre completo de la Asignatura
     * @param planEstudios  PlanEstudios al que pertenece la Asignatura.
     * @param sesiones      Conjunto de Sesiones que tiene la Asignatura.
     * @param grupos        Conjunto de Grupos asociados a la Asignatura.
     */
    public Asignatura(String id, String nom, PlanEstudios planEstudios, ArrayList<Sesion> sesiones, Map<String, Grupo> grupos) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.sesiones = sesiones;
        this.grupos = grupos;
        this.restricciones = new ArrayList<Restriccion>();
    }

    /**
     * Constructora de Asignatura con Nivel, Sesiones y Grupos.
     * @param id            id de la Asignatura.
     * @param nom           nombre completo de la Asignatura
     * @param planEstudios  PlanEstudios al que pertenece la Asignatura.
     * @param nivel         Nivel al que pertenece la Asignatura.
     * @param sesiones      Conjunto de Sesiones que tiene la Asignatura.
     * @param grupos        Conjunto de Grupos asociados a la Asignatura.
     */
    public Asignatura(String id, String nom, PlanEstudios planEstudios, Nivel nivel, ArrayList<Sesion> sesiones, Map<String, Grupo> grupos) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.nivel = nivel;
        this.sesiones = sesiones;
        this.grupos = grupos;
        this.restricciones = new ArrayList<Restriccion>();
    }

    /**Métodos públicos*/

    /**
     * Asigna una id a una Asignatura existente.
     * @param id    Nueva id que se asignará a Asignatura.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna un nuevo nombre a la Asignatura.
     * @param nombre    Nuevo nombre que se asignará a Asignatura.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna un nuevo PlanEstudios a la Asignatura.
     * @param planEstudios  Nuevo PlanEstudios que se asignará a Asignatura.
     */
    public void setPlanEstudios(PlanEstudios planEstudios) {
        this.planEstudios = planEstudios;
    }

    /**
     * Asigna un nuevo Nivel a la Asignatura.
     * @param nivel Nuevo Nivel que se asignará a Asignatura.
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /**
     * Asigna un nuevo conjunto de Sesiones a Asignatura.
     * @param sesiones  Nuevo conjunto de Sesiones que se asignará a Asignatura.
     */
    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    /**
     * Añade una Sesion al conjunto de Sesiones de la Asignatura.
     * @param sesion    Sesion que se quiere añadir al conjunto de Sesiones de la Asignatura.
     */
    public void addSesion(Sesion sesion) {
        this.sesiones.add(sesion);
    }

    /**
     * ELimina una Sesion en funcion de su posicion dentro de las Sesiones de la Asignatura.
     * @param posicion  posicion de la Restriccion que se quiere eliminar dentro de las Restricciones de la Asignatura.
     */
    public void eliminarSesion(Integer posicion) {
        this.sesiones.remove(posicion);
    }

    /**
     * Asigna un nuevo conjunto de Grupos a Asignatura.
     * @param grupos    Nuevo conjunto de Grupos que se asignará a Asignatura.
     */
    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    /**
     * Añade un Grupo al conjunto de Grupos de la Asignatura.
     * @param grupo Grupo que se quiere añadir al conjunto de Grupos de la Asignatura.
     */
    public void addGrupo(Grupo grupo) {
        this.grupos.putIfAbsent(grupo.getId(), grupo);
    }

    /**
     * Reemplaza un Grupo del conjunto de Grupos de la Asignatura con la misma id.
     * @param grupo Grupo con el cual se quiere reemplazar un Grupo existente.
     */
    public void replaceGrupo(Grupo grupo) {
        this.grupos.replace(grupo.getId(), grupo);
    }

    /**
     * Elimina un Grupo del conjunto de Grupos de la Asignatura que tiene la id dada.
     * @param id    id del grupo que se quiere eliminar.
     */
    public void eliminarGrupo(String id) {
        this.grupos.remove(id);
    }

    /**
     * Asigna un nuevo conjunto de Restricciones a Asignatura.
     * @param restricciones Nuevo conjunto de Restricciones que se asignará a Asignatura.
     */
    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    /**
     * Añade una Restriccion al conjunto de Restricciones de Asignatura.
     * @param restriccion   Restriccion que se añade a las Restricciones de la Asignatura.
     */
    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    /**
     * ELimina una Restriccion en funcion de su posicion dentro de las Restricciones de la Asignatura.
     * @param posicion  posicion de la Restriccion que se quiere eliminar dentro de las Restricciones de la Asignatura.
     */
    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    /**Consultoras*/

    /**
     * Devuelve un String con la id de la Asignatura.
     * @return  id de la Asignatura.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve un String con el nombre de la Asignatura.
     * @return  nombre de la Asignatura.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve el PlanEstudios al que pertenece la Asignatura.
     * @return  PlanEstudios de la Asignatura.
     */
    public PlanEstudios getPlanEstudios() {
        return this.planEstudios;
    }

    /**
     * Indica si la Asignatura pertence a un Nivel.
     * @return  Devuelve true si la Asignatura pertenece a un Nivel, false en caso contrario.
     */
    public Boolean tieneNivel() {
        return (this.nivel != null);
    }

    /**
     * Devuelve el Nivel de una Asignatura que pertence a un Nivel.
     * @return  Devuelve el Nivel de la Asignatura.
     */
    public Nivel getNivel() {
        return this.nivel;
    }

    /**
     * Devuelve el conjunto de Sesiones de la Asignatura.
     * @return  Devuelve las Sesiones de la Asignatura.
     */
    public ArrayList<Sesion> getSesiones() {
        return this.sesiones;
    }

    /**
     * Devuelve el conjunto de Grupos de la Asignatura.
     * @return  Devuelve los Grupos de la Asignatura.
     */
    public Map<String, Grupo> getGrupos() {
        return this.grupos;
    }

    /**
     * Devuelve un Grupo de la Asignatura dada su id.
     * @param id    id del Grupo que se quiere obtener.
     * @return      Grupo de la Asignatura con la id indicada.
     */
    public Grupo getGrupo(String id) {
        return this.grupos.get(id);
    }

    /**
     * Devuelve el conjunto de Restricciones de la Asignatura.
     * @return  Devuelve las Restricciones de la Asignatura.
     */
    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    /**
     * Devuelve todas las Clases que se obtienen de los Grupos y Sesiones de la Asignatura.
     * @return  Devuelve el conjunto de Clases de la Asignatura.
     */
    public ArrayList<Clase> getAllClases() {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (Map.Entry<String, Grupo> entry : this.grupos.entrySet()) {
            for (Map.Entry<String, SubGrupo> entry2 : entry.getValue().getSubGrupos().entrySet()) {
                for (int i = 0; i < this.sesiones.size(); ++i) {
                    if (entry2.getValue().getTipo() == this.sesiones.get(i).getTipo()) {
                        clases.add(new Clase(entry2.getValue(), this.sesiones.get(i)));
                    }
                }
            }
        }
        return clases;
    }

    /**Métodos redefinidos*/

    /**
     * Convierte una Asignatura a String.
     * @return String con la información de la Asignatura.
     */
    @Override
    public String toString() {
        String text = "Asignatura: " + this.id + "\n-Nombre: " + this.nombre;
        if (this.tieneNivel()) text += "\n-Nivel: " + this.nivel.getNombre() + "\n-Sesiones:";
        for (int i = 0; i < this.sesiones.size(); ++i) text += ("\n-- " + this.sesiones.get(i).toString());
        text += "\n-Grupos:";
        for (Map.Entry<String, Grupo> entry : this.grupos.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n-Restricciones:";
        for (int i = 0; i < this.restricciones.size(); ++i) text += ("\n-- " + this.restricciones.get(i).toString());
        return text;
    }

}

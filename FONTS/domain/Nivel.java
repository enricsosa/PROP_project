/**Nivel*/

/**Imports*/

package domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Nivel representa una relación entre varias Asignaturas.
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Map
 * @see     HashMap
 */
public class Nivel {

    /**Atributos*/

    /**nombre que identifica al Nivel.*/
    private String nombre;
    /**Asignaturas que pertenecen al Nivel.*/
    private Map<String, Asignatura> asignaturas;
    /**Restricciones que afectan al Nivel.*/
    private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Contructora de la clase Nivel.
     * @param nombre    nombre que se asigna a Nivel.
     */
    public Nivel(String nombre) {
        this.nombre = nombre;
        this.asignaturas = new HashMap<String, Asignatura>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo nombre a Nivel.
     * @param nombre    Nuevo nombre que se asignará a Nivel.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Añade una Asignatura a Nivel
     * @param asignatura    Asignatura que se añade a Nivel.
     */
    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    //public void replaceAsignatura(Asignatura asignatura) {
      //  this.asignaturas.replace(asignatura.getId(), asignatura);
    //}

    /**
     * Elimina de Nivel la Asignatura con id indicada.
     * @param id    id de la Asignatura que se quiere eliminar.
     */
    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    /**
     * Asigna un conjunto de Restricciones a Nivel.
     * @param restricciones Conjunto de Restricciones que se asignará a Nivel.
     */
    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    /**
     * Añade una Restricción al conjunto de Restricciones de Nivel.
     * @param restriccion   Restriccion que se quiere añadir a Nivel.
     */
    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    /**
     * Elimina una Restricción dada su posición dentro del conjunto de Restricciones.
     * @param posicion  posicion de la Restriccion que se quiere eliminar.
     */
    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    /**
     * Elimina una FranjaNivel del Nivel.
     * @param horaIni   hora de inicio de la franja.
     * @param horaFin   hora de fin de la franja.
     */
    public void eliminarFranjaNivel(int horaIni, int horaFin) {
        for (int i = 0; i < this.restricciones.size(); ++i) {
            if (this.restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaNivel) {
                if ((((FranjaNivel)this.restricciones.get(i)).getHoraIni().equals(horaIni))
                        && (((FranjaNivel)this.restricciones.get(i)).getHoraFin().equals(horaFin))) {
                    this.restricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**Consultoras*/

    /**
     * Devuelve el nombre que identifica al Nivel.
     * @return  nombre del Nivel.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve las Asignaturas que pertenecen al Nivel.
     * @return  Asignaturas que pertenecen al Nivel.
     */
    public Map<String, Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    /**
     * Devuelve una Asignatura de Nivel dado su id.
     * @param id    id de la Asignatura que se quiere obtener.
     * @return      Asignatura con id indicado.
     */
    public Asignatura getAsignatura(String id) {
        return this.asignaturas.get(id);
    }

    /**
     * Dada una Asignatura indica si forma parte del Nivel.
     * @param asignatura    Asignatura que se quiere saber si pertenece a Nivel.
     * @return              true si asignatura forma parte de Nivel, false en caso contrario.
     */
    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.asignaturas.get(asignatura.getId()) != null);
    }

    //public Boolean tieneAsignatura(String id) {
    //    return (this.asignaturas.get(id) != null);
    //}

    /**
     * Devuelve las Restricciones asociadas al Nivel.
     * @return  Restricciones asociadas al Nivel.
     */
    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    /**
     * Devuelve la Restriccion situada en la posicion indicada de las Restricciones del Nivel.
     * @param posicion  posicion de la Restriccion que se quiere obtener.
     * @return          Restriccion de Nivel en la posicion indicada.
     */
    public Restriccion getRestriccion(int posicion) {
        return this.restricciones.get(posicion);
    }

    /**Métodos redefinidos*/

    /**
     * Convierte el Nivel en String.
     * @return  Devuelve un String con la información del Nivel.
     */
    @Override
    public String toString() {
        String text = "Nivel: " + this.nombre;
        text += "\n-Asignaturas:";
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) text += ("\n-- " + entry.getValue().getId());
        text += "\n-Restricciones:";
        for (int i = 0; i < this.restricciones.size(); ++i) text += ("\n-- " + this.restricciones.get(i).toString());
        return text;
    }


}
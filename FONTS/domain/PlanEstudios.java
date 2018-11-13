/**PlanEstudios*/

/**Imports*/

package domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * PlanEstudios Contiene toda la información para generar un Horario.
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Map
 * @see     HashMap
 * @see     Collectors
 */
public class PlanEstudios {

    /**Atributos*/

    /**Nombre que identifica el PlanEstudios.*/
    private String nombre;
    /**Niveles que tiene el PlanEstudios.*/
    private Map<String, Nivel> niveles;
    /**Asignaturas que tiene el PlanEstudios.*/
    private Map<String, Asignatura> asignaturas;
    /**Aulas que tiene el PlanEstudios.*/
    private Map<String, Aula> aulas;
    //private Horario horarioGeneral;
    /**Restricciones que afectan al PlanEstudios.*/
    private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Constructora de PlanEstudios.
     * @param nombre    nombre que se asigna a PlanEstudios.
     */
    public PlanEstudios(String nombre) {
        this.nombre = nombre;
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /**Métodos públicos*/

    /**
     * Devuelve todas las Clases que se pueden generar en el PlanEstudios.
     * @return  Clases que se pueden generar en el PlanEstudios.
     */
    public ArrayList<Clase> getAllClases() {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) {
            clases.addAll(entry.getValue().getAllClases());
        }
        return clases;
    }

    /**
     * Asigna un nuevo nombre a PlanEstudios.
     * @param nombre    Nuevo nombre que se asignará a PlanEstudios.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Añade un Nivel a PlanEstudios.
     * @param nivel Nivel que se añade a PlanEstudios.
     */
    public void addNivel(Nivel nivel) {
        this.niveles.putIfAbsent(nivel.getNombre(), nivel);
    }

    //public void replaceNivel(Nivel nivel) {
      //  this.niveles.replace(nivel.getNombre(), nivel);
    //}

    /**
     * Elimina de PlanEstudios el Nivel con el nombre introducido.
     * @param nombre    nombre del Nivel que se quiere eliminar.
     */
    public void eliminarNivel(String nombre) {
        this.niveles.remove(nombre);
    }

    /**
     * Añade una Asignatura a PlanEstudios.
     * @param asignatura    Asignatura que se añade a PlanEstudios.
     */
    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    //public void replaceAsignatura(Asignatura asignatura) {
       // this.asignaturas.replace(asignatura.getId(), asignatura);
    //}

    /**
     * Elimina de PlanEstudios la Asignatura con la id introducida.
     * @param id    id de la Asignatura que se quiere eliminar.
     */
    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    /**
     * Añade un Aula a PlanEstudios.
     * @param aula  Aula que se quiere añadir a PlanEstudios.
     */
    public void addAula(Aula aula) {
        this.aulas.putIfAbsent(aula.getId(), aula);
    }

    //public void replaceAula(Aula aula) {
    //    this.aulas.replace(aula.getId(), aula);
    //}

    /**
     * Elimina el Aula de PlanEstudios con la id indicada.
     * @param id    id del Aula que se quiere eliminar.
     */
    public void eliminarAula(String id) {
        this.aulas.remove(id);
    }

    //public void setHorarioGeneral (Horario horarioGeneral) {
      //  this.horarioGeneral = horarioGeneral;
    //}

    /**
     * Asigna un conjunto de Restricciones a PlanEstudios.
     * @param restricciones Conjunto de Restricciones que se asignan a PlanEstudios.
     */
    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    /**
     * Añade una Restriccion a PlanEstudios.
     * @param restriccion   Restriccion que se quiere añadir a PlanEstudios.
     */
    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    /**
     * Elimina la Restrricion del conjunto de Restricciones de PlanEstudios que se encuentra en la posicion indicada.
     * @param posicion  posicion de la Restriccion que se quiere eliminar.
     */
    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    /**Consultoras*/

    /**
     * Devuelve un String con el nombre del PlanEstudios.
     * @return  String con el nombre del PlanEstudios.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve un Map con los Niveles de PlanEstudios.
     * @return  Map con los Niveles de PlanEstudios.
     */
    public Map<String, Nivel> getNiveles() {
        return this.niveles;
    }

    /**
     * Dado su nombre devuelve un Nivel de PlanEstudios.
     * @param nombre    Nombre del Nivel que se quiere obtener.
     * @return          niveld e PlanEstudios con el nombre dado.
     */
    public Nivel getNivel(String nombre) {
        return this.niveles.get(nombre);
    }

    /**
     * Devuelve un Map con las Asignaturas de PlanEstudios.
     * @return  Map con las Asignaturas de PlanEstudios.
     */
    public Map<String, Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    /**
     * Dado su id devuelve una Asignatura de PlanEstudios.
     * @param id    id de la Asignatura que se quiere obtener.
     * @return      Asignatura de PlanEstudios con la id dada.
     */
    public Asignatura getAsignatura(String id) {
        return this.asignaturas.get(id);
    }

    /**
     * Devuelve un Map con las Aulas de PlanEstudios.
     * @return  Map con las Aulas de PlanEstudios.
     */
    public Map<String, Aula> getAulas() {
        return this.aulas;
    }

    /**
     * Devuelve un Map con las Aulas compatibles con Clases de Teoria de PlanEstudios.
     * @return  Map con las Aulas compatibles con Clases de Teoria de PlanEstudios.
     */
    public Map<String, Aula> getAulasTeoria(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneTeoria() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve un Map con las Aulas compatibles con Clases de Laboratorio de PlanEstudios.
     * @return  Map con las Aulas compatibles con Clases de Laboratorio de PlanEstudios.
     */
    public Map<String, Aula> getAulasLaboratorio(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneLaboratorio() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve un Map con las Aulas compatibles con Clases de Problemas de PlanEstudios.
     * @return  Map con las Aulas compatibles con Clases de Problemas de PlanEstudios.
     */
    public Map<String, Aula> getAulasProblemas(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneProblemas() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve el Aula de PlanEstudios con la id dada.
     * @param id    id del Aula que se quiere obtener.
     * @return      Aula de PlanEstudios con la id dada.
     */
    public Aula getAula(String id) {
        return this.aulas.get(id);
    }

    //public Horario getHorarioGeneral() {
      //  return this.horarioGeneral;
    //}

    //public Boolean tieneHorario() {
      //  return this.horarioGeneral != null;
    //}

    /**
     * Devuelve el conjunto de Restricciones de PlanEstudios.
     * @return  ArrayList con las Restricciones de PlanEstudios.
     */
    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    /**
     * Dada su posicion en el conjunto de Restricciones de PlanEstudios devuelve una Restriccion.
     * @param posicion  posicion en el conjunto de Restricciones de PlanEstudios de la Restriccion que se quiere obtener.
     * @return          Restrcccion que se encuentra en la posicion dada dentro del conjunto de Restricciones de PlanEstudios.
     */
    public Restriccion getRestriccion(int posicion) {
        return this.restricciones.get(posicion);
    }

    /**Métodos redefinidos*/

    /**
     * Convierte PlanEstudios en un String que contiene toda su información.
     * @return  String que contiene toda la información de PlanEstudios.
     */
    @Override
    public String toString() {
        String text = "Plan Estudios: " + this.nombre + "\n-Niveles:";
        for (Map.Entry<String, Nivel> entry : this.niveles.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n\n-Asignaturas:";
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n\n-Aulas:";
        for (Map.Entry<String, Aula> entry : this.aulas.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n\n-Restricciones:";
        for (int i = 0; i < this.restricciones.size(); ++i) text += ("\n-- " + this.restricciones.get(i).toString());
        return text;
    }

}
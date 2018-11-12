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

    public String getNombre() {
        return this.nombre;
    }

    public Map<String, Nivel> getNiveles() {
        return this.niveles;
    }

    public Nivel getNivel(String nom) {
        return this.niveles.get(nom);
    }

    public Map<String, Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    public Asignatura getAsignatura(String nom) {
        return this.asignaturas.get(nom);
    }

    public Map<String, Aula> getAulas() {
        return this.aulas;
    }

    public Map<String, Aula> getAulasTeoria(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneTeoria() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<String, Aula> getAulasLaboratorio(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneLaboratorio() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<String, Aula> getAulasProblemas(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneProblemas() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Aula getAula(String id) {
        return this.aulas.get(id);
    }

    //public Horario getHorarioGeneral() {
      //  return this.horarioGeneral;
    //}

    //public Boolean tieneHorario() {
      //  return this.horarioGeneral != null;
    //}

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    public Restriccion getRestriccion(Integer i) {
        return this.restricciones.get(i);
    }

    /**Métodos redefinidos*/

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
package domain;

/** Imports **/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PlanEstudios {

    /** Atributos **/

    private String nombre;
    private Map<String, Nivel> niveles;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Aula> aulas;
    private Horario horarioGeneral;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public PlanEstudios(String nombre) {
        this.nombre = nombre;
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /** Métodos públicos **/

    public ArrayList<Clase> getAllClases() {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) {
            clases.addAll(entry.getValue().getAllClases());
        }
        return clases;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addNivel(Nivel nivel) {
        this.niveles.putIfAbsent(nivel.getNombre(), nivel);
    }

    public void replaceNivel(Nivel nivel) {
        this.niveles.replace(nivel.getNombre(), nivel);
    }

    public void eliminarNivel(String nombre) {
        this.niveles.remove(nombre);
    }

    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    public void replaceAsignatura(Asignatura asignatura) {
        this.asignaturas.replace(asignatura.getId(), asignatura);
    }

    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    public void addAula(Aula aula) {
        this.aulas.putIfAbsent(aula.getId(), aula);
    }

    public void replaceAula(Aula aula) {
        this.aulas.replace(aula.getId(), aula);
    }

    public void eliminarAula(String id) {
        this.aulas.remove(id);
    }

    public void setHorarioGeneral (Horario horarioGeneral) {
        this.horarioGeneral = horarioGeneral;
    }

    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    /** Consultoras **/

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

    public Horario getHorarioGeneral() {
        return this.horarioGeneral;
    }

    public Boolean tieneHorario() {
        return this.horarioGeneral != null;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    public Restriccion getRestriccion(Integer i) {
        return this.restricciones.get(i);
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        String text = "Plan Estudios: " + this.nombre + "\n-Niveles:";
        for (Map.Entry<String, Nivel> entry : this.niveles.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n-Asignaturas:";
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n-Aulas:";
        for (Map.Entry<String, Aula> entry : this.aulas.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n-Restricciones:";
        for (int i = 0; i < this.restricciones.size(); ++i) text += ("\n-- " + this.restricciones.get(i).toString());
        if (this.tieneHorario()) text += "\n-" + this.horarioGeneral.toString();
        return text;
    }

}
package domain;

/** Imports **/

import java.util.Map;
import java.util.HashMap;

import domain.Asignatura;
import domain.Horario;
import domain.Aula;
import domain.Nivel;

public class PlanEstudios {

    /** Atributos **/

    private String nombre;
    private Map<String, Nivel> niveles;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Aula> aulas;
    private Horario horarioGeneral;

    /** Constructoras **/

    public PlanEstudios(String nombre) {
        this.nombre = nombre;
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
    }

    /** Métodos públicos **/

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
        this.asignaturas.putIfAbsent(asignatura.getNombre(), asignatura);
    }

    public void replaceAsignatura(Asignatura asignatura) {
        this.asignaturas.replace(asignatura.getNombre(), asignatura);
    }

    public void eliminarAsignatura(String nombre) {
        this.asignaturas.remove(nombre);
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

    public Aula getAula(String id) {
        return this.aulas.get(id);
    }

    public Horario getHorarioGeneral() {
        return this.horarioGeneral;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }

}
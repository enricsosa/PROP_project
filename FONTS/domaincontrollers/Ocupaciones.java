package domaincontrollers;

/** Imports **/

import domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ocupaciones {

    /** Atributos **/

    private Map<String, Nivel> niveles;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Aula> aulas;
    private Map<String, SubGrupo> subGrupos;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Ocupaciones() {
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /** Metodos publicos **/

    public void setNiveles(Map<String, Nivel> niveles) {
        this.niveles = niveles;
    }

    public void setAsignaturas(Map<String, Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public void setAulas(Map<String, Aula> aulas) {
        this.aulas = aulas;
    }

    public void setSubGrupos(Map<String, SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
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

    public Map<String, Nivel> getNiveles() {
        return niveles;
    }

    public Map<String, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public Map<String, Aula> getAulas() {
        return aulas;
    }

    public Map<String, SubGrupo> getSubGrupos() {
        return subGrupos;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    /** Métodos redefinidos **/

}

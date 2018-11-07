package domaincontrollers;

/** Imports **/

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dia {

    /** Atributos **/

    private Map<String, Nivel> niveles;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Aula> aulas;
    private Map<String, Grupo> grupos;
    private Map<String, SubGrupo> subGrupos;
    private ArrayList<Restriccion> restricciones;
    private Hora[] horas;

    /** Constructoras **/

    public Dia() {
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.grupos = new HashMap<String, Grupo>();
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.restricciones = new ArrayList<Restriccion>();
        this.horas = new Hora[24];
        Arrays.fill(this.horas, new Hora());
    }

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
        this.restricciones = new ArrayList<Restriccion>();
        this.restricciones.addAll(oldDia.restricciones);
        this.horas = new Hora[24];
        for (int i = 0; i < 24; ++i) {
            this.horas[i] = new Hora(oldDia.getHora(i));
        }
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

    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
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

    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getNombre(), subGrupo);
    }

    public void replaceSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.replace(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getNombre(), subGrupo);
    }

    public void eliminarSubGrupo(String key) {
        this.subGrupos.remove(key);
    }

    public void addGrupo(Grupo grupo) {
        this.grupos.putIfAbsent(grupo.getId() + grupo.getAsignatura().getNombre(), grupo);
    }

    public void replaceGrupo(Grupo grupo) {
        this.grupos.replace(grupo.getId() + grupo.getAsignatura().getNombre(), grupo);
    }

    public void eliminarGrupo(String key) {
        this.grupos.remove(key);
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

    public void setHoras(Hora[] horas) {
        this.horas = horas;
    }

    public void setHora(Hora hora, int i) {
        this.horas[i] = hora;
    }

    /** Consultoras **/

    public Map<String, Nivel> getNiveles() {
        return niveles;
    }

    public Nivel getNivel(String nombre) {
        return this.niveles.get(nombre);
    }

    public Boolean tieneNivel(Nivel nivel) {
        return (this.niveles.get(nivel.getNombre()) != null);
    }

    public Map<String, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public Asignatura getAsignatura(String nombre) {
        return this.asignaturas.get(nombre);
    }

    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.asignaturas.get(asignatura.getNombre()) != null);
    }

    public Map<String, Aula> getAulas() {
        return aulas;
    }

    public Aula getAula(String nombre) {
        return this.aulas.get(nombre);
    }

    public Boolean tieneAula(Aula aula) {
        return (this.aulas.get(aula.getId()) != null);
    }

    public Map<String, Grupo> getGrupos() {
        return grupos;
    }

    public Grupo getGrupo(String id) {
        return this.grupos.get(id);
    }

    public Boolean tieneGrupo(Grupo grupo) {
        return (this.grupos.get(grupo.getId() + grupo.getAsignatura().getNombre()) != null);
    }

    public Map<String, SubGrupo> getSubGrupos() {
        return subGrupos;
    }

    public SubGrupo getSubGrupo(String id) {
        return this.subGrupos.get(id);
    }

    public Boolean tieneSubGrupo(SubGrupo subGrupo) {
        return (this.subGrupos.get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getNombre()) != null);
    }

    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    public Restriccion getRestriccion(int i) {
        return this.restricciones.get(i);
    }

    public Hora[] getHoras() {
        return horas;
    }

    public Hora getHora(int hora) {
        return this.horas[hora];
    }

    /** Métodos redefinidos **/

}
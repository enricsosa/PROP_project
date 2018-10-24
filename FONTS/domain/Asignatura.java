package domain;

import java.util.ArrayList;

/** Imports **/

public class Asignatura {

    /** Atributos **/

    private String id;
    private String nombre;
    private PlanEstudios planEstudios;
    private Nivel nivel;
    private ArrayList sesiones;
    private ArrayList grupos;
    private ArrayList restricciones;

    /** Constructoras **/

    public Asignatura(String id, String nom, PlanEstudios planEstudios) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.sesiones = new ArrayList();
        this.grupos = new ArrayList();
        this.restricciones = new ArrayList();
    }

    public Asignatura(String id, String nom, PlanEstudios planEstudios, Nivel nivel) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.nivel = nivel;
        this.sesiones = new ArrayList();
        this.grupos = new ArrayList();
        this.restricciones = new ArrayList();
    }

    public Asignatura(String id, String nom, PlanEstudios planEstudios, ArrayList sesiones, ArrayList grupos) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.sesiones = sesiones;
        this.grupos = grupos;
        this.restricciones = new ArrayList();
    }

    public Asignatura(String id, String nom, PlanEstudios planEstudios, Nivel nivel, ArrayList sesiones, ArrayList grupos) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.nivel = nivel;
        this.sesiones = sesiones;
        this.grupos = grupos;
        this.restricciones = new ArrayList();
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPlanEstudios(PlanEstudios planEstudios) {
        this.planEstudios = planEstudios;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setSesiones(ArrayList sesiones) {
        this.sesiones = sesiones;
    }

    public void setGrupos(ArrayList grupos) {
        this.grupos = grupos;
    }

    public void setRestricciones(ArrayList restricciones) {
        this.restricciones = restricciones;
    }

    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    public void addSesion(Sesion sesion) {
        this.sesiones.add(sesion);
    }

    public void eliminarSesion(Integer posicion) {
        this.sesiones.remove(posicion);
    }

    public void addGrupo(Grupo grupo) {
        this.grupos.add(grupo);
    }

    public void eliminarGrupo(Integer posicion) {
        this.grupos.remove(posicion);
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public PlanEstudios getPlanEstudios() {
        return this.planEstudios;
    }

    public Nivel getNivel() {
        return this.nivel;
    }

    public ArrayList getSesiones() {
        return this.sesiones;
    }

    public ArrayList getGrupos() {
        return this.grupos;
    }

    public ArrayList getRestricciones() {
        return this.restricciones;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }

}
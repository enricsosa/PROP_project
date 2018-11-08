package domain;

/** Imports **/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Asignatura {

    /** Atributos **/

    private String id;
    private String nombre;
    private PlanEstudios planEstudios;
    private Nivel nivel;
    private ArrayList<Sesion> sesiones;
    private Map<String, Grupo> grupos;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Asignatura(String id, String nom, PlanEstudios planEstudios) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.sesiones = new ArrayList<Sesion>();
        this.grupos = new HashMap<String, Grupo>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    public Asignatura(String id, String nom, PlanEstudios planEstudios, Nivel nivel) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.nivel = nivel;
        this.sesiones = new ArrayList<Sesion>();
        this.grupos = new HashMap<String, Grupo>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    public Asignatura(String id, String nom, PlanEstudios planEstudios, ArrayList<Sesion> sesiones, Map<String, Grupo> grupos) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.sesiones = sesiones;
        this.grupos = grupos;
        this.restricciones = new ArrayList<Restriccion>();
    }

    public Asignatura(String id, String nom, PlanEstudios planEstudios, Nivel nivel, ArrayList<Sesion> sesiones, Map<String, Grupo> grupos) {
        this.id = id;
        this.nombre = nom;
        this.planEstudios = planEstudios;
        this.nivel = nivel;
        this.sesiones = sesiones;
        this.grupos = grupos;
        this.restricciones = new ArrayList<Restriccion>();
    }

    /** Métodos públicos **/

    public ArrayList<Clase> getAllClases() {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (Map.Entry<String, Grupo> entry : this.grupos.entrySet()) {
            for (Map.Entry<String, SubGrupo> entry2 : entry.getValue().getSubGrupos().entrySet()) {
                for (int i = 0; i < this.sesiones.size(); ++i) {
                    if (entry2.getValue().getTipo() == this.sesiones.get(i).getTipo()) {
                        if (this.nivel != null) {
                            clases.add(new Clase(entry2.getValue(), this.sesiones.get(i), this.restricciones, entry2.getValue().getGrupo().getRestricciones()));
                        }
                        else {
                            clases.add(new Clase(entry2.getValue(), this.sesiones.get(i), this.nivel.getRestricciones(), this.restricciones, entry2.getValue().getGrupo().getRestricciones()));
                        }
                    }
                }
            }
        }
        return clases;
    }

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

    public void setSesiones(ArrayList<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public void setGrupos(Map<String, Grupo> grupos) {
        this.grupos = grupos;
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

    public void addSesion(Sesion sesion) {
        this.sesiones.add(sesion);
    }

    public void eliminarSesion(Integer posicion) {
        this.sesiones.remove(posicion);
    }

    public void addGrupo(Grupo grupo) {
        this.grupos.putIfAbsent(grupo.getId(), grupo);
    }

    public void replaceGrupo(Grupo grupo) {
        this.grupos.replace(grupo.getId(), grupo);
    }

    public void eliminarGrupo(String id) {
        this.grupos.remove(id);
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

    public ArrayList<Sesion> getSesiones() {
        return this.sesiones;
    }

    public Map<String, Grupo> getGrupos() {
        return this.grupos;
    }

    public Grupo getGrupo(String id) {
        return this.grupos.get(id);
    }

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    public Boolean tieneNivel() {
        return (this.nivel != null);
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return nombre;
    }

}
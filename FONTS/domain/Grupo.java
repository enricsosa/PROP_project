package domain;

/** Imports **/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Grupo {

    /** Atributos **/

    private String id;
    private Asignatura asignatura;
    private Map<String, SubGrupo> subGrupos;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Grupo(String id, Asignatura asignatura) {
        this.id = id;
        this.asignatura = asignatura;
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.restricciones = new ArrayList<Restriccion>();
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public void setSubGrupos(Map<String, SubGrupo> subGrupos) {
        this.subGrupos = subGrupos;
    }

    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getId(), subGrupo);
    }

    public void replaceSubGrupo2(SubGrupo subGrupo) {
        this.subGrupos.replace(subGrupo.getId(), subGrupo);
    }

    public void eliminarSubGrupo(String id) {
        this.subGrupos.remove(id);
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

    public String getId() {
        return this.id;
    }

    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    public Map<String, SubGrupo> getSubGrupos() {
        return this.subGrupos;
    }

    public Map<String, SubGrupo> getSubGruposTeoria() {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == TipoClase.Teoria)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<String, SubGrupo> getSubGruposLaboratorio() {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == TipoClase.Laboratorio)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<String, SubGrupo> getSubGruposProblemas() {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == TipoClase.Problemas)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public SubGrupo getSubGrupo(String id) {
        return this.subGrupos.get(id);
    }

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return this.id;
    }

}
package domain;

/** Imports **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hora {

    /** Atributos **/

    private Map<String, Nivel> niveles;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Aula> aulas;
    private Map<String, Grupo> grupos;
    private Map<String, SubGrupo> subGrupos;
    private Map<String, Asignacion> asignaciones;
    //private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Hora() {
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.grupos = new HashMap<String, Grupo>();
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.asignaciones = new HashMap<String, Asignacion>();
        //this.restricciones = new ArrayList<Restriccion>();
    }

    public Hora(Hora oldHora) {
        this.niveles = new HashMap<String, Nivel>();
        this.niveles.putAll(oldHora.niveles);
        this.asignaturas = new HashMap<String, Asignatura>();
        this.asignaturas.putAll(oldHora.asignaturas);
        this.aulas = new HashMap<String, Aula>();
        this.aulas.putAll(oldHora.aulas);
        this.grupos = new HashMap<String, Grupo>();
        this.grupos.putAll(oldHora.grupos);
        this.subGrupos = new HashMap<String, SubGrupo>();
        this.subGrupos.putAll(oldHora.subGrupos);
        this.asignaciones = new HashMap<String, Asignacion>();
        this.asignaciones.putAll(oldHora.asignaciones);
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(oldHora.restricciones);
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

    public void setAsignaciones(Map<String, Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
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

    static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
    }

    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()), subGrupo);
    }

    public void replaceSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.replace(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()), subGrupo);
    }

    public void eliminarSubGrupo(String key) {
        this.subGrupos.remove(key);
    }

    public void addGrupo(Grupo grupo) {
        this.grupos.putIfAbsent(grupo.getId() + grupo.getAsignatura().getId(), grupo);
    }

    public void replaceGrupo(Grupo grupo) {
        this.grupos.replace(grupo.getId() + grupo.getAsignatura().getId(), grupo);
    }

    public void eliminarGrupo(String key) {
        this.grupos.remove(key);
    }

    public void addAsignacion(Asignacion asignacion) {
        this.asignaciones.putIfAbsent(asignacion.generateKey(), asignacion);
    }

    public void replaceAsignacion(Asignacion asignacion) {
        this.asignaciones.replace(asignacion.generateKey(), asignacion);
    }

    public void eliminarAsignacion(String key) {
        this.asignaciones.remove(key);
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

    //public void setRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones = restricciones;
    //}

    //public void addRestriccion(Restriccion restriccion) {
      //  this.restricciones.add(restriccion);
    //}

    //public void addRestricciones(ArrayList<Restriccion> restricciones) {
      //  this.restricciones.addAll(restricciones);
    //}

    //public void eliminarRestriccion(Integer posicion) {
      //  this.restricciones.remove(posicion);
    //}

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
        return (this.asignaturas.get(asignatura.getId()) != null);
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
        return (this.grupos.get(grupo.getId() + grupo.getAsignatura().getId()) != null);
    }

    public Boolean tieneGrupo(Asignatura asignatura, Grupo grupo) {
        return (this.grupos.get(grupo.getId() + asignatura.getId()) != null);
    }

    public Map<String, SubGrupo> getSubGrupos() {
        return subGrupos;
    }

    public SubGrupo getSubGrupo(String id) {
        return this.subGrupos.get(id);
    }

    public SubGrupo getSubGrupo(SubGrupo subGrupo) {
        return this.subGrupos.get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo()));
    }

    public Boolean tieneSubGrupo(SubGrupo subGrupo) {
        return (this.subGrupos.get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + strTipo(subGrupo.getTipo())) != null);
    }

    public Map<String, Asignacion> getAsignaciones() {
        return this.asignaciones;
    }

    public Asignacion getAsignacion(String key) {
        return this.asignaciones.get(key);
    }

    public Boolean tieneAsignacion(Asignacion asignacion) {
        return (this.asignaciones.get(asignacion.generateKey()) != null);
    }

    //public ArrayList<Restriccion> getRestricciones() {
     //   return restricciones;
    //}

    //public Restriccion getRestriccion(int i) {
      //  return this.restricciones.get(i);
    //}

    /** Métodos redefinidos **/

}

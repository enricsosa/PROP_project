package domain;

/** Imports **/

import java.util.ArrayList;

public class Clase {

    /** Atributos **/

    private SubGrupo subGrupo;
    private Sesion sesion;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Clase(SubGrupo subGrupo, Sesion sesion) {
        this.subGrupo = subGrupo;
        this.sesion = sesion;
        this.restricciones = new ArrayList<Restriccion>();
        this.restricciones.addAll(subGrupo.getGrupo().getRestricciones());
        this.restricciones.addAll(subGrupo.getAsignatura().getRestricciones());
        if (subGrupo.getAsignatura().tieneNivel()) this.restricciones.addAll(subGrupo.getAsignatura().getNivel().getRestricciones());
    }

    /** Métodos públicos **/

    public void setSubGrupo(SubGrupo subGrupo) {
        this.subGrupo = subGrupo;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
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

    public SubGrupo getSubGrupo() {
        return this.subGrupo;
    }

    public Sesion getSesion() {
        return this.sesion;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    public int getPlazas() {
        return this.subGrupo.getPlazas();
    }

    public Grupo getGrupo() {
        return this.subGrupo.getGrupo();
    }

    public TipoClase getTipoSesion() {
        return this.sesion.getTipo();
    }

    public Asignatura getAsignatura() {
        return this.subGrupo.getAsignatura();
    }

    public Boolean tieneNivel() {
        return this.getAsignatura().tieneNivel();
    }

    public Nivel getNivel() {
        return this.getAsignatura().getNivel();
    }

    public int getDuracion() {
        return this.sesion.getDuracion();
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return ("Clase:\nAsignatura: " + this.getAsignatura().getId() + "\n-Grupo: " + this.getGrupo().getId() + "\n-SubGrupo: "
                + this.getSubGrupo().getId() + "\n- " + this.sesion.toString());
    }

}
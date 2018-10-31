package domain;

import java.util.ArrayList;

/** Imports **/

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
    }

    public Clase(SubGrupo subGrupo, Sesion sesion, ArrayList<Restriccion> restricciones) {
        this.subGrupo = subGrupo;
        this.sesion = sesion;
        this.restricciones = restricciones;
    }

    public Clase(SubGrupo subGrupo, Sesion sesion, ArrayList<Restriccion> restriccionesAsignatura, ArrayList<Restriccion> restriccionesGrupo) {
        this.subGrupo = subGrupo;
        this.sesion = sesion;
        this.restricciones = new ArrayList<Restriccion>();
        this.restricciones.addAll(restriccionesAsignatura);
        this.restricciones.addAll(restriccionesGrupo);
    }

    public Clase(SubGrupo subGrupo, Sesion sesion, ArrayList<Restriccion> restriccionesNivel, ArrayList<Restriccion> restriccionesAsignatura, ArrayList<Restriccion> restriccionesGrupo) {
        this.subGrupo = subGrupo;
        this.sesion = sesion;
        this.restricciones = new ArrayList<Restriccion>();
        this.restricciones.addAll(restriccionesNivel);
        this.restricciones.addAll(restriccionesAsignatura);
        this.restricciones.addAll(restriccionesGrupo);
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

    public TipoClase getTipoSesion() {
        return this.sesion.getTipo();
    }

    /** Métodos redefinidos **/

}
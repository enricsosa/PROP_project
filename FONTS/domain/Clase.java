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

    public String toStringResumido() {
        return "Clase: " + this.getAsignatura().getId() + " " + this.getSubGrupo().getIdCompleta() + " "
                + this.getSubGrupo().getTipo().toString() + " " + this.getSesion().getDuracion().toString();
    }

    public Boolean comprovarRestricciones(int dia, int horaIni, Ocupaciones ocupaciones) {
        for (int i = 0; i < this.restricciones.size(); ++i) {
            if (!(this.getRestriccion(i).comprovarRestriccion(this, dia, horaIni, ocupaciones))) return false;
        }
        return true;
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

    public Restriccion getRestriccion(int i) {
        return this.restricciones.get(i);
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
        return ("Clase:\n-Asignatura: " + this.getAsignatura().getId() + "\n-Grupo: " + this.getGrupo().getId() + "\n-SubGrupo: "
                + this.getSubGrupo().getId() + "\n-" + this.sesion.toString());
    }

}
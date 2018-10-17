package domain;

/** Imports **/

import domain.SubGrupo;
import domain.Sesion;

public class Clase {

    /** Atributos **/

    private SubGrupo subGrupo;
    private Sesion sesion;

    /** Constructoras **/

    public Clase(SubGrupo subGrupo, Sesion sesion) {
        this.subGrupo = subGrupo;
        this.sesion = sesion;
    }

    /** Métodos públicos **/

    public void setSubGrupo(SubGrupo subGrupo) {
        this.subGrupo = subGrupo;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    /** Consultoras **/

    public SubGrupo getSubGrupo() {
        return this.subGrupo;
    }

    public Sesion getSesion() {
        return this.sesion;
    }

    /** Métodos redefinidos **/

}
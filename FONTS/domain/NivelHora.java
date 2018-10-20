package domain;

/** Imports **/

import domain.Nivel;
import domain.TipoRestriccion;

public class NivelHora extends Restriccion {

    /** Atributos **/

    private Nivel nivel;

    /** Constructoras **/

    public NivelHora(Nivel nivel) {
        this.nivel = nivel;
    }

    /** Métodos públicos **/

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /** Consultoras **/

    public Nivel getNivel() {
        return this.nivel;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.NivelHora;
    }

}
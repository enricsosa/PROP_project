package domain;

/** Imports **/

import domain.TipoRestriccion;

public class DiaLibre extends Restriccion {

    /** Atributos **/

    private Integer dia;

    /** Constructoras **/

    public DiaLibre(Integer dia) {
        this.dia = dia;
    }

    /** Métodos públicos **/

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    /** Consultoras **/

    public Integer getDia() {
        return this.dia;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.DiaLibre;
    }

}

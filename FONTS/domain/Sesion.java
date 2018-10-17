package domain;

/** Imports **/

import java.time;

import domain.TipoClase

public class Sesion {

    /** Atributos **/

    private Duration duracion;
    private TipoClase tipo;

    /** Constructoras **/

    public Sesion(Duration duracion, TipoClase tipo) {
        this.duracion = duracion;
        this.tipo = tipo;
    }

    /** Métodos públicos **/

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    /** Consultoras **/

    public Duration getDuracion() {
        return this.duracion;
    }

    public TipoClase getTipo() {
        return this.tipo;
    }

    /** Métodos redefinidos **/

}
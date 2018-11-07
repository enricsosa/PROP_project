package domain;

import domaincontrollers.Ocupaciones;

/** Imports **/

public abstract class Restriccion {

    /** Atributos **/

    /** Constructoras **/

    public Restriccion() {}

    /** Métodos públicos **/

    /** Consultoras **/

    public abstract TipoRestriccion getTipoRestriccion();

    public abstract Boolean comprovarRestriccion(Asignacion asignacion, Ocupaciones ocupaciones);

    /** Métodos redefinidos **/

}

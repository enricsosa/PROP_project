package domain;

/** Imports **/

public abstract class Restriccion {

    /** Atributos **/

    /** Constructoras **/

    public Restriccion() {}

    /** Métodos públicos **/

    /** Consultoras **/

    public abstract TipoRestriccion getTipoRestriccion();

    public abstract Boolean comprovarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones);

    /** Métodos redefinidos **/

}

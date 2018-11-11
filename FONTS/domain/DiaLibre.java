package domain;

/** Imports **/

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

    @Override
    public Boolean comprovarRestriccion(Asignacion asignacion, Ocupaciones ocupaciones) {
        return (asignacion.getDiaSemana() != this.dia);
    }

    @Override
    public Boolean comprovarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        return (this.dia != dia);
    }

    @Override
    public String toString() {
        return "Dia Libre: " + getNombreDia(this.dia);
    }

    static String getNombreDia(int dia) {
        if (dia == 1) return "Lunes";
        else if (dia == 2) return "Martes";
        else if (dia == 3) return "Miercoles";
        else if (dia == 4) return "Jueves";
        else if (dia == 5) return "Viernes";
        else if (dia == 6) return "Sábado";
        else return "Domingo";
    }

}

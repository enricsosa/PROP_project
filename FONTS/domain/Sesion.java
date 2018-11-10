package domain;

/** Imports **/

public class Sesion {

    /** Atributos **/

    private Integer duracion;
    private TipoClase tipo;
    private Asignatura asignatura;

    /** Constructoras **/

    public Sesion(Integer duracion, TipoClase tipo, Asignatura asignatura) {
        this.duracion = duracion;
        this.tipo = tipo;
        this.asignatura = asignatura;
    }

    /** Métodos públicos **/

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /** Consultoras **/

    public Integer getDuracion() {
        return this.duracion;
    }

    public TipoClase getTipo() {
        return this.tipo;
    }

    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return "Sesion:\n-Asignatura: " + this.asignatura.getId() + "\n-Tipo: " + strTipo(this.tipo) + "\n-Duracion: "
                + getHoraCompleta(this.duracion);
    }

    static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
    }

    static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }

}
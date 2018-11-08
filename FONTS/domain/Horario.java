package domain;

/** Imports **/

public class Horario {

    /** Atributos **/

    private String id;
    private Ocupaciones ocupaciones;

    /** Constructoras **/

    public Horario(String id) {
        this.id = id;
        this.ocupaciones = new Ocupaciones();
    }

    public Horario(String id, Ocupaciones ocupaciones) {
        this.id = id;
        this.ocupaciones = ocupaciones;
    }

    public Horario(Horario oldHorario) {
        this.id = oldHorario.getId();
        this.ocupaciones = new Ocupaciones(oldHorario.getOcupaciones());
    }

    /** Métodos públicos **/

    public void setId() {
        this.id = id;
    }

    public void setOcupaciones(Ocupaciones ocupaciones) {
        this.ocupaciones = ocupaciones;
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public Ocupaciones getOcupaciones() {
        return this.ocupaciones;
    }

    public Dia getDia(int dia) {
        return this.ocupaciones.getDia(dia);
    }

    public Hora getHora(int dia, int hora) {
        return this.ocupaciones.getHora(dia, hora);
    }

    /** Métodos redefinidos **/

}
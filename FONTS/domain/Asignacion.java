package domain;

/** Imports **/

import java.util.ArrayList;

public class Asignacion {

    /** Atributos **/

    private Integer horaIni;
    private Integer DiaSemana;
    private Aula aula;
    private Clase clase;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public Asignacion(Integer horaIni, Integer diaSemana, Aula aula, Clase clase) {
        this.horaIni = horaIni;
        this.DiaSemana = diaSemana;
        this.aula = aula;
        this.clase = clase;
        this.restricciones = new ArrayList<Restriccion>();
    }

    public Asignacion(Integer horaIni, Integer diaSemana, Aula aula, Clase clase, ArrayList<Restriccion> restricciones) {
        this.horaIni = horaIni;
        this.DiaSemana = diaSemana;
        this.aula = aula;
        this.clase = clase;
        this.restricciones = restricciones;
    }

    public Asignacion(Asignacion oldAsignacion) {
        this.horaIni = oldAsignacion.getHoraIni();
        this.DiaSemana = oldAsignacion.getDiaSemana();
        this.aula = oldAsignacion.getAula();
        this.clase = oldAsignacion.getClase();
        this.restricciones = oldAsignacion.getRestricciones();
    }

    /** Métodos públicos **/

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public void setDiaSemana(Integer diaSemana) {
        DiaSemana = diaSemana;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
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

    /** Consultoras **/

    public Integer getHoraIni() {
        return this.horaIni;
    }

    public Integer getDiaSemana() {
        return this.DiaSemana;
    }

    public Integer getHoraFin() {
        return this.horaIni + this.clase.getSesion().getDuracion();
    }

    public Aula getAula() {
        return this.aula;
    }

    public Clase getClase() {
        return this.clase;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        String t, tipo, hi, hf;
        hi = "";
        hf = "";
        if (this.horaIni < 10) hi = "0";
        if (this.getHoraFin() < 10) hf = "0";
        if (this.clase.getSesion().getTipo() == TipoClase.Laboratorio) {
            t = "L";
            tipo = "Laboratorio";
        }
        else if (this.clase.getSesion().getTipo() == TipoClase.Teoria) {
            t = "T";
            tipo = "Teoria";
        }
        else {
            t = "P";
            tipo = "Problemas";
        }
        return this.clase.getSubGrupo().getGrupo().getAsignatura().getId() + " " + this.clase.getSubGrupo().getIdCompleta()
                + " " + t + "\n    Asignatura: " + this.clase.getSubGrupo().getGrupo().getAsignatura().getNombre()
                + "\n    Grupo: " + this.clase.getSubGrupo().getIdCompleta() + "\n    Aula: " + this.aula.getId()
                + "Tipo: " + tipo + "\n    HoraIni: " + hi + this.horaIni.toString() + ":00\n    HoraFin: " + hf
                + this.getHoraFin() + ":00\n";
    }

}
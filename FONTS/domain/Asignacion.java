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
        this.restricciones.addAll(clase.getRestricciones());
    }

    public Asignacion(Asignacion oldAsignacion) {
        this.horaIni = oldAsignacion.getHoraIni();
        this.DiaSemana = oldAsignacion.getDiaSemana();
        this.aula = oldAsignacion.getAula();
        this.clase = oldAsignacion.getClase();
        this.restricciones = new ArrayList<Restriccion>();
        this.restricciones.addAll(oldAsignacion.getRestricciones());
    }

    /** Métodos públicos **/

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public String generateKey() {
        return (this.clase.getAsignatura().getId()+this.clase.getSubGrupo().getIdCompleta()+this.getAula().getId());
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

    public void addRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones.addAll(restricciones);
    }

    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    public Boolean comprovarRestricciones(Ocupaciones ocupaciones) {
        for (int i = 0; i < this.restricciones.size(); ++i) {
            if (!(this.getRestriccion(i).comprovarRestriccion(this, ocupaciones))) return false;
        }
        return true;
    }

    public Boolean noCabeSubGrupo() {
        return (this.clase.getPlazas() > this.aula.getPlazas());
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

    public Grupo getGrupo() {
        return this.clase.getGrupo();
    }

    public SubGrupo getSubGrupo() {
        return this.clase.getSubGrupo();
    }

    public Clase getClase() {
        return this.clase;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    public Restriccion getRestriccion(int i) {
        return this.restricciones.get(i);
    }

    public Asignatura getAsignatura() {
        return this.clase.getAsignatura();
    }

    public Boolean tieneNivel() {
        return this.getAsignatura().tieneNivel();
    }

    public Nivel getNivel() {
        return this.getAsignatura().getNivel();
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
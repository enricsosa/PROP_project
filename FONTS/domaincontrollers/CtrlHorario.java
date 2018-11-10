package domaincontrollers;

/** Imports **/

import domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import static java.lang.System.out;

public class CtrlHorario {

    /** Atributos **/

    private PlanEstudios planEstudios;
    private ArrayList<Restriccion> restricciones;
    private LimitacionesHorario limitacionesHorario;

    /** Constructoras **/

    CtrlHorario(PlanEstudios planEstudios, ArrayList restricciones) {
        this.planEstudios = planEstudios;
        this.restricciones = restricciones;
        this.limitacionesHorario = new LimitacionesHorario();
        this.loadLimitacionesHorario();
    }

    /** Métodos públicos **/

    public ReturnSet generarHorario(String id) {
        out.println("Se inicia generarHorario().\n");
        Horario horario = new Horario(id);
        Ocupaciones ocupaciones = new Ocupaciones();
        ArrayList<Clase> clases = this.getAllClases();
        Collections.shuffle(clases);
        //for(int i = 0; i < clases.size(); ++i) out.println(clases.get(i));
        for (int i = 0; i < clases.size(); ++i) {
            ReturnSet franja = getFranjaClase(clases.get(i));
            for (int dia = 1; dia <= 7; ++dia) {
                if(this.limitacionesHorario.esDiaLibre(dia)) {
                    for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {
                            Asignacion asignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i));
                            ReturnSet returnSet = this.generarAsignaciones(asignacion, copyRemoveClases(clases, i), new Ocupaciones(ocupaciones));
                            if (returnSet.getValidez()) {
                                horario.setOcupaciones(returnSet.getOcupaciones());
                                return (new ReturnSet(true, horario));
                            }
                        }
                    }
                }
            }
        }
        return new ReturnSet(false);
    }

    public ReturnSet generarAsignaciones(Asignacion asignacion, ArrayList<Clase> clases, Ocupaciones ocupaciones) {
        //out.println("Provando Asignacion:");
        //out.println(asignacion.toString() + "\n");
        if (!(this.comprovarRestricciones(asignacion, ocupaciones))) return new ReturnSet(false);
        ocupaciones.addAsignacion(asignacion);
        //out.println("asignacion valida");
        if (clases.size() == 0) return new ReturnSet(true, ocupaciones);
        for (int i = 0; i < clases.size(); ++i) {
            ReturnSet franja = getFranjaClase(clases.get(i));
            for (int dia = 1; dia <= 7; ++dia) {
                if ((!(this.limitacionesHorario.esDiaLibre(dia)))
                        && (!(comprovarSubGrupoDia(clases.get(i), dia, ocupaciones)))
                        && (!(comprovarGrupoDia(clases.get(i), dia, ocupaciones)))) {
                    for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {
                            if (!(aulaOcupada(clases.get(i), dia, horaIni, entry.getValue(), ocupaciones))) {
                                Asignacion nextAsignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i));
                                ReturnSet returnSet = this.generarAsignaciones(nextAsignacion, copyRemoveClases(clases, i), new Ocupaciones(ocupaciones));
                                if (returnSet.getValidez()) return returnSet;
                            }
                        }
                    }
                }
            }
        }
        return new ReturnSet(false);
    }

    public ReturnSet getFranjaClase(Clase clase) {
        int horaIni = this.limitacionesHorario.getHoraIni();
        int horaFin = this.limitacionesHorario.getHoraFin();
        if (clase.tieneNivel()) {
            ArrayList<Restriccion> restricciones = clase.getNivel().getRestricciones();
            Boolean found = false;
            for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
                if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaNivel) {
                    found = true;
                    horaIni = max(horaIni, ((FranjaNivel)(restricciones.get(i))).getHoraIni());
                    horaFin = min(horaFin, ((FranjaNivel)(restricciones.get(i))).getHoraFin());
                }
            }
        }
        ArrayList<Restriccion> restricciones = clase.getAsignatura().getRestricciones();
        boolean found = false;
        for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
            if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaAsignatura) {
                found = true;
                horaIni = max(horaIni, ((FranjaAsignatura)(restricciones.get(i))).getHoraIni());
                horaFin = min(horaFin, ((FranjaAsignatura)(restricciones.get(i))).getHoraFin());
            }
        }
        return new ReturnSet(horaIni, horaFin);
    }

    public Boolean aulaOcupada(Clase clase, int dia, int horaIni,  Aula aula,  Ocupaciones ocupaciones) {
        for (int hora = horaIni; hora < (horaIni + clase.getDuracion()); ++hora) {
            if (ocupaciones.getHora(dia, hora).tieneAula(aula)) return true;
        }
        return false;
    }

    public Boolean comprovarRestricciones(Asignacion asignacion, Ocupaciones ocupaciones) {
        return asignacion.comprovarRestricciones(ocupaciones);
    }

    public Boolean comprovarSubGrupoDia(Clase clase, int dia, Ocupaciones ocupaciones) {
        return ocupaciones.getDia(dia).tieneSubGrupo(clase.getSubGrupo());
    }

    public Boolean comprovarGrupoDia(Clase clase, int dia, Ocupaciones ocupaciones) {
        if (!(ocupaciones.getDia(dia).tieneGrupo(clase.getGrupo()))) return false;
        for (Map.Entry<String, SubGrupo> entry : clase.getGrupo().getSubGrupos().entrySet()) {
            if (ocupaciones.getDia(dia).tieneSubGrupo(entry.getValue())
                && (ocupaciones.getDia(dia).getSubGrupo(entry.getValue()).getTipo() != entry.getValue().getTipo())) return true;
        }
        return false;
    }

    static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
    }

    public void loadLimitacionesHorario() {
        Integer horaIni = 0;
        Integer horaFin = 24;
        for (int i = 0; i < this.planEstudios.getRestricciones().size(); ++i) {
            if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.DiaLibre) {
                this.limitacionesHorario.setDiaLibre(((DiaLibre)(this.planEstudios.getRestriccion(i))).getDia(), true);
            }
            else if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.FranjaTrabajo) {
                this.limitacionesHorario.setHoraIni(((FranjaTrabajo)this.planEstudios.getRestriccion(i)).getHoraIni());
                this.limitacionesHorario.setHoraFin(((FranjaTrabajo)this.planEstudios.getRestriccion(i)).getHoraFin());
            }
        }
    }

    public Map<String, Aula> getAulasAdecuadas(Clase clase) {
        if (clase.getTipoSesion() == TipoClase.Teoria) return this.planEstudios.getAulasTeoria(clase.getPlazas());
        else if (clase.getTipoSesion() == TipoClase.Laboratorio) return this.planEstudios.getAulasLaboratorio(clase.getPlazas());
        else return this.planEstudios.getAulasProblemas(clase.getPlazas());
    }

    static ArrayList<Clase> copyRemoveClases (ArrayList<Clase> oldClases, int i) {
        ArrayList<Clase> clases = copyClases(oldClases);
        clases.remove(i);
        return clases;
    }

    static ArrayList<Clase> copyClases (ArrayList<Clase> oldClases) {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        clases.addAll(oldClases);
        return clases;
    }

    public ArrayList<Clase> getAllClases() {
        return this.planEstudios.getAllClases();
    }

    public void setPlanEstudios(PlanEstudios planEstudios) {
        this.planEstudios = planEstudios;
    }

    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    public void setLimitacionesHorario(LimitacionesHorario limitacionesHorario) {
        this.limitacionesHorario = limitacionesHorario;
    }

    static int max(int x, int y) {
        if (x >= y) return x;
        return y;
    }

    static int min(int x, int y) {
        if (x <= y) return x;
        return y;
    }

    /** Consultoras **/

    public PlanEstudios getPlanEstudios() {
        return planEstudios;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    public Restriccion getRestriccion(int i) {
        return this.restricciones.get(i);
    }

    public LimitacionesHorario getLimitacionesHorario() {
        return limitacionesHorario;
    }

    /** Métodos redefinidos **/

}
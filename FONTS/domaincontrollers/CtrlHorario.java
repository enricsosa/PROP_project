package domaincontrollers;

/** Imports **/

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

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

        Horario horario = new Horario(id);
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        Ocupaciones ocupaciones = new Ocupaciones();
        ArrayList<Clase> clases = this.getAllClases();

        for (int dia = 1; dia < 7; ++dia) {
            if (!(this.limitacionesHorario.getDiaLibre(dia))) {
                for (int horaIni = this.limitacionesHorario.getHoraIni(); horaIni < (this.limitacionesHorario.getHoraFin() - 1); ++horaIni) {
                    for (int i = 0; i < clases.size(); ++i) {
                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {
                            Asignacion asignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i), clases.get(i).getRestricciones());
                            ReturnSet returnSet = this.generarAsignaciones(copyAsignaciones(asignaciones), asignacion, copyRemoveClases(clases, i), new Ocupaciones(ocupaciones));
                            if (returnSet.getValidez()) {
                                horario.setAssignaciones(returnSet.getAsignaciones());
                                return (new ReturnSet(true, horario));
                            }
                        }
                    }
                }
            }
        }
        return (new ReturnSet(false, horario));
    }

    public ReturnSet generarAsignaciones(ArrayList<Asignacion> asignaciones, Asignacion asignacion, ArrayList<Clase> clases, Ocupaciones ocupaciones) {
        if (this.excedeHoraMax(asignacion)) return new ReturnSet(false, asignaciones);
        if (this.noCabeSubGrupo(asignacion)) return new ReturnSet(false, asignaciones);
        if (this.aulaOcupada(asignacion, ocupaciones)) return new ReturnSet(false, asignaciones);
        if (this.comprovarGrupoDia(asignacion, ocupaciones)) return new ReturnSet(false, asignaciones);
        if (!(this.comprovarRestricciones(asignacion, ocupaciones))) return new ReturnSet(false, asignaciones);
        asignaciones.add(asignacion);
        ocupaciones.addAsignacion(asignacion);

        for (int dia = 1; dia < 7; ++dia) {
            if (!(this.limitacionesHorario.getDiaLibre(dia))) {
                for (int horaIni = this.limitacionesHorario.getHoraIni(); horaIni < (this.limitacionesHorario.getHoraFin() - 1); ++horaIni) {
                    for (int i = 0; i < clases.size(); ++i) {
                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {
                            Asignacion nextAsignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i), clases.get(i).getRestricciones());
                            ReturnSet returnSet = this.generarAsignaciones(copyAsignaciones(asignaciones), nextAsignacion, copyRemoveClases(clases, i), new Ocupaciones(ocupaciones));
                            if (returnSet.getValidez()) {
                                return returnSet;
                            }
                        }
                    }
                }
            }
        }
        return new ReturnSet(false, asignaciones);
    }

    public Boolean aulaOcupada(Asignacion asignacion, Ocupaciones ocupaciones) {
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            if (ocupaciones.getDia(asignacion.getDiaSemana()).getHora(hora).tieneAula(asignacion.getAula())) return true;
        }
        return false;
    }

    public Boolean comprovarRestricciones(Asignacion asignacion, Ocupaciones ocupaciones) {
        return asignacion.comprovarRestricciones(ocupaciones);
    }

    public Boolean noCabeSubGrupo(Asignacion asignacion) {
        return asignacion.noCabeSubGrupo();
    }

    public Boolean comprovarGrupoDia(Asignacion asignacion, Ocupaciones ocupaciones) {
        if (!(ocupaciones.getDia(asignacion.getDiaSemana()).tieneGrupo(asignacion.getGrupo()))) return false;
        for (Map.Entry<String, SubGrupo> entry : asignacion.getGrupo().getSubGrupos().entrySet()) {
            if ((entry.getValue().getId() != asignacion.getSubGrupo().getId()) && (entry.getValue().getTipo() != asignacion.getSubGrupo().getTipo()) && ocupaciones.getDia(asignacion.getDiaSemana()).tieneSubGrupo(entry.getValue())) return true;
        }
        return false;
    }

    public Boolean excedeHoraMax(Asignacion asignacion) {
        return asignacion.getHoraFin() > this.limitacionesHorario.getHoraFin();
    }

    public void loadLimitacionesHorario() {
        Integer horaIni = 0;
        Integer horaFin = 24;
        Boolean[] diasLibres = new Boolean[7];
        Arrays.fill(diasLibres, Boolean.FALSE);
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
        if (clase.getTipoSesion() == TipoClase.Teoria) return this.planEstudios.getAulasTeoria();
        else if (clase.getTipoSesion() == TipoClase.Laboratorio) return this.planEstudios.getAulasLaboratorio();
        else return this.planEstudios.getAulasProblemas();
    }

    static ArrayList<Asignacion> copyAsignaciones(ArrayList<Asignacion> oldAsignaciones) {
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        asignaciones.addAll(oldAsignaciones);
        return asignaciones;
    }

    static ArrayList<Asignacion> copyAddAsignacion(ArrayList<Asignacion> oldAsignaciones, Asignacion asignacion) {
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        for (int i = 0; i < oldAsignaciones.size(); ++i) {
            if (oldAsignaciones.get(i).getDiaSemana() >= asignacion.getDiaSemana() && oldAsignaciones.get(i).getHoraIni() >= asignacion.getHoraIni()) {
                asignaciones.add(asignacion);
            }
            asignaciones.add(oldAsignaciones.get(i));
        }
        if (asignaciones.size() == 0) asignaciones.add(asignacion);
        return asignaciones;
    }

    static ArrayList<Clase> copyRemoveClases (ArrayList<Clase> oldClases, int j) {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (int i = 0; i < oldClases.size(); ++i) {
            if (i != j) clases.add(oldClases.get(i));
        }
        return clases;
    }

    static ArrayList<Clase> copyClases (ArrayList<Clase> oldClases) {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (int i = 0; i < oldClases.size(); ++i) {
            clases.add(oldClases.get(i));
        }
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

    /** Consultoras **/

    public PlanEstudios getPlanEstudios() {
        return planEstudios;
    }

    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    public LimitacionesHorario getLimitacionesHorario() {
        return limitacionesHorario;
    }

    /** Métodos redefinidos **/

}

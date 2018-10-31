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
        this.loadLimitacionesHorario();
    }

    /** Métodos públicos **/

    public Horario generarHorario(String id) {

        Horario horario = new Horario(id);
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        Ocupaciones[][] ocupaciones = newOcupaciones();
        ArrayList<Clase> clases = this.getAllClases();

        for (int dia = 1; dia < 8; ++dia) {

            if (this.limitacionesHorario.getDiasLibres()[dia - 1]) {

                for (int horaIni = this.limitacionesHorario.getHoraIni(); horaIni < (this.limitacionesHorario.getHoraFin() - 1); ++horaIni) {

                    for (int i = 0; i < clases.size(); ++i) {

                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {

                            Asignacion asignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i), clases.get(i).getRestricciones());
                            ReturnSet returnSet = this.generarAsignaciones(copyAsignaciones(asignaciones), asignacion, copyRemoveClases(clases, i), copyOcupaciones(ocupaciones));

                            if (returnSet.getValidez()) {
                                horario.setAssignaciones(returnSet.getAsignaciones());
                                return horario;
                            }

                        }

                    }

                }

            }

        }
        return horario;
    }

    public ReturnSet generarAsignaciones(ArrayList<Asignacion> asignaciones, Asignacion asignacion, ArrayList<Clase> clases, Ocupaciones[][] ocupaciones) {
        return new ReturnSet(true, new ArrayList<Asignacion>());
    }

    public void loadLimitacionesHorario() {
        Integer horaIni = 0;
        Integer horaFin = 24;
        Boolean[] diasLibres = new Boolean[7];
        Arrays.fill(diasLibres, Boolean.FALSE);
        for (int i = 0; i < this.planEstudios.getRestricciones().size(); ++i) {
            if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.DiaLibre) {
                this.limitacionesHorario.setDiaLibre(((DiaLibre)this.planEstudios.getRestriccion(i)).getDia(), true);
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

    static Ocupaciones[][] newOcupaciones() {
        Ocupaciones[][] ocupaciones = new Ocupaciones[7][25]; // [i][0] es la acumulada del dia.
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; i < 24; ++j) {
                ocupaciones[i][j] = new Ocupaciones();
            }
        }
        return ocupaciones;
    }

    static Ocupaciones[][] copyOcupaciones(Ocupaciones[][] oldOcupaciones) {
        Ocupaciones[][] ocupaciones = new Ocupaciones[7][25]; // [i][0] es la acumulada del dia.
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; i < 24; ++j) {
                ocupaciones[i][j] = new Ocupaciones(oldOcupaciones[i][j]);
            }
        }
        return ocupaciones;
    }

    static ArrayList<Asignacion> copyAsignaciones(ArrayList<Asignacion> oldAsignaciones) {
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        for (int i = 0; i < oldAsignaciones.size(); ++i) {
            asignaciones.add(oldAsignaciones.get(i));
        }
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

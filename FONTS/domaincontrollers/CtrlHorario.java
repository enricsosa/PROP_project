package domaincontrollers;

/** Imports **/

import domain.*;

import java.util.ArrayList;
import java.util.Arrays;

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

    static ArrayList<Asignacion> addAsignacion(ArrayList<Asignacion> oldAsignaciones, Asignacion asignacion) {
        ArrayList<Asignacion> asignaciones = new ArrayList<Asignacion>();
        for (int i = 0; i < oldAsignaciones.size(); ++i) {
            if (oldAsignaciones.get(i).getDiaSemana() >= asignacion.getDiaSemana() && oldAsignaciones.get(i).getHoraIni() >= asignacion.getHoraIni()) {
                asignaciones.add(asignacion);
            }
            asignaciones.add(oldAsignaciones.get(i));
        }
        return asignaciones;
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

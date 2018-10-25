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
    private Ocupaciones[][] ocupaciones;

    /** Constructoras **/

    CtrlHorario(PlanEstudios planEstudios, ArrayList restricciones) {
        this.planEstudios = planEstudios;
        this.restricciones = restricciones;
        this.loadLimitacionesHorario();
        this.ocupaciones = new Ocupaciones[7][24];
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

    /** Consultoras **/

    /** Métodos redefinidos **/

}

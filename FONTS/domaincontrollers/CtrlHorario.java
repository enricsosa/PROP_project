/**CtrlHorario*/

/**Imports*/

package domaincontrollers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
//import static java.lang.System.out;
import java.util.Comparator;

import domain.*;

/**
 * CtrlHorario es un controlador de Dominio que se encarga de generar un Horario dado un escenario.
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Collections
 * @see     Map
 * @see     Comparator
 * @see     domain
 */
public class CtrlHorario {

    /**Atributos*/

    /**PlanEstudios del escenario del que se genera el Horario.*/
    private PlanEstudios planEstudios;
    /**Restricciones del escenario del que se genera el Horario.*/
    private ArrayList<Restriccion> restricciones;
    /**FranjaTrabajo y diasLibres del escenario del que se genera el Horario.*/
    private LimitacionesHorario limitacionesHorario;

    /**Constructoras*/

    CtrlHorario(PlanEstudios planEstudios, ArrayList restricciones) {
        this.planEstudios = planEstudios;
        this.restricciones = restricciones;
        this.limitacionesHorario = new LimitacionesHorario();
        this.loadLimitacionesHorario();
    }

    /**Métodos públicos*/

    public ReturnSet generarHorario(String id) {
        //out.println(this.planEstudios);
        //out.println("Se inicia generarHorario().\n");
        Horario horario = new Horario(id);
        Ocupaciones ocupaciones = new Ocupaciones();
        ArrayList<Clase> clases = this.getAllClases();
        //out.println("CP 1");
        //for(int i = 0; i < clases.size(); ++i) out.println(clases.get(i).toStringResumido());
        /////Collections.shuffle(clases);
        sortClases(clases, this.limitacionesHorario);
        //for(int i = 0; i < clases.size(); ++i) out.println(clases.get(i));
        //out.println("CP 2");
        for (int i = 0; i < clases.size(); ++i) {
            ReturnSet franja = getFranjaClase(clases.get(i), this.limitacionesHorario);
            //out.println("CP 3");
            for (int dia = 1; dia <= 7; ++dia) {
                //out.println(dia);
                //out.println("CP 4");
                if(!(this.limitacionesHorario.esDiaLibre(dia))) {
                    //out.println("CP 5");
                    for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                        //out.println("CP 6");
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

    public void sortClases(ArrayList<Clase> clases, LimitacionesHorario lh) {
        Collections.shuffle(clases);
        Collections.sort(clases, new Comparator<Clase>() {
            @Override
            public int compare(Clase c1, Clase c2)
            {
                ReturnSet rc1 = getFranjaClase(c1, lh);
                int fc1 = rc1.getHoraFin() - rc1.getHoraIni();

                ReturnSet rc2 = getFranjaClase(c2, lh);
                int fc2 = rc2.getHoraFin() - rc2.getHoraIni();

                return fc1 - fc2;
            }
        });
    }

    public ReturnSet generarAsignaciones(Asignacion asignacion, ArrayList<Clase> clases, Ocupaciones ocupaciones) {
        //out.println("CP 7");
        //out.println("Provando Asignacion:");
        //out.println(asignacion.toString() + "\n");
        //out.println(clases.size());
        //if (!(this.comprovarRestricciones(asignacion, ocupaciones))) {
            //out.println("asignacion no valida");
            //return new ReturnSet(false);
        //}
        ocupaciones.addAsignacion(asignacion);
        //out.println("asignacion valida");
        if (clases.size() == 0) return new ReturnSet(true, ocupaciones);

        for (int i = 0; i < clases.size(); ++i) {
            ReturnSet franja = getFranjaClase(clases.get(i), this.limitacionesHorario);
            if ((clases.size() % 2) == 0) {
                for (int dia = 1; dia <= 7; ++dia) {
                    //out.println(dia);
                    if ((!(this.limitacionesHorario.esDiaLibre(dia)))
                            && (!(comprovarSubGrupoDia(clases.get(i), dia, ocupaciones)))
                            && (!(comprovarGrupoDia(clases.get(i), dia, ocupaciones)))) {
                        for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                            if(this.comprovarRestricciones(clases.get(i), dia, horaIni, ocupaciones)) {
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
            }
            else {
                for (int dia = 7; dia >= 1; --dia) {
                    //out.println("CP 8");
                    //out.println(dia);
                    if ((!(this.limitacionesHorario.esDiaLibre(dia)))
                            && (!(comprovarSubGrupoDia(clases.get(i), dia, ocupaciones)))
                            && (!(comprovarGrupoDia(clases.get(i), dia, ocupaciones)))) {
                        for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                            if (this.comprovarRestricciones(clases.get(i), dia, horaIni, ocupaciones)) {
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
            }
        }
        return new ReturnSet(false);
    }

    static ReturnSet getFranjaClase(Clase clase, LimitacionesHorario limitacionesHorario) {
        int horaIni = limitacionesHorario.getHoraIni();
        int horaFin = limitacionesHorario.getHoraFin();
        if (clase.tieneNivel()) {
            ArrayList<Restriccion> restricciones = clase.getNivel().getRestricciones();
            Boolean found = false;
            for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
                if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaNivel) {
                    found = true;
                    horaIni = Aux.max(horaIni, ((FranjaNivel)(restricciones.get(i))).getHoraIni());
                    horaFin = Aux.min(horaFin, ((FranjaNivel)(restricciones.get(i))).getHoraFin());
                }
            }
        }
        ArrayList<Restriccion> restricciones = clase.getAsignatura().getRestricciones();
        boolean found = false;
        for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
            if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaAsignatura) {
                found = true;
                horaIni = Aux.max(horaIni, ((FranjaAsignatura)(restricciones.get(i))).getHoraIni());
                horaFin = Aux.min(horaFin, ((FranjaAsignatura)(restricciones.get(i))).getHoraFin());
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

    public Boolean comprovarRestricciones(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        return clase.comprovarRestricciones(dia, horaIni, ocupaciones);
    }

    public Boolean comprovarSubGrupoDia(Clase clase, int dia, Ocupaciones ocupaciones) {
        return ocupaciones.getDia(dia).tieneSubGrupo(clase.getSubGrupo());
    }

    public Boolean comprovarGrupoDia(Clase clase, int dia, Ocupaciones ocupaciones) {
        if (!(ocupaciones.getDia(dia).tieneGrupo(clase.getGrupo()))) return false;
        for (Map.Entry<String, SubGrupo> entry : clase.getGrupo().getSubGrupos().entrySet()) {
            if (clase.getSubGrupo() != entry.getValue()) {
                if (ocupaciones.getDia(dia).tieneSubGrupo(entry.getValue())) {
                    if (entry.getValue().getTipo() != clase.getSubGrupo().getTipo()) return true;
                }
            }
        }
        return false;
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
        //out.println(limitacionesHorario.toString());
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

    /**Consultoras*/

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

    /**Métodos redefinidos*/

}
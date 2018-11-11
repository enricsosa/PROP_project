package domain;

import java.util.Map;
import static java.lang.System.out;

/** Imports **/

public class NivelHora extends Restriccion {

    /** Atributos **/

    private Nivel nivel;

    /** Constructoras **/

    public NivelHora(Nivel nivel) {
        this.nivel = nivel;
    }

    /** Métodos públicos **/

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /** Consultoras **/

    public Nivel getNivel() {
        return this.nivel;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.NivelHora;
    }

    @Override
    public Boolean comprovarRestriccion(Asignacion asignacion, Ocupaciones ocupaciones) {
        //out.println("Se evalua NivelHora-------------------------------------");
        //out.println(asignacion);
        //out.println(asignacion.getDiaSemana());
        //out.println(asignacion.getHoraIni());
        //out.println(asignacion.getHoraFin());
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            //out.println(hora);
            //out.println(ocupaciones.getHora(asignacion.getDiaSemana(),hora).getGrupos().keySet());
            for (Map.Entry<String, Grupo> entry : ocupaciones.getHora(asignacion.getDiaSemana(),hora).getGrupos().entrySet()) {
                //out.println(asignacion.getGrupo().getId() + " " + entry.getValue().getId());
                if (entry.getValue().getId().equals(asignacion.getGrupo().getId())) {
                    //out.println("tienen el mismo grupo");
                    if (entry.getValue().tieneNivel()) {
                        //out.println(asignacion.getNivel());
                        //out.println("");
                        //out.println(entry.getValue().getNivel());
                        if ((entry.getValue().getNivel() == asignacion.getNivel()) && (entry.getValue().getAsignatura() != asignacion.getAsignatura())) {
                            //out.println("No se cumple");
                            return false;
                        }
                    }
                }
                //else out.println("No tienen el mismo grupo");
            }
        }
        //out.println("Se cumple");
        return true;
    }

    @Override
    public String toString() {
        return "Nivel hora: " + this.nivel.getNombre();
    }

}
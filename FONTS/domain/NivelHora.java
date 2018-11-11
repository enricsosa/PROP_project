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
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            for (Map.Entry<String, Grupo> entry : ocupaciones.getHora(asignacion.getDiaSemana(),hora).getGrupos().entrySet()) {
                if (entry.getValue().getId().equals(asignacion.getGrupo().getId())) {
                    if (entry.getValue().tieneNivel()) {
                        if ((entry.getValue().getNivel() == asignacion.getNivel()) && (entry.getValue().getAsignatura() != asignacion.getAsignatura())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Boolean comprovarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        for (int hora = horaIni; hora < (horaIni + clase.getDuracion()); ++hora) {
            for (Map.Entry<String, Grupo> entry : ocupaciones.getHora(dia,hora).getGrupos().entrySet()) {
                if (entry.getValue().getId().equals(clase.getGrupo().getId())) {
                    if (entry.getValue().tieneNivel()) {
                        if ((entry.getValue().getNivel() == clase.getNivel()) && (entry.getValue().getAsignatura() != clase.getAsignatura())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nivel hora: " + this.nivel.getNombre();
    }

}
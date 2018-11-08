package domain;

import java.util.Map;

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
        if (!(asignacion.tieneNivel())) return true;
        if (asignacion.getNivel() != this.nivel) return true;
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            for (Map.Entry<String, Grupo> entry : ocupaciones.getDia(asignacion.getDiaSemana()).getHora(hora).getGrupos().entrySet()) {
                if (entry.getValue().getId() == asignacion.getGrupo().getId()) {
                    if (entry.getValue().tieneNivel()) {
                        if (entry.getValue().getNivel() == asignacion.getNivel()) return false;
                    }
                }
            }
        }
        return true;
    }

}
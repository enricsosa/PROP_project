/**NivelHora*/

/**Imports*/

package domain;

import java.util.Map;

/**
 * NivelHora contiene la información de una Restriccion que indica que no se pueden dar Clases de mismo Nivel y Grupo
 * simultáneamente.
 * @author  Daniel Martín
 * @see     Map
 */
public class NivelHora extends Restriccion {

    /**Atributos*/

    /**Nivel al que se aplica la Restriccion.*/
    private Nivel nivel;

    /**Constructoras*/

    /**
     * Constructora de la Clase NivelHora.
     * @param nivel Nivel que se asigna a la Restriccion.
     */
    public NivelHora(Nivel nivel) {
        this.nivel = nivel;
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo Nivel a la Restriccion.
     * @param nivel Nuevo Nivel que se asignará a Restricción.
     */
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    /**Consultoras*/

    /**
     * Devuelve el Nivel de la Restriccion.
     * @return  Nivel de Retriccion.
     */
    public Nivel getNivel() {
        return this.nivel;
    }

    /**Métodos redefinidos*/

    /**
     * Devuelve el TipoRestriccion correspondiente.
     * @return  TipoRestriccion.NivelHora.
     */
    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.NivelHora;
    }

    /**
     * Comprueva que clase cumple la restricción respecto a un dia, horaIni y ocupaciones.
     * @param clase         Clase de la que se comprueba la Retriccion.
     * @param dia           dia en que se comprueba la Restriccion.
     * @param horaIni       horaIni con la que se comprueba la Restriccion.
     * @param ocupaciones   Ocupaciones respecto a las cuales se comprueba la Restriccion.
     * @return              true si se cumple la Restriccion con las condiciones dadas, false en caso contrario.
     */
    @Override
    public Boolean comprobarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
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

    /**
     * Convierte NivelHora a un String con su información.
     * @return  String con la información NivelHora.
     */
    @Override
    public String toString() {
        return "Nivel hora: " + this.nivel.getNombre();
    }

}
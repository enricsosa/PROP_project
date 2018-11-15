/**Dia*/

/**Imports*/

package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Dia contiene la información de lo que sucede un dia dentro de Ocupaciones.
 * @author  Daniel Martín
 * @see     Arrays
 * @see     HashMap
 * @see     Map
 */
public class Dia {

    /**Atributos*/

    /**Horas que pertenecen al Dia*/
    private Hora[] horas;

    /**Constructoras*/

    /**Constructora básica de la clase Dia.*/
    public Dia() {
        //this.restricciones = new ArrayList<Restriccion>();
        this.horas = new Hora[24];
        Arrays.fill(this.horas, new Hora());
    }

    /**
     * Constructora de la clase Dia que genera una copia de otro dia.
     * @param oldDia    Dia del cual se copian los datos.
     */
    public Dia(Dia oldDia) {
        //this.restricciones = new ArrayList<Restriccion>();
        //this.restricciones.addAll(oldDia.restricciones);
        this.horas = new Hora[24];
        for (int i = 0; i < 24; ++i) {
            this.horas[i] = new Hora(oldDia.getHora(i));
        }
    }

    /**Metodos publicos*/

    /**
     * Asigna un Array de Horas a Dia.
     * @param horas Array de Horas que se quiere asignar a Dia.
     */
    public void setHoras(Hora[] horas) {
        this.horas = horas;
    }

    /**
     * Asigna una Hora a la posicion i de un Array de Horas.
     * @param hora  Hora que se quiere asignar a la posicion i.
     * @param i     Posicion en el Array a la que se quiere asignar Hora.
     */
    public void setHora(Hora hora, int i) {
        this.horas[i] = hora;
    }

    /**Consultoras*/

    /**
     * Devuelve los Niveles de Dia.
     * @return  ArrayList con los Niveles de Dia.
     */
    public Map<String, Nivel> getNiveles() {
        Map<String, Nivel> niveles = this.horas[0].getNiveles();
        for (int i = 1; i < 24; ++i) {
            niveles.putAll(this.horas[i].getNiveles());
        }
        return niveles;
    }

    /**
     * Indica si en Dia hay un Nivel.
     * @param nivel Nivel que queremos saber si tiene Dia.
     * @return      true si dia tiene nivel, false en caso contrario.
     */
    public Boolean tieneNivel(Nivel nivel) {
        return (this.getNiveles().get(nivel.getNombre()) != null);
    }

    /**
     * Devuelve un Nivel de Dia a partir de su nombre.
     * @param nombre    nombre del nivel que se queire obtener.
     * @return          Devuelve el Nivel con el nombre indicado.
     */
    public Nivel getNivel(String nombre) {
        return this.getNiveles().get(nombre);
    }

    /**
     * Devuelve el conjunto de Asignaturas que tiene Dia.
     * @return  ArrayList de Asignaturas que tiene Dia.
     */
    public Map<String, Asignatura> getAsignaturas() {
        Map<String, Asignatura> asignaturas = this.horas[0].getAsignaturas();
        for (int i = 1; i < 24; ++i) {
            asignaturas.putAll(this.horas[i].getAsignaturas());
        }
        return asignaturas;
    }

    /**
     * Devuelve una Asignatura con el nombre indicado.
     * @param nombre    nombre de la Asignatura que se quiere obtener.
     * @return          Devuelve la Asignatura con le nombre indicado.
     */
    public Asignatura getAsignatura(String nombre) {
        return this.getAsignaturas().get(nombre);
    }

    /**
     * Indica si asignatura se encuentra en Dia.
     * @param asignatura    Asignatura que se quiere comprovar si está en Dia.
     * @return              true si asignatura se encuentra en Dia, false en caso contrario.
     */
    public Boolean tieneAsignatura(Asignatura asignatura) {
        return (this.getAsignaturas().get(asignatura.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Aulas que tiene Dia.
     * @return  ArrayList con las Aulas de Dia.
     */
    public Map<String, Aula> getAulas() {
        Map<String, Aula> aulas = this.horas[0].getAulas();
        for (int i = 1; i < 24; ++i) {
            aulas.putAll(this.horas[i].getAulas());
        }
        return aulas;
    }

    /**
     * Devuelve un Aula de Dia a partir de la id de la misma.
     * @param id    id del Aula que se quiere obtener.
     * @return      Aula de Dia con id igual al id dado.
     */
    public Aula getAula(String id) {
        return this.getAulas().get(id);
    }

    /**
     * Indica si una Aula se encuentra entre las Aulas de Dia.
     * @param aula  Aula de la que se quiere saber si está en Dia.
     * @return      true si aula se encuentra entre las Aulas de Dia, false en caso contrario.
     */
    public Boolean tieneAula(Aula aula) {
        return (this.getAulas().get(aula.getId()) != null);
    }

    /**
     * Devuelve el conjunto de Grupos que tiene Dia.
     * @return  ArrayList que contiene los Grupos de Dia.
     */
    public Map<String, Grupo> getGrupos() {
        Map<String, Grupo> grupos = this.horas[0].getGrupos();
        for (int i = 1; i < 24; ++i) {
            grupos.putAll(this.horas[i].getGrupos());
        }
        return grupos;
    }

    /**
     * Indica si una Grupo se encuentra entre las Grupos de Dia.
     * @param grupo Grupo del que se quiere saber si está en Dia.
     * @return      true si grupo se encuentra entre los grupos de Dia, false en caso contrario.
     */
    public Boolean tieneGrupo(Grupo grupo) {
        return (this.getGrupos().get(grupo.getId() + grupo.getAsignatura().getId()) != null);
    }

    /**
     * Devuelve el conjunto de SubGrupos que tiene Dia.
     * @return  ArrayList que contiene los SubGrupos de Dia.
     */
    public Map<String, SubGrupo> getSubGrupos() {
        Map<String, SubGrupo> subGrupos = this.horas[0].getSubGrupos();
        for (int i = 1; i < 24; ++i) {
            subGrupos.putAll(this.horas[i].getSubGrupos());
        }
        return subGrupos;
    }

    /**
     * Indica si un SubGrupo se encuentra entre las SubGrupos de Dia.
     * @param subGrupo  SubGrupo del que se quiere saber si está en Dia.
     * @return          true si subGrupo se encuentra entre los subGrupos de Dia, false en caso contrario.
     */
    public Boolean tieneSubGrupo(SubGrupo subGrupo) {
        return (this.getSubGrupos().get(subGrupo.getIdCompleta() + subGrupo.getAsignatura().getId() + Aux.strTipo(subGrupo.getTipo())) != null);
    }

    //public ArrayList<Restriccion> getRestricciones() {
      //  return restricciones;
    //}

    //public Restriccion getRestriccion(int i) {
      //  return this.restricciones.get(i);
    //}

    /**
     * Devuelve el Array de Horas que tiene Dia.
     * @return  Array con las Horas de Dia.
     */
    public Hora[] getHoras() {
        return horas;
    }

    /**
     * Devuelve la Hora que se encuentra en una posición del Array de Horas de Dia.
     * @param hora  Posición de la Hora que se quiere obtener dentro del Array de Horas de Dia.
     * @return      Hora que se enceuntra en la posición hora dentro del Array de Horas de Dia.
     */
    public Hora getHora(int hora) {
        return this.horas[hora];
    }

    /**Métodos redefinidos*/

}
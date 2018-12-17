/**PlanEstudios*/

/**Imports*/

package domain;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * PlanEstudios contiene toda la información para generar un Horario.
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Map
 * @see     HashMap
 * @see     Collectors
 */
public class PlanEstudios {

    /**Atributos*/

    /**Nombre que identifica el PlanEstudios.*/
    private String nombre;
    /**Niveles que tiene el PlanEstudios.*/
    private Map<String, Nivel> niveles;
    /**Asignaturas que tiene el PlanEstudios.*/
    private Map<String, Asignatura> asignaturas;
    /**Aulas que tiene el PlanEstudios.*/
    private Map<String, Aula> aulas;
    //private Horario horarioGeneral;
    /**Restricciones que afectan al PlanEstudios.*/
    private ArrayList<Restriccion> restricciones;
    /**Todas las restricciones de PlanEstudios y los elementos que contiene.*/
    private ArrayList<Restriccion> allRestricciones;

    /**Constructoras*/

    /**
     * Constructora de PlanEstudios.
     * @param nombre    nombre que se asigna a PlanEstudios.
     */
    public PlanEstudios(String nombre) {
        this.nombre = nombre;
        this.niveles = new HashMap<String, Nivel>();
        this.asignaturas = new HashMap<String, Asignatura>();
        this.aulas = new HashMap<String, Aula>();
        this.restricciones = new ArrayList<Restriccion>();
        this.allRestricciones = new ArrayList<Restriccion>();
    }

    /**Métodos públicos*/

    /**
     * Devuelve todas las Clases que se pueden generar en el PlanEstudios.
     * @return  Clases que se pueden generar en el PlanEstudios.
     */
    public ArrayList<Clase> getAllClases() {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) {
            clases.addAll(entry.getValue().getAllClases());
        }
        return clases;
    }

    /**
     * Asigna un nuevo nombre a PlanEstudios.
     * @param nombre    Nuevo nombre que se asignará a PlanEstudios.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Asigna un conjunto de Restricciones a PlanEstudios.
     * @param restricciones Conjunto de Restricciones que se asignan a PlanEstudios.
     */
    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
    }

    /**
     * Asigna un nuevo conjunto de Restricciones a allRestricciones.
     * @param restricciones Conjunto de Restricciones que se asignan a allRestricciones.
     */
    public void setAllRestricciones(ArrayList<Restriccion> restricciones) {
        this.allRestricciones = restricciones;
    }

    /**
     * Añade un Nivel a PlanEstudios.
     * @param nivel     Nivel que se añade a PlanEstudios.
     */
    public void addNivel(Nivel nivel) {
        this.niveles.putIfAbsent(nivel.getNombre(), nivel);
    }

    /**
     * Elimina de PlanEstudios el Nivel con el nombre introducido.
     * @param nombre    nombre del Nivel que se quiere eliminar.
     */
    public void eliminarNivel(String nombre) {
        this.niveles.remove(nombre);
    }

    /**
     * Añade una Asignatura a PlanEstudios.
     * @param asignatura    Asignatura que se añade a PlanEstudios.
     */
    public void addAsignatura(Asignatura asignatura) {
        this.asignaturas.putIfAbsent(asignatura.getId(), asignatura);
    }

    /**
     * Elimina de PlanEstudios la Asignatura con la id introducida.
     * @param id    id de la Asignatura que se quiere eliminar.
     */
    public void eliminarAsignatura(String id) {
        this.asignaturas.remove(id);
    }

    /**
     * Añade un Aula a PlanEstudios.
     * @param aula  Aula que se quiere añadir a PlanEstudios.
     */
    public void addAula(Aula aula) {
        this.aulas.putIfAbsent(aula.getId(), aula);
    }

    /**
     * Elimina el Aula de PlanEstudios con la id indicada.
     * @param id    id del Aula que se quiere eliminar.
     */
    public void eliminarAula(String id) {
        this.aulas.remove(id);
    }

    /**
     * Añade una Restriccion a PlanEstudios.
     * @param restriccion   Restriccion que se quiere añadir a PlanEstudios.
     */
    public void addRestriccion(Restriccion restriccion) {
        this.restricciones.add(restriccion);
    }

    /**
     * Elimina la Restricion del conjunto de Restricciones de PlanEstudios que se encuentra en la posicion indicada.
     * @param posicion  posicion de la Restriccion que se quiere eliminar.
     */
    public void eliminarRestriccion(Integer posicion) {
        this.restricciones.remove(posicion);
    }

    /**
     * Añade una Restriccion a allRestricciones.
     * @param restriccion   Restriccion que se quiere añadir a allRestricciones.
     */
    public void addAllRestriccion(Restriccion restriccion) {
        this.allRestricciones.add(restriccion);
    }

    /**
     * Elimina la Restricion del conjunto de Restricciones de allRestricciones que se encuentra en la posicion indicada.
     * @param posicion  posicion de la Restriccion que se quiere eliminar.
     */
    public void eliminarAllRestriccion(Integer posicion) {
        this.allRestricciones.remove(posicion);
    }

    /**
     * Elimina una FranjaAsignatura del PlanEstudios.
     * @param idAsignatura  id de la Asignatura afectado.
     * @param horaIni       hora de inicio de la franja.
     * @param horaFin       hora de fin de la franja.
     */
    public void eliminarFranjaAsignatura(String idAsignatura, int horaIni, int horaFin) {
        for (int i = 0; i < this.allRestricciones.size(); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaAsignatura) {
                if ((((FranjaAsignatura)this.allRestricciones.get(i)).getAsignatura().getId().equals(idAsignatura))
                        && (((FranjaAsignatura)this.allRestricciones.get(i)).getHoraIni().equals(horaIni))
                        && (((FranjaAsignatura)this.allRestricciones.get(i)).getHoraFin().equals(horaFin))) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Elimina una NivelHora del PlanEstudios.
     * @param nombreNivel   id del Nivel afectado.
     */
    public void eliminarNivelHora(String nombreNivel) {
        for (int i = 0; i < this.allRestricciones.size(); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.NivelHora) {
                if (((NivelHora)this.allRestricciones.get(i)).getNivel().getNombre().equals(nombreNivel)) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Elimina una FranjaNivel del PlanEstudios.
     * @param nombreNivel   id del Nivel afectado.
     * @param horaIni       hora de inicio de la franja.
     * @param horaFin       hora de fin de la franja.
     */
    public void eliminarFranjaNivel(String nombreNivel, int horaIni, int horaFin) {
        for (int i = 0; i < this.allRestricciones.size(); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaNivel) {
                if ((((FranjaNivel)this.allRestricciones.get(i)).getNivel().getNombre().equals(nombreNivel))
                        && (((FranjaNivel)this.allRestricciones.get(i)).getHoraIni().equals(horaIni))
                        && (((FranjaNivel)this.allRestricciones.get(i)).getHoraFin().equals(horaFin))) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Elimina una FranjaTrabajo del PlanEstudios.
     * @param horaIni   hora de inicio de la franja.
     * @param horaFin   hora de fin de la franja.
     */
    public void eliminarFranjaTrabajo(int horaIni, int horaFin) {
        for (int i = 0; i < this.allRestricciones.size(); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaTrabajo) {
                if ((((FranjaTrabajo)this.allRestricciones.get(i)).getHoraIni().equals(horaIni))
                        && (((FranjaTrabajo)this.allRestricciones.get(i)).getHoraFin().equals(horaFin))) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
        for (int i = 0; i < this.restricciones.size(); ++i) {
            if (this.restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaTrabajo) {
                if ((((FranjaTrabajo)this.restricciones.get(i)).getHoraIni().equals(horaIni))
                        && (((FranjaTrabajo)this.restricciones.get(i)).getHoraFin().equals(horaFin))) {
                    this.restricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Elimina un Correquisito del PlanEstudios.
     * @param idAsignatura1 id de una Asignatura del Correquisito.
     * @param idAsignatura2 id de una Asignatura del Correquisito.
     */
    public void eliminarCorrequisito(String idAsignatura1, String idAsignatura2) {
        for (int i = 0; (i < this.allRestricciones.size()); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.Correquisito) {
                if (((((Correquisito)this.allRestricciones.get(i)).getAsignatura1().getId().equals(idAsignatura1))
                        && (((Correquisito)this.allRestricciones.get(i)).getAsignatura2().getId().equals(idAsignatura2)))
                        || ((((Correquisito)this.allRestricciones.get(i)).getAsignatura2().getId().equals(idAsignatura1))
                        && (((Correquisito)this.allRestricciones.get(i)).getAsignatura1().getId().equals(idAsignatura2)))) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Elimina un Prerrequisito del PlanEstudios.
     * @param idAsignatura      id de la Asignatura que tiene el Prerrequisito.
     * @param idPrerrequisito   id de la Asignatura prerrequisito.
     */
    public void eliminarPrerrequisito(String idAsignatura, String idPrerrequisito) {
        for (int i = 0; (i < this.allRestricciones.size()); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.Prerrequisito) {
                if ((((Prerrequisito)this.allRestricciones.get(i)).getAsignatura().getId().equals(idAsignatura))
                        && (((Prerrequisito)this.allRestricciones.get(i)).getPrerrequisito().getId().equals(idPrerrequisito))) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**
     * Elimina un DiaLibre de PlanEstudios.
     * @param diaSemana diaSemana del DiaLibre que se quiere eliminar.
     */
    public void eliminarDiaLibre(int diaSemana) {
        for (int i = 0; i < this.restricciones.size(); ++i) {
            if (this.restricciones.get(i).getTipoRestriccion() == TipoRestriccion.DiaLibre) {
                if (((DiaLibre)this.restricciones.get(i)).getDia() == diaSemana) {
                    this.restricciones.remove(i);
                    --i;
                }
            }
        }
        for (int i = 0; i < this.allRestricciones.size(); ++i) {
            if (this.allRestricciones.get(i).getTipoRestriccion() == TipoRestriccion.DiaLibre) {
                if (((DiaLibre)this.allRestricciones.get(i)).getDia() == diaSemana) {
                    this.allRestricciones.remove(i);
                    --i;
                }
            }
        }
    }

    /**Consultoras*/

    /**
     * Devuelve un String con el nombre del PlanEstudios.
     * @return  String con el nombre del PlanEstudios.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Devuelve un Map con los Niveles de PlanEstudios.
     * @return  Map con los Niveles de PlanEstudios.
     */
    public Map<String, Nivel> getNiveles() {
        return this.niveles;
    }

    /**
     * Comprueba si planEstudios tiene un Nivel con el nombre indicado.
     * @param nombre    nombre del nivel que se quiere comprobar.
     * @return          true si tiene el Nivel, false en caso contrario.
     */
    public Boolean tieneNivel(String nombre) {
        return this.niveles.containsKey(nombre);
    }

    /**
     * Dado su nombre devuelve un Nivel de PlanEstudios.
     * @param nombre    Nombre del Nivel que se quiere obtener.
     * @return          niveld e PlanEstudios con el nombre dado.
     */
    public Nivel getNivel(String nombre) {
        return this.niveles.get(nombre);
    }

    /**
     * Devuelve un Map con las Asignaturas de PlanEstudios.
     * @return  Map con las Asignaturas de PlanEstudios.
     */
    public Map<String, Asignatura> getAsignaturas() {
        return this.asignaturas;
    }

    /**
     * Comprueba si planEstudios tiene una Asignatura con la id indicada.
     * @param id    id de la Asignatura que se quiere comprobar.
     * @return      true si tiene la Asignatura, false en caso contrario.
     */
    public Boolean tieneAsignatura(String id) {
        return this.asignaturas.containsKey(id);
    }

    /**
     * Dado su id devuelve una Asignatura de PlanEstudios.
     * @param id    id de la Asignatura que se quiere obtener.
     * @return      Asignatura de PlanEstudios con la id dada.
     */
    public Asignatura getAsignatura(String id) {
        return this.asignaturas.get(id);
    }

    /**
     * Devuelve un Map con las Aulas de PlanEstudios.
     * @return  Map con las Aulas de PlanEstudios.
     */
    public Map<String, Aula> getAulas() {
        return this.aulas;
    }

    /**
     * Comprueba si planEstudios tiene una Aula con la id dada.
     * @param id    id del Aula que se quiere comprobar.
     * @return      true si tiene el Aula, false en caso contrario.
     */
    public Boolean tieneAula(String id) {
        return this.aulas.containsKey(id);
    }

    /**
     * Devuelve una Aula con la id dada.
     * @param id    id del Aula.
     * @return      Map con las Aulas de PlanEstudios.
     */
    public Aula getAula(String id) {
        return this.aulas.get(id);
    }

    /**
     * Devuelve un Map con las Aulas compatibles con Clases de Teoria de PlanEstudios.
     * @return  Map con las Aulas compatibles con Clases de Teoria de PlanEstudios.
     */
    public Map<String, Aula> getAulasTeoria(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneTeoria() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve un Map con las Aulas compatibles con Clases de Laboratorio de PlanEstudios.
     * @return  Map con las Aulas compatibles con Clases de Laboratorio de PlanEstudios.
     */
    public Map<String, Aula> getAulasLaboratorio(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneLaboratorio() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve un Map con las Aulas compatibles con Clases de Problemas de PlanEstudios.
     * @return  Map con las Aulas compatibles con Clases de Problemas de PlanEstudios.
     */
    public Map<String, Aula> getAulasProblemas(int plazas) {
        return this.aulas.entrySet().stream()
                .filter(map -> (map.getValue().tieneProblemas() && (map.getValue().getPlazas() >= plazas)))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    /**
     * Devuelve el conjunto de Restricciones de PlanEstudios.
     * @return  ArrayList con las Restricciones de PlanEstudios.
     */
    public ArrayList<Restriccion> getRestricciones() {
        return this.restricciones;
    }

    /**
     * Dada su posicion en el conjunto de Restricciones de PlanEstudios devuelve una Restriccion.
     * @param posicion  posicion en el conjunto de Restricciones de PlanEstudios de la Restriccion que se quiere obtener.
     * @return          Restriccion que se encuentra en la posicion dada dentro del conjunto de Restricciones de PlanEstudios.
     */
    public Restriccion getRestriccion(int posicion) {
        return this.restricciones.get(posicion);
    }

    /**
     * Devuelve el conjunto de Restricciones de allRestricciones.
     * @return  ArrayList con las Restricciones de allRestricciones.
     */
    public ArrayList<Restriccion> getAllRestricciones() {
        return this.allRestricciones;
    }

    /**
     * Dada su posicion en el conjunto de Restricciones de allRestricciones devuelve una Restriccion.
     * @param posicion  posicion en el conjunto de Restricciones de allRestricciones de la Restriccion que se quiere obtener.
     * @return          Restriccion que se encuentra en la posicion dada dentro del conjunto de Restricciones de allRestricciones.
     */
    public Restriccion getAllRestriccion(int posicion) {
        return this.allRestricciones.get(posicion);
    }

    /**
     * Devuelve un Map con todas las Restricciones clasificadas por TipoRestriccion.
     * @return  Map con todas las Restricciones clasificadas por TipoRestriccion.
     */
    public Map<TipoRestriccion, ArrayList<Restriccion>> getAllRestriccionesCategorizadas() {
        Map<TipoRestriccion, ArrayList<Restriccion>> allRestriccionesCategorizadas = new HashMap<TipoRestriccion, ArrayList<Restriccion>>();
        allRestriccionesCategorizadas.put(TipoRestriccion.DiaLibre, new ArrayList<Restriccion>());
        allRestriccionesCategorizadas.put(TipoRestriccion.FranjaAsignatura, new ArrayList<Restriccion>());
        allRestriccionesCategorizadas.put(TipoRestriccion.FranjaNivel, new ArrayList<Restriccion>());
        allRestriccionesCategorizadas.put(TipoRestriccion.FranjaTrabajo, new ArrayList<Restriccion>());
        allRestriccionesCategorizadas.put(TipoRestriccion.Correquisito, new ArrayList<Restriccion>());
        allRestriccionesCategorizadas.put(TipoRestriccion.NivelHora, new ArrayList<Restriccion>());
        allRestriccionesCategorizadas.put(TipoRestriccion.Prerrequisito, new ArrayList<Restriccion>());
        for (int i = 0; i < this.allRestricciones.size(); ++i) {
            Restriccion restriccion = this.allRestricciones.get(i);
            allRestriccionesCategorizadas.get(restriccion.getTipoRestriccion()).add(restriccion);
        }
        return allRestriccionesCategorizadas;
    }

    /**Métodos redefinidos*/

    /**
     * Convierte PlanEstudios en un String que contiene toda su información.
     * @return  String que contiene toda la información de PlanEstudios.
     */
    @Override
    public String toString() {
        String text = "Plan Estudios: " + this.nombre + "\n-Niveles:";
        for (Map.Entry<String, Nivel> entry : this.niveles.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n\n-Asignaturas:";
        for (Map.Entry<String, Asignatura> entry : this.asignaturas.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n\n-Aulas:";
        for (Map.Entry<String, Aula> entry : this.aulas.entrySet()) text += ("\n-- " + entry.getValue().toString());
        text += "\n\n-Restricciones:";
        for (int i = 0; i < this.restricciones.size(); ++i) text += ("\n-- " + this.restricciones.get(i).toString());
        return text;
    }

}
/**CtrlDomain*/

/**Imports*/

package domaincontrollers;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.util.Map;
//import static java.lang.System.out;

import domain.*;

import data.*;

/**
 * CtrlDominio es el controlador del Dominio que coordina todos los controladores de Dominio, las clases de Dominio y se comunica con las capas
 * de Presentacion y Datos.
 * @author  Enric Sosa
 * @see     IOException
 * @see     List
 * @see     ArrayList
 * @see     JSONObject
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     domain.PlanEstudios
 * @see     domain.Restriccion
 * @see     domain.Nivel
 * @see     domain.Asignatura
 * @see     domain.Sesion
 * @see     domain.Grupo
 * @see     domain.SubGrupo
 * @see     domain.TipoClase
 * @see     domain.Aula
 * @see     domain.DiaLibre
 * @see     domain.FranjaTrabajo
 * @see     domain.NivelHora
 * @see     domain.Correquisito
 * @see     domain.Prerrequisito
 * @see     domain.FranjaAsignatura
 * @see     domain.FranjaNivel
 * @see     domain.Aux
 * @see     data.CtrlAsignaturasFile
 * @see     data.CtrlAulasFile
 * @see     data.CtrlPlanEstudiosFile
 * @see     data.CtrlRestriccionesFile
 * @see     data.CtrlEscenariosDir
 */
public class CtrlDomain {

    /**Atributos*/

    /**Controlador para cargar Asignaturas.*/
    private CtrlAsignaturasFile controladorAsignaturas;
    /**Controlador para cargar Aulas.*/
    private CtrlAulasFile controladorAulas;
    /**Controlador para cargar PlanEstudios.*/
    private CtrlPlanEstudiosFile controladorPlanEstudios;
    /**Controlador para cargar Restricciones.*/
    private CtrlRestriccionesFile controladorRestricciones;
    /**Controlador para gestionar escenarios.*/
    private CtrlEscenariosDir controladorEscenarios;
    /**PlanEstudios con el que trabaja CtrlDominio.*/
    private PlanEstudios planEstudios;
    /**Horario con el que se trabaja.*/
    private Horario horarioActivo;

    /**Constructoras*/

    /**
     * Creadora de la clase CtrlDomain.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public CtrlDomain()throws FileNotFoundException, IOException, ParseException  {
        this.initCtrlDomain();
    }

    /**
     * Ejecuta las acciones necesarias para la inicialización de CtrlDomain.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void initCtrlDomain() throws FileNotFoundException, IOException, ParseException {
        controladorAsignaturas = CtrlAsignaturasFile.getInstance();
        controladorAulas = CtrlAulasFile.getInstance();
        controladorPlanEstudios = CtrlPlanEstudiosFile.getInstance();
        controladorRestricciones = CtrlRestriccionesFile.getInstance();
        this.cargarPlanEstudios("Default");
        this.cargarAllAsignaturas("Default");
        this.cargarAllAulas("Default");
        this.cargarAllRestricciones("Default");
        controladorEscenarios = CtrlEscenariosDir.getInstance();
    }

    /**Métodos públicos*/

    /**Asigna un Horario a horarioActivo.*/
    public void setHorarioActivo(Horario horarioActivo) {
        this.horarioActivo = horarioActivo;
    }

    /**
     * Mueve una Asignacion de un Horario.
     * @param idAsignatura          id de la Asignatura de la Asignacion que se quiere mover.
     * @param idSubGrupoCompleta    idCompleta del SubGrupo de la Asignatura de la Asignacion que se quiere mover.
     * @param dia                   dia donde se encuentra la Asignacion que se quiere mover.
     * @param hora                  hora donde se encuentra la Asignacion que se quiere mover.
     * @param nuevoDia              dia la que se quiere mover la Asignacion.
     * @param nuevaHora             hora la que se quiere mover la Asignacion.
     * @return                      Devuelve un ReturnSet con un Boolean y un mensaje informando de si se ha podido mover
     *                              la Asignacion, y si se ha podido, el Horario resultante.
     */
    public ReturnSet moverAsignacion(String idAsignatura, String idSubGrupoCompleta, int dia, int hora, int nuevoDia, int nuevaHora) {
        if (this.horarioActivo == null) return new ReturnSet(false, "No hay ningún Horario seleccionado.");
        Hora h = this.horarioActivo.getHora(dia, hora);
        boolean found = false;
        Asignacion asignacion = null;
        for (Map.Entry<String, Asignacion> entry : h.getAsignaciones().entrySet()) {
            if ((entry.getValue().getAsignatura().getId().equals(idAsignatura)) && (entry.getValue().getSubGrupo().getIdCompleta().equals(idSubGrupoCompleta))) {
                found = true;
                asignacion = entry.getValue();
            }
        }
        if (!(found)) return new ReturnSet(false, "No se ha podido encontrar la Asignacion a mover.");
        Horario nuevoHorario = new Horario(this.horarioActivo);
        nuevoHorario.eliminarAsignacion(asignacion);
        Clase clase = asignacion.getClase();
        if (CtrlHorario.comprovarSubGrupoDia(clase, nuevoDia, nuevoHorario)
            || CtrlHorario.comprovarGrupoDia(clase, nuevoDia, nuevoHorario)) {
            return new ReturnSet(false, "No se puede mover la Asignacion al dia indicado.");
        }
        if (CtrlHorario.aulaOcupada(clase, nuevoDia, nuevaHora, asignacion.getAula(), nuevoHorario)) {
            return new ReturnSet(false, "No se puede mover la Asignacion a la hora indicada.");
        }
        if (!(CtrlHorario.comprobarRestricciones(clase, nuevoDia, nuevaHora, nuevoHorario))) {
            return new ReturnSet(false, "No se puede mover la Asignacion a la hora indicada.");
        }
        Asignacion nuevaAsignacion = new Asignacion(nuevaHora, nuevoDia, asignacion.getAula(), clase);
        nuevoHorario.addAsignacion(nuevaAsignacion);
        this.horarioActivo = nuevoHorario;
        return new ReturnSet(true, "Se ha podido mover la Asignacion", nuevoHorario);
    }

    /**
     * Añade una NivelHora a planEstudios.
     * @param nombreNivel   nombre del Nivel involucrado.
     * @return              ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addNivelHora(String nombreNivel) {
        if (!(this.planEstudios.tieneNivel(nombreNivel))) {
            return new ReturnSet(false, "No existe Nivel con el nombre dado.");
        }
        this.addNivelHora(new NivelHora(this.planEstudios.getNivel(nombreNivel)));
        return new ReturnSet(true, "Se ha añadido NivelHora correctamente.");
    }

    /**
     * Añade una NivelHora a planEstudios.
     * @param nivelHora nivelHora involucrado.
     */
    private void addNivelHora(NivelHora nivelHora) {
        this.planEstudios.addAllRestriccion(nivelHora);
        nivelHora.getNivel().addRestriccion(nivelHora);
    }

    /**
     * Elimina un NivelHora.
     * @param nombreNivel   nombre del Nivel involucrado.
     * @return              ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarNivelHora(String nombreNivel) {
        if (!(this.planEstudios.tieneNivel(nombreNivel))) {
            return new ReturnSet(false, "No existe Nivel con el nombre dado.");
        }
        this.planEstudios.eliminarNivelHora(nombreNivel);
        this.planEstudios.getNivel(nombreNivel).eliminarNivelHora();
        return new ReturnSet(true, "NivelHora eliminado correctamente.");
    }

    /**
     * Añade una Sesion a una Asignatura.
     * @param duracion      horas que dura la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @return              ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addSesion(int duracion, String tipo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe la Asignatura con la id dada.");
        }
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas"))) {
            return new ReturnSet(false, "El tipo dado no es válido.");
        }
        this.planEstudios.getAsignatura(idAsignatura).addSesion(new Sesion(duracion, TipoClase.valueOf(tipo), this.planEstudios.getAsignatura(idAsignatura)));
        return new ReturnSet(true, "Se ha añadido la Sesion correctamente.");
    }

    /**
     * Elimina una Sesion de una Asignatura.
     * @param duracion      horas que dura la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @return              ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarSesion(int duracion, String tipo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe la Asignatura con la id dada.");
        }
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas"))) {
            return new ReturnSet(false, "El tipo dado no es válido.");
        }
        ArrayList<Sesion> sesiones = this.planEstudios.getAsignatura(idAsignatura).getSesiones();
        boolean found = false;
        for (int i = 0; i < sesiones.size() && !found; ++i) {
            if (sesiones.get(i).getDuracion().equals(duracion) && sesiones.get(i).getTipo().equals(TipoClase.valueOf(tipo))) {
                found = true;
                sesiones.remove(i);
            }
        }
        return new ReturnSet(true, "Se ha eliminado la Sesion correctamente.");
    }

    /**
     * Añade una FranjaAsignatura a planEstudios.
     * @param   idAsignatura id de la Asignatura afectada.
     * @param   horaIni hora de inicio de la franja.
     * @param   horaFin hora de fin de la franja.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addFranjaAsignatura(String idAsignatura, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe la Asignatura dada.");
        }
        if (horaIni < 0 || horaFin > 24) {
            return new ReturnSet(false, "La franja horaria introducida no es válida.");
        }
        this.addFranjaAsignatura(new FranjaAsignatura(this.planEstudios.getAsignatura(idAsignatura), horaIni, horaFin));
        return new ReturnSet(true, "Se ha añadido FranjaAsignatura correctamente.");
    }

    /**
     * Añade una FranjaAsignatura a planEstudios.
     * @param   franjaAsignatura FranjaAsignatura que se quiere añadir.
     */
    private void addFranjaAsignatura(FranjaAsignatura franjaAsignatura) {
        this.planEstudios.addAllRestriccion(franjaAsignatura);
        this.planEstudios.getAsignatura(franjaAsignatura.getAsignatura().getId()).addRestriccion(franjaAsignatura);
    }

    /**
     * Elimina una FranjaAsignatura de planEstudios.
     * @param   idAsignatura id de la Asignatura afectada.
     * @param   horaIni hora de inicio de la franja.
     * @param   horaFin hora de fin de la franja.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarFranjaAsignatura(String idAsignatura, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe la Asignatura dada.");
        }
        if (horaIni < 0 || horaFin > 24) {
            return new ReturnSet(false, "La franja horaria introducida no es válida.");
        }
        this.planEstudios.eliminarFranjaAsignatura(idAsignatura, horaIni, horaFin);
        this.planEstudios.getAsignatura(idAsignatura).eliminarFranjaAsignatura(horaIni, horaFin);
        return new ReturnSet(true, "Se ha eliminado FranjaAsignatura.");
    }

    /**
     * Añade una FranjaNivel a planEstudios.
     * @param   nombreNivel nombre del Nivel afectado.
     * @param   horaIni hora de inicio de la franja.
     * @param   horaFin hora de fin de la franja.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addFranjaNivel(String nombreNivel, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneNivel(nombreNivel))) {
            return new ReturnSet(false, "No existe el Nivel dado.");
        }
        if (horaIni < 0 || horaFin > 24) {
            return new ReturnSet(false, "La franja horaria introducida no es válida.");
        }
        this.addFranjaNivel(new FranjaNivel(this.planEstudios.getNivel(nombreNivel), horaIni, horaFin));
        return new ReturnSet(true, "Se ha añadido FranjaNivel correctamente.");
    }

    /**
     * Añade una FranjaNivel a planEstudios.
     * @param   franjaNivel FranjaNivel que se quiere añadir.
     */
    private void addFranjaNivel(FranjaNivel franjaNivel) {
        this.planEstudios.addAllRestriccion(franjaNivel);
        this.planEstudios.getNivel(franjaNivel.getNivel().getNombre()).addRestriccion(franjaNivel);
    }

    /**
     * Elimina una FranjaNivel de planEstudios.
     * @param   nombreNivel nombre del Nivel afectado.
     * @param   horaIni hora de inicio de la franja.
     * @param   horaFin hora de fin de la franja.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarFranjaNivel(String nombreNivel, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneNivel(nombreNivel))) {
            return new ReturnSet(false, "No existe el Nivel dado.");
        }
        if (horaIni < 0 || horaFin > 24) {
            return new ReturnSet(false, "La franja horaria introducida no es válida.");
        }
        this.planEstudios.eliminarFranjaNivel(nombreNivel, horaIni, horaFin);
        this.planEstudios.getNivel(nombreNivel).eliminarFranjaNivel(horaIni, horaFin);
        return new ReturnSet(true, "Se ha eliminado FranjaNivel.");
    }

    /**
     * Añade una FranjaTrabajo a planEstudios.
     * @param   horaIni hora de inicio de la franja.
     * @param   horaFin hora de fin de la franja.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addFranjaTrabajo(int horaIni, int horaFin) {
        if (horaIni < 0 || horaFin > 24) {
            return new ReturnSet(false, "La franja horaria introducida no es válida.");
        }
        this.addFranjaTrabajo(new FranjaTrabajo(horaIni, horaFin));
        return new ReturnSet(true, "Se ha añadido FranjaTrabajo correctamente.");
    }

    /**
     * Añade una FranjaTrabajo a planEstudios.
     * @param   franjaTrabajo FranjaTrabajo que se quiere añadir.
     */
    private void addFranjaTrabajo(FranjaTrabajo franjaTrabajo) {
        this.planEstudios.addAllRestriccion(franjaTrabajo);
        this.planEstudios.addRestriccion(franjaTrabajo);
    }

    /**
     * Elimina una FranjaTrabajo de planEstudios.
     * @param   horaIni hora de inicio de la franja.
     * @param   horaFin hora de fin de la franja.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarFranjaTrabajo(int horaIni, int horaFin) {
        if (horaIni < 0 || horaFin > 24) {
            return new ReturnSet(false, "La franja horaria introducida no es válida.");
        }
        this.planEstudios.eliminarFranjaTrabajo(horaIni, horaFin);
        return new ReturnSet(true, "Se ha eliminado FranjaTrabajo.");
    }

    /**
     * Añade un DiaLibre a planEstudios.
     * @param   diaSemana dia de la semana del DiaLibre que se quiere añadir.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addDiaLibre(int diaSemana) {
        this.addDiaLibre(new DiaLibre(diaSemana));
        return new ReturnSet(true, "Se ha añadido el diaLibre correctamente.");
    }

    /**
     * Añade un DiaLibre a planEstudios.
     * @param   diaLibre DiaLibre que se quiere añadir.
     */
    private void addDiaLibre(DiaLibre diaLibre) {
        this.planEstudios.addRestriccion(diaLibre);
        this.planEstudios.addAllRestriccion(diaLibre);
    }

    /**
     * Elimina un DiaLibre de planEstudios.
     * @param   diaSemana diaSemana del DiaLibre que se quiere eliminar.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarDiaLibre(int diaSemana) {
        this.planEstudios.eliminarDiaLibre(diaSemana);
        return new ReturnSet(true, "Se ha eliminado el diaLibre correctamente.");
    }

    /**
     * Añade un Prerrequisito a planEstudios.
     * @param   idAsignatura id de Asignatura de que tiene el Correquisito.
     * @param   idPrerrequisito id de Asignatura que es Prerrequisito.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addPrerrequisito(String idAsignatura, String idPrerrequisito) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idAsignatura + '.');
        }
        if (!(this.planEstudios.tieneAsignatura(idPrerrequisito))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idPrerrequisito + '.');
        }
        Prerrequisito prerrequisito = new Prerrequisito(this.planEstudios.getAsignatura(idAsignatura), this.planEstudios.getAsignatura(idPrerrequisito));
        this.addPrerrequisito(prerrequisito);
        return new ReturnSet(true, "Prerrequisito añadido correctamente.");
    }

    /**
     * Añade un Prerrequisito a planEstudios
     * @param prerrequisito Prerrequisito con asignaturas existentes que se quiere añadir.
     */
    private void addPrerrequisito(Prerrequisito prerrequisito) {
        this.planEstudios.addAllRestriccion(prerrequisito);
        prerrequisito.getAsignatura().addRestriccion(prerrequisito);
    }

    /**
     * Elimina un Prerrequisito de planEstudios.
     * @param   idAsignatura id de Asignatura de que tiene el Correquisito.
     * @param   idPrerrequisito id de Asignatura que es Prerrequisito.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarPrerrequisito(String idAsignatura, String idPrerrequisito) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idAsignatura + '.');
        }
        if (!(this.planEstudios.tieneAsignatura(idPrerrequisito))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idPrerrequisito + '.');
        }
        this.planEstudios.eliminarPrerrequisito(idAsignatura, idPrerrequisito);
        this.planEstudios.getAsignatura(idAsignatura).eliminarPrerrequisito(idPrerrequisito);
        return new ReturnSet(true, "Se ha eliminado el Prerrequisito.");
    }

    /**
     * Añade un Correquisito a planEstudios.
     * @param   idAsignatura1 id de Asignatura de Correquisito.
     * @param   idAsignatura2 id de Asignatura de Correquisito.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addCorrequisito(String idAsignatura1, String idAsignatura2) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura1))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idAsignatura1 + '.');
        }
        if (!(this.planEstudios.tieneAsignatura(idAsignatura2))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idAsignatura2 + '.');
        }
        Correquisito correquisito = new Correquisito(this.planEstudios.getAsignatura(idAsignatura1), this.planEstudios.getAsignatura(idAsignatura2));
        this.addCorrequisito(correquisito);
        return new ReturnSet(true, "Correquisito añadido correctamente.");
    }

    /**
     * Añade un Correquisito a planEstudios
     * @param correquisito  Correqusito con asignaturas existentes que se quiere añadir.
     */
    private void addCorrequisito(Correquisito correquisito) {
        this.planEstudios.addAllRestriccion(correquisito);
        correquisito.getAsignatura1().addRestriccion(correquisito);
        correquisito.getAsignatura2().addRestriccion(correquisito);
    }

    /**
     * Elimina un Correquisito de planEstudios.
     * @param   idAsignatura1 id de Asignatura de Correquisito.
     * @param   idAsignatura2 id de Asignatura de Correquisito.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarCorrequisito(String idAsignatura1, String idAsignatura2) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura1))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idAsignatura1 + '.');
        }
        if (!(this.planEstudios.tieneAsignatura(idAsignatura2))) {
            return new ReturnSet(false, "No existe la Asignatura con id " + idAsignatura2 + '.');
        }
        this.planEstudios.getAsignatura(idAsignatura1).eliminarCorrequisito(idAsignatura1, idAsignatura2);
        this.planEstudios.getAsignatura(idAsignatura2).eliminarCorrequisito(idAsignatura1, idAsignatura2);
        this.planEstudios.eliminarCorrequisito(idAsignatura1, idAsignatura2);
        return new ReturnSet(true, "Se ha eliminado el Correquisito.");
    }

    /**
     * Añade un Nivel a planEstudios.
     * @param   nombre nombre que se quiere dar al Nivel añadido.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addNivel(String nombre) {
        if (this.planEstudios.tieneNivel(nombre)) {
            return new ReturnSet(false, "Ya existe un Nivel con el nombre dado.");
        }
        this.planEstudios.addNivel(new Nivel(nombre));
        return new ReturnSet(true, "Se ha añadido el Nivel");
    }

    /**
     * Elimina un Nivel de planEstudios.
     * @param   nombre Nombre del Nivel a eliminar.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarNivel(String nombre) {
        if (!(this.planEstudios.tieneNivel(nombre))) {
            return new ReturnSet(false, "No existe el Nivel que se quiere eliminar.");
        }
        for (Map.Entry<String, Asignatura> entry : this.planEstudios.getNivel(nombre).getAsignaturas().entrySet()) {
            entry.getValue().setNivel(null);
        }
        this.planEstudios.eliminarNivel(nombre);
        return new ReturnSet(true, "Se ha eliminado el Nivel.");
    }

    /**Devuelve una ArrayList con los nombres de todos los Niveles.
     * @return  nombres de todos los Niveles.
     */
    public ArrayList<String> getAllNombresNivel() {
        ArrayList<String> nombres = new ArrayList<String>();
        Map<String, Nivel> niveles = this.planEstudios.getNiveles();
        for (Map.Entry<String, Nivel> entry : niveles.entrySet()) {
            nombres.add(entry.getValue().getNombre());
        }
        return nombres;
    }

    /**
     * Añade una Asignatura a planEstudios.
     * @param   id id que se quiere dar a la Asignatura añadida.
     * @param   nombre nombre que se quiere dar a la Asignatura añadida.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addAsignatura(String id, String nombre) {
        if (this.planEstudios.tieneAsignatura(id)) {
            return new ReturnSet(false, "Ya existe una Asignatura con la id dada.");
        }
        this.planEstudios.addAsignatura(new Asignatura(id, nombre, this.planEstudios));
        return new ReturnSet(true, "Se ha añadido la Asignatura");
    }

    /**
     * Añade una Asignatura a planEstudios y la asocia a un Nivel existente.
     * @param   id id que se quiere dar a la Asignatura añadida.
     * @param   nombre nombre que se quiere dar a la Asignatura añadida.
     * @param   nombreNivel nombre del nivel al que se quiere asociar la Asignatura.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addAsignatura(String id, String nombre, String nombreNivel) {
        if (this.planEstudios.tieneAsignatura(id)) {
            return new ReturnSet(false, "Ya existe una Asignatura con la id dada.");
        }
        if (!(this.planEstudios.tieneNivel(nombreNivel))) {
            return new ReturnSet(false, "No existe un Nivel con el nombre dado.");
        }
        this.planEstudios.addAsignatura(new Asignatura(id, nombre, this.planEstudios, this.planEstudios.getNivel(nombreNivel)));
        return new ReturnSet(true, "Se ha añadido la Asignatura");
    }

    /**
     * Elimina una Asignatura de planEstudios.
     * @param   id id dela Asignatura a eliminar.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarAsignatura(String id) {
        if (!(this.planEstudios.tieneAsignatura(id))) {
            return new ReturnSet(false, "No existe la Asignatura que se quiere eliminar.");
        }
        this.planEstudios.eliminarAsignatura(id);
        if (this.planEstudios.getAsignatura(id).tieneNivel()) {
            this.planEstudios.getAsignatura(id).getNivel().eliminarAsignatura(id);
        }
        return new ReturnSet(true, "Se ha eliminado la Asignatura.");
    }

    /**Devuelve una ArrayList con los nombres de todas las Asignaturas.
     * @return  nombres de todas las Asignaturas.
     */
    public ArrayList<String> getAllNombresAsignatura() {
        ArrayList<String> nombres = new ArrayList<String>();
        Map<String, Asignatura> asignaturas = this.planEstudios.getAsignaturas();
        for (Map.Entry<String, Asignatura> entry : asignaturas.entrySet()) {
            nombres.add(entry.getValue().getNombre());
        }
        return nombres;
    }

    /**Devuelve una ArrayList con las id de todas las Asignaturas.
     * @return  id de todas las Asignaturas.
     */
    public ArrayList<String> getAllIdsAsignatura() {
        ArrayList<String> ids = new ArrayList<String>();
        Map<String, Asignatura> asignaturas = this.planEstudios.getAsignaturas();
        for (Map.Entry<String, Asignatura> entry : asignaturas.entrySet()) {
            ids.add(entry.getValue().getId());
        }
        return ids;
    }

    /**
     * Añade un Grupo a una Asignatura de planEstudios.
     * @param   id id que se quiere dar al Grupo añadido.
     * @param   idAsignatura id de la Asignatura a la que se quiere asociar el Grupo.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addGrupo(String id, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe una Asignatura con la id dada.");
        }
        if (this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(id)) {
            return new ReturnSet(false, idAsignatura + "ya tiene un Grupo con el nombre dado.");
        }
        this.planEstudios.getAsignatura(idAsignatura).addGrupo(new Grupo(id, this.planEstudios.getAsignatura(idAsignatura)));
        return new ReturnSet(true, "Se ha añadido el Grupo");
    }

    /**
     * Elimina un Grupo de planEstudios.
     * @param   id id del Grupo a eliminar.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarGrupo(String id, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe una Asignatura con la id dada.");
        }
        if (!(this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(id))) {
            return new ReturnSet(false, idAsignatura + "no tiene un Grupo con el nombre dado.");
        }
        this.planEstudios.getAsignatura(idAsignatura).eliminarGrupo(id);
        return new ReturnSet(true, "Se ha eliminado el Grupo.");
    }

    /**
     * Añade un SubGrupo a un Grupo de una Asignatura de planEstudios.
     * @param id            id que se quiere dar al SubGrupo añadido.
     * @param plazas        plazas del SubGrupo.
     * @param tipo          TipoClase del SubGrupo.
     * @param idGrupo       id del Grupo al que se quiere asociar el SubGrupo.
     * @param idAsignatura  id de la Asignatura a la que se quiere asociar el SubGrupo.
     * @return              ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addSubGrupo(String id, int plazas, String tipo, String idGrupo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe una Asignatura con la id dada.");
        }
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo)) {
            return new ReturnSet(false, idAsignatura + "No tiene un Grupo con el nombre dado.");
        }
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas"))) {
            return new ReturnSet(false, "El tipo no es válido.");
        }
        if (this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id)) {
            return new ReturnSet(false, "Ya hay un SubGrupo con la id dada.");
        }
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).addSubGrupo(new SubGrupo(id, plazas, TipoClase.Laboratorio.valueOf(tipo), this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo)));
        return new ReturnSet(true, "Se ha añadido el SubGrupo");
    }

    /**
     * Elimina un SubGrupo de planEstudios.
     * @param id            id del SubGrupo a eliminar.
     * @param idAsignatura  id de la Asignatura del SubGrupo.
     * @param idGrupo       id del Grupo del SubGrupo.
     * @return              ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarSubGrupo(String id, String tipo, String idGrupo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura))) {
            return new ReturnSet(false, "No existe una Asignatura con la id dada.");
        }
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo)) {
            return new ReturnSet(false, idAsignatura + "No tiene un Grupo con el nombre dado.");
        }
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas"))) {
            return new ReturnSet(false, "El tipo no es válido.");
        }
        if (!(this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id))) {
            return new ReturnSet(false, "No hay un SubGrupo con la id dada.");
        }
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).eliminarSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString());
        return new ReturnSet(true, "Se ha eliminado el SubGrupo.");
    }

    /**
     * Añade un Aula de planEstudios.
     * @param   id id que se quiere dar a la Aula añadida.
     * @param   plazas número de plazas que tendrá el Aula añadida.
     * @param   tipos TiposClase con los que es compatible el Aula añadida.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet addAula(String id, int plazas, String[] tipos) {
        if (this.planEstudios.tieneAula(id)) {
            return new ReturnSet(false, "Ya existe un Aula con el id dado.");
        }
        ArrayList<TipoClase> t = new ArrayList<TipoClase>();
        for (int i = 0; i < tipos.length; ++i) {
            if (tipos[i].equals("Teoria")) {
                t.add(TipoClase.Teoria);
            }
            else if (tipos[i].equals("Laboratorio")) {
                t.add(TipoClase.Laboratorio);
            }
            else if (tipos[i].equals("Problemas")) {
                t.add(TipoClase.Problemas);
            }
            else {
                return new ReturnSet(false, "Se ha intentado añadir un tipo no válido.");
            }
        }
        this.planEstudios.addAula(new Aula(id, plazas, t));
        return new ReturnSet(true, "Se ha añadido el Aula");
    }

    /**
     * Elimina un Aula de planEstudios.
     * @param   id id del Aula a eliminar.
     * @return  ReturnSet informando de lo que se ha hecho.
     */
    public ReturnSet eliminarAula(String id) {
        if (!(this.planEstudios.tieneAula(id))) {
            return new ReturnSet(false, "No existe el Aula que se quiere eliminar.");
        }
        this.planEstudios.eliminarAula(id);
        return new ReturnSet(true, "Se ha eliminado el Aula.");
    }

    /**Devuelve una ArrayList con los nombres de todas las Aulas.
     * @return  nombres de todas las Aulas.
     */
    public ArrayList<String> getAllIdsAula() {
        ArrayList<String> ids = new ArrayList<String>();
        Map<String, Aula> aulas = this.planEstudios.getAulas();
        for (Map.Entry<String, Aula> entry : aulas.entrySet()) {
            ids.add(entry.getValue().getId());
        }
        return ids;
    }

    /**
     * Carga en CtrlDominio el escenario con el nombre indicado.
     * @param escenario                 Nombre del escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void cargarEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        this.cargarPlanEstudios(escenario);
        this.cargarAllAsignaturas(escenario);
        this.cargarAllAulas(escenario);
        this.cargarAllRestricciones(escenario);
    }

    /**
     * Devuelve todos los escenarios disponibles.
     * @return  ArrayList de String con los nombres de todos los escenerios disponibles.
     */
    public ArrayList<String> allEscenarios() {
        controladorEscenarios.escanearAllEscenarios();
        return controladorEscenarios.getAllEscenarios();
    }

    /**
     * Devuelve todos los Horarios disponibles.
     * @return  ArrayList de String con los nombres de todos los Horarios disponibles.
     */
    public ArrayList<String> allHorarios() {
        return controladorEscenarios.escanearAllHorarios();
    }

    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> escaneaHorario(String h) throws Exception {
        //System.out.println(h);
        return controladorEscenarios.escaneaHorario(h);
    }

    /**
     * Escribe un Horario por consola.
     * @param horario   String con los datos de Horario.
     * @param idHorario id del Horarioque se escribe.
     * @throws IOException
     */
    public void writeHorario(String horario, String idHorario) throws IOException {
        controladorEscenarios.writeHorario(horario, idHorario);
    }

    public void writeHorarioFromMap(HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario, String idHorario) throws IOException {
        controladorEscenarios.writeHorarioFromMap(horario, idHorario);
    }

    /**
     * Lee un horario guardado préviamente.
     * @param horario                   Nombre del Horario que se quiere leer.
     * @return                          String con los datos del Horario indicado.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String readHorario(String horario) throws FileNotFoundException, IOException {
        return controladorEscenarios.readHorario(horario);
    }

    /**
     * Carga el PlanEstudios del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void cargarPlanEstudios(String escenario) throws FileNotFoundException, IOException, ParseException {
        JSONObject planEstudiosData = controladorPlanEstudios.getPlanEstudiosByEscenario(escenario);
        planEstudios = new PlanEstudios((String)planEstudiosData.get("nombre"));
        String niveles = planEstudiosData.get("niveles").toString();

        String s;
        for (int i = 0; i < niveles.length()-1; i+=5) {
            s = niveles.substring(niveles.indexOf("\"", i) + 1, niveles.indexOf("\"", i) + 3);
            Nivel nivel = new Nivel(s);
            planEstudios.addNivel(nivel);
        }
    }

    /**
     * Carga los datos de las Asignaturas del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void cargarAllAsignaturas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> asignaturasData = controladorAsignaturas.getByEscenario(escenario);

        for (JSONObject assig : asignaturasData) {
            Asignatura asignatura;

            if (((String)assig.get("nivel")).length() != 0) {
                asignatura = new Asignatura((String)assig.get("id"), (String)assig.get("nombre"), this.planEstudios, this.planEstudios.getNivel((String)assig.get("nivel")));
                this.planEstudios.getNivel((String)assig.get("nivel")).addAsignatura(asignatura);
            }
            else {
                asignatura = new Asignatura((String)assig.get("id"), (String)assig.get("nombre"), this.planEstudios);
            }

            for (JSONObject ses : (List<JSONObject>)assig.get("sesiones")) {
                Integer hora = new Integer(((Long)ses.get("horas")).intValue());
                Sesion sesion = new Sesion(hora, TipoClase.valueOf((String)ses.get("TipoClase")), asignatura);
                asignatura.addSesion(sesion);
            }

            for (JSONObject gr : (List<JSONObject>)assig.get("grupos")) {
                Grupo grupo = new Grupo((String)gr.get("id"), asignatura);

                for (JSONObject subgr : (List<JSONObject>)gr.get("subGrupos")) {
                    Integer plazas = new Integer(((Long)subgr.get("plazas")).intValue());
                    SubGrupo subg = new SubGrupo((String)subgr.get("id"), plazas, TipoClase.valueOf((String)subgr.get("tipo")), grupo);
                    grupo.addSubGrupo(subg);
                }
                asignatura.addGrupo(grupo);
            }
            planEstudios.addAsignatura(asignatura);
        }
    }

    /**
     * Carga los datos de las Aulas del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void cargarAllAulas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> aulasData = controladorAulas.getByEscenario(escenario);

        for (JSONObject au : aulasData) {
            Integer plazas = new Integer(((Long)au.get("plazas")).intValue());
            ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
            int i = 0;
            for (String tipo : (List<String>)au.get("tipos")) {
                tipos.add(TipoClase.valueOf(tipo));
                ++i;
            }
            Aula aula = new Aula((String)au.get("id"), plazas, tipos);
            planEstudios.addAula(aula);
        }

    }

    /**
     * Carga las Restricciones del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void cargarAllRestricciones(String escenario) throws FileNotFoundException, IOException, ParseException {
        this.planEstudios.setAllRestricciones(new ArrayList<Restriccion>());

        List<JSONObject> restriccionesData = controladorRestricciones.getByEscenario(escenario);
        //System.out.println(restriccionesData);
        //System.out.print("\n");

        //R: diaLibre
        JSONObject diaLibre = restriccionesData.get(0);
        for (Long dia : (List<Long>)diaLibre.get("dias")) {
            Integer diaN = new Integer(dia.intValue());
            DiaLibre diaLib = new DiaLibre(diaN);
            planEstudios.addRestriccion(diaLib);
            this.addAllRestriccion(diaLib);
        }

        //R: franjaTrabajo
        JSONObject franjaTrabajo = restriccionesData.get(1);
        Integer horaIni = new Integer(((Long)franjaTrabajo.get("horaIni")).intValue());
        Integer horaFin = new Integer(((Long)franjaTrabajo.get("horaFin")).intValue());
        FranjaTrabajo ft = new FranjaTrabajo(horaIni, horaFin);
        planEstudios.addRestriccion(ft);
        this.addAllRestriccion(ft);

        //R: nivelHora
        JSONObject nivelHora = restriccionesData.get(2);
        for (String nivel : (List<String>)nivelHora.get("niveles")) {
            NivelHora nh = new NivelHora(planEstudios.getNivel(nivel));
            planEstudios.getNivel(nivel).addRestriccion(nh);
            this.addAllRestriccion(nh);
        }

        //R: correquisitos
        JSONObject correquisitos = restriccionesData.get(3);
        for (JSONObject as : (List<JSONObject>)correquisitos.get("parAsigs")) {
            String a1 = (String)as.get("idAsig1");
            String a2 = (String)as.get("idAsig2");
            Correquisito co = new Correquisito(planEstudios.getAsignatura(a1), planEstudios.getAsignatura(a2));
            planEstudios.getAsignatura(a1).addRestriccion(co);
            planEstudios.getAsignatura(a2).addRestriccion(co);
            this.addAllRestriccion(co);
        }

        //R: prerrequisitos
        JSONObject prerrequisitos = restriccionesData.get(4);
        for (JSONObject as : (List<JSONObject>)prerrequisitos.get("parAsigs")) {
            String a1 = (String)as.get("idAsig");
            String a2 = (String)as.get("idAsigPre");
            Prerrequisito pre = new Prerrequisito(planEstudios.getAsignatura(a1), planEstudios.getAsignatura(a2));
            planEstudios.getAsignatura(a1).addRestriccion(pre);
            this.addAllRestriccion(pre);
        }

        //R: franjaAsignatura
        JSONObject franjaAsignatura = restriccionesData.get(5);
        for (JSONObject as : (List<JSONObject>)franjaAsignatura.get("asignaturas")) {
            Integer hi = new Integer(((Long)as.get("horaIni")).intValue());
            Integer hf = new Integer(((Long)as.get("horaFin")).intValue());
            String idAs = (String)as.get("idAsig");
            FranjaAsignatura fa = new FranjaAsignatura(planEstudios.getAsignatura(idAs), hi, hf);
            planEstudios.getAsignatura(idAs).addRestriccion(fa);
            this.addAllRestriccion(fa);
        }

        //R: franjaNivel
        JSONObject franjaNivel = restriccionesData.get(6);
        for (JSONObject ni : (List<JSONObject>)franjaNivel.get("niveles")) {
            Integer hi = new Integer(((Long)ni.get("horaIni")).intValue());
            Integer hf = new Integer(((Long)ni.get("horaFin")).intValue());
            String idNi = (String)ni.get("idNivel");
            FranjaNivel fn = new FranjaNivel(planEstudios.getNivel(idNi), hi, hf);
            planEstudios.getNivel(idNi).addRestriccion(fn);
            this.addAllRestriccion(fn);
        }
    }

    /**
     * Sube Asignaturas.
     * @param   escenario String con el escenario indicado.
     * @return  ArrayList con la informacion deseada.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public HashMap<String, ArrayList<Object>> subirAsignaturas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> asignaturasData = controladorAsignaturas.getByEscenario(escenario);
        HashMap<String, ArrayList<Object>> asigs = new HashMap<>();

        for (JSONObject assig : asignaturasData) {
            ArrayList<Object> asigProperties = new ArrayList<>();
            asigProperties.add((String)assig.get("nombre"));
            asigProperties.add((String)assig.get("nivel"));

            ArrayList<Object> sesiones = new ArrayList<>();
            for (JSONObject ses : (List<JSONObject>)assig.get("sesiones")) {
                ArrayList<Object> sesProperties = new ArrayList<>();
                sesProperties.add(((Long)ses.get("horas")).intValue());
                sesProperties.add(TipoClase.valueOf((String)ses.get("TipoClase")));
                sesiones.add(sesProperties);
            }
            asigProperties.add(sesiones);

            ArrayList<Object> grupos = new ArrayList<>();
            for (JSONObject gr : (List<JSONObject>)assig.get("grupos")) {
                ArrayList<Object> grProperties = new ArrayList<>();
                grProperties.add((String)gr.get("id"));

                ArrayList<Object> subgrupos = new ArrayList<>();
                for (JSONObject subgr : (List<JSONObject>)gr.get("subGrupos")) {
                    ArrayList<Object> subgrProperties = new ArrayList<>();
                    subgrProperties.add((String)subgr.get("id"));
                    subgrProperties.add(((Long)subgr.get("plazas")).intValue());
                    subgrProperties.add(TipoClase.valueOf((String)subgr.get("tipo")));
                    subgrupos.add(subgrProperties);
                }
                grProperties.add(subgrupos);

                grupos.add(grProperties);
            }
            asigProperties.add(grupos);

            asigs.put((String)assig.get("id"), asigProperties);
        }

        return asigs;
    }

    /**
     * Sube Aulas.
     * @param   escenario String con el escenario indicado.
     * @return  ArrayList con la informacion deseada.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public HashMap<String, ArrayList<Object>> subirAulas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> aulasData = controladorAulas.getByEscenario(escenario);
        HashMap<String, ArrayList<Object>> aulas = new HashMap<>();

        for (JSONObject au : aulasData) {
            ArrayList<Object> auProperties = new ArrayList<>();
            auProperties.add(((Long)au.get("plazas")).intValue());

            ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
            for (String tipo : (List<String>)au.get("tipos")) {
                tipos.add(TipoClase.valueOf(tipo));
            }
            auProperties.add(tipos);

            aulas.put((String)au.get("id"), auProperties);
        }
        return aulas;
    }

    /**
     * Sube las Restricciones.
     * @param   escenario String con el escenario indicado.
     * @return  HashMap con la informacion deseada.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public HashMap<String, ArrayList<Object>> subirRestricciones(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> restriccionesData = controladorRestricciones.getByEscenario(escenario);
        HashMap<String, ArrayList<Object>> restricciones = new HashMap<>();

        //R: diaLibre
        JSONObject diaLibre = restriccionesData.get(0);
        ArrayList<Object> dlProperties = new ArrayList<>();
        for (Long dia : (List<Long>)diaLibre.get("dias")) {
            dlProperties.add(dia.intValue());
        }
        restricciones.put(diaLibre.get("nombre").toString(), dlProperties);


        //R: franjaTrabajo
        JSONObject franjaTrabajo = restriccionesData.get(1);
        ArrayList<Object> ftProperties = new ArrayList<>();
        ftProperties.add(((Long)franjaTrabajo.get("horaIni")).intValue());
        ftProperties.add(((Long)franjaTrabajo.get("horaFin")).intValue());
        restricciones.put(franjaTrabajo.get("nombre").toString(), ftProperties);

        //R: nivelHora
        JSONObject nivelHora = restriccionesData.get(2);
        ArrayList<Object> nhProperties = new ArrayList<>();
        for (String nivel : (List<String>)nivelHora.get("niveles")) {
            nhProperties.add(nivel);
        }
        restricciones.put(nivelHora.get("nombre").toString(), nhProperties);

        //R: correquisitos
        JSONObject correquisitos = restriccionesData.get(3);
        ArrayList<Object> coProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)correquisitos.get("parAsigs")) {
            ArrayList<String> co = new ArrayList<>();
            co.add((String)as.get("idAsig1"));
            co.add((String)as.get("idAsig2"));
            coProperties.add(co);
        }
        restricciones.put(correquisitos.get("nombre").toString(), coProperties);

        //R: prerrequisitos
        JSONObject prerrequisitos = restriccionesData.get(4);
        ArrayList<Object> prProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)prerrequisitos.get("parAsigs")) {
            ArrayList<String> pr = new ArrayList<>();
            pr.add((String)as.get("idAsig"));
            pr.add((String)as.get("idAsigPre"));
            prProperties.add(pr);
        }
        restricciones.put(prerrequisitos.get("nombre").toString(), prProperties);

        //R: franjaAsignatura
        JSONObject franjaAsignatura = restriccionesData.get(5);
        ArrayList<Object> faProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)franjaAsignatura.get("asignaturas")) {
            ArrayList<Object> fa = new ArrayList<>();
            fa.add((String)as.get("idAsig"));
            fa.add(((Long)as.get("horaIni")).intValue());
            fa.add(((Long)as.get("horaFin")).intValue());
            faProperties.add(fa);
        }
        restricciones.put(franjaAsignatura.get("nombre").toString(), faProperties);

        //R: franjaNivel
        JSONObject franjaNivel = restriccionesData.get(6);
        ArrayList<Object> fnProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)franjaNivel.get("niveles")) {
            ArrayList<Object> fn = new ArrayList<>();
            fn.add((String)as.get("idNivel"));
            fn.add(((Long)as.get("horaIni")).intValue());
            fn.add(((Long)as.get("horaFin")).intValue());
            fnProperties.add(fn);
        }
        restricciones.put(franjaNivel.get("nombre").toString(), fnProperties);

        return restricciones;
    }

    /**
     * Sube planEstudios.
     * @param   escenario String con el escenario indicado.
     * @return  ArrayList con la informacion deseada.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public ArrayList<String> subirPlanEstudios(String escenario) throws FileNotFoundException, IOException, ParseException {
        JSONObject planEstudiosData = controladorPlanEstudios.getPlanEstudiosByEscenario(escenario);
        ArrayList<String> pE = new ArrayList<>();
        pE.add((String)planEstudiosData.get("nombre"));
        String niveles = planEstudiosData.get("niveles").toString();

        String s;
        for (int i = 0; i < niveles.length()-1; i+=5) {
            s = niveles.substring(niveles.indexOf("\"", i) + 1, niveles.indexOf("\"", i) + 3);
            pE.add(s);
        }
        return pE;
    }

    /**
     * Genera un Horario con los datos de CtrlDominio.
     * @param id    id del Horario que se generará.
     * @return      Horario generado.
     */
    public String generarHorario(String id) {
        CtrlHorario ctrlHorario = new CtrlHorario(this.planEstudios);
        ReturnSet horario = ctrlHorario.generarHorario(id);
        if (horario.getValidez()) {
            this.horarioActivo = horario.getHorario();
            return horario.getHorario().toString();
        }
        else return "false";
    }

    /**
     * Genera una instancia de CtrlHorario con los datos de CtrlDominio.
     * @return  Instancia de CtrlHorario con los datos de CtrlDominio.
     */
    public CtrlHorario getCtrlHorario() {
        return new CtrlHorario(this.planEstudios);
    }

    /**
     * Añade una Restriccion al escenario
     * @param restriccion   Restriccion que se quiere añadir.
     */
    public void addAllRestriccion(Restriccion restriccion) {
        this.planEstudios.addAllRestriccion(restriccion);
    }

    /**Consultoras*/

    /**
     * Devuelve todas las Restricciones de planEstudios y los elementos que contiene.
     * @return  Restricciones de planEstudios y los elementos que contiene.
     */
    public ArrayList<Restriccion> getAllRestricciones() {
        return this.planEstudios.getAllRestricciones();
    }

    /**
     * Devuelve la Restriccion de allRestricciones que se encuentra en la posicion indicada.
     * @param   posicion posicion de la Restriccion deseada.
     * @return  Restriccion de allRestricciones que se encuentra en la posicion indicada.
     */
    public Restriccion getAllRestricion(int posicion) {
        return this.planEstudios.getAllRestriccion(posicion);
    }

    /**Métodos redefinidos*/
}

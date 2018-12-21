/**CtrlDomain*/

/**Imports*/

package domaincontrollers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.util.Map;

import domain.*;

import data.*;

/**
 * CtrlDominio es el controlador del Dominio que coordina todos los controladores de Dominio, las clases de Dominio y se comunica con las capas
 * de Presentacion y Datos.
 * @author  Enric Sosa i Daniel Martín
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
 * @see     Aux
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
    /**Contiene información de todos los escenarios disponibles.*/
    private Map<String, PlanEstudios> planEstudiosMap;
    /**PlanEstudios con el que trabaja CtrlDominio.*/
    private PlanEstudios planEstudios;
    /**Horario con el que se trabaja.*/
    private Horario horarioActivo;
    /**Referencia a sí misma para Singleton.*/
    private static CtrlDomain ctrlDomain;

    /**Constructoras*/

    /**
     * Creadora de la clase CtrlDomain.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    private CtrlDomain() throws FileNotFoundException, IOException, ParseException  {
        this.initCtrlDomain();
    }

    /**
     * Ejecuta las acciones necesarias para la inicialización de CtrlDomain.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public void initCtrlDomain() throws FileNotFoundException, IOException, ParseException {
        this.planEstudiosMap = new HashMap<String, PlanEstudios>();
        controladorAsignaturas = CtrlAsignaturasFile.getInstance();
        controladorAulas = CtrlAulasFile.getInstance();
        controladorPlanEstudios = CtrlPlanEstudiosFile.getInstance();
        controladorRestricciones = CtrlRestriccionesFile.getInstance();
        controladorEscenarios = CtrlEscenariosDir.getInstance();
        this.cargarAllEscenarios();
    }

    /**
     * Cambia de escenario con el que se trabaja.
     * @param nombreEscenario   nombre del escenario que se quiere guardar.
     */
    public void seleccionarEscenario(String nombreEscenario) {
        System.out.println("Se ha selecionado el escenario: " + nombreEscenario);
        this.planEstudios = planEstudiosMap.get(nombreEscenario);
    }

    /**
     * Carga todos los escenarios en un Map.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public void cargarAllEscenarios() throws FileNotFoundException, IOException, ParseException {
        ArrayList<String> nombresEscenario = this.allEscenarios();
        for (int i = 0; i < nombresEscenario.size(); ++i) {
            this.cargarPlanEstudios(nombresEscenario.get(i));
            this.cargarAllAsignaturas(nombresEscenario.get(i));
            this.cargarAllAulas(nombresEscenario.get(i));
            this.cargarAllRestricciones(nombresEscenario.get(i));
            this.planEstudiosMap.put(nombresEscenario.get(i), this.planEstudios);
        }
    }

    /**
     * Instanciadora de la clase CtrlDomain.
     * @return                          Instancia de la clase CtrlDomain.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public static CtrlDomain getInstance() throws FileNotFoundException, IOException, ParseException {
        if (ctrlDomain == null)
            ctrlDomain = new CtrlDomain();
        return ctrlDomain;
    }

    /**Métodos públicos*/

    /**
     * Asigna un Horario a horarioActivo.
     * @param horarioActivo Nuevo horarioActivo.*/
    public void setHorarioActivo(Horario horarioActivo) {
        this.horarioActivo = horarioActivo;
    }

    /**
     * Comprueba mover una Asignacion de horarioActivo.
     * @param idAsignatura          id de la Asignatura de la Asignacion que se quiere mover.
     * @param idSubGrupoCompleta    idCompleta del SubGrupo de la Asignatura de la Asignacion que se quiere mover.
     * @param dia                   dia donde se encuentra la Asignacion que se quiere mover.
     * @param hora                  hora donde se encuentra la Asignacion que se quiere mover.
     * @param nuevoDia              dia la que se quiere mover la Asignacion.
     * @param nuevaHoraIni          hora la que se quiere mover la Asignacion.
     * @return                      true si se puede, false si no.
     */
    public Boolean checkMoverAsignacion(String idAsignatura, String idSubGrupoCompleta, int dia, int hora, int nuevoDia, int nuevaHoraIni) {
        if (this.horarioActivo == null)
            return false;
        Hora h = this.horarioActivo.getHora(dia, hora);
        boolean found = false;
        Asignacion asignacion = null;
        for (Map.Entry<String, Asignacion> entry : h.getAsignaciones().entrySet()) {
            if ((entry.getValue().getAsignatura().getId().equals(idAsignatura)) && (entry.getValue().getSubGrupo().getIdCompleta().equals(idSubGrupoCompleta))) {
                found = true;
                asignacion = entry.getValue();
            }
        }
        if (!(found))
            return false;
        Horario nuevoHorario = new Horario(this.horarioActivo);
        nuevoHorario.eliminarAsignacion(asignacion);
        Clase clase = asignacion.getClase();
        if (nuevaHoraIni + clase.getDuracion() > 24)
            return false;
        if (CtrlHorario.comprovarSubGrupoDia(clase, nuevoDia, nuevoHorario)
                || CtrlHorario.comprovarGrupoDia(clase, nuevoDia, nuevoHorario))
            return false;
        if (CtrlHorario.aulaOcupada(clase, nuevoDia, nuevaHoraIni, asignacion.getAula(), nuevoHorario))
            return false;
        if (!(CtrlHorario.comprobarRestricciones(clase, nuevoDia, nuevaHoraIni, nuevoHorario)))
            return false;
        return true;
    }

    /**
     * Mueve una Asignacion de horarioActivo.
     * @param idAsignatura          id de la Asignatura de la Asignacion que se quiere mover.
     * @param idSubGrupoCompleta    idCompleta del SubGrupo de la Asignatura de la Asignacion que se quiere mover.
     * @param dia                   dia donde se encuentra la Asignacion que se quiere mover.
     * @param hora                  hora donde se encuentra la Asignacion que se quiere mover.
     * @param nuevoDia              dia la que se quiere mover la Asignacion.
     * @param nuevaHoraIni          hora la que se quiere mover la Asignacion.
     * @return                      codigoResultado de la operación.
     */
    public int moverAsignacion(String idAsignatura, String idSubGrupoCompleta, int dia, int hora, int nuevoDia, int nuevaHoraIni) {
        if (this.horarioActivo == null)
            return -20;
        Hora h = this.horarioActivo.getHora(dia, hora);
        boolean found = false;
        Asignacion asignacion = null;
        for (Map.Entry<String, Asignacion> entry : h.getAsignaciones().entrySet()) {
            if ((entry.getValue().getAsignatura().getId().equals(idAsignatura)) && (entry.getValue().getSubGrupo().getIdCompleta().equals(idSubGrupoCompleta))) {
                found = true;
                asignacion = entry.getValue();
            }
        }
        if (!(found))
            return -19;
        Horario nuevoHorario = new Horario(this.horarioActivo);
        nuevoHorario.eliminarAsignacion(asignacion);
        Clase clase = asignacion.getClase();
        if (nuevaHoraIni + clase.getDuracion() > 24)
            return -17;
        if (CtrlHorario.comprovarSubGrupoDia(clase, nuevoDia, nuevoHorario)
                || CtrlHorario.comprovarGrupoDia(clase, nuevoDia, nuevoHorario))
            return -18;
        if (CtrlHorario.aulaOcupada(clase, nuevoDia, nuevaHoraIni, asignacion.getAula(), nuevoHorario))
            return -18;
        if (!(CtrlHorario.comprobarRestricciones(clase, nuevoDia, nuevaHoraIni, nuevoHorario)))
            return -17;
        Asignacion nuevaAsignacion = new Asignacion(nuevaHoraIni, nuevoDia, asignacion.getAula(), clase);
        nuevoHorario.addAsignacion(nuevaAsignacion);
        this.horarioActivo = nuevoHorario;
        return 26;
    }

    /**
     * Indica si una operación se ha ejecutado exitosamente a partir de su código de resultado.
     * @param codigoResultado   Código de resultado de la operación de la que se quiere conocer su validez.
     * @return                  true si la operación se ha ejecutado exitosamente, false en caso contrario.
     */
    public static Boolean getValidezOperacion(int codigoResultado) {
        return codigoResultado >= 0;
    }

    /**
     * Devuelve el mensaje correspondiente al resultado de ejecutar una operación.
     * @param codigoResultado   Código de resultado de la operación de la que se quiere conocer su mensaje.
     * @return                  String con el mensaje correspondiente al resultado de ejecutar una operación.
     */
    public static String getMensageOperacion(int codigoResultado) {
        switch (codigoResultado) {
            case -26:   return "ERROR: Una Asignatura no puede ser Correquisito o Prerrequsito de sí misma.";
            case -25:   return "ERROR: Número de plazas dado inferior a 1.";
            case -24:   return "ERROR: La Asignatura ya carecía de Nivel.";
            case -23:   return "ERROR: Se busca una Sesion de duracion menor a 1.";
            case -22:   return "ERROR: Se quiere dar una duracion menor a 1.";
            case -21:   return "ERROR: No se ha encontrado la Sesion.";
            case -20:   return "ERROR: No hay ningún Horario seleccionado.";
            case -19:   return "ERROR: No se ha podido encontrar la Asignacion a mover.";
            case -18:   return "ERROR: No se puede mover la Asignacion al dia indicado.";
            case -17:   return "ERROR: No se puede mover la Asignacion a la hora indicada.";
            case -16:   return "ERROR: Ya existe Aula con la id dada.";
            case -14:   return "ERROR: No existe Aula con el nombre dado.";
            case -13:   return "ERROR: No existe SubGrupo con la id dada en el Grupo indicado.";
            case -12:   return "ERROR: No existe Grupo con la id dada en la Asignatura indicado.";
            case -11:   return "ERROR: Ya existe SubGrupo con la id dada en el Grupo indicado.";
            case -10:   return "ERROR: Ya existe Grupo con la id dada en la Asignatura indicado.";
            case -9:    return "ERROR: Ya existe Asignatura con la id dada.";
            case -8:    return "ERROR: Ya existe Nivel con el nombre dado.";
            case -7:    return "ERROR: No existe Asignatura con la id dada (Prerrequisito).";
            case -6:    return "ERROR: No existe Asignatura con la id dada (Asignatura que tiene Prerrequisito).";
            case -5:    return "ERROR: El diaSemana introducido no es válido.";
            case -4:    return "ERROR: La franja horaria introducida no es válida.";
            case -3:    return "ERROR: No existe el TipoClase dado.";
            case -2:    return "ERROR: No existe Asignatura con la id dada.";
            case -1:    return "ERROR: No existe Nivel con el nombre dado.";
            case 0:     return "Se ha añadido NivelHora correctamente.";
            case 1:     return "Se ha eliminado NivelHora correctamente.";
            case 2:     return "Se ha añadido Sesion correctamente.";
            case 3:     return "Se ha eliminado Sesion correctamente.";
            case 4:     return "Se ha añadido FranjaAsignatura correctamente.";
            case 5:     return "Se ha eliminado FranjaAsignatura correctamente.";
            case 6:     return "Se ha añadido FranjaNivel correctamente.";
            case 7:     return "Se ha eliminado FranjaNivel correctamente.";
            case 8:     return "Se ha añadido FranjaTrabajo correctamente.";
            case 9:     return "Se ha eliminado FranjaTrabajo correctamente.";
            case 10:    return "Se ha añadido DiaLibre correctamente.";
            case 11:    return "Se ha eliminado DiaLibre correctamente.";
            case 12:    return "Se ha añadido Prerrequisito correctamente.";
            case 13:    return "Se ha eliminado Prerrequisito correctamente.";
            case 14:    return "Se ha añadido Correquisito correctamente.";
            case 15:    return "Se ha eliminado Correquisito correctamente.";
            case 16:    return "Se ha añadido Nivel correctamente.";
            case 17:    return "Se ha eliminado Nivel correctamente.";
            case 18:    return "Se ha añadido Grupo correctamente.";
            case 19:    return "Se ha eliminado Grupo correctamente.";
            case 20:    return "Se ha añadido SubGrupo correctamente.";
            case 21:    return "Se ha eliminado SubGrupo correctamente.";
            case 22:    return "Se ha añadido Asignatura correctamente.";
            case 23:    return "Se ha eliminado Asignatura correctamente.";
            case 24:    return "Se ha añadido Aula correctamente.";
            case 25:    return "Se ha eliminado Aula correctamente.";
            case 26:    return "Se ha podido mover la Asignacion correctamente.";
            case 27:    return "Se ha editado Sesion correctamente.";
            case 28:    return "Se ha editado Nivel correctamente.";
            case 29:    return "Se ha editado Asignatura correctamente.";
            case 30:    return "Se ha editado SubGrupo correctamente.";
            default:    return "codigoResultado no válido.";
        }
    }

    /**
     * Añade una NivelHora a planEstudios.
     * @param nombreNivel   nombre del Nivel involucrado.
     * @return              codigoResultado de la operación.
     */
    public int addNivelHora(String nombreNivel) {
        if (!(this.planEstudios.tieneNivel(nombreNivel)))
            return -1;
        this.addNivelHora(new NivelHora(this.planEstudios.getNivel(nombreNivel)));
        return 0;
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
     * @return              codigoResultado de la operación.
     */
    public int eliminarNivelHora(String nombreNivel) {
        if (!(this.planEstudios.tieneNivel(nombreNivel)))
            return -1;
        this.planEstudios.eliminarNivelHora(nombreNivel);
        this.planEstudios.getNivel(nombreNivel).eliminarNivelHora();
        return 1;
    }

    /**
     * Añade una Sesion a una Asignatura.
     * @param duracion      horas que dura la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @return              codigoResultado de la operación.
     */
    public int addSesion(int duracion, String tipo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (duracion < 1)
            return -22;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        this.planEstudios.getAsignatura(idAsignatura).addSesion(new Sesion(duracion, TipoClase.valueOf(tipo), this.planEstudios.getAsignatura(idAsignatura)));
        return 2;
    }

    /**
     * Edita la duracion de una Sesion.
     * @param duracion      duracion actual de la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @param nuevaDuracion duracion que se asignará a Sesion.
     * @return              codigoResultado de la operacion.
     */
    public int editarDuracionSesion(int duracion, String tipo, String idAsignatura, int nuevaDuracion) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        if (duracion < 1)
            return -23;
        if (nuevaDuracion < 1)
            return -22;
        ArrayList<Sesion> sesiones = this.planEstudios.getAsignatura(idAsignatura).getSesiones();
        for (int i = 0; i < sesiones.size(); ++i) {
            if (sesiones.get(i).getDuracion().equals(duracion) && sesiones.get(i).getTipo().equals(TipoClase.valueOf(tipo))) {
                sesiones.get(i).setDuracion(nuevaDuracion);
                return 27;
            }
        }
        return -21;
    }

    /**
     * Edita el tipo de una Sesion.
     * @param duracion      duracion actual de la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @param nuevoTipo     TipoClase que se asignará a Sesion.
     * @return              codigoResultado de la operacion.
     */
    public int editarTipoSesion(int duracion, String tipo, String idAsignatura, String nuevoTipo) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if ((!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
                || (!(nuevoTipo.equals("Teoria") || nuevoTipo.equals("Laboratorio") || nuevoTipo.equals("Problemas"))))
            return -3;
        if (duracion < 1)
            return -23;
        ArrayList<Sesion> sesiones = this.planEstudios.getAsignatura(idAsignatura).getSesiones();
        for (int i = 0; i < sesiones.size(); ++i) {
            if (sesiones.get(i).getDuracion().equals(duracion) && sesiones.get(i).getTipo().equals(TipoClase.valueOf(tipo))) {
                sesiones.get(i).setTipo(TipoClase.valueOf(nuevoTipo));
                return 27;
            }
        }
        return -21;
    }

    /**
     * Edita el tipo y la duracion de una Sesion.
     * @param duracion      duracion actual de la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @param nuevaDuracion duracion que se asignará a Sesion.
     * @param nuevoTipo     TipoClase que se asignará a Sesion.
     * @return              codigoResultado de la operacion.
     */
    public int editarSesion(int duracion, String tipo, String idAsignatura, int nuevaDuracion, String nuevoTipo) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if ((!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
                || (!(nuevoTipo.equals("Teoria") || nuevoTipo.equals("Laboratorio") || nuevoTipo.equals("Problemas"))))
            return -3;
        if (duracion < 1)
            return -23;
        if (nuevaDuracion < 1)
            return -22;
        ArrayList<Sesion> sesiones = this.planEstudios.getAsignatura(idAsignatura).getSesiones();
        for (int i = 0; i < sesiones.size(); ++i) {
            if (sesiones.get(i).getDuracion().equals(duracion) && sesiones.get(i).getTipo().equals(TipoClase.valueOf(tipo))) {
                sesiones.get(i).setTipo(TipoClase.valueOf(nuevoTipo));
                sesiones.get(i).setDuracion(nuevaDuracion);
                return 27;
            }
        }
        return -21;
    }

    /**
     * Elimina una Sesion de una Asignatura.
     * @param duracion      horas que dura la Sesion.
     * @param tipo          TipoClase de la Sesion.
     * @param idAsignatura  id de la Asignatura de la Sesion.
     * @return              codigoResultado de la operación.
     */
    public int eliminarSesion(int duracion, String tipo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        if (duracion < 1)
            return -23;
        ArrayList<Sesion> sesiones = this.planEstudios.getAsignatura(idAsignatura).getSesiones();
        for (int i = 0; i < sesiones.size(); ++i) {
            if (sesiones.get(i).getDuracion().equals(duracion) && sesiones.get(i).getTipo().equals(TipoClase.valueOf(tipo))) {
                sesiones.remove(i);
                return 3;
            }
        }
        return -21;
    }

    /**
     * Añade una FranjaAsignatura a planEstudios.
     * @param idAsignatura  id de la Asignatura afectada.
     * @param horaIni       hora de inicio de la franja.
     * @param horaFin       hora de fin de la franja.
     * @return              codigoResultado de la operación.
     */
    public int addFranjaAsignatura(String idAsignatura, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (horaIni < 0 || horaFin > 24)
            return -4;
        this.addFranjaAsignatura(new FranjaAsignatura(this.planEstudios.getAsignatura(idAsignatura), horaIni, horaFin));
        return 4;
    }

    /**
     * Añade una FranjaAsignatura a planEstudios.
     * @param franjaAsignatura  FranjaAsignatura que se quiere añadir.
     */
    private void addFranjaAsignatura(FranjaAsignatura franjaAsignatura) {
        this.planEstudios.addAllRestriccion(franjaAsignatura);
        this.planEstudios.getAsignatura(franjaAsignatura.getAsignatura().getId()).addRestriccion(franjaAsignatura);
    }

    /**
     * Elimina una FranjaAsignatura de planEstudios.
     * @param idAsignatura  id de la Asignatura afectada.
     * @param horaIni       hora de inicio de la franja.
     * @param horaFin       hora de fin de la franja.
     * @return              codigoResultado de la operación.
     */
    public int eliminarFranjaAsignatura(String idAsignatura, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (horaIni < 0 || horaFin > 24)
            return -4;
        this.planEstudios.eliminarFranjaAsignatura(idAsignatura, horaIni, horaFin);
        this.planEstudios.getAsignatura(idAsignatura).eliminarFranjaAsignatura(horaIni, horaFin);
        return 5;
    }

    /**
     * Añade una FranjaNivel a planEstudios.
     * @param nombreNivel   nombre del Nivel afectado.
     * @param horaIni       hora de inicio de la franja.
     * @param horaFin       hora de fin de la franja.
     * @return              codigoResultado de la operación.
     */
    public int addFranjaNivel(String nombreNivel, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneNivel(nombreNivel)))
            return -1;
        if (horaIni < 0 || horaFin > 24)
            return -4;
        this.addFranjaNivel(new FranjaNivel(this.planEstudios.getNivel(nombreNivel), horaIni, horaFin));
        return 6;
    }

    /**
     * Añade una FranjaNivel a planEstudios.
     * @param franjaNivel   FranjaNivel que se quiere añadir.
     */
    private void addFranjaNivel(FranjaNivel franjaNivel) {
        this.planEstudios.addAllRestriccion(franjaNivel);
        this.planEstudios.getNivel(franjaNivel.getNivel().getNombre()).addRestriccion(franjaNivel);
    }

    /**
     * Elimina una FranjaNivel de planEstudios.
     * @param nombreNivel   nombre del Nivel afectado.
     * @param horaIni       hora de inicio de la franja.
     * @param horaFin       hora de fin de la franja.
     * @return              codigoResultado de la operación.
     */
    public int eliminarFranjaNivel(String nombreNivel, int horaIni, int horaFin) {
        if (!(this.planEstudios.tieneNivel(nombreNivel)))
            return -1;
        if (horaIni < 0 || horaFin > 24)
            return -4;
        this.planEstudios.eliminarFranjaNivel(nombreNivel, horaIni, horaFin);
        this.planEstudios.getNivel(nombreNivel).eliminarFranjaNivel(horaIni, horaFin);
        return 7;
    }

    /**
     * Añade una FranjaTrabajo a planEstudios.
     * @param horaIni   hora de inicio de la franja.
     * @param horaFin   hora de fin de la franja.
     * @return          codigoResultado de la operación.
     */
    public int addFranjaTrabajo(int horaIni, int horaFin) {
        if (horaIni < 0 || horaFin > 24)
            return -4;
        this.addFranjaTrabajo(new FranjaTrabajo(horaIni, horaFin));
        return 8;
    }

    /**
     * Añade una FranjaTrabajo a planEstudios.
     * @param franjaTrabajo FranjaTrabajo que se quiere añadir.
     */
    private void addFranjaTrabajo(FranjaTrabajo franjaTrabajo) {
        this.planEstudios.addAllRestriccion(franjaTrabajo);
        this.planEstudios.addRestriccion(franjaTrabajo);
    }

    /**
     * Elimina una FranjaTrabajo de planEstudios.
     * @param horaIni   hora de inicio de la franja.
     * @param horaFin   hora de fin de la franja.
     * @return          codigoResultado de la operación.
     */
    public int eliminarFranjaTrabajo(int horaIni, int horaFin) {
        if (horaIni < 0 || horaFin > 24)
            return -4;
        this.planEstudios.eliminarFranjaTrabajo(horaIni, horaFin);
        return 9;
    }

    /**
     * Añade un DiaLibre a planEstudios.
     * @param diaSemana dia de la semana del DiaLibre que se quiere añadir.
     * @return          codigoResultado de la operación.
     */
    public int addDiaLibre(int diaSemana) {
        if (diaSemana < 1 || diaSemana > 7)
            return -5;
        this.addDiaLibre(new DiaLibre(diaSemana));
        return 10;
    }

    /**
     * Añade un DiaLibre a planEstudios.
     * @param diaLibre  DiaLibre que se quiere añadir.
     */
    private void addDiaLibre(DiaLibre diaLibre) {
        this.planEstudios.addRestriccion(diaLibre);
        this.planEstudios.addAllRestriccion(diaLibre);
    }

    /**
     * Elimina un DiaLibre de planEstudios.
     * @param diaSemana diaSemana del DiaLibre que se quiere eliminar.
     * @return          codigoResultado de la operación.
     */
    public int eliminarDiaLibre(int diaSemana) {
        if (diaSemana < 1 || diaSemana > 7)
            return -5;
        this.planEstudios.eliminarDiaLibre(diaSemana);
        return 11;
    }

    /**
     * Añade un Prerrequisito a planEstudios.
     * @param idAsignatura      id de Asignatura de que tiene el Correquisito.
     * @param idPrerrequisito   id de Asignatura que es Prerrequisito.
     * @return                  codigoResultado de la operación.
     */
    public int addPrerrequisito(String idAsignatura, String idPrerrequisito) {
        if (idAsignatura.equals(idPrerrequisito))
            return -26;
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -6;
        if (!(this.planEstudios.tieneAsignatura(idPrerrequisito)))
            return -7;
        Prerrequisito prerrequisito = new Prerrequisito(this.planEstudios.getAsignatura(idAsignatura), this.planEstudios.getAsignatura(idPrerrequisito));
        this.addPrerrequisito(prerrequisito);
        return 12;
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
     * @param idAsignatura      id de Asignatura de que tiene el Correquisito.
     * @param idPrerrequisito   id de Asignatura que es Prerrequisito.
     * @return                  codigoResultado de la operación.
     */
    public int eliminarPrerrequisito(String idAsignatura, String idPrerrequisito) {
        if (idAsignatura.equals(idPrerrequisito))
            return -26;
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -6;
        if (!(this.planEstudios.tieneAsignatura(idPrerrequisito)))
            return -7;
        this.planEstudios.eliminarPrerrequisito(idAsignatura, idPrerrequisito);
        this.planEstudios.getAsignatura(idAsignatura).eliminarPrerrequisito(idPrerrequisito);
        return 13;
    }

    /**
     * Añade un Correquisito a planEstudios.
     * @param idAsignatura1 id de Asignatura de Correquisito.
     * @param idAsignatura2 id de Asignatura de Correquisito.
     * @return              codigoResultado de la operación.
     */
    public int addCorrequisito(String idAsignatura1, String idAsignatura2) {
        if (idAsignatura1.equals(idAsignatura2))
            return -26;
        if (!(this.planEstudios.tieneAsignatura(idAsignatura1)))
            return -2;
        if (!(this.planEstudios.tieneAsignatura(idAsignatura2)))
            return -2;
        Correquisito correquisito = new Correquisito(this.planEstudios.getAsignatura(idAsignatura1), this.planEstudios.getAsignatura(idAsignatura2));
        this.addCorrequisito(correquisito);
        return 14;
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
     * @param idAsignatura1 id de Asignatura de Correquisito.
     * @param idAsignatura2 id de Asignatura de Correquisito.
     * @return              codigoResultado de la operación.
     */
    public int eliminarCorrequisito(String idAsignatura1, String idAsignatura2) {
        if (idAsignatura1.equals(idAsignatura2))
            return -26;
        if (!(this.planEstudios.tieneAsignatura(idAsignatura1)))
            return -2;
        if (!(this.planEstudios.tieneAsignatura(idAsignatura2)))
            return -2;
        this.planEstudios.getAsignatura(idAsignatura1).eliminarCorrequisito(idAsignatura1, idAsignatura2);
        this.planEstudios.getAsignatura(idAsignatura2).eliminarCorrequisito(idAsignatura1, idAsignatura2);
        this.planEstudios.eliminarCorrequisito(idAsignatura1, idAsignatura2);
        return 15;
    }

    /**
     * Añade un Nivel a planEstudios.
     * @param nombre    nombre que se quiere dar al Nivel añadido.
     * @return          codigoResultado de la operación.
     */
    public int addNivel(String nombre) {
        if (this.planEstudios.tieneNivel(nombre))
            return -8;
        this.planEstudios.addNivel(new Nivel(nombre));
        return 16;
    }

    /**
     * Asigna un nuevo nombre a un Nivel.
     * @param nombre        nombre que se quiere dar al Nivel añadido.
     * @param nuevoNombre   nuevo nombre que se asigna a Nivel.
     * @return          codigoResultado de la operación.
     */
    public int editarNombreNivel(String nombre, String nuevoNombre) {
        if (!(this.planEstudios.tieneNivel(nombre)))
            return -1;
        if (this.planEstudios.tieneNivel(nuevoNombre))
            return -8;
        this.planEstudios.getNivel(nombre).setNombre(nuevoNombre);
        return 17;
    }

    /**
     * Elimina un Nivel de planEstudios.
     * @param nombre    Nombre del Nivel a eliminar.
     * @return          codigoResultado de la operación.
     */
    public int eliminarNivel(String nombre) {
        if (!(this.planEstudios.tieneNivel(nombre)))
            return -1;
        for (Map.Entry<String, Asignatura> entry : this.planEstudios.getNivel(nombre).getAsignaturas().entrySet())
            entry.getValue().setNivel(null);
        this.planEstudios.eliminarNivel(nombre);
        return 17;
    }

    /**
     * Añade una Asignatura a planEstudios.
     * @param id        id que se quiere dar a la Asignatura añadida.
     * @param nombre    nombre que se quiere dar a la Asignatura añadida.
     * @return          codigoResultado de la operación.
     */
    public int addAsignatura(String id, String nombre) {
        if (this.planEstudios.tieneAsignatura(id))
            return -9;
        this.planEstudios.addAsignatura(new Asignatura(id, nombre, this.planEstudios));
        return 22;
    }

    /**
     * Añade una Asignatura a planEstudios y la asocia a un Nivel existente.
     * @param id            id que se quiere dar a la Asignatura añadida.
     * @param nombre        nombre que se quiere dar a la Asignatura añadida.
     * @param nombreNivel   nombre del nivel al que se quiere asociar la Asignatura.
     * @return              codigoResultado de la operación.
     */
    public int addAsignatura(String id, String nombre, String nombreNivel) {
        if (this.planEstudios.tieneAsignatura(id))
            return -9;
        if (!(this.planEstudios.tieneNivel(nombreNivel)))
            return -1;
        this.planEstudios.addAsignatura(new Asignatura(id, nombre, this.planEstudios, this.planEstudios.getNivel(nombreNivel)));
        return 22;
    }

    /**
     * Cambia la id de una Asignatura.
     * @param id        id actual de la Asignatura.
     * @param nuevaId   id que se va a dar a Asignatura.
     * @return          codigoResultado de la operación.
     */
    public int editarIdAsignatura(String id, String nuevaId) {
        if (!(this.planEstudios.tieneAsignatura(id)))
            return -2;
        if (this.planEstudios.tieneAsignatura(nuevaId))
            return -9;
        this.planEstudios.getAsignatura(id).setId(nuevaId);
        return 29;
    }

    /**
     * Cambia el nombre de una Asignatura.
     * @param id            id actual de la Asignatura.
     * @param nuevoNombre   nombre que se va a dar a Asignatura.
     * @return              codigoResultado de la operación.
     */
    public int editarNombreAsignatura(String id, String nuevoNombre) {
        if (!(this.planEstudios.tieneAsignatura(id)))
            return -2;
        this.planEstudios.getAsignatura(id).setNombre(nuevoNombre);
        return 29;
    }

    /**
     * Cambia el Nivel de una Asignatura.
     * @param id                id actual de la Asignatura.
     * @param nombreNuevoNivel  nombre del nuevo Nivel que se asigna a Asignatura.
     * @return                  codigoResultado de la operación.
     */
    public int editarNivelAsignatura(String id, String nombreNuevoNivel) {
        if (!(this.planEstudios.tieneAsignatura(id)))
            return -2;
        if (!(this.planEstudios.tieneNivel(nombreNuevoNivel)))
            return -1;
        if (this.planEstudios.getAsignatura(id).tieneNivel())
            this.planEstudios.getAsignatura(id).getNivel().eliminarAsignatura(id);
        this.planEstudios.getAsignatura(id).setNivel(this.planEstudios.getNivel(nombreNuevoNivel));
        this.planEstudios.getNivel(nombreNuevoNivel).addAsignatura(this.planEstudios.getAsignatura(id));
        return 29;
    }

    /**
     * Quita el Nivel de una Asignatura.
     * @param id        id actual de la Asignatura.
     * @return          codigoResultado de la operación.
     */
    public int quitarNivelAsignatura(String id) {
        if (!(this.planEstudios.tieneAsignatura(id)))
            return -2;
        if (!(this.planEstudios.getAsignatura(id).tieneNivel()))
            return -24;
        this.planEstudios.getAsignatura(id).getNivel().eliminarAsignatura(id);
        this.planEstudios.getAsignatura(id).quitarNivel();
        return 29;
    }

    /**
     * Elimina una Asignatura de planEstudios.
     * @param id    id dela Asignatura a eliminar.
     * @return      codigoResultado de la operación.
     */
    public int eliminarAsignatura(String id) {
        if (this.planEstudios.getAsignatura(id).tieneNivel())
            this.planEstudios.getAsignatura(id).getNivel().eliminarAsignatura(id);
        if (!(this.planEstudios.tieneAsignatura(id)))
            return -2;
        this.planEstudios.eliminarAsignatura(id);
        return 23;
    }

    /**
     * Añade un Grupo a una Asignatura de planEstudios.
     * @param id            id que se quiere dar al Grupo añadido.
     * @param idAsignatura  id de la Asignatura a la que se quiere asociar el Grupo.
     * @return              codigoResultado de la operación.
     */
    public int addGrupo(String id, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(id))
            return -10;
        this.planEstudios.getAsignatura(idAsignatura).addGrupo(new Grupo(id, this.planEstudios.getAsignatura(idAsignatura)));
        return 18;
    }

    /**
     * cambia la id de un Grupo de planEstudios.
     * @param id            id del Grupo a eliminar.
     * @param idAsignatura  id de la Asignatura a la que se quiere asociar el Grupo.
     * @param nuevaId       id que se asignará al Grupo.
     * @return              codigoResultado de la operación.
     */
    public int editarIdGrupo(String id, String idAsignatura, String nuevaId) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!(this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(id)))
            return -12;
        if (this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(nuevaId))
            return -10;
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(id).setId(nuevaId);
        return 19;
    }

    /**
     * Elimina un Grupo de planEstudios.
     * @param id            id del Grupo a eliminar.
     * @param idAsignatura  id de la Asignatura a la que se quiere asociar el Grupo.
     * @return              codigoResultado de la operación.
     */
    public int eliminarGrupo(String id, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!(this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(id)))
            return -12;
        this.planEstudios.getAsignatura(idAsignatura).eliminarGrupo(id);
        return 19;
    }

    /**
     * Añade un SubGrupo a un Grupo de una Asignatura de planEstudios.
     * @param id            id que se quiere dar al SubGrupo añadido.
     * @param plazas        plazas del SubGrupo.
     * @param tipo          TipoClase del SubGrupo.
     * @param idGrupo       id del Grupo al que se quiere asociar el SubGrupo.
     * @param idAsignatura  id de la Asignatura a la que se quiere asociar el SubGrupo.
     * @return              codigoResultado de la operación.
     */
    public int addSubGrupo(String id, int plazas, String tipo, String idGrupo, String idAsignatura) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo))
            return -12;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        if (plazas < 1)
            return -25;
        if (this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString()))
            return -11;
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).addSubGrupo(new SubGrupo(id, plazas, TipoClase.Laboratorio.valueOf(tipo), this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo)));
        return 20;
    }

    /**
     * Edita la id de un SubGrupo de planEstudios.
     * @param id            id del SubGrupo a editar.
     * @param tipo          tipo del SubGrupo.
     * @param idGrupo       id del Grupo del SubGrupo.
     * @param idAsignatura  id de la Asignatura del SubGrupo.
     * @param nuevaId       nueva id que se quiere asignar a SubGrupo.
     * @return              codigoResultado de la operación.
     */
    public int editarIdSubGrupo(String id, String tipo, String idGrupo, String idAsignatura, String nuevaId) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo))
            return -12;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        if (!(this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString())))
            return -13;
        if ((this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(nuevaId + TipoClase.Laboratorio.valueOf(tipo).toString())))
            return -11;
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).getSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString()).setId(nuevaId);
        return 30;
    }

    /**
     * Edita el tipo de un SubGrupo de planEstudios.
     * @param id            id del SubGrupo a editar.
     * @param tipo          tipo del SubGrupo.
     * @param idGrupo       id del Grupo del SubGrupo.
     * @param idAsignatura  id de la Asignatura del SubGrupo.
     * @param nuevoTipo     nuevo tipo que se quiere asignar a SubGrupo.
     * @return              codigoResultado de la operación.
     */
    public int editarTipoSubGrupo(String id, String tipo, String idGrupo, String idAsignatura, String nuevoTipo) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo))
            return -12;
        if ((!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
                || (!(nuevoTipo.equals("Teoria") || nuevoTipo.equals("Laboratorio") || nuevoTipo.equals("Problemas"))))
            return -3;
        if (!(this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString())))
            return -13;
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).getSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString()).setTipo(TipoClase.valueOf(nuevoTipo));
        return 30;
    }

    /**
     * Edita el número de plazas de un SubGrupo de planEstudios.
     * @param id            id del SubGrupo a editar.
     * @param tipo          tipo del SubGrupo.
     * @param idGrupo       id del Grupo del SubGrupo.
     * @param idAsignatura  id de la Asignatura del SubGrupo.
     * @param nuevaPlazas   nuevo número de plazas que se quiere asignar a SubGrupo.
     * @return              codigoResultado de la operación.
     */
    public int editarPlazasSubGrupo(String id, String tipo, String idGrupo, String idAsignatura, int nuevaPlazas) {
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo))
            return -12;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        if (nuevaPlazas < 1)
            return -25;
        if (!(this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString())))
            return -13;
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).getSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString()).setPlazas(nuevaPlazas);
        return 30;
    }

    /**
     * Elimina un SubGrupo de planEstudios.
     * @param id            id del SubGrupo a eliminar.
     * @param tipo          tipo del SubGrupo.
     * @param idGrupo       id del Grupo del SubGrupo.
     * @param idAsignatura  id de la Asignatura del SubGrupo.
     * @return              codigoResultado de la operación.
     */
    public int eliminarSubGrupo(String id, String tipo, String idGrupo, String idAsignatura) {
        System.out.println(id + ' ' + tipo + ' ' + idGrupo + ' ' + idAsignatura);
        if (!(this.planEstudios.tieneAsignatura(idAsignatura)))
            return -2;
        if (!this.planEstudios.getAsignatura(idAsignatura).tieneGrupo(idGrupo))
            return -12;
        if (!(tipo.equals("Teoria") || tipo.equals("Laboratorio") || tipo.equals("Problemas")))
            return -3;
        if (!(this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).tieneSubGrupo(id)))
            return -13;
        this.planEstudios.getAsignatura(idAsignatura).getGrupo(idGrupo).eliminarSubGrupo(id + TipoClase.Laboratorio.valueOf(tipo).toString());
        return 21;
    }

    /**
     * Añade un Aula de planEstudios.
     * @param id        id que se quiere dar a la Aula añadida.
     * @param plazas    número de plazas que tendrá el Aula añadida.
     * @param tipos     TiposClase con los que es compatible el Aula añadida.
     * @return          codigoResultado de la operación.
     */
    public int addAula(String id, int plazas, String[] tipos) {
        if (this.planEstudios.tieneAula(id))
            return -15;
        ArrayList<TipoClase> t = new ArrayList<TipoClase>();
        for (int i = 0; i < tipos.length; ++i) {
            if (tipos[i].equals("Teoria"))
                t.add(TipoClase.Teoria);
            else if (tipos[i].equals("Laboratorio"))
                t.add(TipoClase.Laboratorio);
            else if (tipos[i].equals("Problemas"))
                t.add(TipoClase.Problemas);
            else
                return -3;
        }
        this.planEstudios.addAula(new Aula(id, plazas, t));
        return 24;
    }

    /**
     * Edita la id de una Aula de planEstudios.
     * @param id        id del Aula a editar.
     * @param nuevaId   Nueva id que se quiere asignar.
     * @return          codigoResultado de la operación.
     */
    public int editarIdAula(String id, String nuevaId) {
        if (!(this.planEstudios.tieneAula(id)))
            return -14;
        if (this.planEstudios.tieneAula(nuevaId))
            return -15;
        this.planEstudios.getAula(id).setId(nuevaId);
        return 25;
    }

    /**
     * Edita las plazas de una Aula de planEstudios.
     * @param id            id del Aula a editar.
     * @param nuevasPlazas  Nuevas plazas que se quiere asignar.
     * @return              codigoResultado de la operación.
     */
    public int editarIdAula(String id, int nuevasPlazas) {
        if (!(this.planEstudios.tieneAula(id)))
            return -14;
        if (nuevasPlazas < 1)
            return -25;
        this.planEstudios.getAula(id).setPlazas(nuevasPlazas);
        return 25;
    }

    /**
     * Edita los tipos compatibles con un Aula de planEstudios.
     * @param id            id del Aula que se quiere editar
     * @param nuevosTipos   nuevos TipoClase que se quiere asignar a Aula.
     * @return              codigoResultado de la operación.
     */
    public int editarTiposAula(String id, String[] nuevosTipos) {
        if (!(this.planEstudios.tieneAula(id)))
            return -14;
        ArrayList<TipoClase> t = new ArrayList<TipoClase>();
        for (int i = 0; i < nuevosTipos.length; ++i) {
            if (nuevosTipos[i].equals("Teoria"))
                t.add(TipoClase.Teoria);
            else if (nuevosTipos[i].equals("Laboratorio"))
                t.add(TipoClase.Laboratorio);
            else if (nuevosTipos[i].equals("Problemas"))
                t.add(TipoClase.Problemas);
            else
                return -3;
        }
        this.planEstudios.getAula(id).setTipos(t);
        return 25;
    }

    /**
     * Elimina un Aula de planEstudios.
     * @param id    id del Aula a eliminar.
     * @return      codigoResultado de la operación.
     */
    public int eliminarAula(String id) {
        if (!(this.planEstudios.tieneAula(id)))
            return -14;
        this.planEstudios.eliminarAula(id);
        return 25;
    }

    /**
     * Carga en CtrlDominio el escenario con el nombre indicado.
     * @param escenario                 Nombre del escenario que se quiere cargar.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
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

    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> escaneaHorario(String h, Boolean extended) throws Exception {
        return controladorEscenarios.escaneaHorario(h, extended);
    }

    public Horario getHorarioActivo() {
        return horarioActivo;
    }

    /**
     * Escribe un Horario.
     * @param horario   String con los datos de Horario.
     * @param idHorario id del Horario que se escribe.
     * @throws IOException              Ha fallado una operación IO.
     */
    public void writeHorario(String horario, String idHorario) throws IOException {
        controladorEscenarios.writeHorario(horario, idHorario);
    }

    public void writeExtension(String extended, String idHorario) throws IOException {
        controladorEscenarios.writeExtension(extended, idHorario);
    }

    public boolean readExtension(String idHorario) throws IOException {
        return controladorEscenarios.readExtension(idHorario);
    }

    /**
     * Escribe un Horario por consola.
     * @param horario       String con los datos de Horario.
     * @param idHorario     id del Horario que se escribe.
     * @throws IOException  Problema en IO.
     */
    public void writeHorario(String horario, Integer idHorario) throws IOException {
        controladorEscenarios.writeHorario(horario, idHorario);
    }

    /**
     * Escribe un horario a partir de un HashMap.
     * @param horario       HashMap con los datos de Horario.
     * @param idHorario     id del horario que se escribe.
     * @throws IOException  Problema en IO.
     */
    public void writeHorarioFromMap(HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario, String idHorario) throws IOException {
        controladorEscenarios.writeHorarioFromMap(horario, idHorario);
    }

    /**
     * Lee un horario guardado préviamente.
     * @param horario                   Nombre del Horario que se quiere leer.
     * @return                          String con los datos del Horario indicado.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     */
    public String readHorario(String horario) throws FileNotFoundException, IOException {
        return controladorEscenarios.readHorario(horario);
    }

    /**
     * Carga el PlanEstudios del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
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
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
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
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
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
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
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
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public HashMap<String, ArrayList<Object>> subirAsignaturas(String escenario) throws FileNotFoundException, IOException, ParseException {
        //List<JSONObject> asignaturasData = controladorAsignaturas.getByEscenario(escenario);
        Map<String, Asignatura> aCD = this.planEstudiosMap.get(escenario).getAsignaturas();
        HashMap<String, ArrayList<Object>> asigs = new HashMap<>();

        for (Map.Entry<String, Asignatura> entry : aCD.entrySet()) {
            ArrayList<Object> asigProperties = new ArrayList<>();
            asigProperties.add(entry.getValue().getNombre());
            if (entry.getValue().tieneNivel())
                asigProperties.add(entry.getValue().getNivel().getNombre());
            else
                asigProperties.add(""); //Texto que se pone en Asignatura sin Nivel.

            ArrayList<Sesion> sACD = entry.getValue().getSesiones();
            ArrayList<Object> sesiones = new ArrayList<>();
            for (int i = 0; i < sACD.size(); ++i) {
                ArrayList<Object> sesProperties = new ArrayList<>();
                sesProperties.add(sACD.get(i).getDuracion());
                sesProperties.add(Aux.strTipoCompleto(sACD.get(i).getTipo()));
                sesiones.add(sesProperties);
            }
            asigProperties.add(sesiones);

            Map<String, Grupo> gACD = entry.getValue().getGrupos();
            ArrayList<Object> grupos = new ArrayList<>();
            for (Map.Entry<String, Grupo> grupo : gACD.entrySet()) {
                ArrayList<Object> grProperties = new ArrayList<>();
                grProperties.add(grupo.getValue().getId());

                Map<String, SubGrupo> sGACD = grupo.getValue().getSubGrupos();
                ArrayList<Object> subgrupos = new ArrayList<>();
                for (Map.Entry<String, SubGrupo> subGrupo : sGACD.entrySet()) {
                    ArrayList<Object> subgrProperties = new ArrayList<>();
                    subgrProperties.add(subGrupo.getValue().getId());
                    subgrProperties.add(subGrupo.getValue().getPlazas());
                    subgrProperties.add(subGrupo.getValue().getTipo());
                    subgrupos.add(subgrProperties);
                }
                grProperties.add(subgrupos);

                grupos.add(grProperties);
            }
            asigProperties.add(grupos);

            asigs.put(entry.getValue().getId(), asigProperties);
        }

        return asigs;
    }

    /**
     * Sube Aulas.
     * @param   escenario String con el escenario indicado.
     * @return  ArrayList con la informacion deseada.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public HashMap<String, ArrayList<Object>> subirAulas(String escenario) throws FileNotFoundException, IOException, ParseException {
        //List<JSONObject> aulasData = controladorAulas.getByEscenario(escenario);
        Map<String, Aula> auCD = this.planEstudiosMap.get(escenario).getAulas();
        HashMap<String, ArrayList<Object>> aulas = new HashMap<>();

        for (Map.Entry<String, Aula> aula : auCD.entrySet()) {
            ArrayList<Object> auProperties = new ArrayList<>();
            auProperties.add(aula.getValue().getPlazas());

            auProperties.add(aula.getValue().getTipos());

            aulas.put(aula.getValue().getId(), auProperties);
        }
        return aulas;
    }

    /**
     * Sube las Restricciones.
     * @param   escenario String con el escenario indicado.
     * @return  HashMap con la informacion deseada.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public HashMap<String, ArrayList<Object>> subirRestricciones(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> restriccionesData = controladorRestricciones.getByEscenario(escenario);
        Map<TipoRestriccion, ArrayList<Restriccion>> rCD = this.planEstudiosMap.get(escenario).getAllRestriccionesCategorizadas();
        HashMap<String, ArrayList<Object>> restricciones = new HashMap<>();

        //R: diaLibre
        ArrayList<Restriccion> dLCD = rCD.get(TipoRestriccion.DiaLibre);
        ArrayList<Object> dlProperties = new ArrayList<>();
        for (int i = 0; i < dLCD.size(); ++i) {
            dlProperties.add(((DiaLibre)(dLCD.get(i))).getDia());
        }
        restricciones.put("diaLibre", dlProperties);

        //R: franjaTrabajo
        ArrayList<Restriccion> fTCD = rCD.get(TipoRestriccion.FranjaTrabajo);
        ArrayList<Object> ftProperties = new ArrayList<>();
        for (int i = 0; i < fTCD.size(); ++i) {
            ftProperties = new ArrayList<>();
            ftProperties.add(((FranjaTrabajo)(fTCD.get(i))).getHoraIni());
            ftProperties.add(((FranjaTrabajo)(fTCD.get(i))).getHoraFin());
        }
        restricciones.put("franjaTrabajo", ftProperties);

        //R: nivelHora
        ArrayList<Restriccion> nHCD = rCD.get(TipoRestriccion.NivelHora);
        ArrayList<Object> nhProperties = new ArrayList<>();
        for (int i = 0; i < nHCD.size(); ++i) {
            nhProperties.add(((NivelHora)(nHCD.get(i))).getNivel().getNombre());
        }
        restricciones.put("nivelHora", nhProperties);

        //R: correquisitos
        ArrayList<Restriccion> cCD = rCD.get(TipoRestriccion.Correquisito);
        ArrayList<Object> coProperties = new ArrayList<>();
        for (int i = 0; i < cCD.size(); ++i) {
            ArrayList<String> co = new ArrayList<>();
            co.add(((Correquisito)(cCD.get(i))).getAsignatura1().getId());
            co.add(((Correquisito)(cCD.get(i))).getAsignatura2().getId());
            coProperties.add(co);
        }
        restricciones.put("correquisitos", coProperties);

        //R: prerrequisitos
        ArrayList<Restriccion> pCD = rCD.get(TipoRestriccion.Prerrequisito);
        ArrayList<Object> prProperties = new ArrayList<>();
        for (int i = 0; i < pCD.size(); ++i) {
            ArrayList<String> pr = new ArrayList<>();
            pr.add(((Prerrequisito)(pCD.get(i))).getAsignatura().getId());
            pr.add(((Prerrequisito)(pCD.get(i))).getPrerrequisito().getId());
            prProperties.add(pr);
        }
        restricciones.put("prerrequisitos", prProperties);

        //R: franjaAsignatura
        ArrayList<Restriccion> fACD = rCD.get(TipoRestriccion.FranjaAsignatura);
        ArrayList<Object> faProperties = new ArrayList<>();
        for (int i = 0; i < fACD.size(); ++i) {
            ArrayList<Object> fa = new ArrayList<>();
            fa.add(((FranjaAsignatura)(fACD.get(i))).getAsignatura().getId());
            fa.add(((FranjaAsignatura)(fACD.get(i))).getHoraIni());
            fa.add(((FranjaAsignatura)(fACD.get(i))).getHoraFin());
            faProperties.add(fa);
        }
        restricciones.put("franjaAsignatura", faProperties);

        //R: franjaNivel
        ArrayList<Restriccion> fNCD = rCD.get(TipoRestriccion.FranjaNivel);
        ArrayList<Object> fnProperties = new ArrayList<>();
        for (int i = 0; i < fNCD.size(); ++i) {
            ArrayList<Object> fn = new ArrayList<>();
            fn.add(((FranjaNivel)(fNCD.get(i))).getNivel().getNombre());
            fn.add(((FranjaNivel)(fNCD.get(i))).getHoraIni());
            fn.add(((FranjaNivel)(fNCD.get(i))).getHoraFin());
            fnProperties.add(fn);
        }
        restricciones.put("franjaNivel", fnProperties);

        return restricciones;
    }

    /**
     * Sube planEstudios.
     * @param   escenario String con el escenario indicado.
     * @return  ArrayList con la informacion deseada.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public ArrayList<String> subirPlanEstudios(String escenario) throws FileNotFoundException, IOException, ParseException {
        PlanEstudios pECD = planEstudiosMap.get(escenario);
        ArrayList<String> pE = new ArrayList<>();
        pE.add(pECD.getNombre());
        for (Map.Entry<String, Nivel> entry : pECD.getNiveles().entrySet())
            pE.add(entry.getValue().getNombre());
        return pE;
    }

    /**
     * Genera un Horario con los datos de CtrlDominio.
     * @param id    id del Horario que se generará.
     * @return      Horario generado con el PlanEstudios que se ha usado.
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

    //public String

    /**
     * Activa o desactiva las Restricciones del escenario en función de un HashMap<String, ArrayList<Boolean>>.
     * @param restricciones Valores que se asignan al atributo activa de cada Restriccion.
     */
    public void habilitarRestricciones(HashMap<String, ArrayList<Boolean>> restricciones) {
        Map<TipoRestriccion, ArrayList<Restriccion>> allRestricciones = this.planEstudios.getAllRestriccionesCategorizadas();
        System.out.println(restricciones);
        ArrayList<Restriccion> diaLibres = allRestricciones.get(TipoRestriccion.DiaLibre);
        ArrayList<Boolean> dLBooleans = restricciones.get("diaLibre");
        for (int i = 0; i < diaLibres.size(); ++i) {
            diaLibres.get(i).setActiva(dLBooleans.get(i));
        }
        ArrayList<Restriccion> franjaTrabajos = allRestricciones.get(TipoRestriccion.FranjaTrabajo);
        ArrayList<Boolean> fTBooleans = restricciones.get("franjaTrabajo");
        for (int i = 0; i < franjaTrabajos.size(); ++i) {
            franjaTrabajos.get(i).setActiva(fTBooleans.get(i));
        }
        ArrayList<Restriccion> franjaAsignaturas = allRestricciones.get(TipoRestriccion.FranjaAsignatura);
        ArrayList<Boolean> fABooleans = restricciones.get("franjaAsignatura");
        for (int i = 0; i < franjaAsignaturas.size(); ++i) {
            franjaAsignaturas.get(i).setActiva(fABooleans.get(i));
        }
        ArrayList<Restriccion> franjaNivels = allRestricciones.get(TipoRestriccion.FranjaNivel);
        ArrayList<Boolean> fNBooleans = restricciones.get("franjaNivel");
        for (int i = 0; i < franjaNivels.size(); ++i) {
            franjaNivels.get(i).setActiva(fNBooleans.get(i));
        }
        ArrayList<Restriccion> correquisitos = allRestricciones.get(TipoRestriccion.Correquisito);
        ArrayList<Boolean> cBooleans = restricciones.get("correquisitos");
        for (int i = 0; i < correquisitos.size(); ++i) {
            correquisitos.get(i).setActiva(cBooleans.get(i));
        }
        ArrayList<Restriccion> prerrequisitos = allRestricciones.get(TipoRestriccion.Prerrequisito);
        ArrayList<Boolean> pBooleans = restricciones.get("prerrequisitos");
        for (int i = 0; i < prerrequisitos.size(); ++i) {
            prerrequisitos.get(i).setActiva(pBooleans.get(i));
        }
        ArrayList<Restriccion> nivelHoras = allRestricciones.get(TipoRestriccion.NivelHora);
        ArrayList<Boolean> nHBooleans = restricciones.get("nivelHora");
        for (int i = 0; i < nivelHoras.size(); ++i) {
            nivelHoras.get(i).setActiva(nHBooleans.get(i));
        }
        System.out.println(this.planEstudios.getAllRestricciones());
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
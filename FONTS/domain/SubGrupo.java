/**SubGrupo*/

/** Imports **/

package domain;

//import static java.lang.System.out;

import domaincontrollers.Aux;

/**
 * La clase SubGrupo contiene la información de una unidad compuesta por varios alumnos que realiza Clases de un TipoClase
 * concreto y que pertenece a un Grupo de una Asignatura.
 * @author  Daniel Martín
 */
public class SubGrupo {

    /**Atributos*/

    /**Código identificador del SubGrupo dentro de su Grupo.*/
    private String id;
    /**Número de alumnos que caben en el SubGrupo.*/
    private Integer plazas;
    /**TipoClase del SubGrupo.*/
    private TipoClase tipo;
    /**Grupo al que pertenece el SubGrupo.*/
    private Grupo grupo;

    /**Constructoras*/

    /**
     * Contructora de la clase SubGrupo.
     * @param id        id del SubGrupo creado.
     * @param plazas    plazas del SubGrupo creado.
     * @param tipo      TipoClase del SubGrupo creado.
     * @param grupo     Grupo al que pertence el SubGrupo creado.
     */
    public SubGrupo(String id, Integer plazas, TipoClase tipo, Grupo grupo) {
        this.id = id;
        this.plazas = plazas;
        this.tipo = tipo;
        this.grupo = grupo;
        //out.println(tipo);
        //out.println(this.toString());
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva id a SubGrupo.
     * @param id    Nueva id que se asigna a SubGrupo.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna un nuevo número de plazas al SubGrupo.
     * @param plazas    Nuevo número de plazas que se asigna a SubGrupo.
     */
    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    /**
     * Asigna un nuevo TipoClase a SubGrupo.
     * @param tipo  Nuevo TipoClase que se asigna a SubGrupo.
     */
    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    /**
     * Asigna un nuevo Grupo a SubGrupo.
     * @param grupo Grupo que se asigna a SubGrupo.
     */
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /**Consultoras*/

    /**
     * Devuelve un String con la id del SubGrupo.
     * @return  id del SubGrupo.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Genera una clave única para el SubGrupo.
     * @return  key del SubGrupo.
     */
    public String getKey() {
        return this.id + this.getTipo().toString();
    }

    /**
     * Genera una id completa a partir de la id del SubGrupo y la del Grupo a que pertenece.
     * @return  id completa del SubGrupo.
     */
    public String getIdCompleta() {
        return this.grupo.getId() + this.id;
    }

    /**
     * Devuelve el número de plazas del SubGrupo en forma de Integer.
     * @return  Número de plazas del SubGrupo.
     */
    public Integer getPlazas() {
        return this.plazas;
    }

    /**
     * Devuelve el TipoClase del SubGrupo.
     * @return  TipoClase del SubGrupo.
     */
    public TipoClase getTipo() {
        return this.tipo;
    }

    /**
     * Devuelve el Grupo al que pertence SubGrupo.
     * @return  Grupo al que pertenece SubGrupo.
     */
    public Grupo getGrupo() {
        return this.grupo;
    }

    /**
     * Devuelve la Asignatura a la que pertenece el Grupo del SubGrupo.
     * @return  Asignatura a la que pertenece el Grupo del SubGrupo.
     */
    public Asignatura getAsignatura() {
        return this.grupo.getAsignatura();
    }

    /**
     * Devuelve si la Asignatura del Grupo al que pertenece el SubGrupo tiene Nivel.
     * @return  true si la Asignatura del Grupo al que pertenece el SubGrupo tiene Nivel, false en caso contrario.
     */
    public Boolean tieneNivel() {
        return this.getAsignatura().tieneNivel();
    }

    /**
     * Devuelve el Nivel de la Asignatura a la que pertenece el Grupo del Subgrupo.
     * @return  Nivel de la Asignatura a la que pertenece el Grupo del Subgrupo.
     */
    public Nivel getNivel() {
        return this.getAsignatura().getNivel();
    }

    /**Métodos redefinidos*/

    /**
     * Convierte el SubGrupo a un String que contiene toda su información.
     * @return  String que contiene toda la información del SubGrupo.
     */
    @Override
    public String toString() {
        return "Subgrupo: " + this.id + "\n-Asignatura: " + this.getAsignatura().getId() + "\n-Grupo: " + this.grupo.getId()
                + "\n-Tipo: " + Aux.strTipo(this.tipo);
    }

}
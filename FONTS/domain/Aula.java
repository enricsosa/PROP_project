/**Aula*/

/**Imports*/

package domain;

import java.util.ArrayList;

/**
 * Aula contiene la informacion relativa a una Aula de un PlanEstudios.
 * @æuthor Daniel Martín
 * @see ArrayList
 */
public class Aula {

    /**Atributos*/

    /**Codigo identificador del Aula.*/
    private String id;
    /**Numero de personas que pueden coincidir en el Aula.*/
    private Integer plazas;
    /**Conjunto de TiposClase compatibles con el Aula.*/
    private ArrayList<TipoClase> tipos;

    /**Constructoras*/

    /**
     * Constructora de la clase Aula.
     * @param id        id del Aula.
     * @param plazas    plazas del Aula.
     * @param tipos     Conjunto de TiposClase del Aula.
     */
    public Aula(String id, Integer plazas, ArrayList<TipoClase> tipos) {
        this.id = id;
        this.plazas = plazas;
        this.tipos = tipos;
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva id al Aula.
     * @param id    Nueva id que se asignará al Aula.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna un nuevo número de plazas al Aula.
     * @param plazas    Nuevo número de plazas que se asignará al Aula.
     */
    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    /**
     * Asigna un nuevo conjunto de TiposClase al Aula.
     * @param tipos Nuevo conjunto de TiposClase que se asignará al Aula.
     */
    public void setTipos(ArrayList<TipoClase> tipos) {
        this.tipos = tipos;
    }

    /**Consultoras*/

    /**
     * Devuelve un String con la id del Aula.
     * @return  id del Aula.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve un Integer con el número de plazas del Aula.
     * @return  plazas del Aula.
     */
    public Integer getPlazas() {
        return this.plazas;
    }

    /**
     * Devuelve el conjunto de tiposClase compatibles con el Aula.
     * @return  tipos deñ Aula.
     */
    public ArrayList<TipoClase> getTipos() {
        return this.tipos;
    }

    /**
     * Indica si el Aula es compatible con el TipoClase Teoria.
     * @return  true si el Aula es compatible con el TipoClase Teoria, false en caso contrario.
     */
    public Boolean tieneTeoria() {
        for (int i = 0; i < tipos.size(); ++i) {
            if (tipos.get(i) == TipoClase.Teoria) return true;
        }
        return false;
    }

    /**
     * Indica si el Aula es compatible con el TipoClase Laboratorio.
     * @return  true si el Aula es compatible con el TipoClase Laboratorio, false en caso contrario.
     */
    public Boolean tieneLaboratorio() {
        for (int i = 0; i < tipos.size(); ++i) {
            if (tipos.get(i) == TipoClase.Laboratorio) return true;
        }
        return false;
    }

    /**
     * Indica si el Aula es compatible con el TipoClase Problemas.
     * @return  true si el Aula es compatible con el TipoClase Problemas, false en caso contrario.
     */
    public Boolean tieneProblemas() {
        for (int i = 0; i < tipos.size(); ++i) {
            if (tipos.get(i) == TipoClase.Problemas) return true;
        }
        return false;
    }

    /**Métodos redefinidos*/

    /**
     * Convierte Aula a String.
     * @return  Devuelve un String con la información del Aula.
     */
    @Override
    public String toString() {
        String text = ("Aula: " + this.id + "\n-Plazas: " + this.plazas.toString() + "\n-Tipos:");
        for (int i = 0; i < this.tipos.size(); ++i) {
            text += (" " + strTipo(tipos.get(i)));
            if (i != (this.tipos.size() - 1)) text += ",";
        }
        return text;
    }

    /**
     * Convierte un TipoClase a un String con su forma abreviada.
     * @param tipo  TipoClase que se quiere convertir.
     * @return  Devuelve un String con la forma abreviada del TipoClase.
     */
    static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
    }


}
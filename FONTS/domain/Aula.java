package domain;

import java.util.ArrayList;

/** Imports **/

public class Aula {

    /** Atributos **/

    private String id;
    private Integer plazas;
    private ArrayList<TipoClase> tipos;

    /** Constructoras **/

    public Aula(String id, Integer plazas, ArrayList<TipoClase> tipos) {
        this.id = id;
        this.plazas = plazas;
        this.tipos = tipos;
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public void setTipos(ArrayList<TipoClase> tipos) {
        this.tipos = tipos;
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public Integer getPlazas() {
        return this.plazas;
    }

    public ArrayList<TipoClase> getTipos() {
        return this.tipos;
    }

    public Boolean tieneTeoria() {
        for (int i = 0; i < tipos.size(); ++i) {
            if (tipos.get(i) == TipoClase.Teoria) return true;
        }
        return false;
    }

    public Boolean tieneLaboratorio() {
        for (int i = 0; i < tipos.size(); ++i) {
            if (tipos.get(i) == TipoClase.Laboratorio) return true;
        }
        return false;
    }

    public Boolean tieneProblemas() {
        for (int i = 0; i < tipos.size(); ++i) {
            if (tipos.get(i) == TipoClase.Problemas) return true;
        }
        return false;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return id;
    }


}
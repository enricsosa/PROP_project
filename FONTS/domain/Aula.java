package domain;

/** Imports **/

public class Aula {

    /** Atributos **/

    private String id;
    private Integer plazas;

    /** Constructoras **/

    public Aula(String id, Integer plazas) {
        this.id = id;
        this.plazas = plazas;
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public Integer getPlazas() {
        return this.plazas;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return id;
    }


}
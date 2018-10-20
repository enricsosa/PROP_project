package domain;

/** Imports **/

public class Aula {

    /** Atributos **/

    private String id;
    private Integer plazas;
    private TipoClase[] tipos;

    /** Constructoras **/

    public Aula(String id, Integer plazas, TipoClase[] tipos) {
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

    public void setTipos(TipoClase[] tipos) {
        this.tipos = tipos;
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public Integer getPlazas() {
        return this.plazas;
    }

    public TipoClase[] getTipos() {
        return this.tipos;
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return id;
    }


}
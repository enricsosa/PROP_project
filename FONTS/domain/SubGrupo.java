package domain;

/** Imports **/

public class SubGrupo {

    /** Atributos **/

    private String id;
    private Integer plazas;
    private TipoClase tipo;
    private Grupo grupo;

    /** Constructoras **/

    public SubGrupo(String id, Integer plazas, TipoClase tipo, Grupo grupo) {
        this.id = id;
        this.plazas = plazas;
        this.tipo = tipo;
        this.grupo = grupo;
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public String getIdCompleta() {
        return this.grupo.getId() + this.id;
    }

    public Integer getPlazas() {
        return this.plazas;
    }

    public TipoClase getTipo() {
        return this.tipo;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public Asignatura getAsignatura() {
        return this.grupo.getAsignatura();
    }

    public Boolean tieneNivel() {
        return this.getAsignatura().tieneNivel();
    }

    public Nivel getNivel() {
        return this.getAsignatura().getNivel();
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return "Subgrupo: " + this.id + "\n-Asignatura: " + this.getAsignatura().getId() + "\n-Grupo: " + this.grupo.getId()
                + "\n-Tipo: " + strTipo(this.tipo);
    }

    static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
    }

}
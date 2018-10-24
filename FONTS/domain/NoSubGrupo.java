package domain;

/** Imports **/

public class NoSubGrupo extends Restriccion {

    /** Atributos **/

    private Grupo grupo;

    /** Constructoras **/

    public NoSubGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /** Métodos públicos **/

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    /** Consultoras **/

    public Grupo getGrupo() {
        return this.grupo;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.NoSubGrupo;
    }
}
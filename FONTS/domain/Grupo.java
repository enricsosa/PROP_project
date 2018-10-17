package domain;

/** Imports **/

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import domain.SubGrupo;

public class Grupo {

    /** Atributos **/

    private String id;
    private Map<String, SubGrupo> subGrupos;

    /** Constructoras **/

    public Grupo(String id) {
        this.id = id;
        this.subGrupos = new HashMap<String, SubGrupo>()
    }

    /** Métodos públicos **/

    public void setId(String id) {
        this.id = id;
    }

    public void addSubGrupo(SubGrupo subGrupo) {
        this.subGrupos.putIfAbsent(subGrupo.getId(), subGrupo);
    }

    public void replaceSubGrupo2(SubGrupo subGrupo) {
        this.subGrupos.replace(subGrupo.getId(), subGrupo);
    }

    public void eliminarSubGrupo(String id) {
        this.subGrupos.remove(id);
    }

    /** Consultoras **/

    public String getId() {
        return this.id;
    }

    public Map<String, SubGrupo> getSubGrupos {
        return this.subGrupos;
    }

    public Map<String, SubGrupo> getSubGruposTeoria {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == Teoria)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<String, SubGrupo> getSubGruposLaboratorio {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == Laboratorio)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public Map<String, SubGrupo> getSubGruposProblemas {
        return this.subGrupos.entrySet().stream()
                .filter(map -> map.getValue().getTipo() == Problemas)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    public SubGrupo getSubGrupo(String id) {
        return this.subGrupos.get(id);
    }

    /** Métodos redefinidos **/

    @Override
    public String toString() {
        return this.id;
    }

}
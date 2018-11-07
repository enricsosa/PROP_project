package domaincontrollers;

/** Imports **/

import domain.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Ocupaciones {

    /** Atributos **/

    private Dia[] dias;

    /** Constructoras **/

    public Ocupaciones() {
        this.dias = new Dia[7];
        Arrays.fill(this.dias, new Dia());
    }

    public Ocupaciones(Ocupaciones oldOcupaciones) {
        this.dias = new Dia[7];
        for (int i = 0; i < 7; ++i) {
            this.dias[i] = new Dia(oldOcupaciones.getDia(i));
        }
    }

    /** Metodos publicos **/

    public void setDias(Dia[] dias) {
        this.dias = dias;
    }

    /** Consultoras **/

    public Dia[] getDias() {
        return this.dias;
    }

    public Dia getDia(int dia) {
        return this.dias[dia - 1];
    }

    /** Métodos redefinidos **/

}

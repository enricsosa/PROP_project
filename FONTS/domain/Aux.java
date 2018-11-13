/**Aux*/

/**Imports*/

package domain;

/**
 * Aux contiene funciones static usadas en varias clases del programa.
 * @author  Daniel Martín
 */
public class Aux {

    /**
     * Convierte un TipoClase a un String con su forma abreviada.
     * @param tipo  TipoClase que se quiere convertir.
     * @return  Devuelve un String con la forma abreviada del TipoClase.
     */
    public static String strTipo(TipoClase tipo) {
        if (tipo == TipoClase.Teoria) return "T";
        else if (tipo == TipoClase.Laboratorio) return "L";
        return "P";
    }

    /**
     * Obtiene el String con el nombre del dia de la Semana que corresponde a un int representando un dia.
     * @param dia   dia de la semana del que se quiere obtener el nombre.
     * @return      String con el nombre de dia.
     */
    public static String getNombreDia(int dia) {
        if (dia == 1) return "Lunes";
        else if (dia == 2) return "Martes";
        else if (dia == 3) return "Miercoles";
        else if (dia == 4) return "Jueves";
        else if (dia == 5) return "Viernes";
        else if (dia == 6) return "Sábado";
        else return "Domingo";
    }

    /**
     * Tranforma un int representando una hora a un String que representa la hora de forma normativa.
     * @param hora  hora que se quiere convertir a String.
     * @return      Devuelve el String representando la hora introducida.
     */
    public static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }

    /**
     * Devuelve un String con una linea de guiones.
     * @return  String con una linea de guiones.
     */
    public static String spacer() {
        return "----------------------------------------------------------------";
    }

    /**
     * Devuelve un String con una linea de puntos.
     * @return  String con una linea de puntos.
     */
    public static String lspacer() {
        return "................................................................";
    }

    /**
     * Devuelve el paramentro de valor más alto introducido.
     * @param x Primer parámetro que se compara.
     * @param y Segundo parámetro que se compara.
     * @return  Paramentro de valor más alto introducido.
     */
    public static int max(int x, int y) {
        if (x >= y) return x;
        return y;
    }

    /**
     * Devuelve el paramentro de valor más bajo introducido.
     * @param x Primer parámetro que se compara.
     * @param y Segundo parámetro que se compara.
     * @return  Paramentro de valor más bajo introducido.
     */
    public static int min(int x, int y) {
        if (x <= y) return x;
        return y;
    }
}

/**VistaMantHorario1a*/

/**Imports*/

package presentation;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
//import java.io.*;
import static java.lang.System.out;

/**
 * VistaMantHorario1a gestiona las interacciones de los menús del programa (1a entrega).
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Scanner
 * @see     InputMismatchException
 * @see     System
 */
public class VistaMantHorario1a {

    /**Instancia de Scanner para las lecturas.*/
    private Scanner in = new Scanner(System.in);

    /**Constructora de VistaMantHorario.*/
    public VistaMantHorario1a() {}

    /**
     * Gestiona la orden que da el usuario.
     * @param nOps  Orden que da el usuario.
     * @return      Valor necesario para interpretar la orden.
     * @throws InputMismatchException
     */
    public int obtenerOp(int nOps) throws InputMismatchException {
        int op = -1;
        while (op == -1) {
            String s = in.next();
            int num = (int)(s.charAt(0) - '0');
            if (num > 0 && num <= nOps) {
                op = num;
            } else {
                out.printf("ERROR: el parámetro \"%s\" no es válido\n", s);
                out.print("Opción: ");
            }
        }
        out.print("\n");
        return op;
    }

    /**
     * Muestra un menú en función del nombre de menú que se le da.
     * @param menu  Nombre del menu que se quiere mostrar.
     */
    public void mostrarMenu(String menu) {
        switch (menu) {
            case "mainMenu":
                out.print("1. Escoger escenario\n");
                out.print("2. Consultar horarios generados\n");
                out.print("3. Salir\n");
                out.print("Opción: ");
                break;

            case "escGenHor":
                out.print("1. Generar un documento .txt\n");
                out.print("2. Solamente mostrar el horario\n");
                out.print("3. (Atrás)\n");
                out.print("Opción: ");
                break;

            default:
                break;
        }
    }

    /**
     * Muestra un menú dinámico en función del nombre de menú que se le da y la información proporcionada.
     * @param array Información para el menú dinámico.
     * @param tipo  Nombre del menu que se quiere mostrar.
     */
    public void mostrarMenuDinamico(ArrayList<String> array, String tipo) {
        int n = array.size();
        switch (tipo) {
            case "escenario":
                out.print("\nEscenarios detectados disponibles:\n");
                break;

            case "horario":
                out.print("\nHorarios generados disponibles:\n");
                break;

            default:
                break;
        }

        for (int i = 0; i < n; ++i) {
            out.printf("%d. %s\n", i+1, array.get(i));
        }
        out.printf("%d. (Atrás)\n", n+1);
        out.print("Opción: ");
    }

    public void mostrarError(String e) {
        out.println("ERROR: "+ e + "\n");
    }
}
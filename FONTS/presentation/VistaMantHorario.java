package presentation;

import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class VistaMantHorario {

    private Scanner in = new Scanner(System.in);

    public VistaMantHorario() {}

    public int obtenerOp(int nOps) throws InputMismatchException {
        int op = -1;
        while (op < 0 || op > nOps) {
            String s = in.next();
            int num = (int)(s.charAt(0) - '0');
            if (num > 0 && num <= nOps) {
                op = in.nextInt();
            } else {
                out.printf("ERROR: el parámetro \"%s\" no es válido\n", s);
                out.printf("Opción: ");
            }
        }
        out.printf("\n");
        return op;
    }

    public void mostrarMenu(String menu) {
        switch (menu) {
            case "mainMenu":
                out.printf("1. Generar horario\n");
                out.printf("2. Consultar horarios generados\n");
                out.printf("3. Salir\n");
                out.printf("Opción: ");
                break;

            default:
                break;
        }
    }

    public void mostrarError(String e) {
        out.println("ERROR: "+ e + "\n");
    }
}
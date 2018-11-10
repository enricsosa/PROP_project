package presentation;

import java.util.*;
import java.io.*;
import static java.lang.System.out;

public class VistaMantHorario {

    private Scanner in = new Scanner(System.in);

    public VistaMantHorario() {}

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

    public void mostrarMenu(String menu) {
        switch (menu) {
            case "mainMenu":
                out.print("1. Escoger escenario\n");
                out.print("2. Consultar horarios generados\n");
                out.print("3. Salir\n");
                out.print("Opción: ");
                break;

            default:
                break;
        }
    }

    public void mostrarMenuEscenarios(ArrayList<String> escenarios) {
        int n = escenarios.size();
        out.printf("\nEscenarios detectados disponibles:\n");
        for (int i = 0; i < n; ++i) {
            out.printf("%d. %s\n", i+1, escenarios.get(i));
        }
        out.printf("%d. (Atrás)\n", n+1);
        out.printf("Opción: ");
    }

    public void mostrarError(String e) {
        out.println("ERROR: "+ e + "\n");
    }
}
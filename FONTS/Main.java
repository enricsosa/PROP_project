/**Main*/

/**Imports*/

import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import presentation.CtrlPresentacion;
import javafx.application.Application;
import presentation.MainMenu;

/**
 * Main del programa.
 * @author  Enric Sosa
 * @see     IOException
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     CtrlPresentacion
 */
public class Main {

    /**
     * Método que ejecuta el programa.
     * @param args                      Argumentos del programa.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        System.out.println("Generador de horarios PROP 2017/18 Q2\n");
        CtrlPresentacion viz;
        viz = CtrlPresentacion.getInstance();
        viz.initCtrlPresentacion();
        Application.launch(MainMenu.class, args);
    }
}

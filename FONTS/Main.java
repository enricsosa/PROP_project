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
 * @see     Application
 * @see     MainMenu
 */
public class Main {

    /**
     * Método que ejecuta el programa.
     * @param args                      Argumentos del programa.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        System.out.println("Generador de horarios PROP 2017/18 Q2\n");
        CtrlPresentacion viz;
        viz = CtrlPresentacion.getInstance();
        viz.initCtrlPresentacion();
        Application.launch(MainMenu.class, args);
    }
}

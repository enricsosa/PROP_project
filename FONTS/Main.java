import java.io.IOException;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import presentation.CtrlPresentacion;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        CtrlPresentacion viz = new CtrlPresentacion();
        viz.initCtrlPresentacion();

    }
}

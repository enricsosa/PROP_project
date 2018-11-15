package tests;

import domain.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

public class junitOcupaciones {

    private Integer nToString = 7;
    private Integer dia = 1;
    private Integer hora = 1;


    @Test
    public void testConstructora() {
        Ocupaciones expectedO = new Ocupaciones();
        Ocupaciones ocupaciones = new Ocupaciones();
        assertEquals(expectedO, ocupaciones);
    }

    @Test
    public void testConstructoraCopia() {
        Asignacion asignacion = new Asignacion(hora, dia, null, new Clase(null, new Sesion(1, null, null)));
        Ocupaciones expectedO = new Ocupaciones();
        expectedO.addAsignacion(asignacion);
        Ocupaciones copia = new Ocupaciones(expectedO);
        assertEquals(expectedO, copia);
    }

    @Test
    public void testAddAsignacion() {
        Asignacion expectedA = new Asignacion(hora, dia, null, new Clase(null, new Sesion(1, null, null)));
        Ocupaciones o = new Ocupaciones();
        o.addAsignacion(expectedA);
        assertEquals(1, o.getHora(1, 1).getAsignaciones().size());
    }

    @Test
    public void testEliminarAsignacion() {
        Asignacion a = new Asignacion(hora, dia, null, new Clase(null, new Sesion(1, null, null)));
        Ocupaciones o = new Ocupaciones();
        o.addAsignacion(a);
        o.eliminarAsignacion(a);
        assertEquals(0, o.getHora(1, 1).getAsignaciones().size());
    }

    @Test
    public void testGetDias() {
        Ocupaciones o = new Ocupaciones();
        Dia[] expectedDs = new Dia[7];
        Arrays.fill(expectedDs, new Dia());
        assertEquals(expectedDs, o.getDias());
    }

    @Test
    public void testGetDia() {
        Ocupaciones o = new Ocupaciones();
        Dia expectedD = new Dia();
        assertEquals(expectedD, o.getDia(1));
    }

    @Test
    public void testGetHora() {
        Ocupaciones o = new Ocupaciones();
        Hora expectedH = new Hora();
        assertEquals(expectedH, o.getHora(1, 1));
    }

    @Test
    public void testToString() {
        Ocupaciones o = new Ocupaciones();
        for (int i = 1; i <= nToString; ++i) {
            o.addAsignacion(new Asignacion(i % 7 + 1, i % 24, null, new Clase(null, new Sesion(1, null, null))));
        }
        String text = "";
        for (int dia = 1; dia <= 7; ++dia) {
            if (o.getDia(dia).getAsignaturas().size() > 0) {
                text += (Aux.spacer() + "\n" + Aux.spacer() + "\n" + Aux.getNombreDia(dia) + "\n");
                for (int hora = 0; hora < 24; ++hora) {
                    if (o.getHora(dia, hora).getAsignaciones().size() > 0) {
                        text += (Aux.spacer() + "\n" + Aux.getHoraCompleta(hora) + "-" + Aux.getHoraCompleta(hora + 1) + "\n" + Aux.lspacer() + "\n");
                        for (Map.Entry<String, Asignacion> entry : o.getHora(dia, hora).getAsignaciones().entrySet()) {
                            text += (entry.getValue().toString() + "\n");
                        }
                    }
                }
            }
        }
        text += Aux.spacer();
        assertEquals(text, o.toString());
    }

}

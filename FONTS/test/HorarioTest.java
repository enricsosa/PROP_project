package test;

import domain.*;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HorarioTest {

    private static Asignacion stubAsignacion(int horaIni, int diaSemana, String idAsignatura, String idSubGrupo, String idGrupo, String idAula, int duracionSesion, TipoClase tipoSesion) {
        Asignatura asignatura = new Asignatura(idAsignatura, null, null);
        Grupo grupo = new Grupo(idGrupo, asignatura);
        SubGrupo subGrupo = new SubGrupo(idSubGrupo, null, tipoSesion, grupo);
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        tipos.add(tipoSesion);
        Aula aula = new Aula(idAula, null, tipos);
        Sesion sesion = new Sesion(duracionSesion, tipoSesion, asignatura);
        Clase clase = new Clase(subGrupo, sesion);
        return new Asignacion(horaIni, diaSemana, aula, clase);
    }

    @Test
    public void testAddAsignacion() {
        Horario horario = new Horario("horarioPrueba");
        Asignacion asignacion1 = stubAsignacion(1, 1, "as1", "sg1", "g1", "al1", 1, TipoClase.Teoria);
        Asignacion asignacion2 = stubAsignacion(2, 2, "as2", "sg2", "g2", "al2", 2, TipoClase.Laboratorio);
        Asignacion asignacion3 = stubAsignacion(3, 3, "as3", "sg3", "g3", "al3", 3, TipoClase.Problemas);
        assertEquals(false, horario.getHora(1,1).tieneAsignacion(asignacion1));
        assertEquals(false, horario.getHora(2,2).tieneAsignacion(asignacion2));
        assertEquals(false, horario.getHora(2,3).tieneAsignacion(asignacion2));
        assertEquals(false, horario.getHora(3,3).tieneAsignacion(asignacion3));
        assertEquals(false, horario.getHora(3,4).tieneAsignacion(asignacion3));
        assertEquals(false, horario.getHora(3,5).tieneAsignacion(asignacion3));
        horario.addAsignacion(asignacion1);
        horario.addAsignacion(asignacion2);
        horario.addAsignacion(asignacion3);
        assertEquals(true, horario.getHora(1,1).tieneAsignacion(asignacion1));
        assertEquals(false, horario.getHora(2,1).tieneAsignacion(asignacion1));
        assertEquals(true, horario.getHora(2,2).tieneAsignacion(asignacion2));
        assertEquals(true, horario.getHora(2,3).tieneAsignacion(asignacion2));
        assertEquals(false, horario.getHora(3,1).tieneAsignacion(asignacion2));
        assertEquals(true, horario.getHora(3,3).tieneAsignacion(asignacion3));
        assertEquals(true, horario.getHora(3,4).tieneAsignacion(asignacion3));
        assertEquals(true, horario.getHora(3,5).tieneAsignacion(asignacion3));
    }

    @Test
    public void testEliminarAsignacion() {
        Horario horario = new Horario("horarioPrueba");
        Asignacion asignacion1 = stubAsignacion(1, 1, "as1", "sg1", "g1", "al1", 1, TipoClase.Teoria);
        Asignacion asignacion2 = stubAsignacion(2, 2, "as2", "sg2", "g2", "al2", 2, TipoClase.Laboratorio);
        Asignacion asignacion3 = stubAsignacion(3, 3, "as3", "sg3", "g3", "al3", 3, TipoClase.Problemas);
        horario.addAsignacion(asignacion1);
        horario.addAsignacion(asignacion2);
        horario.addAsignacion(asignacion3);
        assertEquals(true, horario.getHora(1,1).tieneAsignacion(asignacion1));
        assertEquals(true, horario.getHora(2,2).tieneAsignacion(asignacion2));
        assertEquals(true, horario.getHora(2,3).tieneAsignacion(asignacion2));
        assertEquals(true, horario.getHora(3,3).tieneAsignacion(asignacion3));
        assertEquals(true, horario.getHora(3,4).tieneAsignacion(asignacion3));
        assertEquals(true, horario.getHora(3,5).tieneAsignacion(asignacion3));
        horario.eliminarAsignacion(asignacion1);
        horario.eliminarAsignacion(asignacion2);
        horario.eliminarAsignacion(asignacion3);
        assertEquals(false, horario.getHora(1,1).tieneAsignacion(asignacion1));
        assertEquals(false, horario.getHora(2,2).tieneAsignacion(asignacion2));
        assertEquals(false, horario.getHora(2,3).tieneAsignacion(asignacion2));
        assertEquals(false, horario.getHora(3,3).tieneAsignacion(asignacion3));
        assertEquals(false, horario.getHora(3,4).tieneAsignacion(asignacion3));
        assertEquals(false, horario.getHora(3,5).tieneAsignacion(asignacion3));
    }

    @Test
    public void testSetId() {
        Horario horario = new Horario("h");
        assertEquals("h", horario.getId());
        horario.setId("h1");
        assertEquals("h1", horario.getId());
        horario.setId("h2");
        assertEquals("h2", horario.getId());
        horario.setId("h3");
        assertEquals("h3", horario.getId());
    }

    @Test
    public void testSetDias() {
        Horario horario = new Horario("horario");
        Dia[] dias = new Dia[7];
        for (int i = 0; i < 7; ++i)
            dias[i] = new Dia();
        assertNotEquals(dias, horario.getDias());
        horario.setDias(dias);
        assertEquals(dias, horario.getDias());
    }

    @Test
    public void testGetId() {
        Horario horario1 = new Horario("horarioPrueba1");
        Horario horario3 = new Horario("horarioPrueba3");
        Horario horario2 = new Horario("horarioPrueba2");
        assertEquals("horarioPrueba1", horario1.getId());
        assertEquals("horarioPrueba2", horario2.getId());
        assertEquals("horarioPrueba3", horario3.getId());
    }

    @Test
    public void testGetDias() {
        Horario horario = new Horario("horario");
        assertNotEquals(null, horario.getDias());
        horario.setDias(null);
        assertEquals(null, horario.getDias());
        Dia[] dias = new Dia[3];
        horario.setDias(dias);
        assertEquals(dias, horario.getDias());
    }

    @Test
    public void testGetDia() {
        Horario horario = new Horario("horario");
        Dia dia1 = new Dia();
        Dia dia2 = new Dia();
        Dia dia3 = new Dia();
        Dia dia4 = new Dia();
        Dia dia5 = new Dia();
        Dia dia6 = new Dia();
        Dia dia7 = new Dia();
        Dia[] dias = new Dia[7];
        dias[0] = dia1;
        dias[1] = dia2;
        dias[2] = dia3;
        dias[3] = dia4;
        dias[4] = dia5;
        dias[5] = dia6;
        dias[6] = dia7;
        horario.setDias(dias);
        assertEquals(dia1, horario.getDia(1));
        assertEquals(dia2, horario.getDia(2));
        assertEquals(dia3, horario.getDia(3));
        assertEquals(dia4, horario.getDia(4));
        assertEquals(dia5, horario.getDia(5));
        assertEquals(dia6, horario.getDia(6));
        assertEquals(dia7, horario.getDia(7));
    }

    @Test
    public void testGetHora() {
        Horario horario = new Horario("horario");
        Dia[] dias = new Dia[1];
        Dia dia = new Dia();
        Hora hora0 = new Hora();
        Hora hora1 = new Hora();
        Hora hora2 = new Hora();
        Hora hora3 = new Hora();
        Hora hora4 = new Hora();
        Hora hora5 = new Hora();
        Hora hora6 = new Hora();
        dia.setHora(hora0, 0);
        dia.setHora(hora1, 1);
        dia.setHora(hora2, 2);
        dia.setHora(hora3, 3);
        dia.setHora(hora4, 4);
        dia.setHora(hora5, 5);
        dia.setHora(hora6, 6);
        dias[0] = dia;
        horario.setDias(dias);
        assertEquals(hora0, horario.getHora(1,0));
        assertEquals(hora1, horario.getHora(1,1));
        assertEquals(hora2, horario.getHora(1,2));
        assertEquals(hora3, horario.getHora(1,3));
        assertEquals(hora4, horario.getHora(1,4));
        assertEquals(hora5, horario.getHora(1,5));
        assertEquals(hora6, horario.getHora(1,6));
    }

    @Test
    public void testToString() {
        Horario horario = new Horario("horarioPrueba");
        Asignacion asignacion1 = stubAsignacion(1, 1, "as1", "sg1", "g1", "al1", 1, TipoClase.Teoria);
        Asignacion asignacion2 = stubAsignacion(2, 2, "as2", "sg2", "g2", "al2", 2, TipoClase.Laboratorio);
        Asignacion asignacion3 = stubAsignacion(3, 3, "as3", "sg3", "g3", "al3", 3, TipoClase.Problemas);
        assertEquals("Horario: horarioPrueba\n----------------------------------------------------------------", horario.toString());
        horario.addAsignacion(asignacion1);
        assertEquals("Horario: horarioPrueba\n----------------------------------------------------------------\n----------------------------------------------------------------\nLunes\n----------------------------------------------------------------\n01:00-02:00\n................................................................\nas1 g1sg1 T [al1]\n----------------------------------------------------------------", horario.toString());
        horario.addAsignacion(asignacion2);
        assertEquals("Horario: horarioPrueba\n----------------------------------------------------------------\n----------------------------------------------------------------\nLunes\n----------------------------------------------------------------\n01:00-02:00\n................................................................\nas1 g1sg1 T [al1]\n----------------------------------------------------------------\n----------------------------------------------------------------\nMartes\n----------------------------------------------------------------\n02:00-03:00\n................................................................\nas2 g2sg2 L [al2]\n----------------------------------------------------------------\n03:00-04:00\n................................................................\nas2 g2sg2 L [al2]\n----------------------------------------------------------------", horario.toString());
        horario.addAsignacion(asignacion3);
        assertEquals("Horario: horarioPrueba\n----------------------------------------------------------------\n----------------------------------------------------------------\nLunes\n----------------------------------------------------------------\n01:00-02:00\n................................................................\nas1 g1sg1 T [al1]\n----------------------------------------------------------------\n----------------------------------------------------------------\nMartes\n----------------------------------------------------------------\n02:00-03:00\n................................................................\nas2 g2sg2 L [al2]\n----------------------------------------------------------------\n03:00-04:00\n................................................................\nas2 g2sg2 L [al2]\n----------------------------------------------------------------\n----------------------------------------------------------------\nMiercoles\n----------------------------------------------------------------\n03:00-04:00\n................................................................\nas3 g3sg3 P [al3]\n----------------------------------------------------------------\n04:00-05:00\n................................................................\nas3 g3sg3 P [al3]\n----------------------------------------------------------------\n05:00-06:00\n................................................................\nas3 g3sg3 P [al3]\n----------------------------------------------------------------", horario.toString());
    }

}

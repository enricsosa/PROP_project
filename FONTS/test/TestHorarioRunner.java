package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestHorarioRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(HorarioTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) System.out.println("El test a Horario ha funcionado corretamente.");
        else System.out.println("El test a Horario no ha funcionado corretamente.");
    }
}
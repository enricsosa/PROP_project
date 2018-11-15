package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class testRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(junitOcupaciones.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) System.out.println("El test a Ocupaciones ha funcionado corretamente.");
        else System.out.println("El test a Ocupaciones no ha funcionado corretamente.");
    }
}

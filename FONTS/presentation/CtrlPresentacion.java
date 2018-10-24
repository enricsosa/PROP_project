package presentation;

import domaincontrollers.CtrlDomain;

public class CtrlPresentacion {

    private CtrlDomain controladorDominio = new CtrlDomain();

    public CtrlPresentacion() {}

    public void initCtrlPresentacion() {
        System.out.println("Hello World!");
        this.controladorDominio.initCtrlDomain();
    }


}

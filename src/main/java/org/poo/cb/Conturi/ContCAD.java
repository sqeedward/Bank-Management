package org.poo.cb.Conturi;

import org.poo.cb.ComenziHandleri.InputP;

import java.util.HashMap;

public class ContCAD extends Cont {

    private static HashMap<String, Double> preturi = new HashMap<>();
    public ContCAD(String owner) {
        this.tipValuta = "CAD";
        this.setSuma(0);
        this.adminul = owner;
    }

    public  ContCAD(double suma, String owner) {
        this.tipValuta = "CAD";
        this.setSuma(suma);
        this.adminul = owner;
    }

    public String toString() {
        String info = "{";
        info += InputP.csv("currencyname","CAD");
        info += InputP.csvFaraVirgula("amount",String.format("%.2f", this.suma));
        info += "}";
        return info;
    }

    @Override
    public HashMap<String, Double> getMap() {
        return preturi;
    }

    @Override
    public void transfer(double suma, Cont destinatie) {
        String tip = destinatie.tipValuta;
        double sumaTransfer = suma * destinatie.getMap().get("CAD");

        if (sumaTransfer > this.suma) {
            System.out.println("Insufficient amount in account cad for exchange");
            return;
        }
        else if (sumaTransfer >= this.suma / 2)
            this.suma -= sumaTransfer * 1.01d;
        else
            this.suma -= sumaTransfer;

        destinatie.alimentareCont(suma);
    }

    public void setOwner(String owner) {
        this.adminul = owner;
    }

    public String getAdminul() {
        return this.adminul;
    }

    public boolean maiMultDeJurulSuma(double suma, Cont destinatie) {
        String tip = destinatie.tipValuta;
        double sumaTransfer = suma * destinatie.getMap().get("CAD");

        return sumaTransfer > this.suma / 2;
    }

}

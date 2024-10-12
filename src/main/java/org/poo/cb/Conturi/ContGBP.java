package org.poo.cb.Conturi;

import org.poo.cb.ComenziHandleri.InputP;

import java.util.HashMap;

public class ContGBP extends Cont {
    String owner;
    private static HashMap<String, Double> preturi = new HashMap<>();
    public ContGBP(String owner) {
        this.tipValuta = "GBP";
        this.setSuma(0);
        this.owner = owner;
    }

    public  ContGBP(double suma, String owner) {
        this.tipValuta = "GBP";
        this.setSuma(suma);
        this.owner = owner;
    }

    public String toString() {
        String info = "{";
        info += InputP.csv("currencyname","GBP");
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
        double sumaTransfer = suma * destinatie.getMap().get("GBP");

        if (sumaTransfer > this.suma) {
            System.out.println("Insufficient amount in account gbp for exchange");
            return;
        }
        else if (sumaTransfer >= this.suma / 2)
            this.suma -= sumaTransfer * 1.01d;
        else
            this.suma -= sumaTransfer;

        destinatie.alimentareCont(suma);
    }

    public boolean maiMultDeJurulSuma(double suma, Cont destinatie) {
        String tip = destinatie.tipValuta;
        double sumaTransfer = suma * destinatie.getMap().get("GBP");

        return sumaTransfer > this.suma / 2;
    }

}

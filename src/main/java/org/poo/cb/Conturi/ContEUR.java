package org.poo.cb.Conturi;

import org.poo.cb.ComenziHandleri.InputP;

import java.util.HashMap;

public class ContEUR extends Cont {
    private static HashMap<String, Double> preturi = new HashMap<>();

    public ContEUR(String owner) {
        this.tipValuta = "EUR";
        this.setSuma(0);
        this.adminul = owner;
    }

    public  ContEUR(double suma, String owner) {
        this.tipValuta = "EUR";
        this.setSuma(suma);
        this.adminul = owner;
    }

    public String toString() {
        String info = "{";
        info += InputP.csv("currencyname","EUR");
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
        double sumaTransfer = suma * destinatie.getMap().get("EUR");

        if (sumaTransfer > this.suma) {
            System.out.println("Insufficient amount in account eur for exchange");
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
        double sumaTransfer = suma * destinatie.getMap().get("EUR");

        return sumaTransfer > this.suma / 2;
    }

}

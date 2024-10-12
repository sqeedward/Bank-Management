package org.poo.cb.Conturi;

import org.poo.cb.ComenziHandleri.InputP;

import java.util.HashMap;

public class ContUSD extends Cont {

    private static HashMap<String, Double> preturi = new HashMap<>();
    public ContUSD(String owner) {
        this.tipValuta = "USD";
        this.setSuma(0);
        this.adminul = owner;
    }

    public  ContUSD(double suma, String owner) {
        this.tipValuta = "USD";
        this.setSuma(suma);
        this.adminul = owner;
    }

    public String toString() {
        String info = "{";
        info += InputP.csv("currencyname","USD");
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
        double sumaTransfer = suma * destinatie.getMap().get("USD");

        if (sumaTransfer > this.suma) {
            System.out.println("Insufficient amount in account usd for exchange");
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
        double sumaTransfer = suma * destinatie.getMap().get("USD");

        return sumaTransfer > this.suma / 2;
    }

}

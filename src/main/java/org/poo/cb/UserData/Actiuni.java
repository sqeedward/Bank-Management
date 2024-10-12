package org.poo.cb.UserData;

public class Actiuni {
    private String numeCompanie;
    private double[] valori;

    public Actiuni(String numeCompanie, double[] valori) {
        this.numeCompanie = numeCompanie;
        this.valori = valori;
    }

    public boolean obtineSMA() {
        double sma1 = 0;
        double sma2 = 0;

        for (int i = 5 ; i < 10 ; i++) {
            sma1 += valori[i];
        }
        for (int i = 0 ; i < 10 ; i++)
            sma2 += valori[i];

        sma1 /= 5;
        sma2 /= 10;

        return sma1 > sma2;

    }

    public String getNumeCompanie() {
        return numeCompanie;
    }

    public double getPret() {
        return valori[9];
    }
}

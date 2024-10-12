package org.poo.cb.Conturi;

import java.util.HashMap;

public abstract class Cont implements TipuriTranzactii {
    protected String adminul;
    protected String tipValuta;
    protected double suma;

    public static String[] baniValizi = {"USD", "EUR", "GBP", "JPY", "CAD"};
    public void setSuma(double suma) {
        this.suma = suma;
    }

    @Override
    public void alimentareCont(double suma) {
        this.suma += suma;
    }

    public boolean areBani(double suma) {
        return this.suma >= suma;
    }

    public void retragere(double suma) {
        this.suma -= suma;
    }

    public abstract HashMap<String, Double> getMap();

    public String getAdminul() {
        return this.adminul;
    }

    public abstract boolean maiMultDeJurulSuma(double suma, Cont destinatie);

    public double getSuma() {
        return this.suma;
    }

}


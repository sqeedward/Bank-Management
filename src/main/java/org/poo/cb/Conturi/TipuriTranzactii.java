package org.poo.cb.Conturi;

public interface TipuriTranzactii {

    public void retragere(double suma);
    public void alimentareCont(double suma);
    public void transfer(double suma, Cont destinatie);
}

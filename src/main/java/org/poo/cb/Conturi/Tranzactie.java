package org.poo.cb.Conturi;

import org.poo.cb.UserData.Portofoliu;

public class Tranzactie{
    private static TipuriTranzactii tranzactie;

    public static void executaTransfer(Cont cont1, Cont cont2, double suma) {
        tranzactie = cont1;

        if (cont1 == cont2) {
            tranzactie.alimentareCont(suma);
            return;
        }
        tranzactie.transfer(suma, cont2);

    }

    public static void retragere(Cont cont, double suma) {
        tranzactie = cont;
        tranzactie.retragere(suma);
    }

}

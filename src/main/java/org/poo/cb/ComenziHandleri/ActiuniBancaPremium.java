package org.poo.cb.ComenziHandleri;

import org.poo.cb.Banca;
import org.poo.cb.Conturi.Cont;
import org.poo.cb.Conturi.Tranzactie;
import org.poo.cb.UserData.Utilizator;
import org.poo.cb.UserData.UtilizatorPremium;

public class ActiuniBancaPremium extends ActiuniBanca {

    public ActiuniBancaPremium(ActiuniBanca decorat,Banca banca) {
        super(decorat,banca);
    }

    public void PlatesteTaxa(String email) {
        if (!bancaCurrenta.userExists(email)) {
            System.out.println("User with "+email+" doesn't exist");
            return;
        }
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        UtilizatorPremium utilizatorPremium = new UtilizatorPremium(utilizator);
        Cont contbancar = utilizator.getCont("USD");

        if (contbancar.areBani(100) == false) {
            System.out.println("Insufficient amount in account for buying premium option");
            return;
        }

        bancaCurrenta.addPremiumUser(utilizatorPremium);
        utilizatorPremium.setTaxaPlatita(true);


    }

    public void schimbaBaniCont(String email, String valuta1, String valuta2, String suma) {

            referintaBanca.schimbaBaniCont(email, valuta1, valuta2, suma);

        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        Cont contDestinatar = utilizator.getCont(valuta1);
        Cont contDestinatie = utilizator.getCont(valuta2);

        Double sumaTransf = Double.parseDouble(suma);

        if (bancaCurrenta.estePremium(email)) {
            if (contDestinatar.maiMultDeJurulSuma(sumaTransf, contDestinatie)) {
                sumaTransf *= contDestinatie.getMap().get(valuta1) * 0.01d;
                Tranzactie.executaTransfer(contDestinatar, contDestinatar, sumaTransf);
                //trimitem inapoi suma
            }

        }
    }

    public void cumparaStocks(String email, String actiune, String suma) {
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        Cont cont = utilizator.getCont("USD");

        Double sumaTransf = Double.parseDouble(suma);
        Double baniInCont = cont.getSuma();
        Double pretActiune = bancaCurrenta.getActiuni(actiune).getPret();

        if (bancaCurrenta.estePremium(email)) {
            Tranzactie.executaTransfer(cont, cont, baniInCont * 0.05d);
        }


        //bam
        referintaBanca.cumparaStocks(email, actiune, suma);
        //bam

        if (bancaCurrenta.estePremium(email)) {
            if (cont.areBani(pretActiune) == false) {
                Tranzactie.retragere(cont, baniInCont * 0.05d);
            } else {
                sumaTransf = baniInCont * 0.05d - pretActiune * sumaTransf * 0.05d;
                Tranzactie.retragere(cont, sumaTransf);
            }
        }

    }

}

package org.poo.cb.ComenziHandleri;

import org.poo.cb.Conturi.Tranzactie;
import org.poo.cb.UserData.Actiuni;
import org.poo.cb.Banca;
import org.poo.cb.Conturi.Cont;
import org.poo.cb.Conturi.ContFactory;
import org.poo.cb.UserData.Utilizator;

import java.util.ArrayList;
import java.util.HashMap;

public class ActiuniBanca {

    protected ActiuniBanca referintaBanca;
    protected static Banca bancaCurrenta;

    public ActiuniBanca(ActiuniBanca obDecorat, Banca banca) {
        referintaBanca = obDecorat;
        bancaCurrenta = banca;
    }
    public ActiuniBanca(Banca banca) {
        bancaCurrenta = banca;
    }

    public void CreateUser(String email, String nume, String prenume, String adresa) {
        if (bancaCurrenta.userExists(email)) {
            System.out.println("User with "+email+" already exists");
            return;
        }

        Utilizator utilizator = new Utilizator(email, nume, prenume, adresa);
        bancaCurrenta.addUtilizator(utilizator);
    }

    public void ListUser(String email) {
        if (!bancaCurrenta.userExists(email)) {
            System.out.println("User with "+email+" doesn't exist");
            return;
        }

        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        System.out.println(utilizator.toString());
    }

    public void AddFriend(String email1, String email2) {
        if (bancaCurrenta.userExists(email1) == false) {
            System.out.println("User with "+email1+" doesn't exist");
            return;
        }
        if (bancaCurrenta.userExists(email2) == false) {
            System.out.println("User with "+email2+" doesn't exist");
            return;
        }

        Utilizator utilizator1 = bancaCurrenta.getUtilizator(email1);
        Utilizator utilizator2 = bancaCurrenta.getUtilizator(email2);

        if (utilizator1.estePrieten(email2)) {
            System.out.println("User with "+email2+" is already a friend");
            return;
        }


        utilizator1.addPrieteni(utilizator2);
        utilizator2.addPrieteni(utilizator1);
    }

    public void AdaugaCont(String email, String currency) {
        Cont cont =  ContFactory.creazaCont(currency, email);
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);

        if (utilizator.getPortofoliu().contExists(currency)) {
            System.out.println("Account in currency "+currency+" already exists for user");
            return;
        }

        bancaCurrenta.addCont(cont);
        utilizator.addCont(currency,cont);

    }

    public void AdaugaBani(String email, String currency, String ammount) {
        double suma = Double.parseDouble(ammount);
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        Cont cont = utilizator.getCont(currency);
        Tranzactie.executaTransfer(cont, cont, suma);
    }

    public void arataPortofoliu(String email) {
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        System.out.println(utilizator.getPortofoliu());
    }

    public void schimbaBaniCont(String email, String valuta1, String valuta2, String suma) {
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        Cont contDestinatar = utilizator.getCont(valuta1);
        Cont contDestinatie = utilizator.getCont(valuta2);

        double sumaSchimb = Double.parseDouble(suma);
        Tranzactie.executaTransfer(contDestinatar, contDestinatie, sumaSchimb);
    }

    public void transferaBani(String email, String emailPrieten, String valuta, String suma) {
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        Utilizator prieten = bancaCurrenta.getUtilizator(emailPrieten);


        if (utilizator.estePrieten(emailPrieten) == false) {
            System.out.println("You are not allowed to transfer money to " + emailPrieten);
            return;
        }


        Cont cont = utilizator.getCont(valuta);
        Cont contPrieten = prieten.getCont(valuta);
        double sumaTransfer = Double.parseDouble(suma);

        if (cont.areBani(sumaTransfer) == false) {
            System.out.println("Insufficient amount in account "+ valuta +" for transfer");
            return;
        }


        Tranzactie.executaTransfer(cont, contPrieten, sumaTransfer);
    }

    public void recomandaStocks() {
        if (bancaCurrenta.getRecomandari() != null) {
            afiseazaRecomandari();
            return;
        }

        ArrayList<String> recomandari = new ArrayList<>();
        HashMap<String, Actiuni> actiuni = bancaCurrenta.getToateActiunile();
        for (Actiuni actiune : actiuni.values()) {
            if (actiune.obtineSMA()){
                recomandari.add(actiune.getNumeCompanie());
            }
        }
        bancaCurrenta.setRecomandari(recomandari);
        afiseazaRecomandari();
    }

    public void afiseazaRecomandari() {
        String info = "{";
        info += InputP.csvVector("stockstobuy", bancaCurrenta.getRecomandari().toArray(new String[0]));
        info += "}";
        System.out.println(info);
        return;
    }

    public void cumparaStocks(String email, String actiune, String suma) {
        Utilizator utilizator = bancaCurrenta.getUtilizator(email);
        Cont cont = utilizator.getCont("USD");
        Actiuni actiuneCumparata = bancaCurrenta.getActiuni(actiune);
        double sumaCumparata = Double.parseDouble(suma) * actiuneCumparata.getPret();
        double sumaActiune = Double.parseDouble(suma);

        if (cont.areBani(sumaCumparata) == false) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }

        cont.retragere(sumaCumparata);
        utilizator.getPortofoliu().addActiuni(actiune, sumaActiune);

    }
}

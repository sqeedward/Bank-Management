package org.poo.cb.UserData;

import org.poo.cb.ComenziHandleri.InputP;
import org.poo.cb.Conturi.Cont;

import java.util.HashMap;


public class Utilizator {
    private String email;
    private String nume;
    private String prenume;
    private String adresa;
    private Portofoliu portofoliu;
    private HashMap<String, Utilizator> prieteni;

    public Utilizator(String email, String nume, String prenume, String adresa) {
        this.email = email;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.portofoliu = new Portofoliu();
        this.prieteni = new HashMap<String, Utilizator>();
    }

    public String getEmail() {
        return email;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public Portofoliu getPortofoliu() {
        return portofoliu;
    }

    public void addPrieteni(Utilizator utilizator) {
        prieteni.put(utilizator.getEmail(), utilizator);
    }

    public boolean estePrieten(String email) {
        return prieteni.containsKey(email);
    }

    public void addCont(String valuta, Cont cont) {
        portofoliu.addCont(valuta, cont);
    }

    public Cont getCont(String valuta) {
        return portofoliu.getCont(valuta);
    }

    public String toString() {
        String info = "{";
        info += InputP.csv("email", email);
        info += InputP.csv("firstname", nume);
        info += InputP.csv("lastname", prenume);
        info += InputP.csv("address", adresa);
        info += InputP.csvVector("friends", prieteni.keySet().toArray(new String[0]));
        info += "}";
        return info;
    }

}

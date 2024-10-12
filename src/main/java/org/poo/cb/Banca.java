package org.poo.cb;

import org.poo.cb.Conturi.Cont;
import org.poo.cb.UserData.Actiuni;
import org.poo.cb.UserData.Portofoliu;
import org.poo.cb.UserData.Utilizator;

import java.util.ArrayList;
import java.util.HashMap;

public class Banca {

    private static Banca banca;
    private HashMap<String, Utilizator> totiUtilizatorii;
    private ArrayList<Cont> toateConturile;
    private HashMap<String, Actiuni> toateActiunile;
    private ArrayList<Portofoliu> toatePortofolile;
    private ArrayList<String> recomandari;

    private HashMap<String, Utilizator> premiumUsers;

    private Banca() {
        totiUtilizatorii = new HashMap<String, Utilizator>();
        toateConturile = new ArrayList<Cont>();
        toateActiunile = new HashMap<String, Actiuni>();
        toatePortofolile = new ArrayList<Portofoliu>();
        premiumUsers = new HashMap<String, Utilizator>();
    }

    public static Banca getInstance() {
        if (banca == null) {
            banca = new Banca();
        }
        return banca;
    }

    public void addUtilizator(Utilizator utilizator) {
        totiUtilizatorii.put(utilizator.getEmail(), utilizator);
    }

    public void addCont(Cont cont) {
        toateConturile.add(cont);
    }

    public void addActiuni(String actiuni, Actiuni actiune) {
        toateActiunile.put(actiuni, actiune);
    }

    public void addPortofoliu(Portofoliu portofoliu) {
        toatePortofolile.add(portofoliu);
    }

    public boolean userExists(String email) {
        return totiUtilizatorii.containsKey(email);
    }

    public Utilizator getUtilizator(String email) {
        return totiUtilizatorii.get(email);
    }

    public HashMap<String, Actiuni> getToateActiunile() {
        return toateActiunile;
    }

    public Actiuni getActiuni(String nume) {
        return toateActiunile.get(nume);
    }

    public ArrayList<String> getRecomandari() {
        return recomandari;
    }

    public void setRecomandari(ArrayList<String> recomandari) {
        this.recomandari = recomandari;
    }

    public void resetRecomandari() {
        recomandari = null;
    }

    public void addPremiumUser(Utilizator utilizator) {
        premiumUsers.put(utilizator.getEmail(), utilizator);
    }

    public boolean estePremium(String email) {
        return premiumUsers.containsKey(email);
    }

    public void clean() {
        totiUtilizatorii.clear();
        toateConturile.clear();
        toateActiunile.clear();
        toatePortofolile.clear();
        resetRecomandari();
        premiumUsers.clear();
    }

}

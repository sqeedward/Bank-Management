package org.poo.cb.UserData;

import org.poo.cb.ComenziHandleri.InputP;
import org.poo.cb.Conturi.Cont;

import java.util.ArrayList;
import java.util.HashMap;

public class Portofoliu {
    private HashMap<String, Cont> conturi;
    private HashMap<String, Double> actiuni;

    private ArrayList<String> actiuniCumparate;
    private int nrConturi;

    public Portofoliu() {
        conturi = new HashMap<String, Cont>();
        actiuni = new HashMap<String, Double>();
        actiuniCumparate = new ArrayList<String>();
    }

    public void addCont(String valuta,Cont cont) {
        conturi.put(valuta, cont);
        nrConturi++;
    }

    public boolean contExists(String valuta) {
        return conturi.containsKey(valuta);
    }

    public Cont getCont(String valuta) {
        return conturi.get(valuta);
    }

    public String toString() {
        String info = "{";
        info += InputP.csvVectorFaraVirgule("stocks", getActiuniString()) + ",";
        info += InputP.csvVectorFaraVirgule("accounts", getConturiInfo());
        info += "}";
        return info;
    }

    public String[] getConturiInfo() {
        String[] infoConturi = new String[nrConturi];

        int i = 0;
        for (String tip: Cont.baniValizi) {
            if (conturi.containsKey(tip)) {
                infoConturi[i] = conturi.get(tip).toString();
                i++;
            }

        }
        return infoConturi;
    }

    public void addActiuni(String actiune, Double suma) {
        if (actiuni.containsKey(actiune)) {
            actiuni.put(actiune, actiuni.get(actiune) + suma);
        }
        else {
            actiuni.put(actiune, suma);
            actiuniCumparate.add(actiune);
        }
    }

    public String[] getActiuniString() {
        String[] actiuniString = new String[actiuni.size()];
        String info;
        int actiuneValoare;
        int i = 0;

        for (String actiune: actiuniCumparate) {
            actiuneValoare = actiuni.get(actiune).intValue();
            info = "{";
            info += InputP.csv("stockname", actiune);
            info += InputP.csvFaraVirgula("amount", actiuneValoare);
            info += "}";
            actiuniString[i] = info;
            i++;
        }


        return actiuniString;
    }




}

package org.poo.cb.ComenziHandleri;

import org.poo.cb.UserData.Actiuni;
import org.poo.cb.Banca;
import org.poo.cb.Conturi.Cont;
import org.poo.cb.Conturi.ContFactory;

import java.util.HashMap;

public class ProcesareDate {

    private static Banca banca = Banca.getInstance();

    private static String[] bazaValute;
    private static String[] bazaStocks;

    public static void preparamValori(String path) {
        String raw = InputP.primesteRawInput(path);
        String []linii = raw.split("\n");
        setBazaValute(linii[0].split(","));
        for (int i = 1; i < linii.length; i++) {
                String[] argumente = linii[i].split(",");
                SetValoriBani(argumente);
        }
    }

    public static void preparaStocks(String path) {
        String raw = InputP.primesteRawInput(path);
        String []linii = raw.split("\n");
        setBazaStocks(linii[0].split(","));
        for (int i = 1; i < linii.length; i++) {
            String[] argumente = linii[i].split(",");
            SetValoriStocks(argumente);
        }
    }

    public static void SetValoriBani(String[] argumente) {
        String tip = argumente[0];
        Cont temp = ContFactory.creazaCont(tip,"");
        HashMap<String, Double> preturi = temp.getMap();
        for (int i = 1 ; i < argumente.length; i++)
            preturi.put(bazaValute[i-1], Double.parseDouble(argumente[i]));

    }

    public static void SetValoriStocks(String[] argumente) {
        String tip = argumente[0];
        double[] preturi = new double[argumente.length-1];
        for (int i = 1 ; i < argumente.length; i++) {
            preturi[i-1] = Double.parseDouble(argumente[i]);
        }
        banca.addActiuni(tip, new Actiuni(tip, preturi));
    }

    private static void setBazaValute(String[] argumente) {
        bazaValute = new String[argumente.length-1];
        for (int i = 1; i < argumente.length; i++)
            bazaValute[i-1] = argumente[i];
    }

    private static void setBazaStocks(String[] argumente) {
        bazaStocks = new String[argumente.length-1];
        for (int i = 1; i < argumente.length; i++)
            bazaStocks[i-1] = argumente[i];
    }
}

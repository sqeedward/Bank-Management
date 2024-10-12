package org.poo.cb;

import org.poo.cb.ComenziHandleri.Comenzi;
import org.poo.cb.ComenziHandleri.InterfataComenzi;
import org.poo.cb.ComenziHandleri.ProcesareDate;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
            return;
        }

        Banca myBank = Banca.getInstance();
        InterfataComenzi comenzi = new Comenzi(myBank);
        String exchangeFile = args[0];
        String stockFile = args[1];
        String commands = args[2];

        ProcesareDate.preparamValori(exchangeFile);
        ProcesareDate.preparaStocks(stockFile);
        comenzi.handler(commands);

        myBank.clean();
    }
}
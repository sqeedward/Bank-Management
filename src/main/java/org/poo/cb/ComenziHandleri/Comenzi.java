package org.poo.cb.ComenziHandleri;

import org.poo.cb.Banca;

public class Comenzi implements InterfataComenzi {

    ActiuniBancaPremium executor;

    public Comenzi(Banca banca) {
        executor = new ActiuniBancaPremium(new ActiuniBanca(banca) ,banca);
    }

    public void handler(String path) {
        String rawInput = InputP.primesteRawInput(path);
        String[] comenzi = InputP.separaLinii(rawInput);
        for (String comanda: comenzi) {
            paseazaComanda(InputP.separaCuvinte(comanda));
        }

    }

    public void paseazaComanda(String[] argumente) {
        String operatie = argumente[0] + " " + argumente[1];
        String lipici;

        switch (operatie) {
            case "CREATE USER":
                lipici = InputP.lipireVector(argumente, 5, argumente.length);
                executor.CreateUser(argumente[2], argumente[3], argumente[4], lipici);
                break;
            case "LIST USER":
                executor.ListUser(argumente[2]);
                break;
            case "ADD FRIEND":
                executor.AddFriend(argumente[2], argumente[3]);
                break;
            case "ADD ACCOUNT":
                executor.AdaugaCont(argumente[2], argumente[3]);
                break;
            case "ADD MONEY":
                executor.AdaugaBani(argumente[2], argumente[3], argumente[4]);
                break;
            case "LIST PORTFOLIO":
                executor.arataPortofoliu(argumente[2]);
                break;
            case "EXCHANGE MONEY":
                executor.schimbaBaniCont(argumente[2], argumente[3], argumente[4], argumente[5]);
                break;
            case "TRANSFER MONEY":
                executor.transferaBani(argumente[2], argumente[3], argumente[4], argumente[5]);
                break;
            case "RECOMMEND STOCKS":
                executor.recomandaStocks();
                break;
            case "BUY STOCKS":
                executor.cumparaStocks(argumente[2], argumente[3], argumente[4]);
                break;
            case "BUY PREMIUM":
                executor.PlatesteTaxa(argumente[2]);
                break;
            default:
                System.out.println("Comanda invalida: " + operatie);
        }

    }


}

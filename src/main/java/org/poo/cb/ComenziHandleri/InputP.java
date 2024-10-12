package org.poo.cb.ComenziHandleri;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputP {

    private static String pathRelativ = "src/main/resources/";
    public InputP() {
    }
    public static String primesteRawInput(String path) {
        String line;
        StringBuilder rawInput = new StringBuilder();

        String realPath = pathRelativ + path;
        while (true) {
            try (BufferedReader br = new BufferedReader(new FileReader(realPath))) {
                while ((line = br.readLine()) != null) {
                    rawInput.append(line).append("\n");
                }
            } catch (FileNotFoundException e) {
                break;
            } catch (IOException e) {
                break;
            }
            break;
        }
        return rawInput.toString();
    }

    public static String[] separaLinii(String input) {
        return input.split("\n");
    }

    public static String[] separaCuvinte(String input) {
        return input.split(" ");
    }

    public static String csv(String tip, String valoare) {
        return "\""+tip+"\":\""+valoare+"\",";
    }

    public static String csvFaraVirgula(String tip, String valoare) {
        return "\""+tip+"\":\""+valoare+"\"";
    }
    public static String csvFaraVirgula(String tip, int valoare) {
        return "\""+tip+"\":"+valoare;
    }

    public static String csvVector(String tip, String[] valori) {

        String info = "\""+tip+"\":[";
        if (valori != null)
            for (int i = 0; i < valori.length; i++) {
                if (i == valori.length - 1)
                    info += "\""+valori[i]+"\"";
                else
                    info += "\""+valori[i]+"\",";
            }
        info +="]";
        return info;
    }

    public static String csvVectorFaraVirgule(String tip, String[] valori) {
        String info = "\""+tip+"\":[";
        if (valori != null)
            for (int i = 0; i < valori.length; i++) {
                if (i == valori.length - 1)
                    info += valori[i];
                else
                    info += valori[i]+",";
            }
        info +="]";
        return info;
    }

    public static String csvRezultat(String[] tipuri, String[] valori) {
       String rezultat = "{";
         for (int i = 0; i < tipuri.length; i++) {
             rezultat += csv(tipuri[i], valori[i]);
         }

         return rezultat;
    }

    public static String lipireVector(String[] vector, int start, int sfarsit) {
        String info = "";
        for (int i = start; i < sfarsit; i++) {
            if (i == sfarsit - 1)
                info += vector[i];
            else
                info += vector[i] + " ";
        }
        return info;
    }

}

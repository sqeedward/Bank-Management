package org.poo.cb.Conturi;

public class ContFactory {
    public static Cont creazaCont(String tipValuta, String adminul) {
        if (tipValuta == null) {
            return null;
        }
        return switch (tipValuta) {
            case "USD" -> new ContUSD(adminul);
            case "EUR" -> new ContEUR(adminul);
            case "GBP" -> new ContGBP(adminul);
            case "JPY" -> new ContJPY(adminul);
            case "CAD" -> new ContCAD(adminul);
            default -> null;
        };
    }
    public static Cont creazaCont(String tipValuta, double suma, String adminul) {
        if (tipValuta == null) {
            return null;
        }
        return switch (tipValuta) {
            case "USD" -> new ContUSD(suma, adminul);
            case "EUR" -> new ContEUR(suma, adminul);
            case "GBP" -> new ContGBP(suma, adminul);
            case "JPY" -> new ContJPY(suma, adminul);
            case "CAD" -> new ContCAD(suma, adminul);
            default -> null;
        };
    }
}

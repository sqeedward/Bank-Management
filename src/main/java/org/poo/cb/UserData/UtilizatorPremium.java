package org.poo.cb.UserData;

public class UtilizatorPremium extends Utilizator {
    private int numarPrieteni;
    private boolean taxaPlatita = false;

    public UtilizatorPremium(String email, String nume, String prenume, String adresa) {
        super(email, nume, prenume, adresa);
        this.numarPrieteni = 0;
    }

    public UtilizatorPremium(Utilizator user) {
        super(user.getEmail(), user.getNume(), user.getPrenume(), user.getAdresa());
        this.numarPrieteni = 0;
    }

    public void setTaxaPlatita(boolean taxaPlatita) {
        this.taxaPlatita = taxaPlatita;
    }

    public boolean getTaxaPlatita() {
        return taxaPlatita;
    }

}

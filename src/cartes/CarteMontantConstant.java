package cartes;

import cases.CategorieCarte;

public class CarteMontantConstant extends CartePayerRecevoir {

    private int montant;

    public CarteMontantConstant(String enonce, int montant, CategorieCarte categorie) {
        super(enonce, categorie);
        setMontant(montant);
    }

    public int getMontant() {
        return this.montant;
    }

    private void setMontant(int montant) {
        if (montant < 0)
            throw new IllegalArgumentException("montant doit etre un entier positif.");
        else
            this.montant = montant;
    }

}
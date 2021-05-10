package cartes;

import cases.CategorieCarte;

public class CarteReparations extends CartePayerRecevoir {

    private int montantMaison;

    public CarteReparations(String enonce, int montantMaison, CategorieCarte categorie) {
        super(enonce, categorie);
        setMontantMaison(montantMaison);
    }

    public int getMontantMaison() {
        return montantMaison;
    }

    public void setMontantMaison(int montantMaison) {
        if (montantMaison < 0)
            throw new IllegalArgumentException("montantMaison doit etre un entier positif.");
        else
            this.montantMaison = montantMaison;
    }

}
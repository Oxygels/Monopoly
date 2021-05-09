package cartes;

public class CarteReparations extends CartePayerRecevoir{

    private int montantMaison;

    public CarteReparations(String enonce) {
        super(enonce);
    }

    public CarteReparations(String enonce, int montantMaison) {
        super(enonce);
        setMontantMaison(montantMaison);

    }

    public int getMontantMaison() {
        return montantMaison;
    }

    public void setMontantMaison(int montantMaison) {
        if(montantMaison < 0)
            throw new IllegalArgumentException("le montant de la maison doit etre un entier positif");
        else
            this.montantMaison = montantMaison;
    }
}

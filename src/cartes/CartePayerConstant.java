package cartes;

public class CartePayerConstant extends CartePayer {
    private int montant;

    /**
     * C'est une classe qui herite de la classe CartePayer
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @param montant represente le montant que le joueur doit payer , c'est un entier natuel non nul.
     * @see CartePayer pour comprendre comment elle marche qui est la classe mere de CartePayerConstant.
     */
    public CartePayerConstant(String enonce, int montant) {
        super(enonce);
    }

    @Override
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        if (montant < 0)
            throw new IllegalArgumentException("Montant négatif");
        this.montant = montant;
    }
}

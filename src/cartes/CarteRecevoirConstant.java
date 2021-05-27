package cartes;

public class CarteRecevoirConstant extends CarteRecevoir {
    private int montant;

    /**
     * C'est une classe qui herite de la classe CarteRecevoir
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @param montant represente le montant que le joueur doit recevoir , c'est un entier natuel non nul.
     * @see CarteRecevoir pour comprendre comment elle marche qui est la classe mere de CarteRecevoirConstant.
     */

    public CarteRecevoirConstant(String enonce, int montant) {
        super(enonce);
        setMontant(montant);
    }

    @Override
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        if (montant < 0)
            throw new IllegalArgumentException("Montant");
        this.montant = montant;
    }
}

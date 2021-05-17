package cartes;

public class CarteRecevoirConstant extends CarteRecevoir {
    private int montant;

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

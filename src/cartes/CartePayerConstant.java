package cartes;

public class CartePayerConstant extends CartePayer {
    private int montant;

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

package cases;

public class CaseRecevoirConstant extends CaseRecevoir {
    private int montant;

    public CaseRecevoirConstant(int id, String nom) {
        super(id, nom);
    }

    @Override
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        if (montant < 0)
            throw new IllegalArgumentException("Montant negatif");
        this.montant = montant;
    }
}

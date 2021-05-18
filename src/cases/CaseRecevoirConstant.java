package cases;

public class CaseRecevoirConstant extends CaseRecevoir {

    private int montant;

    public CaseRecevoirConstant(int id, String nom, int montant) {
        super(id, nom);
        setMontant(montant);
    }

    @Override
    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        if (montant < 0)
            throw new IllegalArgumentException("Le montant ne peut etre negatif.");

        this.montant = montant;
    }

}
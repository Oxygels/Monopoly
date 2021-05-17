package cases;

public class CasePayerConstant extends CasePayer {

    private int montant;

    public CasePayerConstant(int id, String nom, int montant) {
        super(id, nom);
        setMontant(montant);
    }

    @Override
    public int getMontant() {
        return montant;
    }

    private void setMontant(int montant) {
        if (montant < 0)
            throw new IllegalArgumentException("Le montant ne peut etre negatif.");

        this.montant = montant;
    }

}
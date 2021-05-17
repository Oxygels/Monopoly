package cases;

public abstract class CasePayerRecevoir extends Case {

    public CasePayerRecevoir(int id, String nom) {
        super(id, nom);
    }

    public abstract int getMontant();

}
package cases;

import joueur.Joueur;

public abstract class CasePayer extends CasePayerRecevoir {
    public CasePayer(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) {
        //TODO: Simplement payer
        // getMontant() est disponible
    }
}

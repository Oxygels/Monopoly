package cases;

import exception.FailliteException;
import joueur.Joueur;

public abstract class CasePayer extends CasePayerRecevoir {

    public CasePayer(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) throws FailliteException {
        joueur.payerBanque(getMontant(), true);
    }

}
package cases;

import exception.ArgentException;
import joueur.Joueur;

public abstract class CasePayer extends CasePayerRecevoir {

    public CasePayer(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) throws ArgentException {
        joueur.payerBanque(getMontant(), true);
    }

}
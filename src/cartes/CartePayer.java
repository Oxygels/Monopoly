package cartes;

import exception.ArgentException;
import joueur.Joueur;

public abstract class CartePayer extends CartePayerRecevoir {

    public CartePayer(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) throws ArgentException {

        int montant = getMontant();
        J.payerBanque(montant, true);
    }
}

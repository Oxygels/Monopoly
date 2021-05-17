package cartes;

import joueur.Joueur;

public abstract class CartePayer extends CartePayerRecevoir {

    public CartePayer(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) {
        // TODO: Tu peux récupérer le montant et faire payer le joueur j
    }
}

package cartes;

import joueur.Joueur;

public abstract class CarteRecevoir extends CartePayerRecevoir {

    public CarteRecevoir(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) {
        // TODO: Tu peux récupérer le montant et faire recevoir le joueur j
    }
}

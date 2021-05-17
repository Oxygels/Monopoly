package cartes;

import joueur.Joueur;

public class CarteChoix extends CartePayerRecevoir {

    public CarteChoix(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) {
        // pour le moment rien
    }

    @Override
    public int getMontant() {
        return 0;
    }

}
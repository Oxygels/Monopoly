package cartes;

import exception.ArgentException;
import joueur.Joueur;

public abstract class CartePayer extends CartePayerRecevoir {

    /**
     * Classe abstraite qui herite de la carte PayerRecevoir.
     * Cette Classe permet de faire payer un joueur en lui faisant retirer de l'argent.
     * Le joueur peut soit payer un autre joueur soit payer la banque.
     * @param enonce Chaine de caractere qui represente le texte au dos de la carte.
     * @see CartePayerRecevoir pour comprendre d'ou provient la classe actuelle.
     * @see Joueur pour comprendre le comportement d'un joueur dans la partie.
     */

    public CartePayer(String enonce) {
        super(enonce);
    }

    @Override
    public void actionCarte(Joueur J) throws ArgentException {

        int montant = getMontant();
        J.payerBanque(montant, true);
    }
}

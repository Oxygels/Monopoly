package cartes;

import cases.Case;
import exception.MonopolyException;
import joueur.Joueur;

public abstract class CarteDeplacement extends Carte {

    private boolean caseDepart = false;
    private boolean prendEnCompteCaseDepart;

    /**
     * Classe abstraite qui represente les types de cartes monopoly qui font deplacer le joueur a des cases
     * specifques dans la partie.
     * @see Case pour comprendre quel est la classe Case et elle represente quoi dans l'implementation monopoly.
     * @param enonce une chaine de caractere definie dans la classe mere.
     * @param prendEnCompteCaseDepart un booleen qui represente si on prends on compte que le joueur passe
     *                                par la case depart ou si le joueur est dans la case depart.
     */
    public CarteDeplacement(String enonce, boolean prendEnCompteCaseDepart) {
        super(enonce);
        setPrendEnCompteCaseDepart(prendEnCompteCaseDepart);
    }

    public boolean isPrendEnCompteCaseDepart() {
        return prendEnCompteCaseDepart;
    }

    private void setPrendEnCompteCaseDepart(boolean prendEnCompteCaseDepart) {
        this.prendEnCompteCaseDepart = prendEnCompteCaseDepart;
    }

    /**
     * Pour savoir si le joueur est dans la case depart ou pas.
     * @return un booleen qui confirme si le joueur est dans la case depart ou non.
     */
    public boolean isCaseDepart() {
        return caseDepart;
    }

    /**
     * @param caseDepart Pour mettre en compte si le joueur est dans la case depart ou pas.
     */
    public void setCaseDepart(boolean caseDepart) {
        this.caseDepart = caseDepart;
    }

    /**
     * Une methode abstraite qui permet de savoir a qu'elle destination la carte renvoie le joueur.
     * cette methode abstraite sera implementé dans les classes qui vont deriver de la classe CarteDeplacement.
     * @return une case monopoly, sois la destination du joueur apres que la carte a eu effet sur lui.
     * @see Case pour comprendre comment cette derniere est implementé.
     */
    public abstract Case getDestination();


    /**
     * fait deplacer le joueur dans une destination specifique representé par une case monopoly.
     * fait gagner le joueur $200 si il passe par la case depart.
     * @param J qui represente un joueur de la classe Joueur car la methode modifieras le comportement du joueur.
     * @throws MonopolyException
     */
    @Override
    public void actionCarte(Joueur J) throws MonopolyException {
        J.seDeplacer(getDestination().getId());
        if (caseDepart && prendEnCompteCaseDepart && J.getPositionCase().getNom() != "Case Départ") {
            J.gagnerArgent(200);
        }
    }
}
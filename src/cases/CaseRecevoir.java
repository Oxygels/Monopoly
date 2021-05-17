package cases;

import joueur.Joueur;

public abstract class CaseRecevoir extends CasePayerRecevoir {

    public CaseRecevoir(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) {
        joueur.gagnerArgent(getMontant());
    }

}
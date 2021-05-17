package cases;

import joueur.Joueur;

public class CaseSimpleVisite extends Case {

    public CaseSimpleVisite(int id, String nom) {
        super(id, nom);
    }

    @Override
    public void action(Joueur joueur) {
        // Rien pour le moment, a voir dans l'interface graphique
    }

}
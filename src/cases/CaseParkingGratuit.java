package cases;

import joueur.Joueur;
import plateau.Plateau;

public class CaseParkingGratuit extends CaseRecevoir {
    public CaseParkingGratuit(int id, String nom) {
        super(id, nom);
    }

    @Override
    public int getMontant() {
        return Plateau.getPlateau().getValeurParcGratuit();
    }

    @Override
    public void action(Joueur joueur) {
        // TODO: a faire
    }

}
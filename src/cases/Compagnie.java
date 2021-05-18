package cases;

import plateau.Plateau;

public class Compagnie extends Propriete {

    public Compagnie(int id, String nom, int prix) {
        super(id, nom, prix);
    }

    @Override
    public int calculerLoyer() {
        Plateau plateau = Plateau.getPlateau();
        // Si on calcule le loyer alors forcément il y a un propriétaire
        int nbCompagniesPossedees = (int) getProprietaire()
                .getProprietesPossedees()
                .stream()
                .filter(p -> p instanceof Compagnie)
                .count();

        switch (nbCompagniesPossedees) {
            case 1:
                return plateau.getDernierLancerDes() * 4;
            case 2:
                return plateau.getDernierLancerDes() * 10;
            default:
                return 0;
        }
    }

}
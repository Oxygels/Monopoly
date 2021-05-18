package cases;

import plateau.Plateau;

public class Compagnie extends Propriete {

    public Compagnie(int id, String nom, int prix) {
        super(id, nom, prix);
    }

    @Override
    public int calculerLoyer() {
        int loyer = 0;

        Plateau plateau = Plateau.getPlateau();

        int nbCompagniesPossedees = (int) plateau
                .getJoueurCourant()
                .getProprietesPossedees()
                .stream()
                .filter(p -> p.getNom().contains("Compagnie"))
                .count();

        switch (nbCompagniesPossedees) {
            case 1:
                loyer = plateau.getDernierLancerDes() * 4;
                break;

            case 2:
                loyer = plateau.getDernierLancerDes() * 10;
                break;

            default:
                break;
        }

        return loyer;
    }

}
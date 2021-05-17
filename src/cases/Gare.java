package cases;

import plateau.Plateau;

public class Gare extends Propriete {

    public Gare(int id, String nom, int prix) {
        super(id, nom, prix);
    }

    @Override
    public int calculerLoyer() {
        Plateau plateau = Plateau.getPlateau();
        int nbGaresPossedees = (int) plateau
                .getJoueurCourant()
                .getProprietesPossedees()
                .stream()
                .filter(p -> p.getNom().equals("GARE"))
                .count();

        return 50 * nbGaresPossedees;
    }

}
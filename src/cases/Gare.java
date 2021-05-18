package cases;

import plateau.Plateau;

public class Gare extends Propriete {

    public Gare(int id, String nom, int prix) {
        super(id, nom, prix);
    }

    @Override
    public int calculerLoyer() {
        Plateau plateau = Plateau.getPlateau();
        // Si on calcule le loyer alors forcément il y a un propriétaire
        int nbGaresPossedees = (int) getProprietaire()
                .getProprietesPossedees()
                .stream()
                .filter(p -> p instanceof Gare)
                .count();

        return 50 * nbGaresPossedees;
    }

}
package cases;

public class Gare extends Propriete {

    public Gare(int id, String nom, int prix) {
        super(id, nom, prix);
    }

    @Override
    public int calculerLoyer() {
        // TODO, selon le discord YLBPO c'est bien la règle des 50
        // (50 * nombre de gare possédées)
        return 0;
    }
}
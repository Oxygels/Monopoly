package cases;

public class Compagnie extends Propriete {

    public Compagnie(int id, String nom, int prix) {
        super(id, nom, prix);
    }

    @Override
    public int calculerLoyer() {
        // TODO: Utiliser le dernier lancer de dé et le coefficient (selon si le joueur a 1 ou 2 compagnies)
        // Pas besoin de paramètre coefficient il est constant dans les règles du jeu
        // 4 si le joueur a une compagnie
        // 10 si il en a 2
        return 0;
    }
}
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario2 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario2() {
        System.out.println("[DEBUT] Scénario 2 : Tests pour la construction d'un joueur");
        Plateau plateau = Plateau.getPlateau();
        Joueur joueur = new Joueur("Quentin");
        plateau.ajouterJoueur(joueur);
        assertEquals(1500, joueur.getMontantBillet());
        assertEquals(0, joueur.getProprietesPossedees().size());
        assertEquals("Case Départ", plateau.getCases().get(joueur.getPosition()).getNom());
        assertEquals(1, plateau.getNbJoueurs());
        Joueur joueur2 = new Joueur("Yacine");
        plateau.ajouterJoueur(joueur2);
        assertEquals(2, plateau.getNbJoueurs());
        System.out.println("[REUSSITE] Scénario 2 : Tests pour la construction d'un joueur\n");
    }
}

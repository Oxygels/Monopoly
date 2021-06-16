import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario3 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario3() throws MonopolyException {
        Plateau plateau = Plateau.getPlateau();
        Joueur j = new Joueur("Quentin");
        j.avancer(5);
        assertEquals("Gare Montparnasse", j.getPositionCase().getNom());
        assertEquals(1500, j.getMontantBillet());
        assertEquals(0, j.getProprietesPossedees().size());
        j.avancer(5);
        assertEquals("Simple visite", j.getPositionCase().getNom());
        assertEquals(1500, j.getMontantBillet());
        assertEquals(0, j.getProprietesPossedees().size());

        j.seDeplacer(plateau.getCase("Rue de la Paix").getId());
        j.avancer(2);
        assertEquals("Boulevard de Belleville", j.getPositionCase().getNom());
        assertEquals(1700, j.getMontantBillet());

        boolean test = false;
        try {
            j.avancer(1); // Le stack est vide donc une exception doit être lancée
        } catch (Exception ignore) {
            test = true;
        }
        assertTrue(test);
    }
}

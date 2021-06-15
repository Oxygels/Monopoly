import cases.Case;
import cases.Propriete;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario1 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario1() {
        System.out.println("[DEBUT] Scénario 1 : Tests pour la construction du plateau");
        Plateau plateau = Plateau.getPlateau();

        assertEquals(0, plateau.getNbJoueurs());

        ArrayList<Case> cases = plateau.getCases();
        assertEquals("Case Départ", cases.get(0).getNom());
        assertEquals("Gare Montparnasse", cases.get(5).getNom());
        assertEquals("Simple visite", cases.get(10).getNom());
        assertEquals("Compagnie d'électricité", cases.get(12).getNom());
        assertEquals("Parking Gratuit", cases.get(20).getNom());
        assertEquals("Allez en prison", cases.get(30).getNom());
        assertEquals("Chance", cases.get(36).getNom());
        assertEquals("Rue de la Paix", cases.get(39).getNom());
        assertEquals("Prison", cases.get(40).getNom());

        assertEquals(0, plateau.getValeurParcGratuit());
        // Vérification que toutes les cases n'ont pas de propriété

        assertTrue(cases.stream().allMatch(c -> !(c instanceof Propriete) || ((Propriete) c).getProprietaire() == null));

        System.out.println("[REUSSITE] Scénario 1 : Tests pour la construction du plateau\n");
    }
}

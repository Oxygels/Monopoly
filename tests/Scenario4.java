import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.*;

public class Scenario4 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario4() throws MonopolyException {
        Plateau plateau = Plateau.getPlateau();
        Joueur joueur = new Joueur("Quentin");
        plateau.ajouterJoueur(joueur);
        joueur.avancer(6);

        assertEquals("Rue de Vaugirard", joueur.getPositionCase().getNom());
        assertNull(((Propriete) (joueur.getPositionCase())).getProprietaire());
        joueur.acheterPropriete((Propriete) joueur.getPositionCase());
        assertEquals(joueur, ((Propriete) joueur.getPositionCase()).getProprietaire());
        assertEquals(1400, joueur.getMontantBillet());

        Joueur joueur2 = new Joueur("Yacine");
        plateau.ajouterJoueur(joueur2);
        Propriete vaugirard = (Propriete) (plateau.getCase("Rue de Vaugirard"));
        assertNotNull(vaugirard.getProprietaire());

        boolean exception = false;
        try {
            joueur2.acheterPropriete(vaugirard);
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);
    }
}

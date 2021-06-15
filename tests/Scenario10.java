import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario10 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario10() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 10 : Tests pour le déplacement d’un joueur sur la case « Aller en Prison »");
        Plateau plateau = Plateau.getPlateau();

        Joueur jules = new Joueur("Jules");
        plateau.ajouterJoueur(jules);

        jules.seDeplacer(plateau.getCase("Gare du Nord").getId());
        jules.avancer(5);
        assertEquals("Prison", jules.getPositionCase().getNom());
        jules.payerPrison();
        assertEquals(1450, jules.getMontantBillet());
        assertEquals("Simple visite", jules.getPositionCase().getNom());

        System.out.println("[REUSSITE] Scénario 10 : Tests pour le déplacement d’un joueur sur la case « Aller en Prison »\n");
    }
}

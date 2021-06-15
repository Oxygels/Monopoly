import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario6 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario6() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 6 : Tests pour le déplacement d’un joueur sur une gare achetée");
        Plateau plateau = Plateau.getPlateau();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        quentin.acheterPropriete((Propriete) plateau.getCase("Gare Montparnasse"));
        jules.seDeplacer(plateau.getCase("Gare Montparnasse").getId());
        assertEquals(1450, jules.getMontantBillet());
        assertEquals(1350, quentin.getMontantBillet());

        quentin.acheterPropriete((Propriete) plateau.getCase("Gare de Lyon"));
        jules.seDeplacer(plateau.getCase("Gare de Lyon").getId());

        assertEquals(1350, jules.getMontantBillet());
        assertEquals(1250, quentin.getMontantBillet()); // 1350 - 200 + 2 * 50

        jules.seDeplacer(plateau.getCase("Gare du Nord").getId());
        assertEquals(1350, jules.getMontantBillet());
        assertEquals(1250, quentin.getMontantBillet());

        System.out.println("[REUSSITE] Scénario 6 : Tests pour le déplacement d’un joueur sur une gare achetée\n");

    }
}

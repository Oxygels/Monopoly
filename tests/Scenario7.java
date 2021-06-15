import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario7 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario7() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 7 : Tests pour le déplacement d’un joueur sur une compagnie achetée");

        Plateau plateau = Plateau.getPlateau();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        quentin.acheterPropriete((Propriete) plateau.getCase("Compagnie d'électricité"));
        assertEquals(1350, quentin.getMontantBillet());
        jules.avancer(12);
        assertEquals("Compagnie d'électricité", jules.getPositionCase().getNom());
        assertEquals(1452, jules.getMontantBillet());
        assertEquals(1398, quentin.getMontantBillet());

        quentin.acheterPropriete((Propriete) plateau.getCase("Compagnie des eaux"));
        assertEquals(1248, quentin.getMontantBillet());

        jules.seDeplacer(plateau.getCase("Boulevard Malesherbes").getId());
        assertEquals(1452, jules.getMontantBillet());

        jules.avancer(4);
        assertEquals("Compagnie des eaux", jules.getPositionCase().getNom());
        assertEquals(1412, jules.getMontantBillet());

        assertEquals(1288, quentin.getMontantBillet());


        System.out.println("[REUSSITE] Scénario 7 : Tests pour le déplacement d’un joueur sur une compagnie achetée\n");
    }
}

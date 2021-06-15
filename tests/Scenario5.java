import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario5 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario5() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 5 : Tests pour le déplacement d’un joueur sur " +
                "un terrain constructible acheté");

        Plateau plateau = Plateau.getPlateau();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        quentin.acheterPropriete((Propriete) plateau.getCase("Rue de Vaugirard"));
        assertEquals(1400, quentin.getMontantBillet());
        jules.avancer(6);
        assertEquals("Rue de Vaugirard", jules.getPositionCase().getNom());
        assertEquals(1494, jules.getMontantBillet());
        assertEquals(1406, quentin.getMontantBillet());

        quentin.acheterPropriete((Propriete) plateau.getCase("Rue de Courcelles"));
        quentin.acheterPropriete((Propriete) plateau.getCase("Avenue de la République"));
        assertEquals(1186, quentin.getMontantBillet());
        jules.avancer(3);
        assertEquals("Avenue de la République", jules.getPositionCase().getNom());
        assertEquals(1478, jules.getMontantBillet()); // Le loyer de 2 * 8 (car toutes les couleurs sont possédées)
        assertEquals(1202, quentin.getMontantBillet()); // Et reçu ici

        jules.setMontantBillet(5);

        boolean exception = false;
        try {
            jules.seDeplacer(plateau.getCase("Rue de Courcelles").getId());
        } catch (Exception e) {
            exception = true;
        }
        assertTrue(exception);

        System.out.println("[REUSSITE] Scénario 5 : Tests pour le déplacement d’un joueur sur " +
                "un terrain constructible acheté\n");
    }
}

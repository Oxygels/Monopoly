import cases.Propriete;
import cases.TerrainConstructible;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario8 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario8() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 8 : Tests pour l’achat d’une maison");
        Plateau plateau = Plateau.getPlateau();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        TerrainConstructible vaugirard = (TerrainConstructible) plateau.getCase("Rue de Vaugirard");
        quentin.acheterPropriete(vaugirard);
        boolean exception = false;
        try {
            quentin.acheterMaison(vaugirard);
        } catch (MonopolyException exception1) {
            exception = true;
        }
        assertTrue(exception);
        quentin.acheterPropriete((Propriete) plateau.getCase("Rue de Courcelles"));
        quentin.acheterPropriete((Propriete) plateau.getCase("Avenue de la République"));
        assertEquals(1180, quentin.getMontantBillet());
        quentin.acheterMaison(vaugirard);
        assertEquals(1130, quentin.getMontantBillet());

        jules.seDeplacer(plateau.getCase("Rue de Vaugirard").getId());
        assertEquals(1470, jules.getMontantBillet());


        System.out.println("[REUSSITE] Scénario 8 : Tests pour l’achat d’une maison\n");
    }
}

import cases.TerrainConstructible;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario9 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario9() throws MonopolyException {
        Plateau plateau = Plateau.getPlateau();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        TerrainConstructible vaugirard = (TerrainConstructible) plateau.getCase("Rue de Vaugirard");
        TerrainConstructible courcelles = (TerrainConstructible) plateau.getCase("Rue de Courcelles");
        TerrainConstructible republique = (TerrainConstructible) plateau.getCase("Avenue de la République");
        quentin.acheterPropriete(vaugirard);
        quentin.acheterPropriete(courcelles);
        quentin.acheterPropriete(republique);
        for (int i = 0; i < 3; i++) {
            quentin.acheterMaison(vaugirard);
            quentin.acheterMaison(courcelles);
            quentin.acheterMaison(republique);
        }
        quentin.acheterMaison(courcelles);
        quentin.acheterMaison(republique);
        boolean exception = false;
        try {
            quentin.acheterMaison(republique);
        } catch (MonopolyException exception1) {
            exception = true;
        }
        assertTrue(exception);

        quentin.acheterMaison(vaugirard);
        quentin.acheterHotel(republique);
        assertEquals(530, quentin.getMontantBillet());
        jules.seDeplacer(plateau.getCase("Avenue de la République").getId());
        assertEquals(900, jules.getMontantBillet());
        assertEquals(1130, quentin.getMontantBillet());
    }
}

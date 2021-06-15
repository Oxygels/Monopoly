import cartes.Carte;
import exception.MonopolyException;
import joueur.Joueur;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import plateau.Plateau;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario11 {
    @BeforeAll
    public static void resetInstance() {
        Plateau plateau = Plateau.getPlateau();
        plateau.clear();
        plateau.initTerrains();
        plateau.initCartes();
    }

    @Test
    public void launchScenario11() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 11 : Tests pour les cartes « Chance » ou « Caisse de communauté »");
        Plateau plateau = Plateau.getPlateau();

        Joueur jules = new Joueur("Jules");
        plateau.ajouterJoueur(jules);

        Carte medecin = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("médecin")).findAny().get();
        plateau.getCartesCommunaute().remove(medecin);
        plateau.getCartesCommunaute().push(medecin);

        jules.avancer(2);
        assertEquals(1450, jules.getMontantBillet());

        Carte heriter = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("héritez")).findAny().get();
        plateau.getCartesCommunaute().remove(heriter);
        plateau.getCartesCommunaute().push(heriter);
        jules.avancer(0);
        assertEquals(1550, jules.getMontantBillet());

        Carte bellevile = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("Belleville")).findAny().get();
        plateau.getCartesCommunaute().remove(bellevile);
        plateau.getCartesCommunaute().push(bellevile);
        jules.avancer(0);
        assertEquals(1550, jules.getMontantBillet());
        assertEquals("Boulevard de Belleville", jules.getPositionCase().getNom());

        System.out.println("[REUSSITE] Scénario 11 : Tests pour les cartes « Chance » ou « Caisse de communauté »\n");
    }
}

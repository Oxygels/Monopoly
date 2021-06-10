package tests;

import cases.Case;
import cases.Propriete;
import plateau.Plateau;

import java.util.ArrayList;

public class Scenario1 {
    public static void launch() {
        System.out.println("[DEBUT] Scénario 1 : Tests pour la construction du plateau");
        Plateau plateau = Plateau.getPlateau();
        plateau.initTerrains();
        plateau.initCartes();

        assert plateau.getNbJoueurs() == 0;

        ArrayList<Case> cases = plateau.getCases();
        assert cases.get(0).getNom().equals("Case Départ");
        assert cases.get(1).getNom().equals("Boulevard de Belleville");
        assert cases.get(5).getNom().equals("Gare Montparnasse");
        assert cases.get(10).getNom().equals("Simple visite");
        assert cases.get(12).getNom().equals("Compagnie d'électricité");
        assert cases.get(20).getNom().equals("Parking Gratuit");
        assert cases.get(30).getNom().equals("Allez en prison");
        assert cases.get(36).getNom().equals("Chance");
        assert cases.get(39).getNom().equals("Rue de la Paix");
        assert cases.get(40).getNom().equals("Prison");

        assert plateau.getValeurParcGratuit() == 0;
        // Vérification que toutes les cases n'ont pas de propriété
        assert cases.stream().allMatch(c -> !(c instanceof Propriete) || ((Propriete) c).getProprietaire() == null);

        System.out.println("[REUSSITE] Scénario 1 : Tests pour la construction du plateau\n");
    }
}

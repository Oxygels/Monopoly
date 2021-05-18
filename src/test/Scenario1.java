package test;

import cases.Case;
import cases.Propriete;
import io.parser.Parser;
import io.parser.cases.*;
import io.reader.Fichier;
import plateau.Plateau;

import java.util.ArrayList;

public class Scenario1 {
    public static void launch() {
        System.out.println("[DEBUT] Scénario 1 : Tests pour la construction du plateau");
        Plateau plateau = Plateau.getPlateau();
        assert plateau.getNbJoueurs() == 0;

        Parser first = null;
        first = new ParserCaseAllerEnPrison(first);
        first = new ParserCaseChance(first);
        first = new ParserCaseCommunaute(first);
        first = new ParserCaseCompagnie(first);
        first = new ParserCaseDepart(first);
        first = new ParserCaseGare(first);
        first = new ParserCaseImpot(first);
        first = new ParserCaseParkingGratuit(first);
        first = new ParserCasePrison(first);
        first = new ParserCaseSimpleVisite(first);
        first = new ParserCaseTaxeLuxe(first);
        first = new ParserCaseTerrain(first);

        Fichier.lire("src/data/Terrains.csv", first);

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

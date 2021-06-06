package tests;

import cartes.Carte;
import exception.MonopolyException;
import io.parser.Parser;
import io.parser.cartes.chance.*;
import io.parser.cartes.communaute.*;
import io.reader.Fichier;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario11 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 11 : Tests pour les cartes « Chance » ou « Caisse de communauté »");
        Plateau plateau = Plateau.getPlateau();
        plateau.init();
        Joueur jules = new Joueur("Jules");
        plateau.ajouterJoueur(jules);

        Parser first = null;
        first = new ParserCarteChanceDeplacement(first);
        first = new ParserCarteChanceEncaisser(first);
        first = new ParserCarteChanceDeplacementRelatif(first);
        first = new ParserCarteChanceLiberation(first);
        first = new ParserCarteChancePayer(first);
        first = new ParserCarteChanceReparationImpot(first);

        Fichier.lire("src/data/CartesChance.csv", first);
        first = null;

        first = new ParserCarteCommunauteAnniversaire(first);
        first = new ParserCarteCommunauteChoix(first);
        first = new ParserCarteCommunauteDeplacement(first);
        first = new ParserCarteCommunauteEncaisser(first);
        first = new ParserCarteCommunauteLiberation(first);
        first = new ParserCarteCommunautePayer(first);
        Fichier.lire("src/data/CartesCommunaute.csv", first);

        Carte medecin = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("médecin")).findAny().get();
        plateau.getCartesCommunaute().remove(medecin);
        plateau.getCartesCommunaute().push(medecin);

        jules.seDeplacer(2);
        assert jules.getMontantBillet() == 1450;

        Carte heriter = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("héritez")).findAny().get();
        plateau.getCartesCommunaute().remove(heriter);
        plateau.getCartesCommunaute().push(heriter);
        jules.seDeplacer(0);
        assert jules.getMontantBillet() == 1550;

        Carte bellevile = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("Belleville")).findAny().get();
        plateau.getCartesCommunaute().remove(heriter);
        plateau.getCartesCommunaute().push(heriter);
        jules.seDeplacer(0);
        assert jules.getMontantBillet() == 1550;
        assert jules.getPositionCase().equals("Boulevard de Belleville");


        System.out.println("[REUSSITE] Scénario 11 : Tests pour les cartes « Chance » ou « Caisse de communauté »\n");
    }
}

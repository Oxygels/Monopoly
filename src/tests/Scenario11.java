package tests;

import cartes.Carte;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario11 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 11 : Tests pour les cartes « Chance » ou « Caisse de communauté »");
        Plateau plateau = Plateau.getPlateau();
        plateau.init();
        Joueur jules = new Joueur("Jules");
        plateau.ajouterJoueur(jules);

        Carte medecin = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("médecin")).findAny().get();
        plateau.getCartesCommunaute().remove(medecin);
        plateau.getCartesCommunaute().push(medecin);

        jules.avancer(2);
        assert jules.getMontantBillet() == 1450;

        Carte heriter = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("héritez")).findAny().get();
        plateau.getCartesCommunaute().remove(heriter);
        plateau.getCartesCommunaute().push(heriter);
        jules.avancer(0);
        assert jules.getMontantBillet() == 1550;

        Carte bellevile = plateau.getCartesCommunaute().stream().filter(p -> p.getEnonce().contains("Belleville")).findAny().get();
        plateau.getCartesCommunaute().remove(bellevile);
        plateau.getCartesCommunaute().push(bellevile);
        jules.avancer(0);
        assert jules.getMontantBillet() == 1550;
        assert jules.getPositionCase().getNom().equals("Boulevard de Belleville");

        System.out.println("[REUSSITE] Scénario 11 : Tests pour les cartes « Chance » ou « Caisse de communauté »\n");
    }
}

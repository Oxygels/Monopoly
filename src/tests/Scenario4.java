package tests;

import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario4 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 4 : Tests pour l’achat d’un terrain");

        Plateau plateau = Plateau.getPlateau();
        plateau.init();
        Joueur joueur = new Joueur("Quentin");
        plateau.ajouterJoueur(joueur);
        joueur.avancer(6);

        assert joueur.getPositionCase().getNom().equals("Rue de Vaugirard");
        assert ((Propriete) (joueur.getPositionCase())).getProprietaire() == null;
        joueur.acheterPropriete((Propriete) joueur.getPositionCase(), null);
        assert ((Propriete) joueur.getPositionCase()).getProprietaire().equals(joueur);
        assert joueur.getMontantBillet() == 1400;

        Joueur joueur2 = new Joueur("Yacine");
        plateau.ajouterJoueur(joueur2);
        Propriete vaugirard = (Propriete) (plateau.getCase("Rue de Vaugirard"));
        assert vaugirard.getProprietaire() != null;

        // TODO: En parler pendant la soutenance, on peut tout de même acheter au joueur
        boolean exception = false;
        try {
            joueur2.acheterPropriete(vaugirard, null);
        } catch (Exception e) {
            exception = true;
        }
        assert exception;

        System.out.println("[REUSSITE] Scénario 4 : Tests pour l’achat d’un terrain\n");

    }
}

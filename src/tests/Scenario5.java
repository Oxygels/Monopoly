package tests;

import cases.Propriete;
import exception.MonopolyException;
import joueur.Joueur;
import plateau.Plateau;

public class Scenario5 {
    public static void launch() throws MonopolyException {
        System.out.println("[DEBUT] Scénario 5 : Tests pour le déplacement d’un joueur sur " +
                "un terrain constructible acheté");

        Plateau plateau = Plateau.getPlateau();
        plateau.init();

        Joueur quentin = new Joueur("Quentin");
        Joueur jules = new Joueur("Jules");

        plateau.ajouterJoueur(quentin);
        plateau.ajouterJoueur(jules);

        quentin.acheterPropriete((Propriete) plateau.getCase("Rue de Vaugirard"), null);
        assert quentin.getMontantBillet() == 1400;
        jules.avancer(6);
        assert jules.getPositionCase().getNom().equals("Rue de Vaugirard");
        assert jules.getMontantBillet() == 1494 : "Le loyer n'a pas été payé";
        assert quentin.getMontantBillet() == 1406;

        quentin.acheterPropriete((Propriete) plateau.getCase("Rue de Courcelles"), null);
        quentin.acheterPropriete((Propriete) plateau.getCase("Avenue de la République"), null);
        assert quentin.getMontantBillet() == 1186;
        jules.avancer(3);
        assert jules.getPositionCase().getNom().equals("Avenue de la République");
        assert jules.getMontantBillet() == 1478; // Le loyer de 2 * 8 (car toutes les couleurs sont possédées)
        assert quentin.getMontantBillet() == 1202; // Et reçu ici
        jules.setMontantBillet(5);

        boolean exception = false;
        try {
            jules.seDeplacer(plateau.getCase("Rue de Courcelles").getId());
        } catch (Exception e) {
            exception = true;
        }
        assert exception;

        System.out.println("[REUSSITE] Scénario 5 : Tests pour le déplacement d’un joueur sur " +
                "un terrain constructible acheté\n");
    }
}

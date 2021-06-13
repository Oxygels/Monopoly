package ui.event;

import application.MonopolyGUI;
import cases.Case;
import cases.Propriete;
import exception.FailliteException;
import exception.MonopolyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import joueur.Joueur;

public class EventAchatTerrain implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;


    public EventAchatTerrain(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // TODO: Gérer l'achat d'un terrain

        // Recuperer la case courante
        Case caseCourante = monopoly.getJoueurCourant().getPositionCase();

        // Tester si c'est une propriete (sinon dialogue erreur)
        if (!(caseCourante instanceof Propriete))
            monopoly.DialogAction("Ce n'est pas une propriete", true);


        else // Acheter (vérfier les conditions dans la méthode acheterTerrain dans Joueur)
        {
            try {
                monopoly.getJoueurCourant().acheterPropriete((Propriete) caseCourante, null);
                monopoly.DialogInfo("Vous avez acheté  :  " + caseCourante.getNom());
                for (Propriete p :
                        monopoly.getJoueurCourant().getProprietesPossedees()) {
                    System.out.println(p.getNom());
                    // TODO : update le label des proprietes possede par le joueur courant
                }

            } catch (MonopolyException mE) {
                if (monopoly.getJoueurCourant().getProprietesPossedees().contains(caseCourante))
                    monopoly.DialogAction("Vous possedez deja  " + caseCourante.getNom(), true);
                mE.printStackTrace();
            }

            //TODO: verifier si il possede deja la propriete ou pas , si oui on doit afficher
            // "vous possedez deja cette propriete" sinon on affiche la boite de dialgue ci dessous


        }


    }

}

package ui.event;

import application.MonopolyGUI;
import cases.Case;
import cases.Propriete;
import exception.MonopolyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventAchatTerrain implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;


    public EventAchatTerrain(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // Recuperer la case courante
        Case caseCourante = monopoly.getJoueurCourant().getPositionCase();

        // Tester si c'est une propriete (sinon dialogue erreur)
        if (!(caseCourante instanceof Propriete))
            monopoly.DialogAction("Ce n'est pas une propriete", true);
        else // Acheter (vérfier les conditions dans la méthode acheterTerrain dans Joueur)
        {
            try {
                monopoly.getJoueurCourant().acheterPropriete((Propriete) caseCourante);
                monopoly.DialogInfo("Vous avez acheté  :  " + caseCourante.getNom());

                // Met à jour le panneau des propriétés
                monopoly.getZoneProprietes().getItems().add((Propriete) caseCourante);
                monopoly.getTfPorteMonnaie().setText(String.valueOf(monopoly.getJoueurCourant().getMontantBillet()));

            } catch (MonopolyException mE) {
                monopoly.DialogAction(mE.getMessage(), true);
            }
        }


    }

}

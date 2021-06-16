package ui.event;

import application.MonopolyGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import ui.DialogueTerrain;


public class EventGererMaison implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventGererMaison(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        if (monopoly.getTerrainSelectionne() == null) {
            monopoly.DialogAction("Tu n'as sélectionné aucun terrain !", true);
            return;
        }
        if (!monopoly.getTerrainSelectionne().getProprietaire().equals(monopoly.getJoueurCourant())) {
            monopoly.DialogInfo("Non non, ce n'est pas à toi de faire ça, c'est à " +
                    monopoly.getTerrainSelectionne().getProprietaire().getNom() + " !");
            return;
        }
        DialogueTerrain fenetre = new DialogueTerrain(monopoly);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.getDialogPane().setContent(fenetre.getRoot());
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeAnnuler);
        dialog.showAndWait();
    }
}

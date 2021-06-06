package ui.event;

import application.MonopolyGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EventGererMaison implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventGererMaison(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // TODO: Gerer les maisons du terrain concerné
        if (monopoly.getTerrainSelectionne() == -1)
            monopoly.DialogAction("Tu n'as sélectionné aucun terrain !", true);
        else
            monopoly.getFenetreTerrain().show();
    }
}

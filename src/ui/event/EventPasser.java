package ui.event;

import application.MonopolyGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

public class EventPasser implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventPasser(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // TODO: Décrit ce qui se  passe après le changement de joeur
        ArrayList<String> lesJoueurs = monopoly.getListeJoueurs();
        String jc = monopoly.getJoueurCourant();
        int i = lesJoueurs.indexOf(jc);
        int suivant = (i + 1) % lesJoueurs.size();

        ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
        button.fire();
    }

}

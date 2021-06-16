package ui.event;

import application.MonopolyGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;

import java.util.ArrayList;

public class EventPasser implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventPasser(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // Décrit ce qui se  passe après le changement de joueur
        ArrayList<Joueur> lesJoueurs = monopoly.getListeJoueurs();
        Joueur jc = monopoly.getJoueurCourant();
        int i = jc == null ? 0 : lesJoueurs.indexOf(jc);
        // jc peut être null si le joueur courant a fait faillite, il n'existe donc plus
        // Il doit cependant rester un joueur
        int suivant = (i + 1) % lesJoueurs.size();

        ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
        button.fire();
    }
}
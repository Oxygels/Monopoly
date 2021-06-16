package ui.event;

import application.MonopolyGUI;
import cases.Propriete;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;

public class EventChoixJoueur implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventChoixJoueur(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // Explique ce qui se passe lors du changement de joueur
        // Rappelons que le bouton qui a trigger l'event contient le nouveau joueur courant
        // Modifier le joueur courant
        if (e.getSource() instanceof ToggleButton) {
            ToggleButton b = (ToggleButton) e.getSource();
            Joueur j = (Joueur) b.getUserData();

            monopoly.setJoueurCourant(j);
            monopoly.getZoneProprietes().getItems().clear();
            for (Propriete p : j.getProprietesPossedees()) {
                monopoly.getZoneProprietes().getItems().add(p);
            }
            monopoly.DialogInfo(monopoly.getJoueurCourant().getNom() + " doit jouer");
            monopoly.updateUi();
        }
    }
}
package ui.event;

import application.MonopolyGUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventAchatTerrain implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventAchatTerrain(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // TODO: Gérer l'achat d'un terrain
        // Recuperer la case courante
        // Tester si c'est une propriete (sinon dialogue erreur)
        // Acheter (vérfier les conditions dans la méthode acheterTerrain dans Joueur)
        monopoly.DialogAction("Faut gérer cette affaire....", true);
    }

}

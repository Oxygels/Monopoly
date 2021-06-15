package ui.event;

import application.MonopolyGUI;
import cases.Case;
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

        Case caseCourante = monopoly.getJoueurCourant().getPositionCase();
        //TODO: Gerer les achats de maisons et hôtels via la fenêtre
//        if (!(caseCourante instanceof TerrainConstructible))
//            monopoly.DialogAction("Ce n'est pas un terrain constructible , pas de construction de maison ! ", true);
//        else {
//            try {
//                monopoly.getJoueurCourant().acheterMaison((TerrainConstructible) caseCourante);
//                monopoly.DialogInfo("Vous avez acheté une maison dans " + caseCourante.getNom());
//            } catch (MonopolyException mE) {
//                mE.printStackTrace();
//            }
//        }
    }
}

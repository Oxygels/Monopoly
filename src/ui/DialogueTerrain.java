package ui;

import application.MonopolyGUI;
import cases.Propriete;
import cases.TerrainConstructible;
import exception.MonopolyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DialogueTerrain {

    private final MonopolyGUI monopoly;
    private final VBox root = new VBox();
    private final Label _lMaison;
    private final Label _lHotel;
    private final Button _bAcheterMaison;
    private final Button _bVendreMaison;
    private final Button _bAcheterHotel;
    private final Button _bVendreHotel;

    public DialogueTerrain(MonopolyGUI monopoly) {
        _lMaison = new Label("Sur ce terrain a été construit " +
                ((TerrainConstructible) monopoly.getTerrainSelectionne()).getNbMaison() + " maisons");
        _lHotel = new Label("Sur ce terrain a été construit " +
                ((TerrainConstructible) monopoly.getTerrainSelectionne()).getNbHotel() + " hôtels");
        _bAcheterMaison = new Button();
        _bVendreMaison = new Button();
        _bAcheterHotel = new Button();
        _bVendreHotel = new Button();
        this.monopoly = monopoly;
        root.setSpacing(5);
        _bAcheterMaison.setText("Acheter une maison");
        _bVendreMaison.setText("Vendre une maison");
        _bAcheterHotel.setText("Acheter hôtel");
        _bVendreHotel.setText("Vendre un hôtel");

        setupAcheterMaison();
        setupAcheterHotel();
        setupVendreMaison();
        setupVendreHotel();
        root.getChildren().addAll(_lMaison, _lHotel, _bAcheterMaison, _bVendreMaison, _bAcheterHotel, _bVendreHotel);
    }

    public VBox getRoot() {
        return root;
    }

    private void setupVendreHotel() {
        _bVendreHotel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Propriete prop = monopoly.getTerrainSelectionne();
                if (!(prop instanceof TerrainConstructible))
                    monopoly.DialogAction("On ne peut pas construire sur une gare ou une compagnie", true);
                else {
                    try {
                        monopoly.getJoueurCourant().vendreHotel((TerrainConstructible) prop);
                        monopoly.updateUi();
                        updateLabels();
                    } catch (MonopolyException e) {
                        monopoly.DialogAction(e.getMessage(), true);
                    }
                }
            }
        });
    }

    private void setupVendreMaison() {
        _bVendreMaison.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Propriete prop = monopoly.getTerrainSelectionne();
                if (!(prop instanceof TerrainConstructible))
                    monopoly.DialogAction("On ne peut pas construire sur une gare ou une compagnie", true);
                else {
                    try {
                        monopoly.getJoueurCourant().vendreMaison((TerrainConstructible) prop);
                        monopoly.updateUi();
                        updateLabels();
                    } catch (MonopolyException e) {
                        monopoly.DialogAction(e.getMessage(), true);
                    }
                }
            }
        });
    }

    private void setupAcheterHotel() {
        _bAcheterHotel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Propriete prop = monopoly.getTerrainSelectionne();
                if (!(prop instanceof TerrainConstructible))
                    monopoly.DialogAction("On ne peut pas construire sur une gare ou une compagnie", true);
                else {
                    try {
                        monopoly.getJoueurCourant().acheterHotel((TerrainConstructible) prop);
                        monopoly.updateUi();
                        updateLabels();
                    } catch (MonopolyException e) {
                        monopoly.DialogAction(e.getMessage(), true);
                    }
                }
            }
        });
    }

    private void setupAcheterMaison() {
        _bAcheterMaison.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Propriete prop = monopoly.getTerrainSelectionne();
                if (!(prop instanceof TerrainConstructible))
                    monopoly.DialogAction("On ne peut pas construire sur une gare ou une compagnie", true);
                else {
                    try {
                        monopoly.getJoueurCourant().acheterMaison((TerrainConstructible) prop);
                        monopoly.updateUi();
                        updateLabels();
                    } catch (MonopolyException e) {
                        monopoly.DialogAction(e.getMessage(), true);
                    }
                }
            }
        });
    }

    private void updateLabels() {
        _lMaison.setText("Sur ce terrain a été construit " +
                ((TerrainConstructible) monopoly.getTerrainSelectionne()).getNbMaison() + " maisons");
        _lHotel.setText("Sur ce terrain a été construit " +
                ((TerrainConstructible) monopoly.getTerrainSelectionne()).getNbHotel() + " hôtels");
    }

}

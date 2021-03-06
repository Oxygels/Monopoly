package ui.event;

import application.MonopolyGUI;
import cases.Case;
import cases.CasePiocheChance;
import cases.CasePiocheCommunaute;
import exception.FailliteException;
import exception.MonopolyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import plateau.Plateau;


public class EventJouer implements EventHandler<ActionEvent> {

    private final MonopolyGUI monopoly;

    public EventJouer(MonopolyGUI monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent event) {

        String tfDe1 = monopoly.getTfValeurDe1().getText();
        String tfDe2 = monopoly.getTfValeurDe2().getText();

        try {
            int de1 = Integer.parseInt(tfDe1);
            int de2 = Integer.parseInt(tfDe2);

            int nbCases = de1 + de2;
            if (de1 == de2) {
                int nbDbl = monopoly.getNbDoubles();

                nbDbl++;
                monopoly.setNbDoubles(nbDbl);

                if (nbDbl == 1)
                    monopoly.getMessageFooter().setText("C'est ton premier double !");
                else if (nbDbl == 2)
                    monopoly.getMessageFooter().setText("C'est ton deuxième double !! Encore un et c'est la taule...");
                else {
                    monopoly.getMessageFooter().setText("Police, menottes, prison...");

                    try {
                        int prison = Plateau.getPlateau().getCase("Prison").getId();
                        monopoly.seDeplacer(prison);
                    } catch (MonopolyException e) {

                    }

                    monopoly.setNbDoubles(0);
                }
            } else {
                monopoly.setNbDoubles(0);
            }

            int nbcarteslib = monopoly.getJoueurCourant().getNbCartesLibPrison();
            try {
                Case destination = monopoly.avancer(nbCases);
                // Pour montrer au joueur qu'il a avancé sur une case pioche avant de voir le dialogue
                monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());

                String enonce;

                if (destination instanceof CasePiocheChance) {
                    if (monopoly.getJoueurCourant().getNbCartesLibPrison() > nbcarteslib) {
                        enonce = "Vous êtes libéré de Prison";
                    } else
                        enonce = Plateau.getPlateau().getCartesChance().get(0).getEnonce();

                    monopoly.DialogInfo("Vous avez pioché la carte \"Chance\" suivante:\n\n" + enonce);
                } else if (destination instanceof CasePiocheCommunaute) {
                    if (monopoly.getJoueurCourant().getNbCartesLibPrison() > nbcarteslib) {
                        enonce = "Vous êtes libéré de Prison";
                    } else
                        enonce = Plateau.getPlateau().getCartesCommunaute().get(0).getEnonce();
                    monopoly.DialogInfo("Vous avez pioché la carte \"Caisse de Communauté\" suivante :\n\n" + enonce);
                }
            } catch (FailliteException e) {
                monopoly.DialogInfo(e.getMessage());
                monopoly.DialogInfo(monopoly.getJoueurCourant().getNom() + " a fait faillite");
                monopoly.retirerJoueur(monopoly.getJoueurCourant());
                monopoly.setNbDoubles(0);
            } catch (MonopolyException e) {
                monopoly.DialogAction(e.getMessage(), true);
            } finally {
                // Retirer le pion du plateau si le joueur a fait faillite ou le voir se déplacer si il a tiré
                // une carte de déplacement
                monopoly.updateUi();
            }


            // Voir le pion aller en prison si il a fait 3x un double
            monopoly.updateUi();
        } catch (NumberFormatException e) {
            monopoly.DialogAction("Les valeurs des dés ne sont pas des entiers", true);
        }
    }
}
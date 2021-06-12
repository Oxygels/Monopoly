package ui.event;

import application.MonopolyGUI;
import cases.Case;
import cases.CasePioche;
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

        int de1 = Integer.parseInt(tfDe1);
        int de2 = Integer.parseInt(tfDe2);

        int nbCases = de1 + de2;

        // System.out.println("d1=" + de1 + "  d2=" + de2 + "  nb cases=" + nbCases);

        try {
            monopoly.avancer(nbCases);

            if (monopoly.getJoueurCourant().getPositionCase() instanceof CasePiocheChance) {
                String enonce = Plateau.getPlateau().getCartesChance().get(0).getEnonce();
                monopoly.DialogInfo(enonce);
            }
            else if (monopoly.getJoueurCourant().getPositionCase() instanceof CasePiocheCommunaute) {
                String enonce = Plateau.getPlateau().getCartesCommunaute().get(0).getEnonce();
                monopoly.DialogInfo(enonce);
            }
        } catch (FailliteException e) {
            monopoly.retirerJoueur(monopoly.getJoueurCourant());
        } catch (MonopolyException e) {
            e.printStackTrace();
        }

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
                    e.printStackTrace();
                }

                monopoly.setNbDoubles(0);
            }
        } else {
            monopoly.setNbDoubles(0);
        }

        monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
    }
}
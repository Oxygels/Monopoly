package plateau;

import cartes.Carte;
import cases.Case;
import cases.CategorieCarte;
import cases.Propriete;
import joueur.Joueur;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Plateau {

    private int valeurParcGratuit;
    private int dernierLancerDes;
    private int indiceJoueurTour;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private Stack<Carte> cartesChance = new Stack<Carte>();
    private Stack<Carte> cartesCommunaute = new Stack<Carte>();
    private ArrayList<Case> proprietesPossedees = new ArrayList<Case>();

    private Plateau(int valeurParcGratuit, int dernierLancerDes, int indiceJoueurTour) {
        setValeurParcGratuit(valeurParcGratuit);
        setDernierLancerDes(dernierLancerDes);
        setIndiceJoueurTour(indiceJoueurTour);
    }

    public int getValeurParcGratuit() {
        return valeurParcGratuit;
    }

    public int getDernierLancerDes() {
        return dernierLancerDes;
    }

    public int getIndiceJoueurTour() {
        return indiceJoueurTour;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public Stack<Carte> getCartesChance() {
        return cartesChance;
    }

    public Stack<Carte> getCartesCommunaute() {
        return cartesCommunaute;
    }

    public int getNbCartes() {
        return cartesChance.size() + cartesCommunaute.size();
    }

    public ArrayList<Case> getProprietesPossedees() {
        return proprietesPossedees;
    }

    public Joueur getJoueurCourant() {
        return joueurs.get(indiceJoueurTour);
    }

    public int getNbCases() {
        return proprietesPossedees.size();
    }

    public int getNbJoueurs() {
        return joueurs.size();
    }

    public void setValeurParcGratuit(int valeurParcGratuit) {
        if (valeurParcGratuit < 0)
            throw new IllegalArgumentException("valeurParcGratuit doit etre un entier positif.");
        else
            this.valeurParcGratuit = valeurParcGratuit;
    }

    public void setDernierLancerDes(int dernierLancerDes) {
        if (dernierLancerDes < 0)
            throw new IllegalArgumentException("dernierLancerDes doit etre un entier positif.");
        else
            this.dernierLancerDes = dernierLancerDes;
    }

    public void setIndiceJoueurTour(int indiceJoueurTour) {
        if (indiceJoueurTour < 0)
            throw new IllegalArgumentException("joueurTour doit etre un entier positif.");
        else
            this.indiceJoueurTour = indiceJoueurTour;
    }

    private void lancerDes() {
        Random rand = new Random();
        dernierLancerDes = rand.nextInt(11) + 2;
    }

    public void ajouterJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("L'objet Joueur ne peut valoir null.");
        else if (joueurs.contains(joueur))
            throw new IllegalArgumentException("Le joueur existe deja dans la partie.");
        else
            joueurs.add(joueur);
    }

    public void retirerJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("L'objet Joueur ne peut valoir null.");
        else if (!joueurs.contains(joueur))
            throw new IllegalArgumentException("Le joueur a retirer n'existe pas.");
        else
            joueurs.remove(joueur);
    }

    public void ajouterCase(Propriete caseAchetable) {
        if (caseAchetable == null)
            throw new IllegalArgumentException("caseAchetable ne peut valoir null.");
        else
            proprietesPossedees.add(caseAchetable);
    }

    public void retirerCase(Propriete caseAchetable) {
        if (caseAchetable == null)
            throw new IllegalArgumentException("caseAchetable ne peut valoir null.");
        else if (!proprietesPossedees.contains(caseAchetable))
            throw new IllegalArgumentException("caseAchetable ne peut etre retiree si elle n'existe pas");
        else
            proprietesPossedees.remove(caseAchetable);
    }

    public void ajouterCarte(Carte carte) {
        if (carte.getCategorie() == CategorieCarte.Chance)
            cartesChance.add(carte);
        else if (carte.getCategorie() == CategorieCarte.Communaute)
            cartesCommunaute.add(carte);
    }

    public Carte piocherCarte(CategorieCarte categorie) {
        if (categorie == CategorieCarte.Chance)
            return cartesChance.pop();
        else
            return cartesCommunaute.pop();
    }

    public void retirerCarte(Carte carte, CategorieCarte categorie) {
        if (carte == null)
            throw new IllegalArgumentException("L'objet Carte ne peut valoir null.");
        if (categorie == CategorieCarte.Chance) {
            if (cartesChance.contains(carte))
                cartesChance.remove(carte);
            else
                throw new IllegalArgumentException("La carte a retirer n'existe pas.");
        } else {
            if (cartesCommunaute.contains(carte))
                cartesCommunaute.remove(carte);
            else
                throw new IllegalArgumentException("La carte a retirer n'existe pas.");
        }
    }

    // PATTERN SINGLETON

    private static Plateau plateau;

    public Plateau getPlateau() {
        if (plateau == null)
            plateau = new Plateau(valeurParcGratuit, dernierLancerDes, indiceJoueurTour);

        return plateau;
    }
}
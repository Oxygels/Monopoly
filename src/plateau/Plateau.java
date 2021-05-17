package plateau;

import cartes.Carte;
import cartes.CategorieCarte;
import cases.Case;
import joueur.Joueur;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Plateau {

    private static Plateau plateau;
    private final ArrayList<Joueur> joueurs = new ArrayList<>();
    private final Stack<Carte> cartesChance = new Stack<>();
    private final Stack<Carte> cartesCommunaute = new Stack<>();
    private final ArrayList<Case> cases = new ArrayList<>();
    private int valeurParcGratuit = 0;
    private int dernierLancerDes = 0;
    private int indiceJoueurTour = -1;

    private Plateau() {
    }

    public static Plateau getPlateau() {
        if (plateau == null)
            plateau = new Plateau();

        return plateau;
    }

    public int getValeurParcGratuit() {
        return valeurParcGratuit;
    }

    public void setValeurParcGratuit(int valeurParcGratuit) {
        if (valeurParcGratuit < 0)
            throw new IllegalArgumentException("valeurParcGratuit doit etre un entier positif.");
        else
            this.valeurParcGratuit = valeurParcGratuit;
    }

    public int getDernierLancerDes() {
        return dernierLancerDes;
    }

    public void setDernierLancerDes(int dernierLancerDes) {
        if (dernierLancerDes < 0)
            throw new IllegalArgumentException("dernierLancerDes doit etre un entier positif.");
        else
            this.dernierLancerDes = dernierLancerDes;
    }

    public int getIndiceJoueurTour() {
        return indiceJoueurTour;
    }

    public void setIndiceJoueurTour(int indiceJoueurTour) {
        if (indiceJoueurTour < -1)
            throw new IllegalArgumentException("joueurTour doit etre un entier positif.");
        else
            this.indiceJoueurTour = indiceJoueurTour;
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

    public ArrayList<Case> getCases() {
        return cases;
    }

    public Joueur getJoueurCourant() {
        return joueurs.get(indiceJoueurTour);
    }

    public int getNbCases() {
        return cases.size();
    }

    public int getNbJoueurs() {
        return joueurs.size();
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

    public void ajouterCase(Case caseTerrain) {
        if (caseTerrain == null)
            throw new IllegalArgumentException("caseTerrain ne peut valoir null.");
        else
            cases.add(caseTerrain);
    }

    public void retirerCase(Case caseTerrain) {
        if (caseTerrain == null)
            throw new IllegalArgumentException("caseAchetable ne peut valoir null.");
        else if (!cases.contains(caseTerrain))
            throw new IllegalArgumentException("caseAchetable ne peut etre retiree si elle n'existe pas");
        else
            cases.remove(caseTerrain);
    }

    public void ajouterCarteChance(Carte carte) {
        cartesChance.push(carte);
    }

    public void ajouterCarteCommunaute(Carte carte) {
        cartesCommunaute.push(carte);
    }

    // PATTERN SINGLETON

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
}
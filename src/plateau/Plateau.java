package plateau;

import cartes.Carte;
import cartes.CarteLibereDePrison;
import cartes.CategorieCarte;
import cases.Case;
import exception.MonopolyException;
import io.parser.Parser;
import io.parser.cartes.chance.*;
import io.parser.cartes.communaute.*;
import io.parser.cases.*;
import io.reader.Fichier;
import joueur.Joueur;

import java.util.ArrayList;
import java.util.Optional;
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

    public void initCartes() {
        Parser first = null;
        first = new ParserCarteChanceDeplacement(first);
        first = new ParserCarteChanceEncaisser(first);
        first = new ParserCarteChanceDeplacementRelatif(first);
        first = new ParserCarteChanceLiberation(first);
        first = new ParserCarteChancePayer(first);
        first = new ParserCarteChanceReparationImpot(first);

        Fichier.lire("src/data/CartesChance.csv", first);
        first = null;

        first = new ParserCarteCommunauteAnniversaire(first);
        first = new ParserCarteCommunauteChoix(first);
        first = new ParserCarteCommunauteDeplacement(first);
        first = new ParserCarteCommunauteEncaisser(first);
        first = new ParserCarteCommunauteLiberation(first);
        first = new ParserCarteCommunautePayer(first);
        Fichier.lire("src/data/CartesCommunaute.csv", first);
    }

    public void initTerrains() {
        Parser first = null;
        first = new ParserCaseAllerEnPrison(first);
        first = new ParserCaseChance(first);
        first = new ParserCaseCommunaute(first);
        first = new ParserCaseCompagnie(first);
        first = new ParserCaseDepart(first);
        first = new ParserCaseGare(first);
        first = new ParserCaseImpot(first);
        first = new ParserCaseParkingGratuit(first);
        first = new ParserCasePrison(first);
        first = new ParserCaseSimpleVisite(first);
        first = new ParserCaseTaxeLuxe(first);
        first = new ParserCaseTerrain(first);
        Fichier.lire("src/data/Terrains.csv", first);
    }

    /**
     * Constructeur par defaut de la classe Plateau.
     *
     * @return plateau qui n'est autre qu'un objet de type Plateau.
     */

    public static Plateau getPlateau() {
        if (plateau == null)
            plateau = new Plateau();

        return plateau;
    }

    /**
     * Methode publique permettant l'initialisation d'un plateau.
     */

    public void clear() {
        valeurParcGratuit = 0;
        dernierLancerDes = 0;
        indiceJoueurTour = -1;
        joueurs.clear();
        cartesCommunaute.clear();
        cartesChance.clear();
        cases.clear();
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

    /**
     * Methode publique permettant de simuler le lancer des deux des.
     */

    private void lancerDes() {
        Random rand = new Random();
        dernierLancerDes = rand.nextInt(11) + 2;
    }

    public Case getCase(String nom) {
        Optional<Case> caseCherchee = getCases()
                .stream()
                .filter(c -> c.getNom().equals(nom))
                .findAny();
        if (!caseCherchee.isPresent())
            throw new IllegalArgumentException("La case spécifiée par nom n'existe pas");
        return caseCherchee.get();
    }

    /**
     * Methode publique permettant d'ajouter un joueur.
     *
     * @param joueur designant le joueur a ajouter.
     * @see Joueur
     */

    public void ajouterJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("L'objet Joueur ne peut valoir null.");
        else if (joueurs.contains(joueur))
            throw new IllegalArgumentException("Le joueur existe deja dans la partie.");
        else
            joueurs.add(joueur);
        if (getIndiceJoueurTour() == -1)
            setIndiceJoueurTour(0);
    }

    /**
     * Methode publique permettant de retirer un joueur.
     *
     * @param joueur designant le joueur a retirer.
     * @see Joueur
     */

    public void retirerJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("L'objet Joueur ne peut valoir null.");
        else if (!joueurs.contains(joueur))
            throw new IllegalArgumentException("Le joueur a retirer n'existe pas.");
        else
            joueurs.remove(joueur);
    }

    /**
     * Methode publique permettant d'ajouter une case.
     *
     * @param caseTerrain designant la case a ajouter.
     * @see Case
     */

    public void ajouterCase(Case caseTerrain) {
        if (caseTerrain == null)
            throw new IllegalArgumentException("caseTerrain ne peut valoir null.");
        else
            cases.add(caseTerrain);
    }

    /**
     * Methode publique permettant de retirer une case.
     *
     * @param caseTerrain designant la case a retirer.
     * @see Case
     */

    public void retirerCase(Case caseTerrain) {
        if (caseTerrain == null)
            throw new IllegalArgumentException("caseAchetable ne peut valoir null.");
        else if (!cases.contains(caseTerrain))
            throw new IllegalArgumentException("caseAchetable ne peut etre retiree si elle n'existe pas");
        else
            cases.remove(caseTerrain);
    }

    /**
     * Methode publique permettant d'ajouter une carte Chance.
     *
     * @param carte designant la carte Chance a ajouter.
     * @see Carte
     * @see CategorieCarte
     */

    public void ajouterCarteChance(Carte carte) {
        cartesChance.push(carte);
    }

    /**
     * Methode publique permettant d'ajouter une carte Communaute.
     *
     * @param carte designant la carte Communaute a ajouter.
     * @see Carte
     * @see CategorieCarte
     */

    public void ajouterCarteCommunaute(Carte carte) {
        cartesCommunaute.push(carte);
    }

    // PATTERN SINGLETON

    /**
     * Methode publique permettant de piocher une carte.
     *
     * @param categorie designant la categorie de la carte a piocher.
     * @see Carte
     * @see CategorieCarte
     */

    public void piocherCarte(CategorieCarte categorie, Joueur j) throws MonopolyException {
        if (j == null)
            throw new IllegalArgumentException("Joueur null");
        Carte carte;
        if (categorie == CategorieCarte.Chance) {
            carte = cartesChance.pop();
            if (carte instanceof CarteLibereDePrison) {
                getJoueurCourant().recevoirCarteLibPrison((CarteLibereDePrison) carte);
                return;
            }
            cartesChance.add(0, carte);
        } else {
            carte = cartesCommunaute.pop();
            if (carte instanceof CarteLibereDePrison) {
                getJoueurCourant().recevoirCarteLibPrison((CarteLibereDePrison) carte);
                return;
            }
            cartesCommunaute.add(0, carte);
        }
        carte.actionCarte(j);
    }

    /**
     * Methode publique permettant d'ajouter une carte Chance.
     *
     * @param carte     designant la carte a retirer.
     * @param categorie designant la categorie de la carte a retirer.
     * @see Carte
     * @see CategorieCarte
     */

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
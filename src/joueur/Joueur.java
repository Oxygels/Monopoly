package joueur;

import cartes.CarteLibereDePrison;
import cases.Case;
import cases.CaseRecevoirConstant;
import cases.Propriete;
import cases.TerrainConstructible;
import exception.ArgentException;
import exception.MonopolyException;
import plateau.Plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Joueur {

    private final ArrayList<Propriete> proprietesPossedees = new ArrayList<Propriete>();
    private final Stack<CarteLibereDePrison> cartesLibPrison = new Stack<CarteLibereDePrison>();
    private String nom;
    private int position;
    private int montantBillet;

    public Joueur(String nom) {
        setNom(nom);
        position = 0;
        setMontantBillet(1500); // Montant par defaut selon les regles
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("le nom doit pas etre null ou vide");
        else
            this.nom = nom;
    }

    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        if (position < 0)
            throw new IllegalArgumentException("La position doit etre un nombre positif");
        else
            this.position = position;
    }

    public Case getPositionCase() {
        return Plateau.getPlateau().getCases().get(getPosition());
    }

    public int getNbCartesLibPrison() {
        return cartesLibPrison.size();
    }

    public int getMontantBillet() {
        return montantBillet;
    }

    public void setMontantBillet(int montantBillet) {
        if (montantBillet < 0)
            throw new IllegalArgumentException("Le nombre de montant Billet doit etre un entier positif");
        else
            this.montantBillet = montantBillet;
    }

    public ArrayList<Propriete> getProprietesPossedees() {
        return proprietesPossedees;
    }


    //--------------------METHODES METIERS-----------------------------//

    public void acheterPropriete(Propriete T, Joueur j) throws MonopolyException {

        if (T == null)
            throw new IllegalArgumentException("Propriete ne peut etre null");
        if (montantBillet - T.getPrix() < 0)
            throw new ArgentException("Pas assez d'argent pour acheter le terrain");
        if (j == null && T.getProprietaire() != null)
            throw new MonopolyException("On ne peut pas acheter un terrain sans acheter au joueur concerné");
        if (j != null && !j.proprietesPossedees.contains(T))
            throw new MonopolyException("Le joueur ne possede pas la propriete");

        if (j != null) {
            payer(T.getPrix(), j);
            j.proprietesPossedees.remove(T);
        } else
            payerBanque(T.getPrix(), false);

        T.setProprietaire(this);
        proprietesPossedees.add(T);
    }

    public void payer(int montant, Joueur j) throws ArgentException {
        if (montantBillet - montant < 0)
            throw new ArgentException("Le joueur n'a pas assez d'argent pour payer");
        if (j == null)
            throw new IllegalArgumentException("Le joueur ne peut etre null");
        j.gagnerArgent(montant);
        montantBillet -= montant;
    }

    public void payerBanque(int montant, boolean parcGratuit) throws ArgentException {
        if (montantBillet - montant < 0)
            throw new ArgentException("Le joueur n'a pas assez d'argent pour payer");
        if (parcGratuit) {
            // Typiquement avec case "Taxe" ou certains cartes
            Plateau plateau = Plateau.getPlateau();
            plateau.setValeurParcGratuit(plateau.getValeurParcGratuit() + montant);
        }
        montantBillet -= montant;
    }

    public void gagnerArgent(int money) {
        this.montantBillet += money;
    }

    public void recevoirCarteLibPrison(CarteLibereDePrison carte) {
        cartesLibPrison.push(carte);
    }

    public void utiliserCarteLibPrison() throws MonopolyException {
        if (cartesLibPrison.size() == 0)
            throw new MonopolyException("Pas de carte \"Libéré de prison\" possédée");
        else {
            CarteLibereDePrison carte = cartesLibPrison.pop();
            switch (carte.getCategorieCarte()) {
                // On place la carte en dessous de la pile
                case Chance:
                    Plateau.getPlateau().getCartesChance().add(0, carte);
                    break;
                case Communaute:
                    Plateau.getPlateau().getCartesCommunaute().add(0, carte);
                    break;
            }
        }
    }

    public void vendreCarteLibPrison(int prix, Joueur j) throws MonopolyException {
        if (cartesLibPrison.size() == 0)
            throw new MonopolyException("nombre de cartes prison est deja a 0");
        if (j == null) {
            utiliserCarteLibPrison();
            montantBillet += prix;
        } else {
            try {
                j.payer(prix, this);
            } catch (ArgentException e) {
                e.printStackTrace();
            }
            j.recevoirCarteLibPrison(cartesLibPrison.pop());
        }
    }

    public void finirTour() throws MonopolyException {
        try {
            Plateau plateau = Plateau.getPlateau();
            Joueur jcourant = plateau.getJoueurCourant();
            if (jcourant != this)
                throw new MonopolyException("Un joueur ne peut finir son tour si ce n'est pas a lui de jouer");
            plateau.setIndiceJoueurTour((plateau.getIndiceJoueurTour() + 1) % plateau.getNbJoueurs());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Il n'y a pas de joueur sur le plateau");
        }
    }

    public void seDeplacer(int destination) throws MonopolyException {
        if (destination < 0)
            throw new IllegalArgumentException("La destination doit etre un nombre positif");
        else
            this.position = destination;
        getPositionCase().action(this);
    }

    public void avancer(int montant) throws MonopolyException {

        if (montant < 0)
            throw new IllegalArgumentException("Montant deplacement négatif");
        Plateau plateau = Plateau.getPlateau();
        plateau.setDernierLancerDes(montant);
        if (position + montant >= plateau.getNbCases())
            ((CaseRecevoirConstant) plateau.getCase("Case Départ")).action(this);

        position = (position + montant) % (plateau.getNbCases() - 1);
        getPositionCase().action(this);
    }

    public void acheterMaison(TerrainConstructible T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Le terrain ne doit pas etre null");

        if (!proprietesPossedees.contains(T))
            throw new IllegalArgumentException("On ne peut pas acheter une maison " +
                    "sur un terrain qu'on ne possede pas");

        if (possedePasToutesLesCouleurs(T))
            throw new MonopolyException("On ne peut pas acheter de maison si on ne possede pas tous les terrains" +
                    "de la meme couleur");
        T.ajouterMaison();
        if (!regleDes2Maisons(T)) {
            T.retirerMaison();
            throw new MonopolyException("On ne peut pas avoir 2 maisons d'écart entre deux terrains de la meme famille");
        }
        payerBanque(T.getPrixMaison(), false);
    }

    private boolean regleDes2Maisons(TerrainConstructible terrain) {
        List<Integer> s = getProprietesPossedees()
                .stream()
                .filter(t ->
                        t instanceof TerrainConstructible
                                && ((TerrainConstructible) t).getCouleur().equals(terrain.getCouleur())
                ).map(t -> ((TerrainConstructible) t).getNbMaison()).collect(Collectors.toList());
        // On convertit l'ensemble des cases de la même famille
        // en son nombre de maison sur chacun de ces terrains
        return s.stream().max(Integer::compare).get() - s.stream().min(Integer::compare).get() < 2;
        // Le stream ne peut être vide, si on fait la vérification c'est que le joueur possède au moins
        // un terrain constructible (terrain), donc il y aura au "minimum" un 0 dans le stream

        // La vérification de la règle se comprend par le fait qu'à tout moment, l'éloignement maximal des
        // valeurs ne peut excéder deux, en particulier les cases qui ont le plus et le moins de maison
    }

    public boolean possedePasToutesLesCouleurs(TerrainConstructible T) {
        return Plateau.getPlateau().getCases().stream().anyMatch(c -> {
            return (c instanceof TerrainConstructible)
                    && ((TerrainConstructible) c).getCouleur().equals(T.getCouleur())
                    && ((TerrainConstructible) c).getProprietaire() != this;
        });
    }

    public void vendreMaison(TerrainConstructible T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Le terrain ne doit pas etre null");
        if (!proprietesPossedees.contains(T))
            throw new IllegalArgumentException("On ne peut pas vendre une maison " +
                    "sur un terrain qu'on ne possede pas");
        T.retirerMaison();
        gagnerArgent(T.getPrix() / 2);
    }
}

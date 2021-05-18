package joueur;

import cartes.CarteLibereDePrison;
import cases.Propriete;
import cases.TerrainConstructible;
import exception.ArgentException;
import exception.MonopolyException;
import plateau.Plateau;

import java.util.ArrayList;
import java.util.Stack;

public class Joueur {

    private final ArrayList<Propriete> proprietesPossedees = new ArrayList<Propriete>();
    private final Stack<CarteLibereDePrison> cartesLibPrison = new Stack<CarteLibereDePrison>();
    private String nom;
    private int position;
    private int montantBillet;

    public Joueur(String nom) {
        setNom(nom);
        setPosition(0);
        setMontantBillet(1500); // Montant par defaut selon les regles
    }

    public Joueur(String nom, int position, int montantBillet) {
        setNom(nom);
        setPosition(position);
        setMontantBillet(montantBillet);
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

    public void setPosition(int position) {
        if (position < 0)
            throw new IllegalArgumentException("La position doit etre un nombre positif");
        else
            this.position = position;
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

    public void acheterPropriete(Propriete T, Joueur j) throws ArgentException {

        if (T == null)
            throw new IllegalArgumentException("Propriete ne peut etre null");
        if (montantBillet - T.getPrix() < 0)
            throw new ArgentException("Pas assez d'argent pour acheter le terrain");
        if (j != null && !j.proprietesPossedees.contains(T))
            throw new IllegalArgumentException("Le joueur ne possede pas la propriete");

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

    public void seDeplacer(int destination) {
        if (destination < 0)
            throw new IllegalArgumentException("La destination doit etre un nombre positif");
        else
            this.position = destination;
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
        payerBanque(T.getPrixMaison(), false);
    }

    private boolean possedePasToutesLesCouleurs(TerrainConstructible T) {
        return Plateau.getPlateau().getCases().stream().anyMatch(c -> {
            return (c instanceof TerrainConstructible)
                    && ((TerrainConstructible) c).getCouleur() == T.getCouleur()
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

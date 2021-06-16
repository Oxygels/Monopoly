package joueur;

import cartes.CarteLibereDePrison;
import cases.Case;
import cases.CaseRecevoirConstant;
import cases.Propriete;
import cases.TerrainConstructible;
import exception.FailliteException;
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

    public void acheterPropriete(Propriete T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Propriete ne peut etre null");
        if (T.getProprietaire() != null)
            throw new MonopolyException("Le terrain a déjà été acheté par " + T.getProprietaire().getNom());
        if (montantBillet - T.getPrix() < 0)
            throw new MonopolyException("Pas assez d'argent pour acheter le terrain");

        payerBanque(T.getPrix(), false);

        T.setProprietaire(this);
        proprietesPossedees.add(T);
    }

    public void payer(int montant, Joueur j) throws FailliteException {
        if (montantBillet - montant < 0)
            throw new FailliteException("Le joueur n'a pas assez d'argent pour payer");
        if (j == null)
            throw new IllegalArgumentException("Le joueur ne peut etre null");
        j.gagnerArgent(montant);
        montantBillet -= montant;
    }

    public void payerBanque(int montant, boolean parcGratuit) throws FailliteException {
        if (montantBillet - montant < 0)
            throw new FailliteException("Le joueur n'a pas assez d'argent pour payer");
        if (parcGratuit) {
            // Typiquement avec case "Taxe" ou certaines cartes
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
            try {
                carte.actionCarte(this);
            } catch (MonopolyException e) {
                cartesLibPrison.push(carte);
                // Si on a pas le droit d'utiliser la carte, on la remet dans la pile du joueur
                // Et on propage l'exception à l'interface
                throw e;
            }
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

    public Case avancer(int montant) throws MonopolyException {
        if (montant < 0)
            throw new IllegalArgumentException("Montant deplacement négatif");
        if (getPositionCase().getNom().equals("Prison")) {
            throw new MonopolyException("Tu es bloqué en prison, il faut payer ou se libérer");
        }
        Plateau plateau = Plateau.getPlateau();
        plateau.setDernierLancerDes(montant);
        if (position + montant >= plateau.getNbCases())
            ((CaseRecevoirConstant) plateau.getCase("Case Départ")).action(this);

        position = (position + montant) % (plateau.getNbCases() - 1);
        Case casePreAction = getPositionCase();
        getPositionCase().action(this);
        return casePreAction;
    }

    public void acheterMaison(TerrainConstructible T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Le terrain ne doit pas etre null");

        if (!proprietesPossedees.contains(T))
            throw new IllegalArgumentException("On ne peut pas acheter une maison " +
                    "sur un terrain qu'on ne possede pas");

        if (possedePasToutesLesCouleurs(T))
            throw new MonopolyException("On ne peut pas acheter de maison si on ne possede pas tous les terrains" +
                    " de la meme couleur");
        T.ajouterMaison();
        if (!regleDes2Maisons(T)) {
            T.retirerMaison();
            throw new MonopolyException("On ne peut pas avoir 2 maisons d'écart " +
                    "entre deux terrains de la meme couleur");
        }
        payerBanque(T.getPrixMaison(), false);
    }

    public void acheterHotel(TerrainConstructible T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Le terrain ne doit pas etre null");

        if (!proprietesPossedees.contains(T))
            throw new IllegalArgumentException("On ne peut pas acheter un hôtel " +
                    "sur un terrain qu'on ne possede pas");

        if (possedePasToutesLesCouleurs(T))
            throw new MonopolyException("On ne peut pas acheter de maison si on ne possede pas tous les terrains" +
                    " de la meme couleur");
        T.ajouterHotel();
        if (!regleDes2Maisons(T)) {
            T.retirerHotel();
            throw new MonopolyException("On ne peut pas avoir 2 maisons d'écart " +
                    "entre deux terrains de la meme couleur");
        }
        payerBanque(T.getPrixMaison(), false);
    }

    public void vendreMaison(TerrainConstructible T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Le terrain ne doit pas etre null");
        if (!proprietesPossedees.contains(T))
            throw new IllegalArgumentException("On ne peut pas vendre une maison " +
                    "sur un terrain qu'on ne possede pas");
        if (T.getNbHotel() != 0)
            throw new MonopolyException("Il faut d'abord vendre l'hôtel avant de pouvoir vendre des maisons");
        T.retirerMaison();
        if (!regleDes2Maisons(T)) {
            T.ajouterMaison();
            throw new MonopolyException("On ne peut pas avoir 2 maisons d'écart " +
                    "entre deux terrains de la meme couleur");
        }
        gagnerArgent(T.getPrixMaison() / 2);
    }

    public void vendreHotel(TerrainConstructible T) throws MonopolyException {
        if (T == null)
            throw new IllegalArgumentException("Le terrain ne doit pas etre null");
        if (!proprietesPossedees.contains(T))
            throw new IllegalArgumentException("On ne peut pas vendre un hôtel " +
                    "sur un terrain qu'on ne possede pas");
        T.retirerHotel();
        if (!regleDes2Maisons(T)) {
            T.ajouterHotel();
            throw new MonopolyException("On ne peut pas avoir 2 maisons d'écart " +
                    "entre deux terrains de la meme couleur");
        }
        gagnerArgent(T.getPrixMaison() / 2);
    }


    private boolean regleDes2Maisons(TerrainConstructible terrain) {
        List<Integer> s = getProprietesPossedees()
                .stream()
                .filter(t ->
                        t instanceof TerrainConstructible
                                && ((TerrainConstructible) t).getCouleur().equals(terrain.getCouleur())
                ).map(t -> ((TerrainConstructible) t).getInternalNb()).collect(Collectors.toList());
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

    public void payerPrison() throws MonopolyException {
        Plateau plateau = Plateau.getPlateau();

        if (!getPositionCase().getNom().equals("Prison"))
            throw new MonopolyException("Le joueur n'est pas sur la Case Prison");
        else {
            payerBanque(50, true);
            seDeplacer(plateau.getCase("Simple visite").getId());
        }
    }

}

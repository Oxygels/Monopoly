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

    /**
     * Constructeur standard de la classe Joueur permettant de construire un joueur.
     *
     * @param nom Une chaine de caractere amenee a contenir le nom du joueur a construire.
     */

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

    /**
     * Methode permettant au joueur d'acheter une propriete.
     *
     * @param T Une propriete qui n'est autre que la propriete que le joueur souhaite acheter.
     * @throws MonopolyException Exception relative au fait que le joueur est susceptible, soit d'essayer d'acheter une
     *                           propriete qui a deja ete achetee, soit d'essayer d'acheter une propriete qu'il ne peut
     *                           pas s'offrir par manque d'argent.
     *
     * @see Propriete
     */

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

    /**
     * Methode permettant au joueur de payer un montant a un autre joueur j.
     *
     * @param montant Le montant de la transaction a effectuer.
     * @param j Le joueur qui recoit l'argent de la transaction.
     * @throws FailliteException Exception relative au fait que le joueur peut ne pas posseder la somme d'argent qu'il
     *                           doit au joueur j.
     */

    public void payer(int montant, Joueur j) throws FailliteException {
        if (montantBillet - montant < 0)
            throw new FailliteException("Le joueur n'a pas assez d'argent pour payer");
        if (j == null)
            throw new IllegalArgumentException("Le joueur ne peut etre null");
        j.gagnerArgent(montant);
        montantBillet -= montant;
    }

    /**
     * Methode permettant au joueur de payer un montant a la banque.
     *
     * @param montant Le montant de la transaction a effectuer.
     * @param parcGratuit Variable booleenne permettant de specifier si oui ou non, l'argent ira au parc gratuit
     * @throws FailliteException Exception relative au fait que le joueur peut ne pas posseder la somme d'argent qu'il
     *                           doit au joueur j.
     *
     * @see cases.CaseParkingGratuit
     */

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

    /**
     * Methode permettant au joueur de payer un montant a la banque.
     *
     * @param money Le montant que le joueur est amene a gagner.
     */

    public void gagnerArgent(int money) {
        this.montantBillet += money;
    }

    /**
     * Methode permttant au joueur de  recevoir une carte "Libere de Prison".
     *
     * @param carte Une carte designant la carte "Libere de Prison en question" a attribuer au joueur, du fait qu'il
     *              y ait un nombre limite de ce type de carte.
     *
     * @see CarteLibereDePrison
     */

    public void recevoirCarteLibPrison(CarteLibereDePrison carte) {
        cartesLibPrison.push(carte);
    }

    /**
     * Methode permettant au joueur d'utiliser sa carte "Libere de Prison".
     *
     * @throws MonopolyException Exception relative au fait qu'un joueur ne peut pas utiliser de carte
     *                           "Libere de Prison" s'il ne la possede pas au prealable.
     */

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

    /**
     * Methode permettant de finir le tour d'un joueur et ainsi de passer au tour du joueur suivant.
     *
     * @throws MonopolyException Exception relative au fait qu'un joueur ne peut finir son tour si ce n'est pas a lui
     *                           de jouer.
     */

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

    /**
     * Methode permettant au joueur de se deplacer a une desination.
     *
     * @param destination La desination a laquelle le joueur sera deplace.
     * @throws MonopolyException Exception relative au fait que le deplacement jusqu'a la destination doit etre
     *                           represente par un nombre positif ou nul.
     */

    public void seDeplacer(int destination) throws MonopolyException {
        if (destination < 0)
            throw new IllegalArgumentException("La destination doit etre un nombre positif");
        else
            this.position = destination;
        getPositionCase().action(this);
    }

    /**
     * Methode permettant au joueur d'avancer d'un certain nombre de cases.
     *
     * @param montant Le nombre de case(s) a parcourir par le joueur.
     * @return La case de destination des des.
     * @throws MonopolyException Exception relative au fait que le deplacement jusqu'a la destination doit etre
     *                           represente par un nombre positif ou nul.
     *
     * @see Case
     */

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

    /**
     * Methode permettant au joueur d'acheter/construire une maison.
     *
     * @param T La maison a acheter/construire.
     * @throws MonopolyException Exception relative au fait qu'un joueur ne peut pas acheter de maison s'il ne possede
     *                           pas tous les terrains de la meme couleur et qu'il ne peut pas avoir deux maisons
     *                           d'ecart entre deux terrains de la meme couleur.
     *
     * @see TerrainConstructible
     */

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

    /**
     * Methode permettant au joueur d'acheter/construire un hotel.
     *
     * @param T L'hotel a acheter/construire.
     * @throws MonopolyException Exception relative au fait qu'un joueur ne peut pas acheter de maison s'il ne possede
     *                           pas tous les terrains de la meme couleur et qu'il ne peut pas avoir deux maisons
     *                           d'ecart entre deux terrains de la meme couleur.
     *
     * @see TerrainConstructible
     */

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

    /**
     * Methode permettant au joueur de vendre une maison.
     *
     * @param T La maison a vendre.
     * @throws MonopolyException Exception relative au fait qu'il faut d'abord vendre l'hotel avant de pouvoir vendre
     *                           des maisons ou bien que l'on ne peut pas avoir deux maisons d'ecart entre deux
     *                           terrains de la meme couleur.
     *
     * @see TerrainConstructible
     */

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

    /**
     * Methode permettant au joueur de vendre une hotel.
     *
     * @param T L'hotel a vendre.
     * @throws MonopolyException Exception relative au fait que l'on ne peut pas avoir deux maisons d'ecart entre deux
     *                           terrains de la meme couleur.
     *
     * @see TerrainConstructible
     */

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

    /**
     * Methode permettant d'appliquer la regle des deux maisons.
     *
     * @param terrain Le terrain source necessaire a l'application de la regle des deux maisons.
     * @return Un booleen designant si la regle des deux maisons est appliquee (true) ou non (false).
     *
     * @see TerrainConstructible
     */

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

    /**
     * Methode permettant de verifier si le joueur possede toutes les couleurs ou non
     *
     * @param T Le terrain d'une couleur en particulier.
     * @return Un booleen designant si toutes les couleurs sont possedees (true) ou non (false).
     *
     * @see TerrainConstructible
     */

    public boolean possedePasToutesLesCouleurs(TerrainConstructible T) {
        return Plateau.getPlateau().getCases().stream().anyMatch(c -> {
            return (c instanceof TerrainConstructible)
                    && ((TerrainConstructible) c).getCouleur().equals(T.getCouleur())
                    && ((TerrainConstructible) c).getProprietaire() != this;
        });
    }

    /**
     * Methode permettant au joueur de payer la prison.
     *
     * @throws MonopolyException Exception relative au fait que le joueur ne peut pas payer la prison s'il n'est pas
     *                           sur la case Prison.
     */

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
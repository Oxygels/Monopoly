package cases;

import exception.FailliteException;
import joueur.Joueur;

public abstract class Propriete extends Case {

    private int prix;
    private Joueur proprietaire = null;

    /**
     * Constructeur standard de la classe Propriete permettant de construire
     * une propriete en appelant notamment le constructeur de la classe Case.
     *
     * @param id Un entier faisant office d'identifiant pour la propriete a construire.
     * @param nom Une chaine de caractere amenee a contenir le nom de la propriete a construire.
     * @param prix Un entier amene a contenir la valeur du prix de la propriete a construire.
     *
     * @see Case ; la classe Propriete heritant de la classe Case.
     */

    public Propriete(int id, String nom, int prix) {
        super(id, nom);
        setPrix(prix);
        setProprietaire(proprietaire);
    }

    /**
     * Constructeur standard de la classe Propriete permettant de construire
     * une propriete en appelant le constructeur de la classe Case.
     *
     * @param id Un entier faisant office d'identifiant pour une propriete.
     * @param nom Une chaine de caractere amenee a contenir le nom d'une propriete.
     *
     * @see Case ; la classe Propriete heritant de la classe Case.
     */

    public Propriete(int id, String nom) {
        super(id, nom);
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        if (prix < 0)
            throw new IllegalArgumentException("Prix doit etre positif");
        this.prix = prix;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * Methode publique redefinie par heritage, la classe Propriete heritant de la classe Case.
     *
     * @param joueur Un joueur designant le joueur auquel sera applique l'action.
     * @throws FailliteException Exception relative a la somme d'argent que le joueur peut payer dans l'action.
     */

    @Override
    public void action(Joueur joueur) throws FailliteException {
        if (getProprietaire() == null)
            return;
        if (!getProprietaire().equals(joueur))
            joueur.payer(calculerLoyer(), getProprietaire());
    }

    /**
     * Methode publique abstraite visant a calculer le loyer d'une propriete.
     *
     * @return Une valeur entiere designant le loyer precedemment calcule.
     */

    public abstract int calculerLoyer();

}
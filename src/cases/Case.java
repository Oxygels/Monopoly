package cases;

import exception.MonopolyException;
import joueur.Joueur;

public abstract class Case {

    private int id;
    private String nom;

    /**
     * Constructeur standard de la classe Case permettant de construire une case.
     *
     * @param id Un entier faisant office d'identifiant pour la case a construire.
     * @param nom Une chaine de caractere amenee a contenir le nom de la case a construire.
     */

    public Case(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        if ((nom == null) || (nom.trim().isEmpty()))
            throw new IllegalArgumentException("Le nom ne peut etre vide ou valoir null.");
        else
            this.nom = nom;
    }

    /**
     * Methode redefinie par heritage dans les classes heritant de la classe Case.
     *
     * @param joueur Un joueur designant le joueur auquel sera applique l'action.
     *
     * @throws MonopolyException Exception relative au regles qui seraient
     * susceptibles d'etre enfreintes dans la redefinition de la methode.
     */

    public abstract void action(Joueur joueur) throws MonopolyException;

}
package cases;

import exception.MonopolyException;
import joueur.Joueur;

public abstract class Case {

    private int id;
    private String nom;

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

    public abstract void action(Joueur joueur) throws MonopolyException;

}
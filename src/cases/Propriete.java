package cases;

import exception.ArgentException;
import joueur.Joueur;

public abstract class Propriete extends Case {

    private int prix;
    private Joueur proprietaire = null;

    public Propriete(int id, String nom, int prix) {
        super(id, nom);
        setPrix(prix);
        setProprietaire(proprietaire);
    }

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

    @Override
    public void action(Joueur joueur) throws ArgentException {
        if (getProprietaire() == null)
            return;
        if (!getProprietaire().equals(joueur))
            joueur.payer(calculerLoyer(), getProprietaire());
    }

    public abstract int calculerLoyer();

}
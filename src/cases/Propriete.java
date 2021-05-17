package cases;

import joueur.Joueur;

public abstract class Propriete extends Case {

    private int prix;
    private Joueur proprietaire = null;

    public Propriete(int id, String nom, int prix) {
        super(id, nom);
        setPrix(prix);
        setProprietaire(proprietaire);
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        if (prix < 0)
            this.prix = -prix;
        else
            this.prix = prix;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
            this.proprietaire = proprietaire;
    }

    @Override
    public void action(Joueur joueur) {

    }

    public abstract int calculerLoyer();

}
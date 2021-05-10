package cases;

import joueur.Joueur;

public class Propriete extends Case {

    private int prix;
    private Joueur proprietaire;

    public Propriete(int id, String nom, int prix, Joueur proprietaire) {
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
            this.prix = -prix;
        else
            this.prix = prix;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
        if (proprietaire == null)
            throw new IllegalArgumentException("L'objet Joueur ne peut valoir null.");
        else
            this.proprietaire = proprietaire;
    }

    @Override
    public void action(Joueur joueur) {

    }

    public int calculerLoyer() {
        return 0;
    }

}
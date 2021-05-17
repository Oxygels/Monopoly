package cases;

import exception.ArgentException;
import joueur.Joueur;
import plateau.Plateau;

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
    public void action(Joueur joueur) throws ArgentException {
        if (!getProprietaire().equals(joueur))
            joueur.payer(calculerLoyer(), getProprietaire());
        else if (getProprietaire().equals(null)) {
            Plateau plateau = Plateau.getPlateau();

            joueur.acheterPropriete((Propriete) plateau.getCases().get(joueur.getPosition()), getProprietaire());
        }
    }

    public abstract int calculerLoyer();

}
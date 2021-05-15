package cases;

import joueur.Joueur;

public class TerrainConstructible extends Propriete {

    private int nbMaison;
    private int nbHotel;
    private final int loyerNu;
    private final int loyer1Maison;
    private final int loyer2Maisons;
    private final int loyer3Maisons;
    private final int loyer4Maisons;
    private final int loyerHotel;
    private final int prixMaison;
    private final CouleurCase couleur;

    public TerrainConstructible(int id, String nom, int prix, Joueur proprietaire, int loyerNu,
                                int loyer1Maison, int loyer2Maisons, int loyer3Maisons, int loyer4Maisons,
                                int loyerHotel, int prixMaison, CouleurCase couleur) {
        super(id, nom, prix, proprietaire);
        this.nbMaison = 0;
        this.nbHotel = 0;
        this.loyerNu = loyerNu;
        this.loyer1Maison = loyer1Maison;
        this.loyer2Maisons = loyer2Maisons;
        this.loyer3Maisons = loyer3Maisons;
        this.loyer4Maisons = loyer4Maisons;
        this.loyerHotel = loyerHotel;
        this.prixMaison = prixMaison;
        this.couleur = couleur;
    }

    public CouleurCase getCouleur() {
        return couleur;
    }

    public int getLoyerNu() {
        return loyerNu;
    }

    public int getLoyer1Maison() {
        return loyer1Maison;
    }

    public int getLoyer2Maisons() {
        return loyer2Maisons;
    }

    public int getLoyer3Maisons() {
        return loyer3Maisons;
    }

    public int getLoyer4Maisons() {
        return loyer4Maisons;
    }

    public int getLoyerHotel() {
        return loyerHotel;
    }

    public int getPrixMaison() {
        return prixMaison;
    }

    public int getNbMaison() {
        return nbMaison;
    }

    public void setNbMaison(int nbMaison) {
        if (nbMaison < 0)
            this.nbMaison = -nbMaison;
        else
            this.nbMaison = nbMaison;
    }

    public int getNbHotel() {
        return nbHotel;
    }

    public void setNbHotel(int nbHotel) {
        if (nbHotel < 0)
            this.nbHotel = -nbHotel;
        else
            this.nbHotel = nbHotel;
    }
}
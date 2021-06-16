package cases;

import exception.MonopolyException;

public class TerrainConstructible extends Propriete {

    private int nbMaison;
    private final int loyerNu;
    private final int loyer1Maison;
    private final int loyer2Maisons;
    private final int loyer3Maisons;
    private final int loyer4Maisons;
    private final int loyerHotel;
    private final int prixMaison;
    private final String couleur;

    public TerrainConstructible(int id, String nom, int prix, int loyerNu,
                                int loyer1Maison, int loyer2Maisons, int loyer3Maisons, int loyer4Maisons,
                                int loyerHotel, int prixMaison, String couleur) {
        super(id, nom, prix);
        this.nbMaison = 0;
        this.loyerNu = loyerNu;
        this.loyer1Maison = loyer1Maison;
        this.loyer2Maisons = loyer2Maisons;
        this.loyer3Maisons = loyer3Maisons;
        this.loyer4Maisons = loyer4Maisons;
        this.loyerHotel = loyerHotel;
        this.prixMaison = prixMaison;
        this.couleur = couleur;
    }

    public void init() {
        nbMaison = 0;
    }

    public String getCouleur() {
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

    public int getInternalNb() {
        return nbMaison;
    }

    public int getNbMaison() {
        return nbMaison == 5 ? 0 : nbMaison;
    }

    public int getNbHotel() {
        return nbMaison == 5 ? 1 : 0;
    }

    public void ajouterMaison() throws MonopolyException {
        if (nbMaison == 4)
            throw new MonopolyException("On ne peut plus acheter de maison quand on en a 4");
        if (nbMaison == 5)
            throw new MonopolyException("On ne peut plus acheter de maison quand on a un hôtel");
        nbMaison++;
    }

    public void ajouterHotel() throws MonopolyException {
        if (nbMaison >= 5)
            throw new MonopolyException("On ne peut pas avoir plus qu'un hôtel sur un terrain");
        if (nbMaison <= 3)
            throw new MonopolyException("Il faut déjà avoir 4 maisons sur le terrain pour construire un hôtel");
        nbMaison++;
    }

    public void retirerMaison() throws MonopolyException {
        if (nbMaison == 0)
            throw new MonopolyException("Pas de vente de maison sur un terrain vide");
        nbMaison--;
    }

    public void retirerHotel() throws MonopolyException {
        if (nbMaison != 5)
            throw new MonopolyException("Il faut qu'un hôtel soit construit pour le vendre");
        nbMaison--;
    }

    @Override
    public int calculerLoyer() {
        switch (nbMaison) {
            case 0:
                if (getProprietaire() != null && !getProprietaire().possedePasToutesLesCouleurs(this))
                    return getLoyerNu() * 2;
                return getLoyerNu();
            case 1:
                return getLoyer1Maison();
            case 2:
                return getLoyer2Maisons();
            case 3:
                return getLoyer3Maisons();
            case 4:
                return getLoyer4Maisons();
            case 5:
                return getLoyerHotel();
            default:
                return getLoyerNu();
        }
    }

}
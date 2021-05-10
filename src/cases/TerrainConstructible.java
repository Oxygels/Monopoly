package cases;

public class TerrainConstructible extends Propriete {

    private int nbMaison;
    private int nbHotel;

    public TerrainConstructible(int id, String nom, int nbHotel, int nbMaison) {
        super(id, nom);
        setNbHotel(nbHotel);
        setNbMaison(nbMaison);
    }

    public TerrainConstructible(int id, String nom) {
        super(id, nom);
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
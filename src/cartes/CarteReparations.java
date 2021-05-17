package cartes;

import cases.TerrainConstructible;
import joueur.Joueur;
import plateau.Plateau;

public class CarteReparations extends CartePayer {

    private int montantMaison;
    private int montantHotel;

    public CarteReparations(String enonce, int montantMaison, int montantHotel) {
        super(enonce);
        setMontantMaison(montantMaison);
        setMontantHotel(montantHotel);
    }

    public int getMontantHotel() {
        return montantHotel;
    }

    private void setMontantHotel(int montantHotel) {
        if (montantHotel < 0)
            throw new IllegalArgumentException("montantHotel doit etre un entier positif.");
        else
            this.montantHotel = montantHotel;
    }

    public int getMontantMaison() {
        return montantMaison;
    }

    private void setMontantMaison(int montantMaison) {
        if (montantMaison < 0)
            throw new IllegalArgumentException("montantMaison doit etre un entier positif.");
        else
            this.montantMaison = montantMaison;
    }

    @Override
    public int getMontant() {
        Joueur j = Plateau.getPlateau().getJoueurCourant();
        return j.getProprietesPossedees().stream().map(p -> {
            if (p instanceof TerrainConstructible) {
                // On vérifie qu'ont ait un terrain et pas une gare ou une compagnie
                TerrainConstructible terrain = (TerrainConstructible) p;

                if (terrain.getNbMaison() == 5)
                    return montantHotel;
                else
                    return terrain.getNbMaison() * montantMaison;
            }
            // Sinon on retourne 0
            return 0;
            // Donc pour chaque propriété, on a un nombre correspondant au prix à payer pour chaque propriété
            // Plus qu'à aditionner
        }).reduce(0, Integer::sum);
    }
}
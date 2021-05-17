package cartes;

public abstract class CartePayerRecevoir extends Carte {

    public CartePayerRecevoir(String enonce) {
        super(enonce);
    }

    public abstract int getMontant();

}
package cases;

public class Compagnie extends Propriete {

    private int coefficient;

    public Compagnie(int id, String nom) {
        super(id, nom);
    }

    public Compagnie(int id , String nom , int coefficient){
        super(id,nom);
        setCoefficient(coefficient);
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        if(coefficient < 0)
            this.coefficient = -coefficient;
        else
            this.coefficient = coefficient;
    }
}

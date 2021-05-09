package cartes;

public class CarteAvancerReculer extends CarteDeplacement{

    private int deplacement;

    public CarteAvancerReculer(String enonce, boolean caseDepart) {
        super(enonce, caseDepart);
    }

    public CarteAvancerReculer(String enonce, boolean caseDepart, int deplacement) {
        super(enonce, caseDepart);
        setDeplacement(deplacement);
    }

    public void setDeplacement(int deplacement)
    {
        if(deplacement<0)
            throw new IllegalArgumentException("il faut un nombre positif pour le deplacement");
        else
            this.deplacement=deplacement;
    }

    public int getDeplacement(){
        return this.deplacement;
    }
}

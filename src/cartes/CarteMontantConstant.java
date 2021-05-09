package cartes;

public class CarteMontantConstant extends CartePayerRecevoir{

    private int montant;

    public CarteMontantConstant(String enonce) {
        super(enonce);
    }
    public CarteMontantConstant(String enonce , int montant) {
        super(enonce);
        setMontant(montant);
    }

    private void setMontant(int montant) {
        if(montant < 0)
            throw new  IllegalArgumentException("le montant doit etre un nombre positif");
        else
            this.montant=montant;
    }

   public int getMontant() {
       return this.montant;
   }

}

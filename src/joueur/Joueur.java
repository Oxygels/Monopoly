package joueur;

import cases.Propriete;
import cases.TerrainConstructible;

import java.util.ArrayList;

public class Joueur {

    private String nom;
    private int position;
    private int nbCartesLibPrison;
    private int montantBillet;
    ArrayList<Propriete> proprietesPossede = new ArrayList<Propriete>();

    public Joueur(){}

    public Joueur(String nom, int position, int nbCartesLibPrison, int montantBillet) {
        setNom(nom);
        setPosition(position);
        setNbCartesLibPrison(nbCartesLibPrison);
        setMontantBillet(montantBillet);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException("le nom doit pas etre null ou vide");
        else
            this.nom = nom;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if(position < 0)
            this.position = -position;
        else
            this.position = position;
    }

    public int getNbCartesLibPrison() {
        return nbCartesLibPrison;
    }

    public void setNbCartesLibPrison(int nbCartesLibPrison) {
        if(nbCartesLibPrison < 0)
            this.nbCartesLibPrison = -nbCartesLibPrison;
        else
            this.nbCartesLibPrison = nbCartesLibPrison;
    }

    public int getMontantBillet() {
        return montantBillet;
    }

    public void setMontantBillet(int montantBillet) {
        if(montantBillet < 0)
            this.montantBillet = -montantBillet;
       else
           this.montantBillet = montantBillet;
    }

    public ArrayList<Propriete> getProprietesPossede() {
        return proprietesPossede;
    }

    public void setProprietesPossede(ArrayList<Propriete> proprietesPossede) {
        if (proprietesPossede == null)
            throw new IllegalArgumentException("ArrayList ne doit pas etre null");
        this.proprietesPossede = proprietesPossede;
    }

    //--------------------METHODES METIERS-----------------------------//

    public void acheterTerrain(TerrainConstructible T){

        if(T == null)
            throw new IllegalArgumentException("objet terrain doit pas etre null");

        if(montantBillet - T.getPrix() < 0)
            throw new IllegalArgumentException("pas assez d'argent pour acheter le terrain");
        else
        {
            montantBillet -= T.getPrix();
            proprietesPossede.add(T);
        }

    }

    public void payer(int montant , Joueur j)
    {
        if(montantBillet - montant < 0)
            throw new IllegalArgumentException("le joueur ne possede plus de billets");
        else
        {
            j.montantBillet += montant;
            this.montantBillet -= montant;
        }
    }

    public void gagnerArgent(int money)
    {
        this.montantBillet += money;
    }

    public void recevoirCarteLibPrison()
    {
        nbCartesLibPrison++;
    }

    public void utiliserCarteLibPrison()
    {
        if(nbCartesLibPrison < 0)
            throw new IllegalArgumentException("nombre de cartes est deja a 0");
        else
            nbCartesLibPrison--;
    }

    public void vendreCarteLibPrison(int prix)
    {
        if(nbCartesLibPrison < 0)
            throw new IllegalArgumentException("nombre de cartes prison est deja a 0");
        else{
            nbCartesLibPrison--;
            montantBillet += prix;
        }
    }

    public void finirTour(){
        this.montantBillet += 200;
    }

    public void seDeplacer(int destination){
        if(destination < 0)
            throw new IllegalArgumentException("la destination doit etre un nombre positif");
        else
            this.position=destination;
    }

    public void vendreMaison(TerrainConstructible T)
    {
        if(T==null)
            throw new IllegalArgumentException("objet terrain ne doit pas etre null");
        else if(!proprietesPossede.contains(T))
            throw new IllegalArgumentException("on peut pas vendre une propriete qu'on possede pas");
        else
        {
            montantBillet += T.getPrix();
            proprietesPossede.remove(T);
        }
    }
}

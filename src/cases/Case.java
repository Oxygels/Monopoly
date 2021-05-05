package cases;

public abstract class Case {

    private int id;
    private String nom;

    public  Case(){

    }
    public Case(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if(nom == null || nom.trim().isEmpty())
            throw new IllegalArgumentException(" pas de nom vide ou null");
        else
            this.nom = nom;
    }


    public abstract void action();


}

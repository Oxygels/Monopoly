package ui;

import java.util.ArrayList;

public class UICase {

    public int x1, y1, x2, y2;
    private final ArrayList<Pion> listePions = new ArrayList<Pion>();


    public UICase() {
    }

    public void setCoordonnees(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }


    public void poser(Pion pion) {
        if (pion == null)
            throw new IllegalArgumentException("Le pion ne peu pas être null");

        listePions.add(pion);
    }

    public void enlever(Pion pion) throws Exception {
        if (pion == null)
            throw new IllegalArgumentException("Le pion ne peu pas être null");

        if (!listePions.contains(pion))
            throw new Exception("Le pion n'est pas sur cette case");

        listePions.remove(pion);
    }

    public ArrayList<Pion> getListePions() {
        return listePions;
    }

    public int getNombrePion() {
        return listePions.size();
    }

    public void vider() {
        listePions.clear();
    }
}

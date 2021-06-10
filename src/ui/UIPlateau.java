package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class UIPlateau {

    private static final int NOMBRE_CASES = 40;
    final static String COORDONNEES = "src/data/CoordonneesCases.csv";
    final static String PLATEAU = "file:image/Plateau2.jpg";

    private final HashMap<Integer, UICase> cases = new HashMap<Integer, UICase>();
    private Image imagePlateau = null;
    private final HashMap<String, Image> imagesPions = new HashMap<String, Image>();


    public UIPlateau() {

        for (int i = 0; i < 41; i++)
            cases.put(i, new UICase());

        initImagePlateau(PLATEAU);
        initCoordonnees(COORDONNEES);
        initImagesPions();
    }


    private void initImagesPions() {
        imagesPions.put("Chien", new Image("file:image/Chien.png"));
        imagesPions.put("Bateau", new Image("file:image/Bateau.png"));
        imagesPions.put("Brouette", new Image("file:image/Brouette.png"));
        imagesPions.put("Chapeau", new Image("file:image/Chapeau.png"));
        imagesPions.put("Chat", new Image("file:image/Chat.png"));
        imagesPions.put("Chaussure", new Image("file:image/Chaussure.png"));
        imagesPions.put("DeACoudre", new Image("file:image/DeACoudre.png"));
        imagesPions.put("Voiture", new Image("file:image/Voiture.png"));
    }


    public Image getImage() {
        return imagePlateau;
    }


    private void initImagePlateau(String nomFichierPlateau) {
        try {
            imagePlateau = new Image(nomFichierPlateau);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UICase getCase(int numCase) {
        if (numCase < 0 || numCase > NOMBRE_CASES)
            throw new IllegalArgumentException("Le numéro de la case est incorrect");

        return cases.get(numCase);
    }

    /**
     * initCase lit un fichier au format .csv. Une ligne doit avoir le format "n;x1;y1;x2;y2;" où n,x1,y1,x2,y2
     * sont des entiers. En cas de non respect de ce format, le programme est interrompu.
     *
     * @param nf nom du fichier contenant les coordonnées des cases du plateau. Ces coordonnées
     *           sont celles pour le plateau 800x800 pixels
     *           <p>
     *           YL : --> A remplacer avec vos parser !!! ça c'est moche et ça doit disparaitre !!!
     */
    private void initCoordonnees(String nf) {
        BufferedReader f = null;
        String ligne;

        try {
            f = new BufferedReader(new FileReader(nf));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while ((ligne = f.readLine()) != null) {
                parserCoordonnees(ligne);
            }

            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static final String REGEX_COORDONNEES = "[0-9]+(;[0-9]+){4}";


    /**
     * La méthode parserCoordonnées vérifie que la ligne à parser respecte le bon format. Sinon arrêt
     * du programme en lançant une exception du type Error
     *
     * @param ligne La ligne à parser
     */
    private void parserCoordonnees(String ligne) {
        if (!Pattern.matches(REGEX_COORDONNEES, ligne))
            throw new Error("Format des coordonnées non respecté");


        // Tout est ok pour parser
        int numCase, x1, y1, x2, y2;

        String[] mots = ligne.split(";");
        numCase = Integer.parseInt(mots[0]);
        x1 = Integer.parseInt(mots[1]);
        y1 = Integer.parseInt(mots[2]);
        x2 = Integer.parseInt(mots[3]);
        y2 = Integer.parseInt(mots[4]);

        UICase c = cases.get(numCase);
        c.setCoordonnees(x1, y1, x2, y2);
    }

    public void dessiner(Canvas grillePane) {
        for (int i = 0; i <= NOMBRE_CASES; i++) {
            cases.get(i).vider();
        }

        // TODO: Repositionner les joueurs sur les cases grâce à leur position ou gestion par exception
        // Nécessaire en cas de faillite
        grillePane.getGraphicsContext2D().drawImage(imagePlateau, 0, 0);
        for (int i = 0; i <= NOMBRE_CASES; i++) {
            UICase c = cases.get(i);
            for (int p = 0; p < c.getNombrePion(); p++) {
                Image imagePion = imagesPions.get(c.getListePions().get(p).getNom());
                // Fonctionne pour au moins 4 pions (2 pions en largeur, inf en hauteur)
                grillePane.getGraphicsContext2D().drawImage(imagePion, c.x1 + 30 * (p % 2), c.y1 + 30 * (p / 2));
            }
        }
    }
}

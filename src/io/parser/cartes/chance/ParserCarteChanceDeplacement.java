package io.parser.cartes.chance;

import cartes.CarteAllerA;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChanceDeplacement extends Parser {

    public ParserCarteChanceDeplacement(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        boolean prison = fields[2].contains("Prison");
        Plateau.getPlateau().ajouterCarteChance(new CarteAllerA(
                fields[1],
                prison, // On ne prend pas en compte la case départ,
                Plateau.getPlateau().getCase(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("DEPLACEMENT;.+;.+");
    }
}

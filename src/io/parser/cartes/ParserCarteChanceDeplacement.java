package io.parser.cartes;

import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChanceDeplacement extends Parser {

    public ParserCarteChanceDeplacement(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteChance(new C);
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("DEPLACEMENT;.+;.+");
    }
}

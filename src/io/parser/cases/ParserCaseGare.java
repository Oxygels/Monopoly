package io.parser.cases;

import cases.Gare;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseGare extends Parser {
    public ParserCaseGare(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new Gare(
                Integer.parseInt(fields[0]),
                fields[2],
                200, // CF: Discord YLPBO
                //TODO: Modifier la condition
                null
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;GARE;.+;");
    }
}

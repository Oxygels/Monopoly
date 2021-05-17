package io.parser.cases;

import cases.CaseAllerEnPrison;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseAllerEnPrison extends Parser {
    public ParserCaseAllerEnPrison(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CaseAllerEnPrison(
                Integer.parseInt(fields[0]),
                fields[1]
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;ALLEZ EN PRISON;;;;;;;;;;");
    }
}

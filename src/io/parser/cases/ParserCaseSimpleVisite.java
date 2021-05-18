package io.parser.cases;

import cases.CaseSimpleVisite;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseSimpleVisite extends Parser {
    public ParserCaseSimpleVisite(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CaseSimpleVisite(
                Integer.parseInt(fields[0]),
                "Simple visite"
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;SIMPLE VISITE;;;;;;;;;;");
    }
}

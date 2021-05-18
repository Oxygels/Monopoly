package io.parser.cases;

import cases.CasePrison;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCasePrison extends Parser {
    public ParserCasePrison(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CasePrison(
                Integer.parseInt(fields[0]),
                "Prison"
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;PRISON;;;;;;;;;;");
    }
}


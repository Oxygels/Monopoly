package io.parser.cases;

import cases.CasePayer;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseImpot extends Parser {
    public ParserCaseImpot(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CasePayer(
                Integer.parseInt(fields[0]),
                fields[1],
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;IMPOT SUR LE REVENU;\\d+;");
    }
}

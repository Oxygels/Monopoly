package io.parser.cases;

import cases.CaseRecevoirConstant;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseDepart extends Parser {

    public ParserCaseDepart(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CaseRecevoirConstant(
                Integer.parseInt(fields[0]),
                "Case Départ",
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;CASE DEPART;\\d+;;;;;;;;;");
    }
}

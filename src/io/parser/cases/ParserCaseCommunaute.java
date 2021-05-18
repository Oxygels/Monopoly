package io.parser.cases;

import cases.CasePiocheCommunaute;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseCommunaute extends Parser {

    public ParserCaseCommunaute(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CasePiocheCommunaute(
                Integer.parseInt(fields[0]),
                "Caisse Communauté"
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;CAISSE COMMUNAUTE;;;;;;;;;;");
    }
}

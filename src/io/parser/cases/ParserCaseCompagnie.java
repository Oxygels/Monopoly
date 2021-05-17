package io.parser.cases;

import cases.Compagnie;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseCompagnie extends Parser {

    public ParserCaseCompagnie(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new Compagnie(
                Integer.parseInt(fields[0]),
                fields[2],
                Integer.parseInt(fields[3]),
                null
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;COMPAGNIE;.+;\\d+;;;;;;;;");
    }
}

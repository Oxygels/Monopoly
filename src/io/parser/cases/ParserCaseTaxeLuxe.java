package io.parser.cases;

import cases.CasePayer;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseTaxeLuxe extends Parser {
    public ParserCaseTaxeLuxe(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CasePayer(
                Integer.parseInt(fields[0]),
                fields[1],
                100 // TODO: Mettre dans le .csv
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;TAXE DE LUXE;\\d+;;;;;;;;;");
    }
}

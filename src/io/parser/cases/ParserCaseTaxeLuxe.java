package io.parser.cases;

import cases.CasePayerConstant;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseTaxeLuxe extends Parser {
    public ParserCaseTaxeLuxe(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CasePayerConstant(
                Integer.parseInt(fields[0]),
                "Taxe de Luxe",
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;TAXE DE LUXE;\\d+;;;;;;;;;");
    }
}

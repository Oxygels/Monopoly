package io.parser.cases;

import cases.CasePiocheChance;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseChance extends Parser {

    public ParserCaseChance(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CasePiocheChance(
                Integer.parseInt(fields[0]),
                "Chance"
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;CHANCE;;;;;;;;;;");
    }
}

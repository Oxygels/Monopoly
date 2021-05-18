package io.parser.cartes.chance;

import cartes.CartePayerConstant;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChancePayer extends Parser {
    public ParserCarteChancePayer(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteChance(new CartePayerConstant(
                fields[1],
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("PAYER;.+;\\d+");
    }
}

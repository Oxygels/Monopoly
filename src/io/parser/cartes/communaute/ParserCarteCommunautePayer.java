package io.parser.cartes.communaute;

import cartes.CartePayerConstant;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteCommunautePayer extends Parser {
    public ParserCarteCommunautePayer(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteCommunaute(new CartePayerConstant(
                fields[1],
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("PAYER;.+;\\d+");
    }
}

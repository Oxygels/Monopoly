package io.parser.cartes.communaute;

import cartes.CarteAllerA;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteCommunauteDeplacement extends Parser {
    public ParserCarteCommunauteDeplacement(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        boolean prison = fields[2].contains("Prison");
        Plateau.getPlateau().ajouterCarteCommunaute(new CarteAllerA(
                fields[1],
                prison,
                Plateau.getPlateau().getCase(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("DEPLACEMENT;.+;.+");
    }
}

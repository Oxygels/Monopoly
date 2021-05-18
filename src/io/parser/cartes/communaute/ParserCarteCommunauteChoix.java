package io.parser.cartes.communaute;

import cartes.CarteChoix;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteCommunauteChoix extends Parser {
    public ParserCarteCommunauteChoix(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteCommunaute(new CarteChoix(
                fields[1]
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("CHANCE;.+;\\d+");
    }
}

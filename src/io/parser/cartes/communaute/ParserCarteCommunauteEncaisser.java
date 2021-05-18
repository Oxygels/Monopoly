package io.parser.cartes.communaute;

import cartes.CarteRecevoirConstant;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteCommunauteEncaisser extends Parser {
    public ParserCarteCommunauteEncaisser(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteCommunaute(new CarteRecevoirConstant(
                fields[1],
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("ENCAISSER;.+;\\d+");
    }
}

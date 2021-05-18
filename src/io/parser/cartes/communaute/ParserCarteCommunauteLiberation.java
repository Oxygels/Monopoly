package io.parser.cartes.communaute;

import cartes.CarteLibereDePrison;
import cartes.CategorieCarte;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteCommunauteLiberation extends Parser {
    public ParserCarteCommunauteLiberation(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteCommunaute(new CarteLibereDePrison(
                fields[1],
                CategorieCarte.Communaute
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("LIBERATION;.+");
    }
}

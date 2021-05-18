package io.parser.cartes.chance;

import cartes.CarteLibereDePrison;
import cartes.CategorieCarte;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChanceLiberation extends Parser {

    public ParserCarteChanceLiberation(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteChance(new CarteLibereDePrison(
                fields[1],
                CategorieCarte.Chance
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("LIBERATION;.+");
    }
}

package io.parser.cartes.chance;

import cartes.CarteRecevoirConstant;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChanceEncaisser extends Parser {
    public ParserCarteChanceEncaisser(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteChance(new CarteRecevoirConstant(
                fields[1],
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("ENCAISSER;.+;\\d+");
    }
}

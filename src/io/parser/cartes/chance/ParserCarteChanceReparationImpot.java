package io.parser.cartes.chance;

import cartes.CarteReparations;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChanceReparationImpot extends Parser {
    public ParserCarteChanceReparationImpot(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteChance(new CarteReparations(
                fields[1],
                Integer.parseInt(fields[2]),
                Integer.parseInt(fields[3])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("(REPARATIONS|IMPOTS);.+;\\d+;\\d+");
    }
}

package io.parser.cartes.chance;

import cartes.CarteAvancerReculer;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteChanceDeplacementRelatif extends Parser {
    public ParserCarteChanceDeplacementRelatif(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteChance(new CarteAvancerReculer(
                fields[1],
                false,
                Integer.parseInt(fields[3])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("DEPLACEMENT RELATIF;.+;-?\\d+");
    }
}

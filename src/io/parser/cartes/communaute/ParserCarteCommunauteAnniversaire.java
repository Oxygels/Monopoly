package io.parser.cartes.communaute;

import cartes.CarteAnniversaire;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCarteCommunauteAnniversaire extends Parser {
    public ParserCarteCommunauteAnniversaire(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCarteCommunaute(new CarteAnniversaire(
                fields[1],
                Integer.parseInt(fields[2])
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("ANNIVERSAIRE;.+;\\d+");
    }
}

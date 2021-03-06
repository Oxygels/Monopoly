package io.parser.cases;

import cases.CaseParkingGratuit;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseParkingGratuit extends Parser {
    public ParserCaseParkingGratuit(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new CaseParkingGratuit(
                Integer.parseInt(fields[0]),
                "Parking Gratuit"
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;PARKING GRATUIT;;;;;;;;;;");
    }
}

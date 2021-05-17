package io.parser.cases;

import cases.TerrainConstructible;
import io.parser.Parser;
import plateau.Plateau;

public class ParserCaseTerrain extends Parser {

    public ParserCaseTerrain(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
        Plateau.getPlateau().ajouterCase(new TerrainConstructible(
                Integer.parseInt(fields[0]),
                fields[2],
                Integer.parseInt(fields[4]),
                Integer.parseInt(fields[6]),
                Integer.parseInt(fields[7]),
                Integer.parseInt(fields[8]),
                Integer.parseInt(fields[9]),
                Integer.parseInt(fields[10]),
                Integer.parseInt(fields[11]),
                Integer.parseInt(fields[5]),
                fields[3]
        ));
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;TERRAIN CONSTRUCTIBLE;.+;.+;\\d+;\\d+;\\d+;\\d+;\\d+;\\d+;\\d+;\\d+");
    }
}

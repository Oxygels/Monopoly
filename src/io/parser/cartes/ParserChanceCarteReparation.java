package io.parser.cartes;

import io.parser.Parser;

public class ParserChanceCarteReparation extends Parser {
    public ParserChanceCarteReparation(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {

    }

    @Override
    public boolean saitParser(String ligne) {
        return false;
    }
}

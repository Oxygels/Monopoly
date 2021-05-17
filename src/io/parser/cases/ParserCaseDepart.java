package io.parser.cases;

import io.parser.Parser;

public class ParserCaseDepart extends Parser {

    public ParserCaseDepart(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String ligne) throws Exception {
        String[] fields = ligne.split(";");
//        Plateau.getPlateau().ajouterCase(new CaseDepart(
//                Integer.parseInt(fields[0]),
//                fields[1],
//                Integer.parseInt(fields[2])));
        // TODO: Utiliser CaseRecevoir
    }

    @Override
    public boolean saitParser(String ligne) {
        return ligne.matches("\\d+;CASE DEPART;\\d+;;;;;;;;;");
    }
}

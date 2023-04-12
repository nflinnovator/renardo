package org.nfl.renardo.calculation;

interface Operation {

    int make(Operand premier, Operand deuxieme);

    String format(Operand premier, Operand deuxieme);

}

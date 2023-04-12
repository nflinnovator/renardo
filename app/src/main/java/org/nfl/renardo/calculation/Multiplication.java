package org.nfl.renardo.calculation;

class Multiplication implements Operation {

    @Override
    public int make(Operand premier, Operand deuxieme) {
        return premier.getValue() * deuxieme.getValue();
    }

    @Override
    public String format(Operand premier, Operand deuxieme) {
    	int max = Math.max(premier.getValue(),deuxieme.getValue());
    	int min = Math.min(premier.getValue(),deuxieme.getValue());
        return max + " x " + min;
    }

}

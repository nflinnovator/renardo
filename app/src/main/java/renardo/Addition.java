package renardo;

class Addition implements Operation {

    @Override
    public int make(Operand premier, Operand deuxieme) {
        return premier.getValue() + deuxieme.getValue();
    }

    @Override
    public String format(Operand premier, Operand deuxieme) {
        return premier.getValue() + " + " + deuxieme.getValue();
    }

}

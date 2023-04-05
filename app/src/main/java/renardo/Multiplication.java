package renardo;

class Multiplication implements Operation {

    @Override
    public int make(Operand premier, Operand deuxieme) {
        return premier.getValue() * deuxieme.getValue();
    }

    @Override
    public String format(Operand premier, Operand deuxieme) {
        return premier.getValue() + " x " + deuxieme.getValue();
    }

}

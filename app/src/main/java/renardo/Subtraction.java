package renardo;

class Subtraction implements Operation {

    @Override
    public int make(Operand premier, Operand deuxieme) {
        return Math.abs(premier.getValue() - deuxieme.getValue());
    }

    @Override
    public String format(Operand premier, Operand deuxieme) {
        final int max = Math.max(premier.getValue(), deuxieme.getValue());
        final int min = Math.min(premier.getValue(), deuxieme.getValue());
        return max + " - " + min;
    }

}

package renardo;

class Division implements Operation {

    @Override
    public int make(Operand premier, Operand deuxieme) {
        final int max = Math.max(premier.getValue(), deuxieme.getValue());
        final int min = Math.min(premier.getValue(), deuxieme.getValue());
        int result;
        try {
            result = max % min == 0 ? max / min : 0;
        } catch (ArithmeticException exception) {
            result = 0;
        }
        return result;
    }

    @Override
    public String format(Operand premier, Operand deuxieme) {
        final int max = Math.max(premier.getValue(), deuxieme.getValue());
        final int min = Math.min(premier.getValue(), deuxieme.getValue());
        return max + " : " + min;
    }

}

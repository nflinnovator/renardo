package renardo;

import java.util.List;

abstract class Calculation {

    protected int[] suite;
    protected List<Operand> result;

    static Calculation basic(int[] suite) {
        final Calculation calculation = new BasicCalculation(suite);
        calculation.perform();
        return calculation;
    }

    static Calculation linear(int[] suite) {
        final Calculation calculation = new GenericCalculation(suite);
        calculation.perform();
        return calculation;
    }

    static Calculation orthogonal(int[] suite) {
        final Calculation calculation = new GenericCalculation(suite);
        calculation.perform();
        return calculation;
    }

    abstract void perform();

    List<Operand> getResult() {
        return result;
    }

    CalculationIterator createIterator() {
        return new CalculationIterator(result);
    }

}

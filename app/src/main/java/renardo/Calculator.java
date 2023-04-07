package renardo;

import java.util.ArrayList;
import java.util.List;

class Calculator {

    private final int[] suite;
    private List<Calculation> calculations;

    Calculator(int[] suite) {
        this.suite = suite;
    }

    void calculate() {
        if (suite.length == 2) {
            calculations = new ArrayList<>(1);
            calculations.add(Calculation.basic(suite));
        } else if (suite.length == 3) {
            calculations = new ArrayList<>(1);
            calculations.add(Calculation.linear(suite));
        } else if (suite.length >= 4) {
            calculations = new ArrayList<>(2);
            calculations.add(Calculation.linear(suite));
            calculations.add(Calculation.orthogonal(suite));
        } else {
            throw new IllegalArgumentException("A Suite must have at least two elements");
        }
    }

    List<Calculation> getCalculations() {
        return calculations;
    }

}

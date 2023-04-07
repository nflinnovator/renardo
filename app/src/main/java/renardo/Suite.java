package renardo;

import java.util.ArrayList;
import java.util.List;

class Suite {
    private final int[] value;
    private List<Calculator> calculators;
    private final int numberOfShifts;
    private int[] shift;

    private static final int BASE = 2;
    private static final int BASIC_NUMBER_OF_SHIFTS = 1;

    private Suite(int[] value) {
        this.value = value;
        numberOfShifts = this.value.length == BASE ? BASIC_NUMBER_OF_SHIFTS : this.value.length;
    }

    static Suite of(int[] value) {
        if (value.length < BASE)
            throw new IllegalArgumentException("A Suite must have at least two elements");
        return new Suite(value);
    }

    void calculate() {
        calculators = new ArrayList<>();
        shift = new int[value.length];
        for (int counter = 0; counter < numberOfShifts; counter++) {
            shift(counter);
            final Calculator calculator = new Calculator(shift);
            calculator.calculate();
            calculators.add(calculator);
        }
    }

    void shift(int round) {
        if (round >= 0 && round < numberOfShifts) {
            if (round == 0)
                shift = value;
            else {
                final int firstElement = shift[0];
                for (int i = 0; i < shift.length - 1; i++) {
                    shift[i] = shift[i + 1];
                }
                shift[shift.length - 1] = firstElement;
            }
        } else {
            throw new IllegalArgumentException("shift has an invalid argument");
        }
    }

    int[] getValue() {
        return value;
    }

    List<Operand> getResult() {
        List<Operand> result = new ArrayList<>();
        for (Calculator calculator : calculators) {
            for (Calculation calculation : calculator.getCalculations()) {
                final CalculationIterator iterator = calculation.createIterator();
                result.addAll(iterator.getResult());
            }
        }
        return result;

    }

    List<Integer> getAllValues() {
        List<Integer> allValues = new ArrayList<>();
        for (Operand operand : getResult()) {
            allValues.add(operand.getValue());
        }
        return allValues;
    }

    List<Integer> getAllValidValues() {
        List<Integer> allValidValues = new ArrayList<>();
        for (Calculator calculator : calculators) {
            for (Calculation calculation : calculator.getCalculations()) {
                final CalculationIterator iterator = calculation.createIterator();
                allValidValues.addAll(iterator.getAllValidValues());
            }
        }
        return allValidValues;
    }

    List<String> getAllOperations() {
        List<String> allOperations = new ArrayList<>();
        for (Operand operand : getResult()) {
            allOperations.add(operand.getOperation());
        }
        return allOperations;
    }

    List<String> getAllValidOperations() {
        List<String> allValidOperations = new ArrayList<>();
        for (Calculator calculator : calculators) {
            for (Calculation calculation : calculator.getCalculations()) {
                final CalculationIterator iterator = calculation.createIterator();
                allValidOperations.addAll(iterator.getAllValidOperations());
            }
        }
        return allValidOperations;
    }

}

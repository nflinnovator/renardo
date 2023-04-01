package renardo;

import java.util.ArrayList;
import java.util.List;

class Couple {

    private int firstNumber;
    private int secondNumber;

    Couple(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    int[] compute() {
        int[] computation = new int[4];
        computation[0] = add();
        computation[1] = subtract();
        computation[2] = multiply();
        computation[3] = divide();
        return normalize(computation);
    }

    private static int[] normalize(int[] calculation) {
        List<Integer> cleanCalculationList = new ArrayList<>();
        for (int i = 0; i < calculation.length; i++) {
            if (calculation[i] > 0) {
                cleanCalculationList.add(calculation[i]);
            }
        }
        int[] cleanCalculation = new int[cleanCalculationList.size()];
        for (int i = 0; i < cleanCalculationList.size(); i++) {
            cleanCalculation[i] = cleanCalculationList.get(i);
        }
        return cleanCalculation;
    }

    private int add() {
        return this.firstNumber + this.secondNumber;
    }

    private int subtract() {
        return Math.abs(this.firstNumber - this.secondNumber);
    }

    private int multiply() {
        return this.firstNumber * this.secondNumber;
    }

    private int divide() {
        int result;
        int max = Math.max(this.firstNumber, this.secondNumber);
        int min = Math.min(this.firstNumber, this.secondNumber);
        try {
            result = max % min == 0 ? max / min : 0;
        } catch (ArithmeticException exception) {
            result = 0;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Couple))
            return false;
        if (this.getClass() != o.getClass())
            return false;
        Couple couple = (Couple) o;
        return this.firstNumber == couple.firstNumber
                && this.secondNumber == couple.secondNumber
                || this.secondNumber == couple.firstNumber
                        && this.firstNumber == couple.secondNumber;
    }

    @Override
    public int hashCode() {
        return 7 * this.firstNumber + 9 * this.secondNumber;
    }

    @Override
    public String toString() {
        return "(" + this.firstNumber + " . " + this.secondNumber + ")";
    }

}

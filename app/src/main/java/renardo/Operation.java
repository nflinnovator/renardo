package renardo;

class Operation {
    private int firstNumber;
    private int secondNumber;
    private char operand;
    private double result;

    private Operation(int firstNumber, int secondNumber, char operand, double result) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operand = operand;
        this.result = result;
    }

    static Operation add(int augend, int addend) {
        return new Operation(augend, addend, '+', (augend + addend));
    }

    static Operation subtract(int subtrahend, int minuend) {
        return new Operation(subtrahend, minuend, '-', (subtrahend - minuend));
    }

    static Operation multiply(int multiplicand, int multiplier) {
        return new Operation(multiplicand, multiplier, '*', (multiplicand * multiplier));
    }

    static Operation divide(int dividend, int diviser) {
        double result = (double) dividend / (double) diviser;
        return new Operation(dividend, diviser, ':', (result));
    }

    int getFirstNumber() {
        return firstNumber;
    }

    int getSecondNumber() {
        return secondNumber;
    }

    char getOperand() {
        return operand;
    }

    double getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Operation))
            return false;
        if (this.getClass() != o.getClass())
            return false;
        Operation operation = (Operation) o;
        return (firstNumber == operation.getFirstNumber() && secondNumber == operation.getSecondNumber()
                || secondNumber == operation.getFirstNumber() && firstNumber == operation.getSecondNumber())
                && operand == operation.getOperand()
                && result == operation.getResult();
    }

    @Override
    public int hashCode() {
        return 7 * firstNumber + 9 * secondNumber + 11 * Integer.valueOf(operand) + 13 * (int) result;
    }

    @Override
    public String toString() {
        return firstNumber + " " + operand + " " + secondNumber + " = " + result;
    }

}

package renardo;

import java.util.ArrayList;
import java.util.List;

class Calculation {

    private int[] suite;

    private Calculation(int[] suite) {
        this.suite = suite;
    }

    static Calculation of(int[] suite) {
        return new Calculation(suite);
    }

    int[] perform() {
        List<Operation> operations = calculate();
        List<Operation> resultWithPositiveIntegers = removeNonPositiveIntegers(operations);
        int[] resultWithNoDuplicates = removeDuplication(resultWithPositiveIntegers);
        return resultWithNoDuplicates;
    }

    private List<Operation> calculate() {
        List<Operation> operations = new ArrayList<>(8);
        operations.add(Operation.add(suite[0], suite[1]));
        operations.add(Operation.subtract(suite[0], suite[1]));
        operations.add(Operation.multiply(suite[0], suite[1]));
        operations.add(Operation.divide(suite[0], suite[1]));
        operations.add(Operation.add(suite[1], suite[0]));
        operations.add(Operation.subtract(suite[1], suite[0]));
        operations.add(Operation.multiply(suite[1], suite[0]));
        operations.add(Operation.divide(suite[1], suite[0]));
        return operations;
    }

    private List<Operation> removeNonPositiveIntegers(List<Operation> operations) {
        List<Operation> positiveIntegerOperations = new ArrayList<>();
        for (Operation each : operations) {
            double result = each.getResult();
            if (result > 0 && Math.floor(result) == result) {
                positiveIntegerOperations.add(each);
            }
        }
        return positiveIntegerOperations;
    }

    private int[] removeDuplication(List<Operation> positiveIntegerOperations) {
        List<Operation> operationsWithNoDuplicates = new ArrayList<>();
        for (Operation each : positiveIntegerOperations) {
            if (!operationsWithNoDuplicates.contains(each))
                operationsWithNoDuplicates.add(each);
        }
        int[] resultWithNoDuplicates = new int[operationsWithNoDuplicates.size()];
        for (int i = 0; i < resultWithNoDuplicates.length; i++) {
            resultWithNoDuplicates[i] = (int) operationsWithNoDuplicates.get(i).getResult();
        }
        return resultWithNoDuplicates;
    }

}

package renardo;

import java.util.ArrayList;
import java.util.List;

class Suite {
    private final int[] value;
    private Calculation calculation;

    Suite(int[] value) {
        this.value = value;

    }

    void calculate() {
        selectMode();
        calculation.perform();
    }

    private void selectMode() {
        calculation = value.length == 2 ? new BasicCalculation(value) : new GenericCalculation(value);
    }

    int[] getValues() {
        List<Integer> listOfValues = new ArrayList<>();
        for (Operand each : calculation.getResults()) {
            if (each.getValue() > 99 && each.getValue() < 1000)
                listOfValues.add(each.getValue());
        }
        int[] results = RenardoUtil.toIntegerArray(listOfValues);
        return results;
    }

    String[] getOperations() {
        List<String> listOfOperations = new ArrayList<>();
        for (Operand each : calculation.getResults()) {
            if (each.getValue() > 99 && each.getValue() < 1000)
                listOfOperations.add(each.getOperation());
        }
        String[] operations = RenardoUtil.toStringArray(listOfOperations);
        return operations;
    }

    int[] getValue() {
        return value;
    }

    Calculation getCalculation() {
        return calculation;
    }

}

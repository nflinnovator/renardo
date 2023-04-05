package renardo;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

abstract class Calculation {

    protected int[] suite;
    protected List<Operand> results;
    protected Iterator<Operand> iterator;

    static BasicCalculation ofASuiteOfTwoNumbers(int[] suite) {
        return new BasicCalculation(suite);
    }

    static GenericCalculation ofASuiteOfMoreThanTwoNumbers(int[] suite) {
        return new GenericCalculation(suite);
    }

    abstract void perform();

    List<Operand> getResults() {
        return results;
    }

    Iterator<Operand> createIterator() {
        return results.iterator();
    }

    int[] getValues() {
        final List<Integer> listOfResults = new ArrayList<>(results.size());
        iterator = createIterator();
        while (iterator.hasNext()) {
            Operand each = (Operand) iterator.next();
            listOfResults.add(each.getValue());
        }
        int[] values = RenardoUtil.toIntegerArray(listOfResults);
        return values;
    }

    String[] getOperations() {
        final List<String> listOfOperations = new ArrayList<>(results.size());
        iterator = createIterator();
        while (iterator.hasNext()) {
            Operand each = (Operand) iterator.next();
            listOfOperations.add(each.getOperation());
        }
        String[] operations = RenardoUtil.toStringArray(listOfOperations);
        return operations;
    }

}

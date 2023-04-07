package renardo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CalculationIterator implements OperandIterator {

    private List<Operand> result;
    private int position;
    private Iterator<Operand> iterator;

    private static final int MIN_VALID_RESULT = 100;
    private static final int MAX_VALID_RESULT = 999;

    CalculationIterator(List<Operand> result) {
        this.result = result;
    }

    @Override
    public boolean hasNext() {
        return position >= result.size() ? false : true;
    }

    @Override
    public Operand next() {
        final Operand operand = result.get(position);
        ++position;
        return operand;
    }

    @Override
    public List<Integer> getAllValues() {
        List<Integer> allValues = new ArrayList<>();
        iterator = result.iterator();
        while (iterator.hasNext()) {
            allValues.add(iterator.next().getValue());
        }
        return allValues;
    }

    @Override
    public List<String> getAllOperations() {
        List<String> allOperations = new ArrayList<>();
        iterator = result.iterator();
        while (iterator.hasNext()) {
            allOperations.add(iterator.next().getOperation());
        }
        return allOperations;
    }

    @Override
    public List<Integer> getAllValidValues() {
        List<Integer> allValidValues = new ArrayList<>();
        iterator = result.iterator();
        while (iterator.hasNext()) {
            final Operand each = iterator.next();
            final int value = each.getValue();
            if (value >= MIN_VALID_RESULT && value <= MAX_VALID_RESULT) {
                allValidValues.add(value);
            }
        }
        return allValidValues;
    }

    @Override
    public List<String> getAllValidOperations() {
        List<String> allValidOperations = new ArrayList<>();
        iterator = result.iterator();
        while (iterator.hasNext()) {
            final Operand each = iterator.next();
            final int value = each.getValue();
            final String operation = each.getOperation();
            if (value >= MIN_VALID_RESULT && value <= MAX_VALID_RESULT) {
                allValidOperations.add(operation);
            }
        }
        return allValidOperations;
    }

    List<Operand> getResult() {
        return result;
    }

}

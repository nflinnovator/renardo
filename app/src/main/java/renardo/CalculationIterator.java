package renardo;

import java.util.List;

class CalculationIterator implements Iterator {

    private List<Operand> result;
    private int position;

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

}

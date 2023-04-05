package renardo;

import java.util.ArrayList;
import java.util.List;

class GenericCalculation extends Calculation {

    private Suite reducer;
    private Operand factor;
    private List<Couple> composition;

    GenericCalculation(int[] suite) {
        this.suite = suite;
    }

    @Override
    public void perform() {
        decompose();
        reduce();
        factorize();
        compose();
        merge();
    }

    private void decompose() {
        int[] reducerValue = new int[suite.length - 1];
        for (int i = 0; i < reducerValue.length; i++) {
            reducerValue[i] = suite[i];
        }
        reducer = new Suite(reducerValue);
    }

    private void reduce() {
        results = new ArrayList<>();
        reducer.calculate();
        results.addAll(reducer.getCalculation().getResults());
    }

    private void factorize() {
        factor = new Operand(suite[suite.length - 1]);
    }

    private void compose() {
        composition = new ArrayList<>();
        List<Operand> reducerResults = reducer.getCalculation().getResults();
        for (Operand each : reducerResults) {
            composition.add(new Couple(each, factor));
        }
    }

    private void merge() {
        for (Couple each : composition) {
            results.addAll(each.calculate());
        }
    }

}

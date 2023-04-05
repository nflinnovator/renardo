package renardo;

import java.util.ArrayList;
import java.util.List;

class BasicCalculation extends Calculation {

    private List<Operand> transformation;

    BasicCalculation(int[] suite) {
        this.suite = suite;
    }

    @Override
    public void perform() {
        transform();
        calculate();
    }

    private void transform() {
        transformation = new ArrayList<>(suite.length);
        for (int i = 0; i < suite.length; i++) {
            transformation.add(new Operand(suite[i]));
        }
    }

    private void calculate() {
        results = new ArrayList<>();
        final Couple couple = new Couple(transformation.get(0), transformation.get(1));
        results.addAll(couple.calculate());
    }

}

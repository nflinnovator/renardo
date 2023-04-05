package renardo;

import java.util.ArrayList;
import java.util.List;

class Couple {

    private Operand premier;
    private Operand deuxieme;

    Couple(Operand premier, Operand deuxieme) {
        this.premier = premier;
        this.deuxieme = deuxieme;
    }

    List<Operand> calculate() {
        List<Operand> computation = new ArrayList<>();
        computation.add(make(new Addition()));
        computation.add(make(new Subtraction()));
        computation.add(make(new Multiplication()));
        final Operand division = make(new Division());
        if (division.getValue() > 0)
            computation.add(division);
        return computation;
    }

    private Operand make(Operation operation) {
        final int result = operation.make(premier, deuxieme);
        final String format = operation.format(premier, deuxieme);
        final String value = buildOperation(result, format);
        return new Operand(result, value);
    }

    private String buildOperation(int result, String format) {
        String value = result + " = " + format;
        if (!premier.getOperation().isEmpty()) {
            value += "," + premier.getOperation();
        }
        if (!deuxieme.getOperation().isEmpty()) {
            value += "," + deuxieme.getOperation();
        }
        return value;
    }

}

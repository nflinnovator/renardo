package renardo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test void numberOfPossibleResults() {
        int [] numbers = {2,3};
        int target = 6;
        Calculator calculator = new Calculator(numbers,target);
        int result = calculator.numberOfResults();
        assertEquals(1, result);
    }
    
}

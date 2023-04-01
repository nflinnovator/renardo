package renardo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Renardo Calculation Test Case")
public class RenardoTest {

    @DisplayName("Perform all arithmetic operations on a suite of 2 numbers")
    @Test
    void calculationOfTwoNumbers() {
        int[] value = new int[] { 10, 25 };
        Suite suite = Suite.of(value);
        int[] result = suite.calculate();
        assertArrayEquals(new int[] { 250 }, result);
        value = new int[] { 9, 8 };
        suite = Suite.of(value);
        result = suite.calculate();
        assertArrayEquals(new int[] {}, result);
        value = new int[] { 9, 100 };
        suite = Suite.of(value);
        result = suite.calculate();
        assertArrayEquals(new int[] { 109, 900 }, result);
    }

    @Test
    void arithmeticOperation() {
        Operation operation = Operation.add(3, 2);
        assertEquals(5, operation.getResult());
        operation = Operation.subtract(3, 2);
        assertEquals(1, operation.getResult());
        operation = Operation.multiply(3, 2);
        assertEquals(6, operation.getResult());
        operation = Operation.divide(3, 2);
        assertEquals(1.5, operation.getResult());
        operation = Operation.divide(3, 0);
        assertEquals(Double.POSITIVE_INFINITY, operation.getResult());
    }

    @DisplayName("Perform all arithmetic operations on a suite of 3 numbers")
    @Test
    void calculationOfThreeNumbers() {
        int[] suite = new int[] { 9, 100, 25 };
        Calculation calculation = Calculation.of(suite);
        int[] result = calculation.perform();
        assertArrayEquals(new int[] { 134, 925, 875, 116 }, result);
    }

}

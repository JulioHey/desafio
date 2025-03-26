package desafiotinnova.exercicio4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultipleSumCalcTest {
        @Test
    public void testCalcSumMulti3Or5() {
        // Testing calcSumMulti3Or5 with known values

        // Test case 1: Sum of multiples of 3 or 5 below 10
        int result = MultipleSumCalc.calcSumMulti3Or5(10);
        assertEquals(23, result); // 3 + 5 + 6 + 9 = 23

        // Test case 2: Sum of multiples of 3 or 5 below 20
        result = MultipleSumCalc.calcSumMulti3Or5(20);
        assertEquals(78, result); // 3 + 5 + 6 + 9 + 10 + 12 + 15 + 18 = 78

        // Test case 3: Sum of multiples of 3 or 5 below 100
        result = MultipleSumCalc.calcSumMulti3Or5(100);
        assertEquals(2318, result); // Sum of all multiples of 3 or 5 below 100

        // Test case 4: Edge case with n = 0 (no multiples of 3 or 5)
        result = MultipleSumCalc.calcSumMulti3Or5(0);
        assertEquals(0, result); // No numbers below 0, sum should be 0

        // Test case 5: Edge case with n = 3 (only 3 is a multiple of 3 or 5)
        result = MultipleSumCalc.calcSumMulti3Or5(3);
        assertEquals(0, result); // Only 3 is a multiple of 3 and less than 3

        // Test case 6: Edge case with n = 5 (only 3 and 5 are multiples)
        result = MultipleSumCalc.calcSumMulti3Or5(5);
        assertEquals(3, result); // 3 + 5 = 8
    }
}

package desafiotinnova.exercicio3;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class FactorialTest {
        @Test
    public void testFactorialCalculation() {
        assertEquals(BigInteger.ONE, Factorial.calcFactorial(0));

        assertEquals(BigInteger.ONE, Factorial.calcFactorial(1));

        assertEquals(BigInteger.valueOf(120), Factorial.calcFactorial(5));

        assertEquals(BigInteger.valueOf(3628800), Factorial.calcFactorial(10));

        assertEquals(new BigInteger("2432902008176640000"), Factorial.calcFactorial(20));
    }
}

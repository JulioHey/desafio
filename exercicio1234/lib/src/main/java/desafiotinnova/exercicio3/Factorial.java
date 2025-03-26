package desafiotinnova.exercicio3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factorial {
   private static List<BigInteger> memo = new ArrayList<>(Arrays.asList(BigInteger.ONE,BigInteger.ONE));

    public static BigInteger calcFactorial(int n) {
        if (memo.size() > n) {
            return memo.get(n);
        }

        int size = memo.size();
        BigInteger result = memo.get(size - 1); 

        for (int i = size; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
            memo.add(result);
        }


        return result;
    }
}

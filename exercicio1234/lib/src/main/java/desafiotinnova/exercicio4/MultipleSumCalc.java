package desafiotinnova.exercicio4;

import java.util.HashMap;
import java.util.Map;

public class MultipleSumCalc {
    public static Map<Integer, Integer> memo = new HashMap<>();
    
    public static int calcSumMulti3Or5(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int sum3 = sumOfMultiples(n - 1, 3);
        int sum5 = sumOfMultiples(n - 1, 5);
        int sum15 = sumOfMultiples(n - 1, 15);

        int result = sum3 + sum5 - sum15;
        
        memo.put(n, result);

        return result;
    }

    private static int sumOfMultiples(int limit, int x) {
        int p = (limit) / x;  
        return x * p * (p + 1) / 2; 
    }
}

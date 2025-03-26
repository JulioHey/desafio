package desafiotinnova.exercicio2;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class BubbleSortTest {
    @Test
    public void testBubbleSort() {
        // Listas de teste
        List<Integer> input1 = Arrays.asList(5, 3, 2, 4, 7, 1, 0, 6);
        List<Integer> input2 = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> input3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> input4 = Arrays.asList(4, 2, 2, 8, 3, 3, 1);
        List<Integer> input5 = Arrays.asList(42);
        List<Integer> input6 = Arrays.asList();

        // Listas esperadas (ordenadas)
        List<Integer> expected1 = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
        List<Integer> expected2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> expected3 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected4 = Arrays.asList(1, 2, 2, 3, 3, 4, 8);
        List<Integer> expected5 = Arrays.asList(42);
        List<Integer> expected6 = Arrays.asList();

        // Testa cada caso
        assertSorted(input1, expected1);
        assertSorted(input2, expected2);
        assertSorted(input3, expected3);
        assertSorted(input4, expected4);
        assertSorted(input5, expected5);
        assertSorted(input6, expected6);
    }

    private void assertSorted(List<Integer> input, List<Integer> expected) {
        BubbleSort exercicio = new BubbleSort(input);
        exercicio.bubbleSort();
        assertEquals(expected, exercicio.list);
    }
}

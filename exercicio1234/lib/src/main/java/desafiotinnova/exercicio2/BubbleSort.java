package desafiotinnova.exercicio2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BubbleSort {
    List<Integer> list = new ArrayList<>(Arrays.asList(5, 3, 2, 4, 7, 1, 0, 6));

    public BubbleSort() {
    }

    public BubbleSort(List<Integer> list) {
        this.list = list;
    }

    public void bubbleSort() {
        int n = list.size();
        boolean isSorted;

        for (int i = 0; i < n - 1; i++) {
            isSorted = true;

            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    this.swap(j, j + 1);

                    isSorted = false;
                }
            }

            // Caso não tenha tido troca no loop o algoritmo pode parar
            if (isSorted)
                break;
        }
    }

    private void swap(int i, int j) {
        int aux = list.get(i);
        list.set(i, list.get(j));
        list.set(j, aux);
    }
}

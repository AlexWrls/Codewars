package algyrhythms.solution.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Сортировка слиянием
 * <p>
 * Это алгоритм сортировки по принципу «разделяй и властвуй», который эффективно сортирует элементы, разделяя входные
 * данные на более мелкие подзадачи, сортируя их, а затем объединяя их вместе.
 * <p>
 * Алгоритм:
 * 1. Рекурсивно разделите входной массив на две половины, пока каждый подмассив не будет содержать только один элемент.
 * 2. Отсортируйте каждый из подмассивов индивидуально.
 * 3. Объедините отсортированные подмассивы вместе, чтобы создать один полностью отсортированный массив.
 * Это достигается путем сравнения элементов двух подмассивов и их объединения в порядке возрастания.
 * 4. Объединенный результат представляет собой отсортированный массив.
 * <p>
 * Сложность алгоритма:
 * В лучшем случаи:  O(n logn)
 * В cреднем: O(n logn)
 * В худшем: O(n logn)
 */
public class СортировкаСлиянием {

    @Test
    public void test() {
        int[] arr = new int[]{10, 5, 2, 4, 1, 3, 8, 7, 9, 6};
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sort(arr));
    }

    public static int[] sort(int[] arr) {
        // проверяем не нулевой ли он?
        if (arr == null) {
            return null;
        }
        // проверяем не 1 ли элемент в массиве?
        if (arr.length < 2) {
            return arr; // возврат в рекурсию в строки ниже см комменты.
        }
        // копируем левую часть от начала до середины
        int[] arrayB = new int[arr.length / 2];
        System.arraycopy(arr, 0, arrayB, 0, arr.length / 2);

        // копируем правую часть от середины до конца массива, вычитаем из длины первую часть
        int[] arrayC = new int[arr.length - arr.length / 2];
        System.arraycopy(arr, arr.length / 2, arrayC, 0, arr.length - arr.length / 2);

        // рекурсией закидываем поделенные обе части обратно в наш метод, он будет крутится до тех пор,
        // пока не дойдет до 1 элемента в массиве, после чего вернется в строку и будет искать второй такой же,
        // точнее правую часть от него и опять вернет его назад
        arrayB = sort(arrayB); // левая часть возврат из рекурсии строкой return arr;
        arrayC = sort(arrayC); // правая часть возврат из рекурсии строкой return arr;

        // далее опять рекурсия возврата слияния двух отсортированных массивов
        return mergeArray(arrayB, arrayC);
    }

    private static int[] mergeArray(int[] arrayA, int[] arrayB) {

        int[] result = new int[arrayA.length + arrayB.length];
        for (int k = 0, i = 0, j = 0; k < result.length; k++) {
            if (i > arrayA.length - 1) {
                int a = arrayB[j];
                result[k] = a;
                j++;
            } else if (j > arrayB.length - 1) {
                int a = arrayA[i];
                result[k] = a;
                i++;
            } else if (arrayA[i] < arrayB[j]) {
                int a = arrayA[i];
                result[k] = a;
                i++;
            } else {
                int b = arrayB[j];
                result[k] = b;
                j++;
            }
        }
        return result;
    }
}

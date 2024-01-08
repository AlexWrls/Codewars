package algyrhythms.solution.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * <h3>Битоническая сортировка</h3>
 * <p>
 * Алгоритм сортировки, названный в честь понятия «битонической последовательности».
 * <p>
 * Для эффективной работы алгоритма длина списка должна быть степенью 2.
 * <p>
 * Шаг 1: Разделите список на две равные половины. Отсортируйте каждую половину таким образом,
 * она стала битонической последовательностью. <p>
 * Шаг 2: Битоническое слияние. Слияние предполагает сравнение элементов из обеих последовательностей
 * и их перестановку для сохранения битонического свойства.<p>
 * Шаг 3: Продолжайте делить последовательности на половины и объединять их, пока не получите
 * полную отсортированную битоническую последовательность.<p>
 * Шаг 4: После получения отсортированной битонической последовательности переверните ее,
 * чтобы отсортировать в желаемом порядке (по возрастанию или по убыванию).<p>
 * <p>
 * Сложность: O(log^2n)
 */
public class БитоническаяСортировка {

    @Test
    public void test() {
        int[] arr = new int[]{10, 5, 2, 4, 1, 3, 8, 7, 9, 6};
        new БитоническаяСортировка().sort(arr);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
    }

    private int[] array;
    private final static boolean ASCENDING = true;    // sorting direction

    public int[] sort(int[] arr) {
        this.array = arr;
        bitonicSort(0, array.length, ASCENDING);
        return array;
    }

    private void bitonicSort(int lo, int n, boolean dir) {
        if (n > 1) {
            int m = n / 2;
            bitonicSort(lo, m, !dir);
            bitonicSort(lo + m, n - m, dir);
            bitonicMerge(lo, n, dir);
        }
    }

    private void bitonicMerge(int lo, int n, boolean dir) {
        if (n > 1) {
            int m = greatestPowerOfTwoLessThan(n);
            for (int i = lo; i < lo + n - m; i++) {
                if (dir == (array[i] > array[i + m])) {
                    int t = array[i];
                    array[i] = array[i + m];
                    array[i + m] = t;
                }
            }
            bitonicMerge(lo, m, dir);
            bitonicMerge(lo + m, n - m, dir);
        }
    }

    // n>=2  and  n<=Integer.MAX_VALUE
    private int greatestPowerOfTwoLessThan(int n) {
        int k = 1;
        while (k > 0 && k < n)
            k = k << 1;
        return k >>> 1;
    }

}

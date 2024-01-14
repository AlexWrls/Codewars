package algyrhythms.solution.level4;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Из Википедии https://en.wikipedia.org/wiki/Partition_(number_theory)
 * <p>
 * В теории чисел и комбинаторике разбиение натурального числа n, также называемое целочисленным разбиением, — это способ записи n в виде суммы положительных целых чисел. Две суммы, отличающиеся только порядком слагаемых, считаются одним и тем же разбиением.
 * <p>
 * Например, число 4 можно разделить пятью различными способами:
 * <p>
 * 4, 3 + 1, 2 + 2, 2 + 1 + 1, 1 + 1 + 1 + 1.
 * <p>
 * Мы можем написать:
 * <p>
 * enum(4) -> [[4],[3,1],[2,2],[2,1,1],[1,1,1,1]]и
 * <p>
 * enum(5) -> [[5],[4,1],[3,2],[3,1,1],[2,2,1],[2,1,1,1],[1,1,1,1,1]].
 * <p>
 * Количество частей в разделе растет очень быстро. Для n = 50 количество частей равно 204226,
 * для 80 — 15,796,476 слишком долго проверять ответы на массивах такого размера. Итак, наша задача следующая:
 * <p>
 * 1 - n задано (n целое число, 1 <= n <= 50) вычислить enum(n), то есть разбиение n. Получим что-то вроде этого:
 * enum(n) -> [[n],[n-1,1],[n-2,2],...,[1,1,...,1]](порядок массива и подмассивов не имеет значения). Эта часть не тестировалась.
 * <p>
 * 2. Для каждого подмассива enum(n) вычислите его произведение. Если n = 5 , после удаления дубликатов и сортировки мы получим :
 * <p>
 * prod(5) -> [1,2,3,4,5,6]
 * <p>
 * prod(8) -> [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 16, 18]
 * <p>
 * Если n = 40, prod(n) имеет длину, 2699следовательно, тесты не будут проверять такие массивы. Вместо этого наша задача номер 3:
 * <p>
 * 3 — вернуть диапазон, среднее и медиану prod(n) в следующей форме (пример для n = 5):
 * <p>
 * "Range: 5 Average: 3.50 Median: 3.50"
 * <p>
 * Диапазон — целое число, среднее значение и медиана — числа с плавающей запятой, округленные до двух десятичных знаков («.2f» в некоторых языках).
 * <p>
 * Примечания:
 * Range: разница между самым высоким и самым низким значениями.
 * <p>
 * Mean or Average: Чтобы вычислить среднее значение, сложите все числа в наборе, а затем разделите сумму на общее количество чисел.
 * <p>
 * Median: Медиана — это число, отделяющее верхнюю половину выборки данных от нижней половины. ( https://en.wikipedia.org/wiki/Median )
 * <p>
 * Подсказки:
 * Постарайтесь оптимизировать свою программу, чтобы избежать тайм-аута.
 * <p>
 * Мемоизация может быть полезной, но не обязательной для достижения успеха.
 */
public class IntPart {

    @Test
    public void Numbers_Small() {
        assertEquals("Range: 1 Average: 1.50 Median: 1.50", IntPart.part(2));
        assertEquals("Range: 2 Average: 2.00 Median: 2.00", IntPart.part(3));
        assertEquals("Range: 3 Average: 2.50 Median: 2.50", IntPart.part(4));
        assertEquals("Range: 5 Average: 3.50 Median: 3.50", IntPart.part(5));
        assertEquals("Range: 17 Average: 8.29 Median: 7.50", IntPart.part(8));
        assertEquals("Range: 86093441 Average: 1552316.81 Median: 120960.00", IntPart.part(50));
    }

    private static final DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("#0.00", dfs);
    }


    public static String part(long n) {
        System.out.println("input:" + n);
        List<Integer> list = new ArrayList<>();
        int[] partition = new int[(int) n];
        Arrays.fill(partition, 1);
        while (partition != null) {
//            System.out.println(Arrays.toString(partition));
            int mu = Arrays.stream(partition).reduce(1, (a, b) -> a * b);
            if (!list.contains(mu)) {
                list.add(mu);
            }
            partition = nextNextPartition(partition);
        }
        list.sort(Comparator.naturalOrder());
        StringBuilder sb = new StringBuilder();
        sb.append("Range: ").append(calcRange(list))
                .append(" Average: ").append(calcAverage(list))
                .append(" Median: ").append(calcMedian(list));
        return sb.toString();
    }

    //Решение было тут https://www.youtube.com/watch?v=Y7djM_dUWnQ
    public static int[] nextNextPartition(int[] partition) {
        if (partition.length == 1) {
            return null;
        }
        int minIdx = 0;
        for (int i = 0; i < partition.length - 1; i++) {
            if (partition[i] < partition[minIdx]) {
                minIdx = i;
            }
        }
        partition[minIdx] += 1;
        partition[partition.length - 1] -= 1;
        minIdx += 1;
        int partSum = 0;
        for (int i = minIdx; i < partition.length; i++) {
            partSum += partition[i];
        }
        int[] nextPartition = Arrays.copyOf(partition, minIdx + partSum);
        for (int i = minIdx; i < nextPartition.length; i++) {
            nextPartition[i] = 1;
        }
        return nextPartition;
    }

    private static String calcRange(List<Integer> list) {
        return String.valueOf(list.get(list.size() - 1) - list.get(0));
    }

    private static String calcAverage(List<Integer> list) {
        long res = list.stream().mapToLong(i -> i).sum();
        return decimalFormat.format((double) res / list.size());
    }

    private static String calcMedian(List<Integer> list) {
        int middle = list.size() / 2;
        double medianValue = list.get(middle);
        if ((list.size() - 1) % 2 == 0) {
            return decimalFormat.format(medianValue);
        }
        return decimalFormat.format((medianValue + list.get(middle - 1)) / 2);
    }

}

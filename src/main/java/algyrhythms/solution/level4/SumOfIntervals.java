package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Напишите функцию с именем sumIntervals/sum_intervals, которая принимает массив интервалов и возвращает сумму
 * длин всех интервалов. Перекрывающиеся интервалы следует учитывать только один раз.
 *
 * Интервалы
 * Интервалы представлены парой целых чисел в виде массива. Первое значение интервала всегда будет меньше второго значения
 * . Пример интервала: [1, 5] — это интервал от 1 до 5. Длина этого интервала равна 4.
 *
 * Перекрывающиеся интервалы
 * Список, содержащий перекрывающиеся интервалы:
 *
 * [
 *    [1, 4],
 *    [7, 10],
 *    [3, 5]
 * ]
 * Сумма длин этих интервалов равна 7. Поскольку [1, 4] и [3, 5] перекрываются, мы можем рассматривать интервал
 * как [1, 5], длина которого равна 4.
 */

public class SumOfIntervals {

    @Test
    public void shouldHandleEmptyIntervals() {
        assertEquals(0, sumIntervals(new int[][]{}));
        assertEquals(0, sumIntervals(new int[][]{{4, 4}, {6, 6}, {8, 8}}));
    }

    @Test
    public void shouldAddDisjoinedIntervals() {
        assertEquals(9, sumIntervals(new int[][]{{1, 2}, {6, 10}, {11, 15}}));
        assertEquals(11, sumIntervals(new int[][]{{4, 8}, {9, 10}, {15, 21}}));
        assertEquals(7, sumIntervals(new int[][]{{-1, 4}, {-5, -3}}));
        assertEquals(78, sumIntervals(new int[][]{{-245, -218}, {-194, -179}, {-155, -119}}));
    }

    @Test
    public void shouldHandleLargeIntervals() {
        assertEquals(2_000_000_000, sumIntervals(new int[][]{{-1_000_000_000, 1_000_000_000}}));
        assertEquals(100_000_030, sumIntervals(new int[][]{{0, 20}, {-100_000_000, 10}, {30, 40}}));
    }

    @Test
    public void shouldAddAdjacentIntervals() {
        assertEquals(54, sumIntervals(new int[][]{{1, 2}, {2, 6}, {6, 55}}));
        assertEquals(23, sumIntervals(new int[][]{{-2, -1}, {-1, 0}, {0, 21}}));
    }

    @Test
    public void shouldAddOverlappingIntervals() {
        assertEquals(7, sumIntervals(new int[][]{{1, 4}, {7, 10}, {3, 5}}));
        assertEquals(6, sumIntervals(new int[][]{{5, 8}, {3, 6}, {1, 2}}));
        assertEquals(19, sumIntervals(new int[][]{{1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11}}));
    }

    @Test
    public void shouldHandleMixedIntervals() {
        assertEquals(13, sumIntervals(new int[][]{{2, 5}, {-1, 2}, {-40, -35}, {6, 8}}));
        assertEquals(1234, sumIntervals(new int[][]{{-7, 8}, {-2, 10}, {5, 15}, {2000, 3150}, {-5400, -5338}}));
        assertEquals(158, sumIntervals(new int[][]{{-101, 24}, {-35, 27}, {27, 53}, {-105, 20}, {-36, 26},}));
    }

    public static int sumIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<Integer> mergedIntervals = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            if (mergedIntervals.size() > 0 && currentInterval[0] <= mergedIntervals.get(mergedIntervals.size() - 1)) {
                if (currentInterval[1] > mergedIntervals.get(mergedIntervals.size() - 1)) {
                    mergedIntervals.set(mergedIntervals.size() - 1, currentInterval[1]);
                }
            } else {
                mergedIntervals.add(currentInterval[0]);
                mergedIntervals.add(currentInterval[1]);
            }
        }
        int result = 0;
        for (int i = 0; i < mergedIntervals.size() - 1; i += 2) {
            result += mergedIntervals.get(i + 1) - mergedIntervals.get(i);
        }
        return result;
    }

}

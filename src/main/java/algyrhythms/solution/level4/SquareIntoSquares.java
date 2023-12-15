package algyrhythms.solution.level4;

import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Задача
 * Для положительного целого числа n вернуть строго возрастающую последовательность чисел
 * (список/массив/строку в зависимости от языка), поэтому что сумма квадратов равна n².
 * <p>
 * Если решений несколько (а они будут), верните, насколько это возможно, результат с максимально возможными значениями:
 * Примеры
 * decompose(11) должен вернуть [1,2,4,10]. Обратите внимание,
 * что на самом деле существует два способа разложения 11²: 11² = 121 = 1 + 4 + 16 + 100 = 1² + 2² + 4² + 10²,
 * но не возвращайте [2,6,9], так как 9 меньше 10.
 * <p>
 * Для decompose(50) не возвращайте [1, 1, 4, 9, 49], но [1, 3, 5, 8, 49],
 * поскольку [1, 1, 4, 9, 49] не образует строго возрастающую последовательность.
 */
public class SquareIntoSquares {

    @Test
    public void test1() {
        assertEquals("1 2 4 10", decompose(11));
    }

    public static String decompose(long n) {
        List<Long> squareList = genSquareList(n);
        long target;
        Set<Long> set;
        for (int i = 1; i < squareList.size(); i++) {
            set = new TreeSet<>();
            target = n * n;
            for (int j = squareList.size() - i; j >= 0; j--) {
                if (target - squareList.get(j) >= 0) {
                    target -= squareList.get(j);
                    set.add((long) Math.sqrt(squareList.get(j)));
                }
            }
            if (target == 0) {
                StringBuilder sb = new StringBuilder();
                set.forEach(item -> sb.append(item).append(" "));
                sb.delete(sb.length() - 1, sb.length());
                return sb.toString();
            }
        }
        return null;
    }

    private static List<Long> genSquareList(long n) {
        long sum = 1;
        long i = 2;
        List<Long> res = new ArrayList<>();
        while (sum < (n * n)) {
            res.add(sum);
            sum = i * i;
            i++;
        }
        return res;
    }

}

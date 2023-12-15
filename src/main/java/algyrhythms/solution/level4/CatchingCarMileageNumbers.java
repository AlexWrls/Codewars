package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Интересные числа — это трехзначные и более значные числа, которые соответствуют одному или нескольким из следующих критериев:
 *
 * Любая цифра, за которой следуют все нули: 100, 90000
 * Каждая цифра представляет собой одно и то же число:1111
 * Цифры идут последовательно, начиная с нуля†: 1234
 * Цифры идут последовательно, уменьшаются‡: 4321
 * Цифры представляют собой палиндром: 1221 или 73837
 * Цифры соответствуют одному из значений в массиве awesomePhrases
 * † Для увеличивающихся последовательностей 0 должен идти после 9, а не перед 1, как в 7890.
 * ‡ Для уменьшения последовательностей должно появиться 0 после 1, а не до 9, как в 3210.
 */
public class CatchingCarMileageNumbers {
    @Test
    public void test() {
        assertEquals(0, isInteresting(3, new int[]{1337, 256}));
        assertEquals(1, isInteresting(1336, new int[]{1337, 256}));
        assertEquals(2, isInteresting(1337, new int[]{1337, 256}));
        assertEquals(0, isInteresting(11208, new int[]{1337, 256}));
        assertEquals(2, isInteresting(11211, new int[]{1337, 256}));
    }

    public static int isInteresting(int number, int[] awesomePhrases) {
        List<Integer> res = new ArrayList<>();
        for (int i = number - 2, idx = 0; i <= number + 2; i++, idx++) {
            boolean flag = i == number;
            res.add(lookup(i, flag));
            for (int awesomePhrase : awesomePhrases) {
                if (i == awesomePhrase) {
                    res.add(flag ? 2 : 1);
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    private static int lookup(int num, boolean first) {
        if (num < 100) {
            return 0;
        }
        String strNum = String.valueOf(num);
        Function<String, Boolean> isPalindrome = s -> new StringBuilder(strNum).reverse().toString().equals(strNum);
        Function<String, Boolean> grow = "123456790"::contains;
        Function<String, Boolean> low = "9876543210"::contains;
        if (num % Math.pow(10, strNum.length() - 1) == 0 || isPalindrome.apply(strNum) || grow.apply(strNum) || low.apply(strNum)) {
            return first ? 2 : 1;
        }
        return 0;
    }
}

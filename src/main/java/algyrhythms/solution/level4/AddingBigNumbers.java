package algyrhythms.solution.level4;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Нам нужно суммировать большие числа, и нам нужна ваша помощь.
 * <p>
 * Напишите функцию, возвращающую сумму двух чисел. Входные числа представляют собой строки, и функция должна возвращать строку.
 * <p>
 * Пример
 * add("123", "321"); -> "444"
 * add("11", "99");   -> "110"
 * Примечания
 * Введенные числа большие.
 * Входные данные представляют собой строку, состоящую только из цифр.
 * Числа положительные
 */
public class AddingBigNumbers {

    @Test
    public void test() {
        Assert.assertEquals(add("01", "1"), "2");
        Assert.assertEquals(add("123", "456"), "579");
        Assert.assertEquals(add("888", "222"), "1110");
        Assert.assertEquals(add("1372", "69"), "1441");
        Assert.assertEquals(add("12", "456"), "468");
        Assert.assertEquals(add("63829983432984289347293874", "90938498237058927340892374089"), "91002328220491911630239667963");
    }


    public static String add(String a, String b) {
        List<Integer> numA = splitToInt(a.length() >= b.length() ? a : b);
        List<Integer> numB = splitToInt(a.length() < b.length() ? a : b);
        StringBuilder res = new StringBuilder();
        int mem = 0;
        int idxA = numA.size() - 1;
        for (int idxB = numB.size() - 1; idxB >= 0; idxA--, idxB--) {
            int sum = numA.get(idxA) + numB.get(idxB) + mem;
            mem = calc(sum, res);
        }
        while (idxA >= 0) {
            int sum = numA.get(idxA) + mem;
            mem = calc(sum, res);
            idxA--;
        }
        if (mem != 0) res.append(mem);
        return res.reverse().toString();
    }

    private static List<Integer> splitToInt(String s) {
        char[] chars = s.toCharArray();
        List<Integer> list = new ArrayList<>();
        boolean firstNonZeroAt = true;
        for (char aChar : chars) {
            firstNonZeroAt = String.valueOf(aChar).equalsIgnoreCase("0") && firstNonZeroAt;
            if (!firstNonZeroAt) {
                list.add(Integer.parseInt(String.valueOf(aChar)));
            }
        }
        return list;
    }

    private static int calc(int sum, StringBuilder res) {
        if (sum > 9) {
            res.append(String.valueOf(sum).charAt(1));
            return 1;
        }
        res.append(sum);
        return 0;
    }

}

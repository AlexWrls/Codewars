package algyrhythms.solution.level6;

import org.junit.Test;

import java.math.BigInteger;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Вы знаете комбинации: например, если вы возьмете 5 карт из колоды из 52 карт, у вас будет 2 598 960 различных комбинаций. *
 * В математике количество комбинаций x, которые вы можете взять из набора из n элементов,
 * называется биномиальным коэффициентом n и x, или чаще n выбирает x.
 * Формула для вычисления n выбирает x: *
 * n! / (x! * (n - x)!) *
 * где ! — факториальный оператор.
 * <p>
 * Вы известный дизайнер плакатов и художник. Вас просят предоставить 6 плакатов с одинаковым дизайном, каждый в 2 цветах.
 * Все плакаты должны иметь разную цветовую комбинацию, и у вас есть выбор из 4 цветов: красный, синий, желтый, зеленый.
 * Сколько цветов вы можете выбрать для каждого плаката?
 * <p>
 * Ответ — два, так как 4 выбирают 2 = 6.
 * Комбинации будут следующими: {красный, синий}, {красный, желтый}, {красный, зеленый}, {синий, желтый}, {синий, зеленый}, {желтый, зеленый}.
 * <p>
 * Теперь тот же вопрос, но у вас есть 35 плакатов для поставки и 7 доступных цветов. Сколько цветов для каждого плаката?
 * Если вы возьмете комбинации 7 выбрать 2, вы получите 21 по приведенной выше формуле. Но 21 схемы недостаточно для 35 плакатов.
 * Если вы возьмете комбинации 7 выбрать 5, вы также получите 21.
 * К счастью, если вы возьмете комбинации 7 выбрать 3 или 7 выбрать 4, вы получите 35,
 * и поэтому каждый плакат будет иметь другую комбинацию из 3 цветов или 4 цветов. Вы возьмете 3 цвета, потому что это дешевле.
 * <p>
 * Отсюда и проблема:
 * Зная m (количество плакатов для дизайна) и n (общее количество доступных цветов),
 * давайте найдем x, количество цветов для каждого плаката, так что каждый плакат будет иметь уникальную комбинацию цветов,
 * а количество комбинаций будет точно таким же, как количество плакатов m.
 * <p>
 * Другими словами, вы должны найти x такой, что: *
 * n выбрать x = m *
 * для заданного m >= 0 и заданного n > 0. Если есть несколько решений, вернуть наименьшее. Если решений нет, вернуть -1.
 */
public class Checkchoose {

    @Test
    public void BasicTests() {
        System.out.println("****** Basic Tests ******");
        assertEquals(2, Checkchoose.checkchoose(6, 4));
        assertEquals(1, Checkchoose.checkchoose(4, 4));
        assertEquals(3, Checkchoose.checkchoose(35, 7));
        assertEquals(-1, Checkchoose.checkchoose(4, 2));
        assertEquals(-1, Checkchoose.checkchoose(36, 7));
        assertEquals(3, Checkchoose.checkchoose(5984, 34));
        assertEquals(10, Checkchoose.checkchoose(184756, 20));
        assertEquals(10, Checkchoose.checkchoose(6540715896L, 48));
    }

    public static long checkchoose(long m, int n) {
        System.out.printf("****** check choose m=%s, n=%s ******\n", m, n);
        try {
            Function<Integer, BigInteger> factorial = f -> {
                BigInteger result = BigInteger.ONE;
                for (int i = 1; i <= f; i++) result = result.multiply(BigInteger.valueOf(i));
                return result;
            };
            for (int x = 1; (n - x) > 0; x++) {
                BigInteger res = factorial.apply(n).divide(factorial.apply(n - x).multiply(factorial.apply(x)));
                if (m == res.longValue()) {
                    return x;
                }
            }
            System.out.printf("****** can't find solution from m=%s, n=%s ******\n", m, n);
            return -1;
        } catch (Exception e) {
            System.out.printf("****** error check from m=%s, n=%s ******\n", m, n);
            return -1;
        }
    }

//    Best solution
//    public static long checkchoose(long m, int n) {
//        long c = 1;
//        for(int l = 1; l <= n; l++) {
//            c = c * (n-l + 1)/l;
//            if(c == m) return l;
//        }
//        return -1;
//    }

}

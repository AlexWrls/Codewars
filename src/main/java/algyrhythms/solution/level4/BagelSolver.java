package algyrhythms.solution.level4;


import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

/**
 * Вот, казалось бы, простая задача.
 * Мы даем вам класс под названием bagel, именно такой, как он представлен ниже.
 * Все, что он на самом деле делает, это возвращает int, а именно 3.
 * <p>
 * В чем подвох? Для решения мы проверяем, что результат равен 4.
 * Но в качестве небольшой подсказки: решение этого Kata (почти) точно такое же, как и в примерах тестовых случаев.
 */
public class BagelSolver {

    @Test
    public void testBagel() throws Throwable {
        Bagel bagel = getBagel();
        assertEquals(
                bagel.getValue() == 4,
                Boolean.TRUE
        );
        assertEquals(
                Boolean.FALSE,
                Boolean.TRUE
        );
    }

    public static Bagel getBagel() throws Throwable {
        try {
            Field value = Boolean.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(Boolean.TRUE, false);
        } catch (Exception e) {}
        return new Bagel();
    }

    static class Bagel {
        public final int getValue() {
            return 3;
        }
    }
}


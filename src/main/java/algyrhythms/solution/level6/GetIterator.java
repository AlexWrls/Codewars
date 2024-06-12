package algyrhythms.solution.level6;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;

/**
 * Написать функцию более высокого порядка, возвращающую новую функцию,
 * которая выполняет итерацию указанной функции заданное количество раз.
 * Эта новая функция принимает аргумент в качестве начального значения, с которого начинается вычисление.
 * <p>
 * Например, рассмотрим функцию getDouble. При повторном запуске со значением 3 результат равен 12, как показано ниже.
 * <p>
 * Получаем удвоение(3) => 6
 * Получаем удвоение(6) => 12
 * Давайте назовем новую функцию createIterator, и мы сможем получить тот же результат, используя createIterator, как показано ниже:
 * <p>
 * var doubleIterator = createIterator(getDouble, 2); // Это означает, что он запускает *getDouble* дважды
 * doubleIterator(3) => 12
 */
public class GetIterator {
    @Test
    public void testDoubling(){
        UnaryOperator<Integer> getDouble = x -> x * 2;
        Function<Integer, Integer> custDouble = GetIterator.getIterator(getDouble, 1);
        assertEquals("getDouble", (int) 4, (int) custDouble.apply(2));
        Function<Integer, Integer> getQuadruple = GetIterator.getIterator(getDouble, 2);
        assertEquals("getQuadruple", (int) 8, (int) getQuadruple.apply(2));
    }

    public static Function<Integer, Integer> getIterator(UnaryOperator<Integer> getDouble, int i) {
        return calc(getDouble, i);
    }

    private static Function<Integer, Integer> calc(Function<Integer, Integer> fnc, int count) {
        return i -> {
            Function<Integer, Integer> newFunc = fnc;
            for (int j = 1; j < count; j++) {
                newFunc = newFunc.andThen(fnc);
            }
            return newFunc.apply(i);
        };
    }

}

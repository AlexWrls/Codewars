package algyrhythms.solution.level6;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс Guesser настроен на генерацию случайного числа от 1 до 1000 и позволяет получить это число за 10 попыток.
 *
 * Ваша задача — написать метод getNumber9) внутри класса GuesserSolution для идентификации случайного числа от 1 до 1000.
 *
 * Вам следует использовать метод:
 * "Too high!" - If the guess is too high.
 * "Too low!"  - If the guess is too low.
 * "Correct!"  - If the guess is correct.
 */
public class GuesserSolution {

    @Test
    public void testBasic() {
        for (int i = 0; i < 100; i++) {
            int num = (int) Math.abs(Math.random() * 1000);
            GuesserSolution solution = new GuesserSolution();
            solution.setAnswer(num);
            int guessed = solution.getNumber();
            Assert.assertEquals(num, guessed);
            System.out.println("You guessed " + guessed + " correctly!");
        }
    }

    private static final int[] DATA = new int[1000];

    static {
        for (int i = 0; i < 1000; i++) {
            DATA[i] = i + 1;
        }
    }

    private static final String HIGH = "Too high!";
    private static final String LOW = "Too low!";
    private static final String CORRECT = "Correct!";


    public int getNumber() {
        // в начале левая и правая границы равны первому и последнему элементу массива
        int left = 0;
        int right = DATA.length - 1;
        // пока левая и правая границы поиска не пересеклись
        while (left <= right) {
            // индекс текущего элемента находится посередине
            int middle = (left + right) / 2;
            int current = DATA[middle];
            String element = guess(current);
            System.out.println(element);
            if (element.equals(CORRECT)) {
                // нашли элемент - возвращаем его индекс
                return DATA[middle];
            } else if (element.equals(LOW)) {
                // текущий элемент меньше искомого - сдвигаем левую границу
                left = middle + 1;
            } else {
                // иначе сдвигаем правую границу
                right = middle - 1;
            }
        }
        // проверили весь массив, но не нашли элемент
        return -1;
    }


    // Запрещено изменять часть кода!
    private int num;

    private void setAnswer(int num) {
        this.num = num;
    }

    private String guess(int i) {
        if (i < num) {
            return LOW;
        } else if (i > num) {
            return HIGH;
        }
        return CORRECT;
    }


}
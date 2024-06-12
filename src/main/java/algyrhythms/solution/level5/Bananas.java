package algyrhythms.solution.level5;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Учитывая последовательность букв a, b, n, сколькими различными способами вы можете составить слово "банан",
 * зачеркивая различные буквы и затем читая слева направо?
 * <p>
 * (Используйте - для обозначения зачеркнутой буквы)
 * <p>
 * Пример
 * Ввод
 * <p>
 * ббананана
 * Выход
 * <p>
 * б-анана-
 * б-анан-а
 * б-ана-на
 * б-анана-ана
 * б-а-нана
 * б---анана
 * -банан-
 * -банан-а
 * -бана-на
 * -бан-ана
 * - ба-нана
 * -б-анана
 * Записи
 * Вы должны вернуть все возможные бананы, но порядок не имеет значения
 */
//Не свое
public class Bananas {

    // common test code
    private void doTest(final String input, final Set<String> expected, final Set<String> actual) {
        System.out.println(String.format("INPUT: %s", input));
        System.out.println(String.format("EXPECTED: %s ", expected));
        assertEquals("wrong number of bananas!", expected.size(), actual.size());
        if (!actual.containsAll(expected)) {
            System.out.println(String.format("ACTUAL: %s ", actual));
            fail("banana mismatch!");
        }
    }

    @Test
    public void ex0() {
        final String input = "banann";
        final Set<String> expected = Collections.emptySet();
        final Set<String> actual = Bananas.bananas(input);
        doTest(input, expected, actual);
    }

    @Test
    public void ex1() {
        final String input = "banana";
        final Set<String> expected = new HashSet<>(Arrays.asList("banana"));
        final Set<String> actual = Bananas.bananas(input);
        doTest(input, expected, actual);
    }

    @Test
    public void ex2() {
        final String input = "bbananana";
        final Set<String> expected = new HashSet<>(Arrays.asList(
                "b-an--ana", "-banana--", "-b--anana", "b-a--nana", "-banan--a",
                "b-ana--na", "b---anana", "-bana--na", "-ba--nana", "b-anan--a",
                "-ban--ana", "b-anana--"));
        final Set<String> actual = Bananas.bananas(input);
        doTest(input, expected, actual);
    }

    @Test
    public void ex3() {
        final String input = "bananaaa";
        final Set<String> expected = new HashSet<>(Arrays.asList(
                "banan-a-", "banana--", "banan--a"));
        final Set<String> actual = Bananas.bananas(input);
        doTest(input, expected, actual);
    }

    @Test
    public void ex4() {
        final String input = "bananana";
        final Set<String> expected = new HashSet<>(Arrays.asList(
                "ban--ana", "ba--nana", "bana--na", "b--anana", "banana--",
                "banan--a"));
        final Set<String> actual = Bananas.bananas(input);
        doTest(input, expected, actual);
    }

//    ====================== Solution  ======================

    public static final String TARGET = "banana";

    static Set<String> bananas(final String s) {
        Set<String> set = new HashSet<>();
        bananas(s, 0, set);
        return set;
    }

    static void bananas(String string, int index, Set<String> set) {
        String withoutDashes = string.replace("-", "");
        if (withoutDashes.equals(TARGET)) {
            set.add(string);
        } else if (index < string.length() && withoutDashes.length() > TARGET.length()) {
            bananas(string, index + 1, set);
            bananas(new StringBuilder(string).replace(index, index + 1, "-").toString(), index + 1, set);
        }
    }
}


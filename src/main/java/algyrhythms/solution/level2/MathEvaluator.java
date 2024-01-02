package algyrhythms.solution.level2;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Числа
 * Число может быть как целым, так и десятичным числом. То же самое касается возвращаемого результата.
 *
 * Операторы
 * Вам необходимо поддерживать следующие математические операторы:
 *
 * Умножение*
 * Деление /(как деление с плавающей запятой)
 * Добавление+
 * Вычитание-
 * Операторы всегда оцениваются слева направо и *должны /вычисляться перед +и -.
 *
 * Круглые скобки
 * Вам необходимо поддерживать несколько уровней вложенных круглых скобок, например.(2 / (2 + 3.33) * 4) - -6
 *
 * Пробелы
 * Между числами и операторами могут быть или не быть пробелы.
 *
 * Дополнением к этому правилу является то, что знак минус ( -),
 * используемый для отрицания чисел и круглых скобок, никогда не разделяется пробелом. Т.е. все нижеперечисленное является допустимыми выражениями.
 */
public class MathEvaluator {

    @Test
    public void testAddition() {
        assertEquals(2d, new MathEvaluator().calculate("1+1"), 0.01);
        assertEquals(0d, new MathEvaluator().calculate("1 - 1"), 0.01);
        assertEquals(1d, new MathEvaluator().calculate("1* 1"), 0.01);
        assertEquals(1d, new MathEvaluator().calculate("1 /1"), 0.01);
        assertEquals(-123d, new MathEvaluator().calculate("-123"), 0.01);
        assertEquals(123d, new MathEvaluator().calculate("123"), 0.01);
        assertEquals(21.25, new MathEvaluator().calculate("2 /2+3 * 4.75- -6"), 0.01);
        assertEquals(1476d, new MathEvaluator().calculate("12* 123"), 0.01);
        assertEquals(7.732, new MathEvaluator().calculate("2 / (2 + 3) * 4.33 - -6"), 0.01);
        assertEquals(7.45625, new MathEvaluator().calculate("((2.33 / (2.9+3.5)*4) - -6)"), 0.01);
        assertEquals(-12042.760875, new MathEvaluator().calculate("123.45*(678.90 / (-2.5+ 11.5)-(80 -19) *33.25) / 20 + 11"), 0.01);
        assertEquals(1, new MathEvaluator().calculate("(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11) "), 0.01);
    }

    private static final Pattern BRACKETS = Pattern.compile("\\(([.0-9+*\\/-]+)\\)");
    private static final Pattern HiGHT_PRIORITY = Pattern.compile("(([.0-9]+)|(-[.0-9]+))([*\\/])(([.0-9]+)|(-[.0-9]+))");
    private static final Pattern LOW_PRIORITY = Pattern.compile("(([.0-9]+)|(-[.0-9]+))([+-])(([.0-9]+)|(-[.0-9]+))");


    public double calculate(String expression) {
        String prepared = expression.replaceAll("\\s+|\\t+", "").replaceAll("--", "+");
        if (!HiGHT_PRIORITY.matcher(prepared).find() && !LOW_PRIORITY.matcher(prepared).find() && !BRACKETS.matcher(prepared).find()) {
            return Double.parseDouble(prepared);
        }
        return Double.parseDouble(solution(prepared));
    }

    private String solution(String ex) {
        Matcher m;
        while ((m = BRACKETS.matcher(ex)).find()) {
            String resOperation = operation(m.group(1));
            ex = ex.replaceAll(escape(m.group(0)), resOperation).replaceAll("--", "+");
        }
        return operation(ex);
    }

    private String operation(String ex) {
        ex = ex.replaceAll("\\+\\+", "+")
                .replaceAll("-\\+", "-")
                .replaceAll("\\*\\+", "*")
                .replaceAll("/\\+", "/");
        Matcher m;
        while ((m = HiGHT_PRIORITY.matcher(ex)).find()) {
            String operation = doOperation(m.group(1), m.group(5), m.group(4));
            System.out.printf("%s %s %s = %s%n\n", m.group(1), m.group(4), m.group(5), operation);
            ex = ex.replaceAll(escape(m.group(1) + m.group(4) + m.group(5)), operation);
        }
        while ((m = LOW_PRIORITY.matcher(ex)).find()) {
            String operation = doOperation(m.group(1), m.group(5), m.group(4));
            System.out.printf("%s %s %s = %s%n\n", m.group(1), m.group(4), m.group(5), operation);
            ex = ex.replaceAll(escape(m.group(1) + m.group(4) + m.group(5)), operation);
        }
        return ex;
    }

    private String doOperation(String num1, String num2, String op) {
        double dNum1 = Double.parseDouble(num1);
        double dNum2 = Double.parseDouble(num2);
        if (("+").equals(op)) {
            return String.valueOf(dNum1 + dNum2);
        } else if (("-").equals(op)) {
            return String.valueOf(dNum1 - dNum2);
        } else if (("*").equals(op)) {
            return String.valueOf(dNum1 * dNum2);
        } else {
            return String.valueOf(dNum1 / dNum2);
        }
    }

    private String escape(String ex) {
        return ex.replaceAll("\\+", "\\\\+")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\*", "\\\\*")
                .replaceAll("\\)", "\\\\)");
    }

}

package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * В этом ката ваша задача — создать регулярное выражение, способное оценивать двоичные строки
 * (строки, содержащие только 1 и 0) и определять представляет ли данная строка число, делящееся на 3.
 */
public class BinaryMultipleOf3 {
    @Test
    public void testSimple() {
        assertEquals(false, multipleOf3().matcher(" 0").matches());
        assertEquals(false, multipleOf3().matcher("abc").matches());
        assertEquals(true, multipleOf3().matcher("000").matches());

        assertEquals(true, multipleOf3().matcher("110").matches());
        assertEquals(false, multipleOf3().matcher("111").matches());
        assertEquals(true, multipleOf3().matcher(Integer.toBinaryString(12345678)).matches());
    }

    public static Pattern multipleOf3() {
        return Pattern.compile("^(1(01*0)*1|0)+$");
    }
}

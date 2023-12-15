package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class FindUnknownDigit {

    @Test
    public void testSample() {
        assertEquals( "Answer for expression '1+1=?' " , 2 , solveExpression("1+1=?") );
        assertEquals( "Answer for expression '123*45?=5?088' " , 6 , solveExpression("123*45?=5?088") );
        assertEquals( "Answer for expression '-5?*-1=5?' " , 0 , solveExpression("-5?*-1=5?") );
        assertEquals( "Answer for expression '19--45=5?' " , -1 , solveExpression("19--45=5?") );
        assertEquals( "Answer for expression '??*??=302?' " , 5 , solveExpression("??*??=302?") );
        assertEquals( "Answer for expression '?*11=??' " , 2 , solveExpression("?*11=??") );
        assertEquals( "Answer for expression '??*1=??' " , 2 , solveExpression("??*1=??") );
        assertEquals( "Answer for expression '??+??=??' " , -1 , solveExpression("??+??=??") );
    }


    public static int solveExpression(final String expression ) {
        for (int i=0; i<10; i++) {
            if (expression.contains(""+i)) continue; // unknown digit will not be the same as any other digits
            if (thisIsTrue(expression.replace("?",""+i))) return i;
        }
        return -1;
    }

    private static boolean thisIsTrue(final String expr) {
        final Pattern p = Pattern.compile("(-?+[0-9]+)([*+-])(-?+[0-9]+)=(.*)"); // Expr always has form: (num)[op](num)=(num)
        final Matcher m = p.matcher(expr);
        if (m.matches()) {
            final String a = m.group(1), b = m.group(3), c = m.group(4);
            if (a.matches("-*0.+") || b.matches("-*0.+") || c.matches("-*0.+")) return false; // leading zeroes not allowed
            final int ai = Integer.valueOf(a), bi = Integer.valueOf(b), ci = Integer.valueOf(c);
            switch (m.group(2)) {
                case "+" : return ai + bi == ci;
                case "-" : return ai - bi == ci;
                case "*" : return ai * bi == ci;
            }
        }
        return false;
    }
}

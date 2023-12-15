package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Напишите функцию, которая составляет список строк, представляющих все способы балансировки n пар скобок
 */
public class BalancedParentheses {
    @Test
    public void testExample() {
        List<String> warriorsList;
        //test for n = 0
        warriorsList = balancedParens(0);
        assertEquals(new ArrayList<>(Arrays.asList(""))
                ,  warriorsList
        );
        //test for n = 1
        warriorsList = balancedParens(1);
        assertEquals(new ArrayList<>(Arrays.asList("()"))
                , warriorsList
        );
        //test for n =2
        warriorsList = balancedParens(2);
        Collections.sort(warriorsList);
        assertEquals(new ArrayList<>(Arrays.asList("(())","()()"))
                , warriorsList
        );
        //test for n = 3
        warriorsList = balancedParens(3);
        Collections.sort(warriorsList);
        assertEquals(new ArrayList<>(Arrays.asList("((()))","(()())","(())()","()(())","()()()"))
                , warriorsList
        );

    }

    public static List<String> balancedParens(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesis(n, 0, 0, "", res);
        return res;
    }

    public static void generateParenthesis(int n, int open, int close, String s, List res) {
        if (open >= n && close >= n) {
            res.add(s);
            return;
        }
        if (open < n) {
            generateParenthesis(n, open + 1, close, s + "(", res);
        }
        if (close < open) {
            generateParenthesis(n, open, close + 1, s + ")", res);
        }
    }
}

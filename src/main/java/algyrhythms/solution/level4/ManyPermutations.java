package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * В этом ката ваша задача — создать все перестановки непустой входной строки и удалить дубликаты, если они есть.
 *
 * Создайте как можно больше «перетасовок»; как вы можете!
 */
public class ManyPermutations {

    @Test
    public void example1() {
        assertEquals( new ArrayList<>(Arrays.asList("a")),
                singlePermutations("a").stream().sorted().collect(Collectors.toList()) );
        assertEquals( new ArrayList<>(Arrays.asList("ab","ba")),
                singlePermutations("ab").stream().sorted().collect(Collectors.toList()) );
        assertEquals( new ArrayList<>(Arrays.asList("aabb", "abab", "abba", "baab", "baba", "bbaa")),
                singlePermutations("aabb").stream().sorted().collect(Collectors.toList()) );
    }

    public static List<String> singlePermutations(String s) {
        Set<String> set = new HashSet<>();
        if (s.length() == 1) {
            set.add(s);
        } else {
            for (int i = 0; i < s.length(); i++) {
                List<String> temp = singlePermutations(s.substring(0, i) + s.substring(i + 1));
                for (String string : temp) {
                    set.add(s.charAt(i) + string);
                }
            }
        }

        return new ArrayList<>(set);
    }

//    ======================= Solution Google ================================
// <dependency>
//	<groupId>com.google.guava</groupId>
//	<artifactId>guava</artifactId>
//	<version>24.1-jre</version>
//</dependency>

    public static List<String> GoogleSinglePermutations(String s) {
        return  com.google.common.collect.Collections2.permutations(List.of(s.split("")))
                .stream()
                .map(p->String.join("",p))
                .distinct()
                .collect(Collectors.toList());
    }

}

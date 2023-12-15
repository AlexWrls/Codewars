package algyrhythms.solution.level4;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

/**
 * Напишите функцию, которая по текстовой строке (возможно, со знаками препинания и переносами строк)
 * возвращает массив из трех наиболее часто встречающихся слов в порядке убывания количества вхождений.
 *
 * Слово — это строка букв (от A до Z), которая может содержать один или несколько апострофов (') в формате ASCII.
 * Апострофы могут появляться в начале, середине или конце слова ('abc, abc', 'abc', < /span>ab'c все действительны)
 * Любые другие символы (например, #, \, / , . . ..) не являются частью слова и должны рассматриваться как пробелы.
 * Совпадения должны быть нечувствительны к регистру, а слова в результате должны быть написаны строчными буквами.
 * Связи могут быть разорваны произвольно.
 * Если текст содержит менее трех уникальных слов, то должны быть возвращены либо первые 2, либо первые 1 слова, либо пустой массив, если текст не содержит слов.
 */
public class TopWords {

    @Test
    public void sampleTests() {
        assertTrue(TopWords.top3("a a a  b  c c  d d d d  e e e e e").containsAll(Arrays.asList("e", "d", "a")));
        assertTrue(TopWords.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e").containsAll(Arrays.asList("e", "ddd", "aa")));
        assertTrue(TopWords.top3("  //wont won't won't ").containsAll(Arrays.asList("won't", "wont")));
        assertTrue(TopWords.top3("  , e   .. ").containsAll(Arrays.asList("e")));
        assertTrue(TopWords.top3("  ...  ").isEmpty());
        assertTrue(TopWords.top3("  '  ").isEmpty());
        assertTrue(TopWords.top3("  '''  ").isEmpty());
        assertTrue(TopWords.top3("'a 'A 'a' a'A' a'a'!").containsAll(Arrays.asList("a'a'", "'a", "'a'")));
        assertTrue(TopWords.top3("In a village of La Mancha, the name of which I have no desire to call to " +
                "mind, there lived not long since one of those gentlemen that keep a lance " +
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for " +
                "coursing. An olla of rather more beef than mutton, a salad on most " +
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra " +
                "on Sundays, made away with three-quarters of his income."
        ).containsAll(Arrays.asList("a", "of", "on")));
    }

    private final static Pattern PATTERN_WORD = Pattern.compile("[a-zA-Z]", Pattern.MULTILINE);

    public static List<String> top3(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return Collections.emptyList();
        }
        String prepareText = s.replaceAll("[\\.,-\\/#!$%\\^&\\*;:{}=\\-_`~()\\?]", " ")
                .replaceAll("\\s+", " ").toLowerCase();
        String[] words = prepareText.split(" ");
        Map<String, Integer> mapWord = new HashMap<>();
        for (String word : words) {
            if (PATTERN_WORD.matcher(word).find()) {
                mapWord.put(word, mapWord.getOrDefault(word, 0) + 1);
            }
        }
        if (mapWord.isEmpty()){
            return Collections.emptyList();
        }
        String[] res = mapWord.keySet().toArray(new String[0]);
        Arrays.sort(res, (a, b) -> mapWord.get(b) - mapWord.get(a));
        return Arrays.asList(Arrays.copyOfRange(res, 0, Math.min(3, res.length)));
    }
}

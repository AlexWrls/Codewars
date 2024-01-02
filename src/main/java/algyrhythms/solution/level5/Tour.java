package algyrhythms.solution.level5;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * У вашей бабушки, которая живет в городе X0, есть друзья. Эти друзья задаются в массиве, например:
 * массив друзей ["A1", "A2", "A3", "A4", "A5"].
 *
 * Порядок друзей в этом массиве изменять нельзя, поскольку этот порядок определяет порядок их посещения.
 *
 * Друзья населяют города, и вы получаете массив с друзьями и их городами (или ассоциативный массив),
 * например: [["A1", "X1"], ["A2", "X2"], ["A3", "X3"], ["A4", "X4"]] что означает,
 * что A1 находится в городе X1, A2 в городе X2... Может случиться так, что мы не знаем город одного друзей,
 * следовательно, его не будут посещать.
 *
 * Ваша бабушка хочет навестить своих друзей и узнать, сколько примерно миль ей придется проехать.
 * Вы совершите круг, который позволит ей навестить своих друзей.
 * Например, здесь схема будет такой: X0, X1, X2, X3, X4, X0 и вы примерно вычислите общее расстояние X0X1 + X1X2 + .. + X4X0.
 *
 * Для расстояний вам предоставляется массив или словарь, который дает каждое расстояние X0X1, X0X2 и так далее.
 * Например (зависит от языка):
 *
 * [ ["X1", 100.0], ["X2", 200.0], ["X3", 250.0], ["X4", 300.0] ]
 * or
 * ("X1" -> 100.0, "X2" -> 200.0, "X3" -> 250.0, "X4" -> 300.0)
 * это означает, что X1 находится на расстоянии 100,0 миль от X0, X2 - на расстоянии 200,0 миль от X0 и т. д.
 * Это не реальная жизнь, это история...: города X0, X1, .., X0 расположены следующим образом. (см. рисунок ниже):
 *
 * X0X1X2 — прямоугольный треугольник с прямым углом в X1, X0X2X3 — прямоугольный треугольник с прямым углом в X2,
 * ... В путешествии X0, X1, .., Xi-1, Xi, Xi+1.., X0 для простоты вы предположите, что в Xi существует прямой угол (i > 0).
 *
 * Таким образом, если город Си не посещается, вы будете считать, что треугольник все еще является прямоугольным,
 * и вы можете использовать «теорему Пифагора». X0Xi-1Xi+1Xi-1
 *
 * Задача
 * Можете ли вы помочь своей бабушке и сообщить ей примерное расстояние, которое нужно преодолеть?
 */
public class Tour {

    @Test
    public void test1() {
        String[] friends1 = new String[]{"A1", "A2", "A3", "A4", "A5"};
        String[][] fTowns1 = {new String[]{"A1", "X1"}, new String[]{"A2", "X2"}, new String[]{"A3", "X3"},
                new String[]{"A4", "X4"}};
        Map<String, Double> distTable1 = new HashMap<String, Double>();
        distTable1.put("X1", 100.0);
        distTable1.put("X2", 200.0);
        distTable1.put("X3", 250.0);
        distTable1.put("X4", 300.0);
        assertEquals(889, Tour.tour(friends1, fTowns1, distTable1));
    }

    public static int tour(String[] arrFriends, String[][] ftwns, Map<String, Double> h) {
        Map<String, Double> friendsH = new TreeMap<>();
        for (String[] ftwn : ftwns) {
            for (String arrFriend : arrFriends) {
                if (arrFriend.equals(ftwn[0])) {
                    friendsH.put(arrFriend, h.get(ftwn[1]));
                }
            }
        }
        Iterator<Map.Entry<String, Double>> iterator = friendsH.entrySet().iterator();
        Double h1 = iterator.next().getValue();
        Double sum = h1;
        while (iterator.hasNext()) {
            Double h2 = iterator.next().getValue();
            sum += Math.sqrt(h2 * h2 - h1 * h1);
            h1 = h2;
        }
        sum += h1;
        return sum.intValue();
    }

}

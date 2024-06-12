package algyrhythms.solution.level6;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Задача
 * Распределите клиентов по номерам в отеле в зависимости от дней их прибытия и отъезда.
 * Каждый клиент хочет получить свой собственный номер, поэтому два клиента могут проживать в одном номере,
 * только если день отъезда первого клиента приходится на более ранний день, чем день прибытия второго клиента.
 * Количество используемых номеров должно быть сведено к минимуму.
 * <p>
 * Ввод
 * Список из n клиентов, 1 <= n <= 1000. Каждый клиент представлен числами (arrival_day, departure_day),
 * которые являются целыми положительными числами, удовлетворяющими параметру arrival_day <= departure_day.
 * <p>
 * Выход
 * Список размером n, где элемент i указывает на номер, который был выделен клиенту i.
 * Номера номеров 1,2, ..., k для некоторого значения 1 <= k <= n.
 * Любое распределение, которое минимизирует количество комнат k, является допустимым решением.
 * <p>
 * Пример:
 * Предположим, что клиенты - это [(1,5), (2,4), (6,8), (7,7)].
 * Очевидно, что клиенты 1 и 2 не могут получить одну и ту же комнату.
 * Клиент 3 может использовать одну и ту же комнату в качестве одного из них,
 * потому что они оба уходят до прихода клиента 3.
 * Тогда клиенту 4 может быть предоставлена другая комната.
 * Таким образом, любое из [1,2,1,2], [1,2,2,1], [2,1,2,1], [2,1,1,2] является допустимым решением.
 */

public class AllocatingHotelRooms {

//    @Test
//    void test() {
//        final int[][][] testCases = {
//                {{1, 2}, {2, 4}, {4, 4}},         // Solution [1,2,1] or [2,1,2]
//                {{1, 5}, {2, 4}, {6, 8}, {7, 7}}, // Solution [1,2,1,2], [1,2,2,1], [2,1,2,1], or [2,1,1,2
//                {}, // Solution [1,2,2,3,2], [2,1,1,3,1], [3,1,3,2,1], etc
//
//                {{1, 10}, {2, 5}, {6, 6}, {3, 7}, {6, 6}, {11, 13}, {9, 15}, {8, 14}},
//                // Solutions include [1,2,2,3,4,1,3,2], [1,2,2,3,4,1,2,3],
//                //                   [1,2,4,3,2,1,3,2], [2,3,3,1,4,2,1,3], and others
//
//                {{8, 8}, {5, 8}, {8, 9}, {1, 4}, {1, 3}, {5, 7}, {4, 8}, {2, 2}, {4, 5}, {6, 8}}
//                // Solutions include [4, 1, 5, 1, 2, 4, 2, 3, 3, 3], [5, 4, 2, 2, 1, 2, 3, 3, 1, 1], and others
//        };
//
//        final int[] roomsNeeded = {2, 2, 3, 4, 5};

//        for (int i = 0; i < testCases.length; i++) {
//            final int[][] thisTest = testCases[i];
//            final Preloaded.Pair<Boolean, String> result = Preloaded.validateSolution(thisTest, AllocatingHotelRooms.allocateRooms(thisTest), roomsNeeded[i]);
//            assertTrue(result.getKey(), result.getValue());
//        }
//    }

    @Test
    public void test() {
        int[] ints = allocateRooms(new int[][]{{1, 5}, {2, 4}, {6, 8}, {7, 7}});
        int[] ints2 = allocateRooms(new int[][]{{1, 10}, {2, 5}, {6, 6}, {3, 7}, {6, 6}, {11, 13}, {9, 15}, {8, 14}});
        int[] ints3 = allocateRooms(new int[][]{{8, 8}, {5, 8}, {8, 9}, {1, 4}, {1, 3}, {5, 7}, {4, 8}, {2, 2}, {4, 5}, {6, 8}});
        Assert.assertArrayEquals(new int[]{1,2,1,2},ints);
        Assert.assertArrayEquals(new int[]{1,2,2,3,4,1,2,3},ints2);
        Assert.assertArrayEquals(new int[]{1,2,3,1,2,1,4,3,3,5},ints3);
    }

//   TODO НЕ ПРОХОДИТ ПРОВЕРКУ РАНДОМНЫЙ ТЕСТ Codewars
    public static int[] allocateRooms(int[][] customers) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> roomByDay = new HashMap<>();
        Queue<List<Integer>> client = Arrays.stream(customers)
                .map(customer -> IntStream.rangeClosed(customer[0], customer[1]).boxed()
                        .collect(Collectors.toList())).collect(Collectors.toCollection(LinkedList::new));


        while (!client.isEmpty()) {
            List<Integer> clientDay = client.peek();
            for (int r = 1; r <= 1000; r++) {
                if (Objects.isNull(roomByDay.get(r))) {
                      roomByDay.put(r,clientDay);
                      client.poll();
                      result.add(r);
                      break;
                } else  {
                    List<Integer> days = roomByDay.get(r);
                    boolean isContains = days.stream().anyMatch(clientDay::contains);
                    if (!isContains){
                        days.addAll(clientDay);
                        roomByDay.put(r,days);
                        client.poll();
                        result.add(r);
                        break;
                    }
                }
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }
}

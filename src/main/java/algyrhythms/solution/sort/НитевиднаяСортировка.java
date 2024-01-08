package algyrhythms.solution.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>Нитевидная сортировка</h3>
 * Рекурсивный алгоритм сортировки, который сортирует элементы списка в порядке возрастания.
 * Алгоритм:
 * Пусть input[] — входной список, а output[] — выходной список.
 * <ul>
 * <li>Шаг 1: Создайте еще один пустой список sublist и переместите в него первый элемент input.</li>
 * <li>Шаг 2: Для каждого элемента x из input проверьте, превышает ли x последний добавленный элемент в список.<br/>
 * - Если да, удалите x из input и добавьте в конец sublist.<br/>
 * - Если нет, игнорируйте x (сохраните его в input)</li>
 * <li>Шаг 3: Объединить подсписок в outputp.</li>
 * </ul>
 * Повторите для оставшихся элементов в input и текущих элементов в output.</br>
 * <p>
 * Сложность:
 * В лучшем: O(n)
 * В худшем: O(n^2)
 */
public class НитевиднаяСортировка {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>(Arrays.asList(10, 5, 2, 4, 1, 3, 8, 7, 9, 6));
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), sort(list));
    }

    /**
     * Рекурсивный меиод для выполнения сортировки по цепочкам
     *
     * @param inputList - входной список неотсортированных элементов
     * @return отсотртрованный список в порядке возрастания элементов
     */
    public static List<Integer> sort(List<Integer> inputList) {
        // Базовый вариант: если входной список содержит 1 или меньше элементов, он уже отсортирован
        if (inputList.size() <= 1) {
            return inputList;
        }

        // Инициализируем подсписок первым элементом входного списка
        List<Integer> sublist = new ArrayList<>();
        sublist.add(inputList.remove(0));

        int i = 0;
        while (i < inputList.size()) {
            // Если текущий элемент во входном списке больше, чем
            // последний элемент в подсписке,
            // добавьте его в подсписок; в противном случае перейдите к следующему элементу во входном списке.
            if (inputList.get(i) > sublist.get(sublist.size() - 1)) {
                sublist.add(inputList.remove(i));
            } else {
                i++;
            }
        }

        // sortedSublist содержит отсортированные элементы из текущего подсписка
        List<Integer> sortedSublist = new ArrayList<>(sublist);

        // Рекурсивно отсортировать оставшуюся часть входного списка
        List<Integer> remainingList = sort(inputList);

        //Объедините отсортированный подсписок и отсортированный оставшийся список
        return mergeLists(sortedSublist, remainingList);
    }

    /**
     * Объединение двух отсортированных списков
     *
     * @param list1 - первый список элементов
     * @param list2 - второй список элементов
     * @return объединенный список отсортированных элементов
     */
    private static List<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        while (!list1.isEmpty() && !list2.isEmpty()) {
            if (list1.get(0) < list2.get(0)) {
                result.add(list1.remove(0));
            } else {
                result.add(list2.remove(0));
            }
        }
        result.addAll(list1);
        result.addAll(list2);
        return result;
    }
}

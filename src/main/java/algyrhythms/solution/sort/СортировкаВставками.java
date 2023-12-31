package algyrhythms.solution.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * <h3>Сортировка вставками</h3>
 * <p>
 * Это простой алгоритм сортировки, который неоднократно берет один элемент из неотсортированной части массива и
 * вставляет его в правильную позицию в отсортированной части.
 * <p>
 * Алгоритм: <p>
 * 1. Начинаем с первого элемента, который считается отсортированным. <p>
 * 2. Перебираем неотсортированную часть массива, беря по одному элементу за раз и сравниваем его с элементами в
 * отсортированной части массива.<p>
 * 3. Сдвигаем элементы в отсортированной части массива вправо, пока не найдем правильную позицию для текущего элемента.<p>
 * 4. Как только правильная позиция найдена, вставляем текущий элемент в отсортированную часть массива.<p>
 * 5. Продолжаем этот процесс, пока не будет отсортирован весь массив.<p>
 * Сложность алгоритма:<p>
 * В лучшем случаи:  O(n)<p>
 * В cреднем: O(n^2)<p>
 * В худшем: O(n^2)<p>
 */
public class СортировкаВставками {
    @Test
    public void test() {
        int[] arr = new int[]{10, 5, 2, 4, 1, 3, 8, 7, 9, 6};
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sort(arr));
    }

    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                int tmp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }
}

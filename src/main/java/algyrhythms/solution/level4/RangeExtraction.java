package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * ОПИСАНИЕ:
 * Формат представления упорядоченного списка целых чисел заключается в использовании списка, разделенного запятыми.
 * отдельные целые числа или диапазон целых чисел, обозначенный начальным целым числом, отделенным от конечного целого
 * числа в диапазоне тире, '-'. Диапазон включает все целые числа в интервале, включая обе конечные точки.
 * Он не считается диапазоном, если он  не охватывает как минимум 3 числа. Например, «12,13,15-17».
 * Завершите решение так, чтобы оно принимало список целых чисел в порядке возрастания и возвращало правильно
 * отформатированную строку в формате диапазона.
 */
public class RangeExtraction {
    @Test
    public void test_BasicTests() {
        assertEquals("-6,-3-1,3-5,7-11,14,15,17-20", rangeExtraction(new int[]{-6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20}));
        assertEquals("-3--1,2,10,15,16,18-20", rangeExtraction(new int[]{-3, -2, -1, 2, 10, 15, 16, 18, 19, 20}));
    }

    public static String rangeExtraction(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<String> result = new ArrayList<>();
        List<Integer> tempArr = new ArrayList<>();
        tempArr.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            int first = list.get(i - 1);
            int next = list.get(i);
            if (first + 1 == next) {
                tempArr.add(next);
            } else {
                mergeRes(result, tempArr);
                tempArr.clear();
                tempArr.add(next);
            }
        }
        mergeRes(result, tempArr);
        return String.join(",", result);
    }

    private static void mergeRes(List<String> result, List<Integer> tempArr) {
        if (tempArr.size() >= 3) {
            result.add(tempArr.get(0) + "-" + tempArr.get(tempArr.size() - 1));
        } else if (tempArr.size() == 2) {
            result.add(String.valueOf(tempArr.get(0)));
            result.add(String.valueOf(tempArr.get(1)));
        } else {
            result.add(String.valueOf(tempArr.get(0)));
        }
    }


    //======================== Popular Solution =============================================
    public static String popRangeExtraction(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            int j = i;
            while (j < arr.length - 1 && arr[j] + 1 == arr[j + 1]) j++;
            if (i + 1 < j) {
                i = j;
                sb.append("-");
                sb.append(arr[i]);
            }
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}

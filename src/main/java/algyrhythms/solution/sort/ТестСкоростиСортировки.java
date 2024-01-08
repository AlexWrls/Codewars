package algyrhythms.solution.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ТестСкоростиСортировки {

    @Test
    public void speed() {

//        int capacity = 99999;
//        StreamComparatorJava: 87 ms
//        НитевиднаяСортировка: 67476 ms
//        СортировкаВставками: 3986 ms
//        СортировкаВыбором: 17747 ms
//        СортировкаПузырьком: 20267 ms
//        СортировкаРасчёской: 16 ms
//        СортировкаСлиянием: 17 ms
//        ЦиклическаяСортировка: 20088 ms
//        БитоническаяСортировка: 47 ms
//        БыстраяСортировка: 18 ms

//        int capacity = 9999999;
//        StreamComparatorJava: 6789 ms
//        СортировкаРасчёской: 1750 ms
//        СортировкаСлиянием: 2049 ms
//        БитоническаяСортировка: 4575 ms
//        БыстраяСортировка: 1318 ms


        int capacity = 99999;
        int[] arr = newArr(capacity);
        long start = System.currentTimeMillis();
        List<Integer> javaSort = Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.printf("StreamComparatorJava: %s ms\n", (System.currentTimeMillis() - start));

        arr = newArr(capacity);
        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
        start = System.currentTimeMillis();
        НитевиднаяСортировка.sort(collect);
        System.out.printf("НитевиднаяСортировка: %s ms\n", (System.currentTimeMillis() - start));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] вставками = СортировкаВставками.sort(arr);
        System.out.printf("СортировкаВставками: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(вставками).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] выбором = СортировкаВыбором.sort(arr);
        System.out.printf("СортировкаВыбором: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(выбором).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] пузырьком = СортировкаПузырьком.sort(arr);
        System.out.printf("СортировкаПузырьком: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(пузырьком).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] расчесткой = СортировкаРасчёской.sort(arr);
        System.out.printf("СортировкаРасчёской: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(расчесткой).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] слиянием = СортировкаСлиянием.sort(arr);
        System.out.printf("СортировкаСлиянием: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(слиянием).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] циклическая = ЦиклическаяСортировка.sort(arr);
        System.out.printf("ЦиклическаяСортировка: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(циклическая).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] битоническая = new БитоническаяСортировка().sort(arr);
        System.out.printf("БитоническаяСортировка: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(битоническая).boxed().collect(Collectors.toList()));

        arr = newArr(capacity);
        start = System.currentTimeMillis();
        int[] быстрая = БыстраяСортировка.sort(arr);
        System.out.printf("БыстраяСортировка: %s ms\n", (System.currentTimeMillis() - start));
        Assert.assertEquals(Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()),
                Arrays.stream(быстрая).boxed().collect(Collectors.toList()));
    }

    private int[] newArr(int capacity) {
        Random rnd = new Random();
        return IntStream.range(0, capacity)
                .map(i -> rnd.nextInt(capacity))
                .toArray();
    }
}

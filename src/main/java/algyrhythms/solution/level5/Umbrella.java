package algyrhythms.solution.level5;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

import static algyrhythms.solution.level5.Umbrella.Weather.*;


public class Umbrella {

    @Test
    public void test() {
        Assert.assertEquals(0, Umbrella.minUmbrellas(CLOUDY));
        Assert.assertEquals(1, Umbrella.minUmbrellas(RAINY, RAINY, RAINY, RAINY));
        Assert.assertEquals(2, Umbrella.minUmbrellas(OVERCAST, RAINY, CLEAR, THUNDERSTORMS));
        Assert.assertEquals(6, Umbrella.minUmbrellas(WINDY, SUNNY, WINDY, SUNNY, CLEAR, CLOUDY, CLEAR, CLEAR, SUNNY, RAINY, CLEAR, CLEAR, SUNNY, SUNNY, CLOUDY, WINDY, CLEAR, CLEAR, SUNNY, WINDY, CLEAR, WINDY, WINDY, SUNNY, SUNNY, WINDY, WINDY, WINDY, SUNNY, RAINY, CLEAR, WINDY, CLEAR, CLEAR, CLEAR, WINDY, CLEAR, CLEAR, WINDY, THUNDERSTORMS, WINDY, CLOUDY, WINDY, CLOUDY, CLEAR, CLEAR, CLEAR, WINDY, WINDY, CLEAR, WINDY, WINDY, SUNNY, SUNNY, CLEAR, RAINY, SUNNY, WINDY, WINDY, RAINY, CLEAR, CLEAR, CLOUDY, CLEAR, CLEAR, CLEAR, WINDY, THUNDERSTORMS, CLEAR, CLEAR, SUNNY, CLEAR, SUNNY, CLEAR, CLEAR, CLEAR, CLEAR, WINDY, WINDY, WINDY, SUNNY, CLEAR, WINDY, CLEAR, THUNDERSTORMS, CLEAR, CLEAR, CLEAR, RAINY, WINDY, CLEAR, WINDY, CLEAR, CLOUDY, WINDY, SUNNY, RAINY, THUNDERSTORMS, THUNDERSTORMS, SUNNY));
    }

//    Не понял условия
//    public static int minUmbrellas(Weather... forecast) {
//        int count = 0;
//        boolean useUmbrella = false;
//        List<Weather> weathers = Arrays.stream(forecast).collect(Collectors.toList());
//        for (Weather weather : weathers) {
//            if (weather == RAINY || weather == THUNDERSTORMS) {
//                count = !useUmbrella ? count + 1 : count;
//                useUmbrella = true;
//            } else {
//                useUmbrella = false;
//            }
//        }
//        return count;
//    }

    public enum Weather {
        CLEAR, SUNNY, CLOUDY, RAINY, OVERCAST, WINDY, THUNDERSTORMS;
    }


    public static int minUmbrellas(Weather... forecast) {
        Function<Weather, Boolean> isRainy = (day) -> day == Weather.RAINY || day == Weather.THUNDERSTORMS;
        int home = 0;
        int work = 0;
        for (int i = 0; i < forecast.length; i += 2) {
            if (isRainy.apply(forecast[i])) {
                work++;
                home = Math.max(home - 1, 0);
            }
            if (i < forecast.length - 1 && isRainy.apply(forecast[i + 1])) {
                home++;
                work = Math.max(work - 1, 0);
            }
        }
        return home + work;
    }
}

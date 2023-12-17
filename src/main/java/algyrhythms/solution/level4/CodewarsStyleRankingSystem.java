package algyrhythms.solution.level4;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Напишите класс User, который будет использоваться для расчета прогресса пользователя в системе ранжирования,
 * аналогичной той, которую использует Codewars.
 * <p>
 * Бизнес правила:
 * Пользователь начинает с ранга -8 и может прогрессировать до 8.
 * Нет 0 (нулевого) ранга. Следующий ранг после -1 равен 1.
 * Пользователи будут выполнять действия. Эта деятельность также имеет ранги.
 * Каждый раз, когда пользователь завершает рейтинговое действие, прогресс в рейтинге пользователя обновляется
 * на основе рейтинга действия.
 * Прогресс, полученный от завершенного действия, зависит от текущего ранга пользователя по сравнению с рангом действия.
 * Прогресс ранга пользователя начинается с нуля, каждый раз, когда прогресс достигает 100, ранг пользователя повышается
 * до следующего уровня.
 * Весь оставшийся прогресс, полученный на предыдущем ранге, будет засчитан в прогресс следующего ранга
 * (мы не отбрасываем прогресс). Исключением является случай, когда не осталось другого ранга, к которому можно
 * продвигаться (как только вы достигнете 8-го ранга, прогресс больше не будет).
 * Пользователь не может подняться выше 8-го ранга.
 * Единственный приемлемый диапазон значений ранга — -8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8. Любое другое значение
 * должно вызвать ошибку.
 * Прогресс оценивается так:
 * <p>
 * Завершение действия, которое имеет тот же рейтинг, что и действие пользователя, будет оценено в 3 балла.
 * Завершение действия, рейтинг которого на один ниже, чем у пользователя, будет оценено в 1 балл.
 * Любые завершенные действия, рейтинг которых на 2 уровня или более ниже рейтинга пользователя, будут игнорироваться.
 * Выполнение действия, рейтинг которого выше, чем рейтинг текущего пользователя, ускорит повышение ранга. Чем больше
 * разница между рейтингами, тем больше будет увеличиваться прогресс. Формула: 10 * d * d, где d равна разнице
 * в рейтинге между действием и пользователем.
 * <p>
 * Логические примеры:
 * Если пользователь с рейтингом -8 выполнит действие с рейтингом -7, он получит 10 баллов прогресса.
 * Если пользователь с рейтингом -8 выполнит действие с рейтингом -6, он получит прогресс 40.
 * Если пользователь с рейтингом -8 выполнит действие с рейтингом -5, он получит 90 баллов прогресса.
 * Если пользователь с рейтингом -8 выполнит действие с рейтингом -4, он получит 160 прогресса, в результате чего
 * пользователь будет повышен до ранга -7 и заработает 60 прогресса для перехода к следующему рангу.
 * Если пользователь с рейтингом -1 выполнит действие с рейтингом 1, он получит 10 прогресса
 * (помните, нулевой рейтинг игнорируется).
 * Примеры использования кода:
 * User user = new User();
 * user.rank; // => -8
 * user.progress; // => 0
 * user.incProgress(-7);
 * user.progress; // => 10
 * user.incProgress(-5); // will add 90 progress
 * user.progress; // => 0 // progress is now zero
 * user.rank; // => -7 // rank was upgraded to -7
 * Примечание: В Java некоторые методы могут выдавать IllegalArgumentException .
 * <p>
 * Примечание: Codewars больше не использует этот алгоритм для своей собственной системы ранжирования.
 * Он использует чисто математическое решение, которое дает стабильные результаты независимо от порядка выполнения ряда ранжированных действий.
 */
public class CodewarsStyleRankingSystem {
    /**
     * User codewars =)
     */
    class User {

        private int rank;
        private int progress;

        public User() {
            this.rank = -8;
            this.progress = 0;
        }

        /**
         * Рассчитать прогресс пользователя
         * @param rank ранг решенной задачи пользователем
         */
        public void incProgress(int rank) {
            if (rank == 0 || rank < -8 || rank > 8) {
                throw new IllegalArgumentException();
            }
            if (this.rank == 8) {
                return;
            }
            int curProgress = this.progress;
            if (this.rank == rank) {
                curProgress += 3;
            } else if (rank < this.rank) {
                curProgress += 1;
            } else {
                int d = (int) IntStream.range(this.rank, rank).filter(i -> i != 0).count();
                curProgress += 10 * d * d;
            }
            if (curProgress > 100) {
                this.rank += curProgress / 100;
                curProgress %= 100;
            }
            this.progress = curProgress;
            if (this.rank == 0) this.rank = 1;
        }

        public int getRank() {
            return rank;
        }

        public int getProgress() {
            return progress;
        }
    }

    //  ===============  Testing   ===============
    User user = new User();

    private void do_test(int rank, int expectedRank, int expectedProgress) {
        if (rank != 0) user.incProgress(rank);
        assertEquals("After applying rank of " + rank, expectedProgress, user.progress);
        assertEquals("After applying rank of " + rank, expectedRank, user.rank);
    }

    @Test
    public void testIncProgress() throws Exception {
        do_test(-8, -8, 3);

        user = new User();
        do_test(-7, -8, 10);

        user = new User();
        do_test(-6, -8, 40);

        user = new User();
        do_test(-5, -8, 90);

        user = new User();
        do_test(-4, -7, 60);

        user = new User();
        do_test(-8, -8, 3);


        user = new User();
        do_test(1, -2, 40);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(1, -2, 80);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(1, -1, 20);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(1, -1, 30);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(1, -1, 40);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(2, -1, 80);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(2, 1, 20);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
        do_test(-1, 1, 21);
        System.out.println("rank=" + user.rank + " pro=" + user.progress);
    }

}

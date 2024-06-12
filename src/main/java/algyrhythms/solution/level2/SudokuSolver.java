package algyrhythms.solution.level2;

//{3, 4, 6, 1, 2, 7, 9, 5, 8},
//{7, 8, 5, 6, 9, 4, 1, 3, 2},
//{2, 1, 9, 3, 8, 5, 4, 6, 7},
//{4, 6, 2, 5, 3, 1, 8, 7, 9},
//{9, 3, 1, 2, 7, 8, 6, 4, 5},
//{8, 5, 7, 9, 4, 6, 2, 1, 3},
//{5, 9, 8, 4, 1, 3, 7, 2, 6},
//{6, 2, 4, 7, 5, 9, 3, 8, 1},
//{1, 7, 3, 8, 6, 2, 5, 9, 4};

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

public class SudokuSolver {

    public static void main(String[] args) {
        int[][] puzzle = {
                {0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 7, 0, 0, 4, 0},
                {0, 0, 7, 9, 0, 0, 0, 0, 3},
                {0, 0, 8, 4, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 5, 0, 0, 8, 0},
                {1, 0, 0, 0, 0, 2, 5, 0, 0}};
        int[][] solve = new SudokuSolver(puzzle).solve();
        String s = "stop";
    }

    private final int[][] GRID;
    private final boolean[][] GRID_STATE;

    public SudokuSolver(int[][] grid) {
        this.GRID = grid;
        this.GRID_STATE = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[y][x] != 0) {
                    GRID_STATE[y][x] = true;
                }
            }
        }
    }

    public int[][] solve() {
        while (!isSolve()) {
            mayBe();
            String s = "s";
        }
        return GRID;
    }

    private void mayBe() {
        for (int x = 0; x < GRID.length; x++) {
            for (int y = 0; y < GRID[x].length; y++) {
                if (!GRID_STATE[y][x]) {
                    Map<Integer, Integer> resolve = resolve(x, y);
                    if (limit(resolve)) {
//                        print();
                        reset();
                    } else {
                        GRID[y][x] = getRnd(resolve);
                    }
                }
            }
        }
    }

    private boolean limit(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() != 0 && entry.getValue() > 3) {
                return true;
            }
        }
        return false;
    }

    private Map<Integer, Integer> resolve(int xi, int yi) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x = 0; x < GRID.length; x++) {
            map.put(GRID[yi][x], map.getOrDefault(GRID[yi][x], 0) + 1);
        }
        for (int y = 0; y < GRID[0].length; y++) {
            map.put(GRID[y][xi], map.getOrDefault(GRID[y][xi], 0) + 1);
        }
        Map<Integer, Integer> collectMap = new HashMap<>();
        if (xi >= 0 && xi <= 2 && yi >= 0 && yi <= 2) {
            collectMap.putAll(collect(0, 2, 0, 2));
        }
        if (xi >= 0 && xi <= 2 && yi >= 3 && yi <= 5) {
            collectMap.putAll(collect(0, 2, 3, 5));
        }
        if (xi >= 0 && xi <= 2 && yi >= 6 && yi <= 8) {
            collectMap.putAll(collect(0, 2, 6, 8));
        }
        if (xi >= 3 && xi <= 5 && yi >= 0 && yi <= 2) {
            collectMap.putAll(collect(3, 5, 0, 2));
        }
        if (xi >= 3 && xi <= 5 && yi >= 3 && yi <= 5) {
            collectMap.putAll(collect(3, 5, 3, 5));
        }
        if (xi >= 3 && xi <= 5 && yi >= 6 && yi <= 8) {
            collectMap.putAll(collect(3, 5, 6, 8));
        }
        if (xi >= 6 && xi <= 8 && yi >= 0 && yi <= 2) {
            collectMap.putAll(collect(6, 8, 0, 2));
        }
        if (xi >= 6 && xi <= 8 && yi >= 3 && yi <= 5) {
            collectMap.putAll(collect(6, 8, 3, 5));
        }
        if (xi >= 6 && xi <= 8 && yi >= 6 && yi <= 8) {
            collectMap.putAll(collect(6, 8, 6, 8));
        }
        return concatMap(map, collectMap);
    }

    private Map<Integer, Integer> concatMap(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Map<Integer, Integer> map = new HashMap<>();
        if (map1.size() > map2.size()) {
            map1.forEach((k, v) -> {
                map.put(k, v + map1.getOrDefault(map2.get(k), 0));
            });
        } else {
            map2.forEach((k, v) -> {
                map.put(k, v + map2.getOrDefault(map1.get(k), 0));
            });
        }
        return map;
    }

    private Map<Integer, Integer> collect(int xi, int xj, int yi, int yj) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x = xi; x <= xj; x++) {
            for (int y = yi; y < yj; y++) {
                map.put(GRID[y][x], map.getOrDefault(GRID[y][x], 0) + 1);
            }
        }
        return map;
    }

    private static int getRnd(Map<Integer, Integer> map) {
        Set<Integer> zero = new HashSet<>();
        Set<Integer> one = new HashSet<>();
        Set<Integer> two = new HashSet<>();
        Set<Integer> three = new HashSet<>();
        Stack<Set<Integer>> list = new Stack<>();
        map.forEach((k, v) -> {
            if (k != 0) {
                if (v == 0) {
                    zero.add(k);
                } else if (v == 1) {
                    one.add(k);
                    one.addAll(zero);
                } else if (v == 2) {
                    two.add(k);
                    two.addAll(one);
                } else if (v == 3) {
                    three.add(k);
                    three.addAll(two);

                }
            }
        });
        list.add(zero);
        list.add(one);
        list.add(two);
        list.add(three);
        list.forEach(i->{
            if (i.size()>9){
                list.pop();
            }
        });
        return rnd(list);

    }

    private static int rnd(Stack<Set<Integer>> list) {
        Function<Integer, Integer> rnd = max -> {
            max -= 1;
            return (int) (Math.random() * ++max) + 1;
        };
        int res;
        do {
            res = rnd.apply(9);
        } while (list.peek().contains(res));
        return res;
    }

    private void reset() {
        for (int x = 0; x < GRID.length; x++) {
            for (int y = 0; y < GRID[x].length; y++) {
                if (!GRID_STATE[y][x]) {
                    GRID[y][x] = 0;
                }
            }
        }
    }

    private boolean isSolve() {
        for (int x = 0; x < GRID.length; x++) {
            for (int y = 0; y < GRID[x].length; y++) {
                if (GRID[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void print() {
        System.out.println("Grid state:");
        for (int x = 0; x < GRID.length; x++) {
            for (int y = 0; y < GRID[x].length; y++) {
                System.out.print(GRID[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("========================");
    }
}

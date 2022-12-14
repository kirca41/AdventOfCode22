package day10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day10\\input.txt";
    private static int cycle = 1;
    private static int X = 1;
    private static HashSet<Integer> checkpoints = new HashSet<>();
    private static int sum = 0;

    public static void init() {
        checkpoints.addAll(List.of(20, 60, 100, 140, 180, 220));
    }

    private static void noop() {
        if (checkpoints.contains(cycle))
            sum += cycle * X;

        cycle += 1;
    }

    private static void addx(int value) {
        if (checkpoints.contains(cycle))
            sum += cycle * X;

        cycle += 1;

        if (checkpoints.contains(cycle))
            sum += cycle * X;

        cycle += 1;
        X += value;
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(l -> {
                    if (l.startsWith("noop"))
                        noop();
                    else
                        addx(Integer.parseInt(l.split("\\s+")[1]));
                });
    }

    public static void main(String[] args) throws FileNotFoundException {
        init();
        parseInput();

        System.out.println(sum);
    }
}

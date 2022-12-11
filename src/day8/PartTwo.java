package day8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartTwo {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day8\\input.txt";
    private static List<List<Integer>> listOfLists = new ArrayList<>();

    private static int countTop(int i, int j) {
        if (i == 0)
            return 0;

        int current = listOfLists.get(i).get(j);
        int count = 1;
        for (int k = i - 1; k >= 1; k--) {
            if (listOfLists.get(k).get(j) >= current)
                return count;

            count += 1;
        }

        return count;
    }

    private static int countBottom(int i, int j) {
        if (i == listOfLists.size() - 1)
            return 0;

        int current = listOfLists.get(i).get(j);
        int count = 1;
        for (int k = i + 1; k < listOfLists.size() - 1; k++) {
            if (listOfLists.get(k).get(j) >= current)
                return count;

            count += 1;
        }

        return count;
    }

    private static int countLeft(int i, int j) {
        if (j == 0)
            return 0;

        int current = listOfLists.get(i).get(j);
        int count = 1;
        for (int k = j - 1; k >= 1; k--) {
            if (listOfLists.get(i).get(k) >= current)
                return count;

            count += 1;
        }

        return count;
    }

    private static int countRight(int i, int j) {
        if (j == listOfLists.get(0).size() - 1)
            return 0;

        int current = listOfLists.get(i).get(j);
        int count = 1;
        for (int k = j + 1; k < listOfLists.get(0).size() - 1; k++) {
            if (listOfLists.get(i).get(k) >= current)
                return count;

            count += 1;
        }

        return count;
    }

    public static int getScenicScore() {
        int maxScore = 0;
        int score = 1;
        for (int i = 0; i < listOfLists.size(); i++) {
            for (int j = 0; j < listOfLists.get(0).size(); j++) {
                score = countTop(i, j) * countBottom(i, j) * countLeft(i, j) * countRight(i, j);
                if (score > maxScore) {
                    maxScore = score;
                }

            }
        }

        return maxScore;
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(l -> {
                    List<Integer> innerList = IntStream.range(0, l.length()).map(i -> Integer.parseInt(l.substring(i, i+1)))
                            .boxed()
                            .collect(Collectors.toList());
                    listOfLists.add(innerList);
                });
    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();
        System.out.println(getScenicScore());
    }
}

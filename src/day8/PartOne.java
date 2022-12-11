package day8;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day8\\input.txt";
    private static List<List<Integer>> listOfLists = new ArrayList<>();

    private static boolean checkTop(int i, int j) {
        int current = listOfLists.get(i).get(j);
        for (int k = i - 1; k >= 0; k--) {
            if (listOfLists.get(k).get(j) >= current)
                return false;
        }

        return true;
    }

    private static boolean checkBottom(int i, int j) {
        int current = listOfLists.get(i).get(j);
        for (int k = i + 1; k < listOfLists.size(); k++) {
            if (listOfLists.get(k).get(j) >= current)
                return false;
        }

        return true;
    }

    private static boolean checkLeft(int i, int j) {
        int current = listOfLists.get(i).get(j);
        for (int k = j - 1; k >= 0; k--) {
            if (listOfLists.get(i).get(k) >= current)
                return false;
        }

        return true;
    }

    private static boolean checkRight(int i, int j) {
        int current = listOfLists.get(i).get(j);
        for (int k = j + 1; k < listOfLists.get(0).size(); k++) {
            if (listOfLists.get(i).get(k) >= current)
                return false;
        }

        return true;
    }

    public static int countVisible() {
        int visible = listOfLists.size() * 2 + listOfLists.get(0).size() * 2 - 4;
        for (int i = 1; i < listOfLists.size() - 1; i++) {
            for (int j = 1; j < listOfLists.get(0).size() - 1; j++) {
                if (checkTop(i, j) || checkBottom(i, j) || checkLeft(i, j) || checkRight(i, j)) {
                    visible += 1;
                    continue;
                }
            }
        }

        return visible;
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
        System.out.println(countVisible());
    }
}

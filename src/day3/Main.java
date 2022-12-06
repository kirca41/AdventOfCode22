package day3;

import java.io.*;
import java.util.*;

public class Main {

    private static int priority = 0;
    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day3\\input.txt";

    public static void updatePriority(Character c) {
        if (Character.isUpperCase(c)) {
            int n = c - 'A';
            priority += n + 27;
        } else {
            int n = c - 'a';
            priority += n + 1;
        }
    }

    public static void findCommon(String line) {
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i < line.length(); i++) {
            if (i < (line.length() / 2)) {
                set.add(line.charAt(i));
            } else {
                if (set.contains(line.charAt(i))) {
                    updatePriority(line.charAt(i));
                    break;
                }
            }
        }
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new FileReader(path))
                .lines().forEach(Main::findCommon);

    }

    public static HashSet<Character> populateSet(String rucksack) {
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i < rucksack.length(); i++) {
            set.add(rucksack.charAt(i));
        }

        return set;
    }

    public static void findCommonPartTwo(List<String> group) {
        HashSet<Character> set1 = populateSet(group.get(0));
        HashSet<Character> set2 = populateSet(group.get(1));
        HashSet<Character> set3 = populateSet(group.get(2));
        Character badge = set1.stream().filter(c -> set2.contains(c) && set3.contains(c)).findFirst().get();
        updatePriority(badge);
    }

    public static void parse() throws FileNotFoundException {
        Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path)));
        int i = 0;
        List<String> group = new ArrayList<>();
        while(scanner.hasNextLine()) {
            if (i == 3) {
                findCommonPartTwo(group);
                i = 0;
                group.clear();
            } else {
                i++;
                group.add(scanner.nextLine());
            }
        }

        if (group.size() > 0) {
            findCommonPartTwo(group);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //parseInput();
        parse();
        System.out.println(priority);
    }
}

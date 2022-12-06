package day1;

import java.io.*;
import java.util.*;

public class Main {

    private static TreeSet<Elf> elves = new TreeSet<>();
    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day1\\input.txt";

    public static void parseInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path)));
        List<Integer> calories = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals("")) {
                elves.add(Elf.createElf(calories));
                calories = new ArrayList<>();
            } else {
                calories.add(Integer.parseInt(line));
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();

        Integer mostCalories = elves.first().getTotalCalories();
        System.out.println(mostCalories);
        Integer topThree = elves.stream().limit(3).mapToInt(Elf::getTotalCalories).sum();
        System.out.println(topThree);
    }
}

class Elf implements Comparable<Elf> {

    private List<Integer> calories;

    private Elf(List<Integer> calories) {
        this.calories = calories;
    }

    public static Elf createElf(List<Integer> calories) {
        return new Elf(calories);
    }

    public Integer getTotalCalories() {
        return this.calories.stream().mapToInt(i -> i).sum();
    }

    @Override
    public int compareTo(Elf o) {
        return Integer.compare(o.getTotalCalories(), this.getTotalCalories());
    }
}

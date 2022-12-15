package day11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartOneAndTwo {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day11\\input.txt";
    private static List<Monkey> monkeys = new ArrayList<>();

    public static void simulateRounds() {
        for (int j = 0; j < 10000; j++) {
            monkeys.forEach(m -> {
                IntStream.range(0, m.getItems().size()).forEach(m::inspectItem);
                IntStream.range(0, m.getItems().size())
                        .forEach(i -> monkeys.get(m.throwTo(i)).catchItem(m.getItems().get(i)));
                m.endTurn();
            });
        }
    }

    public static void parseInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path)));
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("Monkey")) {
                Monkey m = new Monkey();
                String[] items = scanner.nextLine().substring(18).split(", ");
                for (int i = 0; i < items.length; i++) {
                    m.getItems().add(Double.parseDouble(items[i]));
                }
                String operation = scanner.nextLine().substring(13);
                m.setOperationAndAmount(operation);
                m.setDivisibleBy(Integer.parseInt(scanner.nextLine().split("\\s+")[4]));
                m.setIfTrue(Integer.parseInt(scanner.nextLine().split("\\s+")[6]));
                m.setIfFalse(Integer.parseInt(scanner.nextLine().split("\\s+")[6]));
                Monkey.modulo *= m.getDivisibleBy();
                monkeys.add(m);
            }
        }
    }

    public static long getMonkeyBusiness() {
        List<Monkey> sortedMonkeys = monkeys.stream().
                sorted(Comparator.comparing(m -> m.getInspections(), Comparator.reverseOrder())).collect(Collectors.toList());
        return (long) sortedMonkeys.get(0).getInspections() * (long) sortedMonkeys.get(1).getInspections();
    }

    public static void main(String[] args) throws FileNotFoundException {
        parseInput();
        simulateRounds();
        System.out.println(getMonkeyBusiness());
    }
}

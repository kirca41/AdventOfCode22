package day5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartOne {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day5\\input.txt";
    private static List<LinkedList<String>> queues = new ArrayList<>();

    public static void init() {
        IntStream.range(0, 9).forEach(i -> queues.add(i, new LinkedList<>()));
    }

    public static void parseInput() throws FileNotFoundException {
        new BufferedReader(new InputStreamReader(new FileInputStream(path)))
                .lines().forEach(l -> {
                    Pattern pattern = Pattern.compile("\\[.\\]");
                    Matcher matcher = pattern.matcher(l);
                    while (matcher.find()) {
                        String group = matcher.group();
                        int start = matcher.start();

                        Queue<String> q = queues.get(start / 4);

                        q.add(group);
                    }

                    if (l.startsWith("move")) {
                        String[] parts = l.split("\\s+");
                        int amount = Integer.parseInt(parts[1]);
                        int from = Integer.parseInt(parts[3]);
                        int to = Integer.parseInt(parts[5]);
                        while(amount != 0) {
                            String toPush = queues.get(from - 1).poll();
                            queues.get(to - 1).addFirst(toPush);
                            amount--;
                        }
                    }
                });
    }

    public static String getSolution() {
        return queues.stream().map(q -> q.pollFirst().substring(1, 2)).collect(Collectors.joining());
    }

    public static void main(String[] args) throws FileNotFoundException {
        init();
        parseInput();
        System.out.println(getSolution());
    }
}

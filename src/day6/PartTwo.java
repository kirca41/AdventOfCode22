package day6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PartTwo {

    private static String path = "C:\\Data\\Kiril\\AdventOfCode22\\src\\day6\\input.txt";

    public static int processInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path)));
        String line = scanner.next();

        for (int i = 0; i <= line.length() - 14; i++) {
            String window = line.substring(i, i + 14);
            if (window.chars().map(c -> (char) c).distinct().count() == 14)
                return i + 14;
        }

        return 0;
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(processInput());
    }
}

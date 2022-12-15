package day11;

import java.util.ArrayList;
import java.util.List;

public class Monkey {

    private List<Double> items;
    private char operation;
    private int amount;
    private int divisibleBy;
    private int ifTrue;
    private int ifFalse;
    private int inspections;
    public static int modulo = 1;

    public Monkey() {
        this.items = new ArrayList<>();
        this.inspections = 0;
    }

    public void inspectItem(int i) {
        Double item = items.get(i);
        switch (operation) {
            case '+':
                if (amount == -1)
                    item += item;
                else
                    item += amount;
                break;
            case '*':
                if (amount == -1)
                    item *= item;
                else
                    item *= amount;
        }
        // Part One
        // item /= 3;
        // item = Math.floor(item);

        // Part Two
        item = item % modulo;
        items.add(i, item);
        items.remove(i + 1);
        this.inspections++;
    }

    public void catchItem(Double item) {
        items.add(item);
    }

    public int throwTo(int i) {
        if (items.get(i) % divisibleBy == 0)
            return ifTrue;

        return ifFalse;
    }

    public void endTurn() {
        this.items = new ArrayList<>();
    }

    public void setOperationAndAmount(String line) {
        String[] parts = line.split("\\s+");
        this.operation = parts[3].charAt(0);
        if (parts[4].equals("old")) {
            this.amount = -1;
        } else {
            this.amount = Integer.parseInt(parts[4]);
        }
    }

    public int getDivisibleBy() {
        return divisibleBy;
    }

    public List<Double> getItems() {
        return items;
    }


    public void setDivisibleBy(int divisibleBy) {
        this.divisibleBy = divisibleBy;
    }


    public void setIfTrue(int ifTrue) {
        this.ifTrue = ifTrue;
    }


    public void setIfFalse(int ifFalse) {
        this.ifFalse = ifFalse;
    }

    public int getInspections() {
        return inspections;
    }
}

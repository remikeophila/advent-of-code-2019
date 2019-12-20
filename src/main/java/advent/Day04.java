package advent;

import java.util.ArrayList;
import java.util.List;

public class Day04 {

    public static boolean isAdjacent(int number) {
        String numberStr = String.valueOf(number);
        Integer lastInt = Integer.valueOf(numberStr.charAt(0));
        for (int i = 1; i < numberStr.length(); i++) {
            Integer current = Integer.valueOf(numberStr.charAt(i));
            if (current.equals(lastInt)) {
                return true;
            }
            lastInt = current;
        }
        return false;
    }

    public static boolean onlyTwoAdjacent(int number) {
        String numberStr = String.valueOf(number);
        Integer lastInt = Integer.valueOf(numberStr.charAt(0));
        List<Integer> repeatedChars = new ArrayList<>();
        int repeat = 1;

        for (int i = 1; i < numberStr.length(); i++) {
            Integer current = Integer.valueOf(numberStr.charAt(i));
            if (current.equals(lastInt)) {
                repeat++;
                if (i == numberStr.length() - 1) {
                    repeatedChars.add(repeat);
                }
            } else {
                repeatedChars.add(repeat);
                repeat = 1;
            }

            lastInt = current;
        }
        return repeatedChars.contains(2);
    }

    public static boolean increasedDigits(int number) {
        String numberStr = String.valueOf(number);
        Integer lastInt = Integer.valueOf(numberStr.charAt(0));
        for (int i = 1; i < numberStr.length(); i++) {
            Integer current = Integer.valueOf(numberStr.charAt(i));
            if (current < lastInt) {
                return false;
            }
            lastInt = current;
        }
        return true;
    }

    public static boolean match(int number) {
        return isAdjacent(number) && increasedDigits(number);
    }

    public static boolean matchPart2(int number) {
        return isAdjacent(number) && increasedDigits(number) && onlyTwoAdjacent(number);
    }

    public static void main(String[] args) {
        int min = 382345;
        int max = 843167;

        int matchingNumbers = 0;
        int matchingNumbersPart2 = 0;
        for (int i = min; i <= max; i++) {
            if (match(i)) {
                matchingNumbers++;
            }
            if (matchPart2(i)) {
                matchingNumbersPart2++;
            }
        }

        System.out.println("Part 1: "+matchingNumbers);
        System.out.println("Part 2: "+matchingNumbersPart2);


    }


}

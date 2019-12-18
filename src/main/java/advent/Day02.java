package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {
    public int[] alarm(int[] assist) {
        int[] alarm = Arrays.copyOf(assist, assist.length);
        int opCodePosition = 0;
        while (true) {
            int opCode = alarm[opCodePosition];
            if (opCode == 99) {
                return alarm;
            }

            int op1Position = alarm[opCodePosition + 1];
            int op1 = alarm[op1Position];

            int op2Position = alarm[opCodePosition + 2];
            int op2 = alarm[op2Position];
            int resultPosition = alarm[opCodePosition + 3];

            int result = 0;

            if (opCode == 1) {
                result = op1 + op2;
            } else if (opCode == 2) {
                result = op1 * op2;
            } else {
                throw new IllegalStateException("Unknown Opcode");
            }

            alarm[resultPosition] = result;
            opCodePosition += 4;
        }

    }

    public int gravityAssist(final int[] program, int noun, int verb) {
        program[1] = noun;
        program[2] = verb;

        int[] result = new Day02().alarm(program);
        return result[0];
    }

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get(Day01.class.getClassLoader().getResource("day02.txt").getPath()));

        List<Integer> integerList = Arrays.stream(input.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        int[] array = new int[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            array[i] = integerList.get(i);
        }

        Day02 day02 = new Day02();

        System.out.println("part 1 : " + day02.gravityAssist(array, 12, 2));
        System.out.println("part 1 : " + day02.gravityAssist(array, 12, 2));

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (day02.gravityAssist(array, i, j) == 19690720) {
                    System.out.println("Found a result ! noun="+i+", verb="+j);
                    System.out.println("Type "+(100 * i + j)+" to validate");
                }
            }
        }
    }
}

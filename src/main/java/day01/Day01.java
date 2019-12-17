package day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {
    public static void main(String[] args) throws IOException {
        List<String> inputMasses = Files.readAllLines(Paths.get(Day01.class.getClassLoader().getResource("day01.txt").getPath()));

        List<Integer> masses = inputMasses.stream().map(mass -> Integer.parseInt(mass)).collect(Collectors.toList());

        List<Integer> fuels = masses.stream().map(Day01::computeFuel).collect(Collectors.toList());

        int requiredFuel = fuels.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Part 1: "+ requiredFuel);

        int totalRequiredFuel = fuels.stream().mapToInt(Day01::computeFuelOfFuel).sum();
        System.out.println("Part 2: "+ totalRequiredFuel);
    }

    private static int computeFuelOfFuel(int fuel) {
        if (computeFuel(fuel) == 0) {
            return fuel;
        }
        return fuel + computeFuelOfFuel(computeFuel(fuel));
    }

    private static int computeFuel(int mass) {
        if (mass / 3 == 0 || mass / 3 < 2) {
            return 0;
        }
        return (mass/3) - 2;
    }
}

package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Day03 {

    private final List<Connection> connections;

    public Day03(List<Connection> connections) {
        this.connections = connections;
    }

    enum Direction {
        U(new Coordinate(0, 1)),
        D(new Coordinate(0, -1)),
        R(new Coordinate(1, 0)),
        L(new Coordinate(-1, 0));

        private final Coordinate coordinate;

        Direction(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

         public Coordinate getCoordinate() {
             return coordinate;
         }
     }

    static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void add(Coordinate coordinateToAdd) {
            this.x = this.x + coordinateToAdd.getX();
            this.y = this.y + coordinateToAdd.getY();
        }

        public Coordinate copy() {
            return new Coordinate(x, y);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

    }

    static class Connection {
         private final Direction direction;
         private final int distance;

        public Connection(Direction direction, int distance) {
            this.direction = direction;
            this.distance = distance;
        }

        public List<Coordinate> pathFrom(Coordinate initialCoordinate) {
            List<Coordinate> path = new ArrayList<>();
            Coordinate currentCoordinate = initialCoordinate;
            for (int i = 0; i < distance; i++) {
                currentCoordinate.add(direction.getCoordinate());
                path.add(currentCoordinate.copy());
            }
            return path;
        }
    }

    public List<Coordinate> trace() {
        Coordinate currentCoordinate = new Coordinate(0, 0);
        List<Coordinate> path = new ArrayList<>();
        for (Connection connection : connections) {
            List<Coordinate> coordinates = connection.pathFrom(currentCoordinate);
            currentCoordinate = coordinates.get(coordinates.size() - 1);
            path.addAll(coordinates);
        }

        return path;
    }

    public static List<Coordinate> intersect(List<Coordinate> coords1, List<Coordinate> coords2) {
        List<Coordinate> intersection = coords1.stream().filter(coordinate -> coords2.contains(coordinate)).collect(Collectors.toList());
        return intersection;
    }


    public static int manhattan(Coordinate coordinate) {
        return Math.abs(coordinate.getX()) + Math.abs(coordinate.getY());
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(Day01.class.getClassLoader().getResource("day03.txt").getPath()));
        List<List<Connection>> wires = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(",");
            List<Connection> connections = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                String connectionStr = split[i];
                Direction direction = Direction.valueOf(connectionStr.charAt(0)+"");

                int distance = Integer.valueOf(connectionStr.substring(1, connectionStr.length()));
                connections.add(new Connection(direction, distance));
            }

            wires.add(connections);
        }


        List<Connection> wire1Connections = wires.get(0);
        Day03 wire1 = new Day03(wire1Connections);

        List<Connection> wire2Connections = wires.get(1);
        Day03 wire2 = new Day03(wire2Connections);

        List<Coordinate> trace1 = wire1.trace();
        List<Coordinate> trace2 = wire2.trace();
        List<Coordinate> intersect = Day03.intersect(trace1, trace2);

        int min = intersect.stream().mapToInt(coordinate -> Day03.manhattan(coordinate)).min().getAsInt();

        System.out.println("Part 1: "+ min);

        int minStep = intersect.stream().mapToInt(coordinate -> trace1.indexOf(coordinate) + trace2.indexOf(coordinate) +2).min().getAsInt();

        System.out.println("Part 2: "+ minStep);

    }
}

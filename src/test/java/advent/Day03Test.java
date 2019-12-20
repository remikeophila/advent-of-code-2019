package advent;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day03Test {
    @Test
    public void traceOneStep() {
        Day03.Connection connection = new Day03.Connection(Day03.Direction.R, 1);

        Day03 day03 = new Day03(Arrays.asList(connection));
        List<Day03.Coordinate> trace = day03.trace();

        assertEquals(1, trace.size());
        assertEquals(1, trace.get(0).getX());
        assertEquals(0, trace.get(0).getY());
    }

    @Test
    public void traceMoreSteps() {
        Day03.Connection connection1 = new Day03.Connection(Day03.Direction.R, 1);
        Day03.Connection connection2 = new Day03.Connection(Day03.Direction.R, 1);
        Day03.Connection connection3 = new Day03.Connection(Day03.Direction.R, 3);

        Day03 day03 = new Day03(Arrays.asList(connection1, connection2, connection3));
        List<Day03.Coordinate> trace = day03.trace();

        assertEquals(5, trace.size());
        assertEquals(5, trace.get(4).getX());
        assertEquals(0, trace.get(4).getY());
    }

    @Test
    public void traceTwoDimension() {
        Day03.Connection connection1 = new Day03.Connection(Day03.Direction.R, 2);
        Day03.Connection connection2 = new Day03.Connection(Day03.Direction.U, 2);

        Day03 day03 = new Day03(Arrays.asList(connection1, connection2));
        List<Day03.Coordinate> trace = day03.trace();

        assertEquals(4, trace.size());
        assertEquals(2, trace.get(3).getX());
        assertEquals(2, trace.get(3).getY());
    }


    @Test
    public void intersectionOneElement() {
        List<Day03.Coordinate> coords1 = Arrays.asList(
                new Day03.Coordinate(1, 0),
                new Day03.Coordinate(2, 0)
        );

        List<Day03.Coordinate> coords2 = Arrays.asList(
                new Day03.Coordinate(1, 0),
                new Day03.Coordinate(3, 0)
        );

        List<Day03.Coordinate> intersect = Day03.intersect(coords1, coords2);

        assertEquals(1, intersect.size());
        assertEquals(new Day03.Coordinate(1, 0), intersect.get(0));
    }

    @Test
    public void testManhattan() {
        int result = Day03.manhattan(new Day03.Coordinate(1, 2));
        assertEquals(3, result);
    }


}


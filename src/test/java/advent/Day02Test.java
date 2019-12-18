package advent;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Day02Test {
    @Test
    public void testWithExample_ShouldWork() {
        Day02 day02 = new Day02();
        int[] example =new int[] {1, 9,10,3, 2,3,11,0, 99, 30,40,50};


        int[] result = day02.alarm(example);
        int[] expected  =new int[] {3500,9,10,70,
                2,3,11,0,
                99,
                30,40,50};

        assertEquals(Arrays.toString(expected), Arrays.toString(result));
    }
}

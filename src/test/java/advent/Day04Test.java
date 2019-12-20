package advent;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Day04Test {

    @Test
    public void adjacentNumber_ShouldReturnTrue() {
        assertTrue(Day04.isAdjacent(122345));
    }

    @Test
    public void notAdjacentNumber_ShouldReturnFalse() {
        assertFalse(Day04.isAdjacent(123456));
    }

    @Test
    public void increaseDigit_shouldReturnTrue() {
        assertTrue(Day04.increasedDigits(123789));
    }

    @Test
    public void notIncreaseDigit_shouldReturnFalse() {
        assertFalse(Day04.increasedDigits(223450));
    }

    @Test
    public void sameDigit_IncreaseDigit_shouldReturnTrue() {
        assertTrue(Day04.increasedDigits(111111));
    }

    @Test
    public void only2adjacentDigits_ShouldReturnTrue() {
        assertTrue(Day04.onlyTwoAdjacent(111122));
    }

    @Test
    public void repeated_adjacentDigits_ShouldReturnTrue() {
        assertTrue(Day04.onlyTwoAdjacent(112233));
    }

    @Test
    public void not2adjacentDigits_ShouldReturnFalse() {
        assertFalse(Day04.onlyTwoAdjacent(123444));
    }
}

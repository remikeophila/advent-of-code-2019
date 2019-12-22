package advent;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {
    private int exampleOpCode = 1002;
    @Test
    public void parseOperation_exampleTest_OpType() {
        Day05.OperationType operationType = Day05.parseOperationType(exampleOpCode);
        assertEquals(Day05.OpType.MULTIPLY, operationType.getOpType());
    }

    @Test
    public void firstParameterType_parseOperation_exampleTest() {
        Day05.OperationType operationType = Day05.parseOperationType(exampleOpCode);
        assertEquals(Day05.ParameterType.POSITION, operationType.getFirstParameterType());
    }

    @Test
    public void secondParameterType_parseOperation_exampleTest() {
        Day05.OperationType operationType = Day05.parseOperationType(exampleOpCode);
        assertEquals(Day05.ParameterType.IMMEDIATE, operationType.getSecondParameterType());
    }

    @Test
    public void thirdParameterType_parseOperation_exampleTest() {
        Day05.OperationType operationType = Day05.parseOperationType(exampleOpCode);
        assertEquals(Day05.ParameterType.POSITION, operationType.getThirdParameterType());
    }
}

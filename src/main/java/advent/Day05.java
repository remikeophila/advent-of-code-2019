package advent;

import jdk.dynalink.Operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day05 {

    public enum OpType {
        SUM(3),
        MULTIPLY(3),
        INPUT(1),
        OUTPUT(1),
        JUMP_IF_TRUE(2),
        JUMP_IF_FALSE(2),
        LESS_THAN(3),
        EQUALS(3),
        NONE(0),
        EXIT(0);

        private final int parameters;

        OpType(int parameters) {
            this.parameters = parameters;
        }

        public int getParameters() {
            return parameters;
        }

        static OpType getOp(int code) {
            switch (code) {
                case 1: return SUM;
                case 2: return MULTIPLY;
                case 3: return INPUT;
                case 4: return OUTPUT;
                case 5: return JUMP_IF_TRUE;
                case 6: return JUMP_IF_FALSE;
                case 7: return LESS_THAN;
                case 8: return EQUALS;
                case 99: return EXIT;
                default: return NONE;
            }
        }

    }

    public enum ParameterType {
        POSITION(0),
        IMMEDIATE(1),
        NONE(-1);

        private final int parameterCodeType;

        ParameterType(int parameterCodeType) {
            this.parameterCodeType = parameterCodeType;
        }

        public static ParameterType from(int parameterCodeType) {
            if (parameterCodeType == 0) {
                return POSITION;
            } else if (parameterCodeType == 1) {
                return IMMEDIATE;
            }

            return NONE;
        }
    }

    static class OperationType {
        private final OpType opType;
        private final ParameterType firstParameterType;
        private final ParameterType secondParameterType;
        private final ParameterType thirdParameterType;

        public OperationType(OpType opType,
                             ParameterType firstParameterType,
                             ParameterType secondParameterType,
                             ParameterType thirdParameterType) {
            this.opType = opType;
            this.firstParameterType = firstParameterType;
            this.secondParameterType = secondParameterType;
            this.thirdParameterType = thirdParameterType;
        }

        public OpType getOpType() {
            return opType;
        }

        public ParameterType getFirstParameterType() {
            return firstParameterType;
        }

        public ParameterType getSecondParameterType() {
            return secondParameterType;
        }

        public ParameterType getThirdParameterType() {
            return thirdParameterType;
        }
    }

    private static ParameterType parseFirstParameterType(int instructionCode) {
        String instructionStr = Integer.toString(instructionCode);
        int length = instructionStr.length();
        String firstParameterTypeStr = instructionStr.substring(length - 3, length - 2);
        int firstParameterCode = Integer.parseInt(firstParameterTypeStr);

        return ParameterType.from(firstParameterCode);
    }

    private static ParameterType parseSecondParameterType(int instructionCode) {
        String instructionStr = Integer.toString(instructionCode);
        int length = instructionStr.length();
        if (length < 4) {
            return ParameterType.POSITION;
        }
        String firstParameterTypeStr = instructionStr.substring(length - 4, length - 3);
        int firstParameterCode = Integer.parseInt(firstParameterTypeStr);

        return ParameterType.from(firstParameterCode);
    }

    private static ParameterType parseThirdParameterType(int instructionCode) {
        String instructionStr = Integer.toString(instructionCode);
        int length = instructionStr.length();
        if (length < 5) {
            return ParameterType.POSITION;
        }
        String firstParameterTypeStr = instructionStr.substring(length - 5, length - 4);
        int firstParameterCode = Integer.parseInt(firstParameterTypeStr);

        return ParameterType.from(firstParameterCode);
    }

    private static OpType parseOpType(int instructionCode) {
        String instructionStr = Integer.toString(instructionCode);
        int length = instructionStr.length();
        String opCodeStr = instructionStr.substring(length - 2, length);
        Integer opTypeCode = Integer.valueOf(opCodeStr);
        return  OpType.getOp(opTypeCode);
    }

    public static OperationType parseOperationType(int instructionCode) {
        OpType opType = parseOpType(instructionCode);
        ParameterType firstParameterType = parseFirstParameterType(instructionCode);

        ParameterType secondParameterType;
        ParameterType thirdParameterType;
        if (opType == OpType.INPUT || opType == OpType.OUTPUT) {
            secondParameterType = ParameterType.POSITION;
            thirdParameterType = ParameterType.POSITION;


        } else {
            secondParameterType = parseSecondParameterType(instructionCode);
            thirdParameterType = parseThirdParameterType(instructionCode);
        }

        return new OperationType(opType, firstParameterType, secondParameterType, thirdParameterType);
    }

    public static OperationType operationType(int[] input, int start) {
        int opCode = input[start];
        OpType opType = OpType.getOp(opCode);
        OperationType operationType;
        if (opType == OpType.NONE) {
            operationType = parseOperationType(opCode);
        } else {
            operationType = new OperationType(opType,
                    ParameterType.POSITION,
                    ParameterType.POSITION,
                    ParameterType.POSITION);
        }

        return operationType;
    }

    public static void sumOrMult(int[] input, int position, OperationType operationType) {
        int op1;
        if (operationType.getFirstParameterType() == ParameterType.POSITION) {
            int op1Position = input[position + 1];
            op1 = input[op1Position];
        } else {
            op1 = input[position + 1];
        }

        int op2;
        if (operationType.getSecondParameterType() == ParameterType.POSITION) {
            int op2Position = input[position + 2];
            op2 = input[op2Position];
        } else {
            op2 = input[position + 2];
        }

        int resultPosition = input[position + 3];

        int result;

        if (operationType.getOpType() == OpType.SUM) {
            result = op1 + op2;
        } else {
            result = op1 * op2;
        }

        input[resultPosition] = result;
    }

    public static void readInput(int[] input, int position, OperationType operationType) {
        System.out.println("Enter a number: ");
        Scanner in = new Scanner(System.in);
        int value = Integer.parseInt(in.next());
        int adressToStore;
        if (operationType.getFirstParameterType() == ParameterType.POSITION) {
            adressToStore = input[position + 1];
        } else {
            adressToStore = position + 1;
        }

        input[adressToStore] = value;
    }

    public static void output(int[] input, int position, OperationType operationType) {
        int adressToRead;
        if (operationType.getFirstParameterType() == ParameterType.POSITION) {
            adressToRead = input[position + 1];
        } else {
            adressToRead = position + 1;
        }
        System.out.println("Value at Input["+adressToRead+"] = "+input[adressToRead]);
    }

    public static void jumpIfTrue(int[] input, int position, OperationType operationType) {

    }

    public static void jumpIfFalse(int[] input, int position, OperationType operationType) {

    }

    public static void lessThan(int[] input, int position, OperationType operationType) {

    }

    public static void equals(int[] input, int position, OperationType operationType) {

    }



    public static void compute(int[] input) {
        int currentPosition = 0;
        OperationType operationType = operationType(input, currentPosition);

        while (operationType.opType != OpType.EXIT) {

            if (operationType.opType == OpType.SUM || operationType.opType == OpType.MULTIPLY) {
                sumOrMult(input, currentPosition, operationType);
                currentPosition += 4;
            } else if(operationType.getOpType() == OpType.INPUT) {
                readInput(input, currentPosition, operationType);
                currentPosition += 2;
            } else if(operationType.getOpType() == OpType.OUTPUT) {
                output(input, currentPosition, operationType);
                currentPosition += 2;
            }

            operationType = operationType(input, currentPosition);
        }
    }


    public static void main(String[] args) throws IOException {
        String intputStr = Files.readString(Paths.get(Day01.class.getClassLoader().getResource("day05.txt").getPath()));

        List<Integer> integerList = Arrays.stream(intputStr.split(",")).map(Integer::parseInt).collect(Collectors.toList());

        int[] input = new int[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            input[i] = integerList.get(i);
        }

        compute(input);
    }
}
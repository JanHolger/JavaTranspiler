package eu.bebendorf.bytecodemanipulator.instruction;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class CodeParser {

    public static List<Instruction> parse(byte[] source) {
        List<Instruction> instructions = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.wrap(source);
        while (bb.hasRemaining()) {
            int offset = bb.position() + 4;
            int rawOpCode = ByteCodeHelper.readUnsignedByte(bb);
            OpCode opCode = OpCode.fromCode(rawOpCode);
            if(opCode == null)
                throw new RuntimeException("Unknown Opcode 0x"+Integer.toHexString(rawOpCode));
            instructions.add(opCode.getConstructor().construct(opCode, offset, opCode.readBytes(bb)));
        }
        return instructions;
    }

}

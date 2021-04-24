package eu.bebendorf.bytecodemanipulator.instruction;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import lombok.Getter;

@Getter
public class ShortIndexInstruction extends Instruction {

    final int index;

    public ShortIndexInstruction(OpCode code, int offset, byte[] bytes) {
        super(code, offset, bytes);
        index = ByteCodeHelper.toUnsignedByte(bytes[0]);
    }

}

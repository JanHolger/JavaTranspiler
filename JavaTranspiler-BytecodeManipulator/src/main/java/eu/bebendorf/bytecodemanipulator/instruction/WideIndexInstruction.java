package eu.bebendorf.bytecodemanipulator.instruction;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public class WideIndexInstruction extends Instruction {

    final int index;

    public WideIndexInstruction(OpCode code, int offset, byte[] bytes) {
        super(code, offset, bytes);
        index = ByteCodeHelper.readUnsignedShort(ByteBuffer.wrap(bytes));
    }

}

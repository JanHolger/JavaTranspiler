package eu.bebendorf.bytecodemanipulator.instruction;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public class ShortOrWideIndexInstruction extends Instruction {

    final int index;

    public ShortOrWideIndexInstruction(OpCode code, int offset, byte[] bytes) {
        super(code, offset, bytes);
        if(bytes.length == 1) {
            index = ByteCodeHelper.toUnsignedByte(bytes[0]);
        } else {
            index = ByteCodeHelper.readUnsignedShort(ByteBuffer.wrap(bytes));
        }
    }

}

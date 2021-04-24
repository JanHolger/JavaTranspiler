package eu.bebendorf.bytecodemanipulator.instruction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public class Instruction {

    OpCode code;
    int offset;
    byte[] bytes;

    public interface Constructor {
        Instruction construct(OpCode code, int offset, byte[] bytes);
    }

}

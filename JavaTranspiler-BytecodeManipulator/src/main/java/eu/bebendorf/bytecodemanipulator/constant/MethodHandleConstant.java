package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MethodHandleConstant extends Constant {

    byte referenceKind;
    int referenceIndex;

    public ConstantTag getTag() {
        return ConstantTag.METHOD_HANDLE;
    }

}

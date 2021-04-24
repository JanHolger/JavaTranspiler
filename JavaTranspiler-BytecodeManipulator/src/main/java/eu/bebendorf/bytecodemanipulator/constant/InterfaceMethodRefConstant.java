package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InterfaceMethodRefConstant extends Constant {

    int classIndex;
    int nameAndTypeIndex;

    public ConstantTag getTag() {
        return ConstantTag.INTERFACE_METHOD_REF;
    }

}

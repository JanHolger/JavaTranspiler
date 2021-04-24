package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InvokeDynamicConstant extends Constant {

    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    public ConstantTag getTag() {
        return ConstantTag.INVOKE_DYNAMIC;
    }

}

package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MethodTypeConstant extends Constant {

    int descriptorIndex;

    public ConstantTag getTag() {
        return ConstantTag.METHOD_TYPE;
    }

}

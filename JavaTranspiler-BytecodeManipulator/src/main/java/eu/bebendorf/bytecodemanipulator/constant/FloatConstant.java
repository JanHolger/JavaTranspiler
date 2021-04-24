package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FloatConstant extends Constant {

    float value;

    public ConstantTag getTag() {
        return ConstantTag.FLOAT;
    }

}

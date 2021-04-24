package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IntegerConstant extends Constant {

    int value;

    public ConstantTag getTag() {
        return ConstantTag.INTEGER;
    }

}

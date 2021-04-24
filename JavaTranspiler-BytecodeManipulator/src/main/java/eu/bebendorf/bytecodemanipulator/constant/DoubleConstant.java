package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoubleConstant extends Constant {

    double value;

    public ConstantTag getTag() {
        return ConstantTag.DOUBLE;
    }

}

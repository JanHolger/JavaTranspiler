package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LongConstant extends Constant {

    long value;

    public ConstantTag getTag() {
        return ConstantTag.LONG;
    }

}

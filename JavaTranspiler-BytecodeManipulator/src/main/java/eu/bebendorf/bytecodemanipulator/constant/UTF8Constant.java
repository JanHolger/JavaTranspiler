package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UTF8Constant extends Constant {

    String string;

    public ConstantTag getTag() {
        return ConstantTag.UTF8;
    }

}

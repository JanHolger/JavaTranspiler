package eu.bebendorf.bytecodemanipulator.constant;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StringConstant extends Constant {

    int index;

    public ConstantTag getTag() {
        return ConstantTag.STRING;
    }

    public String getString(ClassFile cf) {
        return cf.getConstantPool().getConstant(index).asUTF().getString();
    }

}

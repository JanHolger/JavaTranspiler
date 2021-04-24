package eu.bebendorf.bytecodemanipulator.constant;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NameAndTypeConstant extends Constant {

    int nameIndex;
    int descriptorIndex;

    public ConstantTag getTag() {
        return ConstantTag.NAME_AND_TYPE;
    }

    public String getName(ClassFile cf) {
        return cf.getConstantPool().getConstant(nameIndex).asUTF().getString();
    }

    public String getDescriptor(ClassFile cf) {
        return cf.getConstantPool().getConstant(descriptorIndex).asUTF().getString();
    }

}

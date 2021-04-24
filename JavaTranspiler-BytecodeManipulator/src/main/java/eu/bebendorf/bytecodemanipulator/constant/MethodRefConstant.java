package eu.bebendorf.bytecodemanipulator.constant;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MethodRefConstant extends Constant {

    int classIndex;
    int nameAndTypeIndex;

    public ConstantTag getTag() {
        return ConstantTag.METHOD_REF;
    }

    public String getClassName(ClassFile cf) {
        return getClassConstant(cf).getName(cf);
    }

    public ClassConstant getClassConstant(ClassFile cf) {
        return cf.getConstantPool().getConstant(classIndex).asClass();
    }

    public String getName(ClassFile cf) {
        return getNameAndTypeConstant(cf).getName(cf);
    }

    public String getDescriptor(ClassFile cf) {
        return getNameAndTypeConstant(cf).getDescriptor(cf);
    }

    public NameAndTypeConstant getNameAndTypeConstant(ClassFile cf) {
        return cf.getConstantPool().getConstant(nameAndTypeIndex).asNameAndType();
    }

}

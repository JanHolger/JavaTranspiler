package eu.bebendorf.bytecodemanipulator.attribute;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionTableEntry {

    int startPC;
    int endPC;
    int handlerPC;
    int catchType;

    public String getCatchTypeName(ClassFile cf) {
        return cf.getConstantPool().getConstant(catchType).asClass().getName(cf);
    }

}

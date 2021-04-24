package eu.bebendorf.bytecodemanipulator;

import eu.bebendorf.bytecodemanipulator.attribute.CodeAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;

import static eu.bebendorf.bytecodemanipulator.ByteCodeHelper.readUnsignedShort;

@Getter
@Setter
@AllArgsConstructor
public class AttributeInfo {

    ClassFile classFile;
    int nameIndex;
    byte[] data;

    public String getName() {
        return classFile.getConstantPool().getConstant(nameIndex).asUTF().getString();
    }

    public static AttributeInfo read(ClassFile classFile, ByteBuffer bb) {
        int nameIndex = readUnsignedShort(bb);
        byte[] bytes = new byte[bb.getInt()];
        bb.get(bytes);
        return new AttributeInfo(classFile, nameIndex, bytes);
    }

    public CodeAttribute asCode() {
        return CodeAttribute.fromData(classFile, data);
    }

}

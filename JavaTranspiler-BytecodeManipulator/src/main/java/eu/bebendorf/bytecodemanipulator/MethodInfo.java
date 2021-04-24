package eu.bebendorf.bytecodemanipulator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import static eu.bebendorf.bytecodemanipulator.ByteCodeHelper.readUnsignedShort;

@Getter
@Setter
@AllArgsConstructor
public class MethodInfo {

    ClassFile classFile;
    Flags flags;
    int nameIndex;
    int descriptorIndex;
    List<AttributeInfo> attributes;

    public String getName() {
        return classFile.getConstantPool().getConstant(nameIndex).asUTF().getString();
    }

    public void setName(String name) {
        classFile.getConstantPool().getConstant(nameIndex).asUTF().setString(name);
    }

    public String getDescriptor() {
        return classFile.getConstantPool().getConstant(descriptorIndex).asUTF().getString();
    }

    public void setDescriptor(String descriptor) {
        classFile.getConstantPool().getConstant(descriptorIndex).asUTF().setString(descriptor);
    }

    public static MethodInfo read(ClassFile classFile, ByteBuffer bb) {
        short accessFlags = bb.getShort();
        int nameIndex = readUnsignedShort(bb);
        int descriptorIndex = readUnsignedShort(bb);
        List<AttributeInfo> attributes = new ArrayList<>();
        int attributeCount = readUnsignedShort(bb);
        for(int i = 0; i < attributeCount; i++)
            attributes.add(AttributeInfo.read(classFile, bb));
        return new MethodInfo(classFile, new Flags(accessFlags), nameIndex, descriptorIndex, attributes);
    }

}

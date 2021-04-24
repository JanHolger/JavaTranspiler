package eu.bebendorf.bytecodemanipulator.attribute;

import eu.bebendorf.bytecodemanipulator.AttributeInfo;
import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CodeAttribute {

    ClassFile classFile;
    int maxStack;
    int maxLocals;
    byte[] code;
    List<ExceptionTableEntry> exceptionTable;
    List<AttributeInfo> attributes;

    public static CodeAttribute fromData(ClassFile classFile, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        int maxStack = ByteCodeHelper.readUnsignedShort(bb);
        int maxLocals = ByteCodeHelper.readUnsignedShort(bb);
        byte[] code = new byte[bb.getInt()];
        bb.get(code);
        int eTableLen = ByteCodeHelper.readUnsignedShort(bb);
        List<ExceptionTableEntry> exceptionTable = new ArrayList<>();
        for(int i=0; i<eTableLen; i++) {
            exceptionTable.add(new ExceptionTableEntry(
                    ByteCodeHelper.readUnsignedShort(bb),
                    ByteCodeHelper.readUnsignedShort(bb),
                    ByteCodeHelper.readUnsignedShort(bb),
                    ByteCodeHelper.readUnsignedShort(bb)
            ));
        }
        List<AttributeInfo> attributes = new ArrayList<>();
        int attributeCount = ByteCodeHelper.readUnsignedShort(bb);
        for(int i = 0; i < attributeCount; i++)
            attributes.add(AttributeInfo.read(classFile, bb));
        return new CodeAttribute(classFile, maxStack, maxLocals, code, exceptionTable, attributes);
    }

}

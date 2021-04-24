package eu.bebendorf.bytecodemanipulator;

import eu.bebendorf.bytecodemanipulator.constant.Constant;
import eu.bebendorf.bytecodemanipulator.constant.ConstantPool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static eu.bebendorf.bytecodemanipulator.ByteCodeHelper.readUnsignedShort;

public class ClassFileParser {

    public static ClassFile parse(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int r;
            byte[] buffer = new byte[1024];
            while ((r = fis.read(buffer)) != -1)
                baos.write(buffer, 0, r);
            fis.close();
            return parse(baos.toByteArray());
        } catch (IOException ignored) {
            return null;
        }
    }

    public static ClassFile parse(byte[] byteCode) {
        ByteBuffer bb = ByteBuffer.wrap(byteCode);
        if(bb.getInt() != 0xCafeBabe)
            throw new RuntimeException("Class file doesn't start with 0xCAFEBABE");
        int minorVersion = readUnsignedShort(bb);
        int majorVersion = readUnsignedShort(bb);
        int constantPoolSize = readUnsignedShort(bb) - 1;
        ConstantPool constantPool = new ConstantPool();
        for(int i = 0; i < constantPoolSize; i++)
            constantPool.addConstant(Constant.read(bb));
        short accessFlags = bb.getShort();
        int classIndex = readUnsignedShort(bb);
        int superClassIndex = readUnsignedShort(bb);
        ClassFile classFile = new ClassFile(new Version(majorVersion, minorVersion), constantPool, new Flags(accessFlags), classIndex, superClassIndex);
        int interfaceClassCount = readUnsignedShort(bb);
        for(int i = 0; i < interfaceClassCount; i++)
            classFile.getInterfaceClassIndexes().add(readUnsignedShort(bb));
        int fieldCount = readUnsignedShort(bb);
        for(int i = 0; i < fieldCount; i++)
            classFile.getFields().add(FieldInfo.read(classFile, bb));
        int methodCount = readUnsignedShort(bb);
        for(int i = 0; i < methodCount; i++)
            classFile.getMethods().add(MethodInfo.read(classFile, bb));
        int attributeCount = readUnsignedShort(bb);
        for(int i = 0; i < attributeCount; i++)
            classFile.getAttributes().add(AttributeInfo.read(classFile, bb));
        return classFile;
    }

}

package eu.bebendorf.bytecodemanipulator;

import eu.bebendorf.bytecodemanipulator.constant.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static eu.bebendorf.bytecodemanipulator.ByteCodeHelper.toBytes;
import static eu.bebendorf.bytecodemanipulator.ByteCodeHelper.toSignedShortBytes;

public class ClassFileWriter {

    public static void write(File file, ClassFile classFile) {
        byte[] byteCode = write(classFile);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(byteCode);
            fos.flush();
            fos.close();
        } catch (IOException ignored) {}
    }

    public static byte[] write(ClassFile classFile) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            os.write(toBytes(0xCafeBabe));
            os.write(toSignedShortBytes(classFile.getVersion().getMinorVersion()));
            os.write(toSignedShortBytes(classFile.getVersion().getMajorVersion()));
            os.write(toSignedShortBytes(classFile.getConstantPool().getConstants().size() + 1));
            for(Constant constant : classFile.getConstantPool().getConstants())
                writeConstant(os, constant);
            os.write(toBytes(classFile.getFlags().getFlags()));
            os.write(toSignedShortBytes(classFile.getClassIndex()));
            os.write(toSignedShortBytes(classFile.getSuperClassIndex()));
            os.write(toSignedShortBytes(classFile.getInterfaceClassIndexes().size()));
            for(Integer index : classFile.getInterfaceClassIndexes())
                os.write(toSignedShortBytes(index));
            os.write(toSignedShortBytes(classFile.getFields().size()));
            for(FieldInfo info : classFile.getFields())
                writeFieldInfo(os, info);
            os.write(toSignedShortBytes(classFile.getMethods().size()));
            for(MethodInfo info : classFile.getMethods())
                writeMethodInfo(os, info);
            os.write(toSignedShortBytes(classFile.getAttributes().size()));
            for(AttributeInfo info : classFile.getAttributes())
                writeAttributeInfo(os, info);
        } catch (IOException exception) {
            // Will never happen
        }
        return os.toByteArray();
    }

    private static void writeFieldInfo(OutputStream os, FieldInfo info) throws IOException {
        os.write(toBytes(info.getFlags().getFlags()));
        os.write(toSignedShortBytes(info.getNameIndex()));
        os.write(toSignedShortBytes(info.getDescriptorIndex()));
        os.write(toSignedShortBytes(info.getAttributes().size()));
        for(AttributeInfo attr : info.getAttributes())
            writeAttributeInfo(os, attr);
    }

    private static void writeMethodInfo(OutputStream os, MethodInfo info) throws IOException {
        os.write(toBytes(info.getFlags().getFlags()));
        os.write(toSignedShortBytes(info.getNameIndex()));
        os.write(toSignedShortBytes(info.getDescriptorIndex()));
        os.write(toSignedShortBytes(info.getAttributes().size()));
        for(AttributeInfo attr : info.getAttributes())
            writeAttributeInfo(os, attr);
    }

    private static void writeAttributeInfo(OutputStream os, AttributeInfo info) throws IOException {
        os.write(toSignedShortBytes(info.getNameIndex()));
        os.write(toBytes(info.getData().length));
        os.write(info.getData());
    }

    private static void writeConstant(OutputStream os, Constant constant) throws IOException {
        if(constant.getTag() == null)
            return;
        os.write(new byte[]{ constant.getTag().getTag() });
        switch (constant.getTag()) {
            case UTF8:
                byte[] bytes = ((UTF8Constant) constant).getString().getBytes(StandardCharsets.UTF_8);
                os.write(toSignedShortBytes(bytes.length));
                os.write(bytes);
                break;
            case FIELD_REF:
                os.write(toSignedShortBytes(((FieldRefConstant) constant).getClassIndex()));
                os.write(toSignedShortBytes(((FieldRefConstant) constant).getNameAndTypeIndex()));
                break;
            case METHOD_REF:
                os.write(toSignedShortBytes(((MethodRefConstant) constant).getClassIndex()));
                os.write(toSignedShortBytes(((MethodRefConstant) constant).getNameAndTypeIndex()));
                break;
            case INTERFACE_METHOD_REF:
                os.write(toSignedShortBytes(((InterfaceMethodRefConstant) constant).getClassIndex()));
                os.write(toSignedShortBytes(((InterfaceMethodRefConstant) constant).getNameAndTypeIndex()));
                break;
            case CLASS:
                os.write(toSignedShortBytes(((ClassConstant) constant).getIndex()));
                break;
            case NAME_AND_TYPE:
                os.write(toSignedShortBytes(((NameAndTypeConstant) constant).getNameIndex()));
                os.write(toSignedShortBytes(((NameAndTypeConstant) constant).getDescriptorIndex()));
                break;
            case STRING:
                os.write(toSignedShortBytes(((StringConstant) constant).getIndex()));
                break;
            case INTEGER:
                os.write(toBytes(((IntegerConstant) constant).getValue()));
                break;
            case LONG:
                os.write(toBytes(((LongConstant) constant).getValue()));
                break;
            case FLOAT:
                os.write(toBytes(((FloatConstant) constant).getValue()));
                break;
            case DOUBLE:
                os.write(toBytes(((DoubleConstant) constant).getValue()));
                break;
            case METHOD_TYPE:
                os.write(toSignedShortBytes(((MethodTypeConstant) constant).getDescriptorIndex()));
                break;
            case METHOD_HANDLE:
                os.write(new byte[]{ ((MethodHandleConstant) constant).getReferenceKind() });
                os.write(toSignedShortBytes(((MethodHandleConstant) constant).getReferenceIndex()));
                break;
            case INVOKE_DYNAMIC:
                os.write(toSignedShortBytes(((InvokeDynamicConstant) constant).getBootstrapMethodAttrIndex()));
                os.write(toSignedShortBytes(((InvokeDynamicConstant) constant).getNameAndTypeIndex()));
                break;
        }
    }

}

package eu.bebendorf.bytecodemanipulator.constant;

import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static eu.bebendorf.bytecodemanipulator.ByteCodeHelper.readUnsignedShort;

@Getter
public abstract class Constant {

    public abstract ConstantTag getTag();

    public UTF8Constant asUTF(){
        return (UTF8Constant) this;
    }

    public NameAndTypeConstant asNameAndType(){
        return (NameAndTypeConstant) this;
    }

    public ClassConstant asClass(){
        return (ClassConstant) this;
    }

    public StringConstant asString() {
        return (StringConstant) this;
    }

    public FieldRefConstant asField() {
        return (FieldRefConstant) this;
    }

    public MethodRefConstant asMethod() {
        return (MethodRefConstant) this;
    }

    public static Constant read(ByteBuffer bb) {
        ConstantTag tag = ConstantTag.get(bb.get());
        if(tag == null)
            throw new RuntimeException("Invalid Tag");
        switch (tag) {
            case UTF8:
                byte[] bytes = new byte[readUnsignedShort(bb)];
                bb.get(bytes);
                return new UTF8Constant(new String(bytes, StandardCharsets.UTF_8));
            case FIELD_REF:
                return new FieldRefConstant(readUnsignedShort(bb), readUnsignedShort(bb));
            case METHOD_REF:
                return new MethodRefConstant(readUnsignedShort(bb), readUnsignedShort(bb));
            case INTERFACE_METHOD_REF:
                return new InterfaceMethodRefConstant(readUnsignedShort(bb), readUnsignedShort(bb));
            case CLASS:
                return new ClassConstant(readUnsignedShort(bb));
            case NAME_AND_TYPE:
                return new NameAndTypeConstant(readUnsignedShort(bb), readUnsignedShort(bb));
            case STRING:
                return new StringConstant(readUnsignedShort(bb));
            case INTEGER:
                return new IntegerConstant(bb.getInt());
            case LONG:
                return new LongConstant(bb.getLong());
            case FLOAT:
                return new FloatConstant(bb.getFloat());
            case DOUBLE:
                return new DoubleConstant(bb.getDouble());
            case METHOD_TYPE:
                return new MethodTypeConstant(readUnsignedShort(bb));
            case METHOD_HANDLE:
                return new MethodHandleConstant(bb.get(), readUnsignedShort(bb));
            case INVOKE_DYNAMIC:
                return new InvokeDynamicConstant(readUnsignedShort(bb), readUnsignedShort(bb));
        }
        return null;
    }

}

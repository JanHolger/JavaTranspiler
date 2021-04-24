package eu.bebendorf.bytecodemanipulator.attribute;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class AnnotationInfo {

    ClassFile cf;
    int typeIndex;
    Map<Integer, ElementValue> values;

    public String getClassName() {
        return cf.getConstantPool().getConstant(typeIndex).asUTF().getString();
    }

    public Map<String, ElementValue> getResolvedValues() {
        return values.keySet().stream().collect(Collectors.toMap(i -> cf.getConstantPool().getConstant(i).asUTF().getString(), i -> values.get(i)));
    }

    public static AnnotationInfo read(ClassFile cf, ByteBuffer bb) {
        int typeIndex = ByteCodeHelper.readUnsignedShort(bb);
        int valueCount = ByteCodeHelper.readUnsignedShort(bb);
        Map<Integer, ElementValue> values = new HashMap<>();
        for(int i=0; i<valueCount; i++)
            values.put(ByteCodeHelper.readUnsignedShort(bb), ElementValue.read(cf, bb));
        return new AnnotationInfo(cf, typeIndex, values);
    }

    @Getter
    public static class StringValue extends ElementValue {
        final int index;
        public StringValue(ClassFile classFile, Tag tag, int index) {
            super(classFile, tag);
            this.index = index;
        }
        public String getString() {
            return getClassFile().getConstantPool().getConstant(index).asUTF().getString();
        }
    }

    @Getter
    public static class ClassValue extends ElementValue {
        final int index;
        public ClassValue(ClassFile classFile, Tag tag, int index) {
            super(classFile, tag);
            this.index = index;
        }
        public String getClassName() {
            return getClassFile().getConstantPool().getConstant(index).asClass().getName(getClassFile());
        }
    }

    @Getter
    public static class AnnotationValue extends ElementValue {
        final AnnotationInfo annotation;
        public AnnotationValue(ClassFile classFile, Tag tag, AnnotationInfo annotation) {
            super(classFile, tag);
            this.annotation = annotation;
        }
    }

    @Getter
    public static class EnumValue extends ElementValue {
        final int typeNameIndex;
        final int constNameIndex;
        public EnumValue(ClassFile classFile, Tag tag, int typeNameIndex, int constNameIndex) {
            super(classFile, tag);
            this.typeNameIndex = typeNameIndex;
            this.constNameIndex = constNameIndex;
        }
        public String getTypeName() {
            return getClassFile().getConstantPool().getConstant(typeNameIndex).asUTF().getString();
        }
        public String getConstName() {
            return getClassFile().getConstantPool().getConstant(constNameIndex).asUTF().getString();
        }
    }

    @Getter
    public static class ArrayValue extends ElementValue {
        final ElementValue[] array;
        public ArrayValue(ClassFile classFile, Tag tag, ElementValue[] array) {
            super(classFile, tag);
            this.array = array;
        }
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class ElementValue {
        final ClassFile classFile;
        final Tag tag;
        public static ElementValue read(ClassFile cf, ByteBuffer bb) {
            Tag tag = Tag.fromTag(ByteCodeHelper.readUnsignedByte(bb));
            switch (tag) {
                case STRING:
                    return new StringValue(cf, tag, ByteCodeHelper.readUnsignedShort(bb));
                case CLASS:
                    return new ClassValue(cf, tag, ByteCodeHelper.readUnsignedShort(bb));
                case ANNOTATION:
                    return new AnnotationValue(cf, tag, AnnotationInfo.read(cf, bb));
                case ENUM:
                    return new EnumValue(cf, tag, ByteCodeHelper.readUnsignedShort(bb), ByteCodeHelper.readUnsignedShort(bb));
                case ARRAY: {
                    int len = ByteCodeHelper.readUnsignedShort(bb);
                    ElementValue[] array = new ElementValue[len];
                    for(int i=0; i<len; i++)
                        array[i] = ElementValue.read(cf, bb);
                    return new ArrayValue(cf, tag, array);
                }
            }
            return null;
        }
        @AllArgsConstructor
        @Getter
        public enum Tag {
            STRING('s'),
            ENUM('e'),
            CLASS('c'),
            ANNOTATION('@'),
            ARRAY('[');
            final int tag;
            public static Tag fromTag(int tag) {
                for(Tag t : values()) {
                    if(t.tag == tag)
                        return t;
                }
                return null;
            }
        }
    }

}

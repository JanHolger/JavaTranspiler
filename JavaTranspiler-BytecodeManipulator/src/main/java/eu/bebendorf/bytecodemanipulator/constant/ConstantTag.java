package eu.bebendorf.bytecodemanipulator.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConstantTag {
    UTF8((byte) 1),
    INTEGER((byte) 3),
    FLOAT((byte) 4),
    LONG((byte) 5),
    DOUBLE((byte) 6),
    CLASS((byte) 7),
    STRING((byte) 8),
    FIELD_REF((byte) 9),
    METHOD_REF((byte) 10),
    INTERFACE_METHOD_REF((byte) 11),
    NAME_AND_TYPE((byte) 12),
    METHOD_HANDLE((byte) 15),
    METHOD_TYPE((byte) 16),
    INVOKE_DYNAMIC((byte) 18);

    final byte tag;

    public static ConstantTag get(byte tag) {
        for(ConstantTag constantTag : values()) {
            if(constantTag.tag == tag) {
                return constantTag;
            }
        }
        return null;
    }

}

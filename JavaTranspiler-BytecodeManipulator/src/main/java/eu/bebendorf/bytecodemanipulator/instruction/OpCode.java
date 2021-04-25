package eu.bebendorf.bytecodemanipulator.instruction;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.nio.ByteBuffer;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public enum OpCode {

    AALOAD(0x32, 0, Instruction::new),
    AASTORE(0x53, 0, Instruction::new),
    ACONST_NULL(0x01, 0, Instruction::new),
    ALOAD(0x19, 1, ShortIndexInstruction::new),
    ALOAD_0(0x2a, 0, Instruction::new),
    ALOAD_1(0x2b, 0, Instruction::new),
    ALOAD_2(0x2c, 0, Instruction::new),
    ALOAD_3(0x2d, 0, Instruction::new),
    ANEWARRAY(0xbd, 2, WideIndexInstruction::new),
    ARETURN(0xb0, 0, Instruction::new),
    ARRAYLENGTH(0xbe, 0, Instruction::new),
    ASTORE(0x3a, 1, Instruction::new),
    ASTORE_0(0x4b, 0, Instruction::new),
    ASTORE_1(0x4c, 0, Instruction::new),
    ASTORE_2(0x4d, 0, Instruction::new),
    ASTORE_3(0x4e, 0, Instruction::new),
    ATHROW(0xbf, 0, Instruction::new),
    BALOAD(0x33, 0, Instruction::new),
    BASTORE(0x54, 0, Instruction::new),
    BIPUSH(0x10, 1, Instruction::new),
    BREAKPOINT(0xca, 0, Instruction::new),
    CALOAD(0x34, 0, Instruction::new),
    CASTORE(0x55, 0, Instruction::new),
    CHECKCAST(0xc0, 2, Instruction::new),
    D2F(0x90, 0, Instruction::new),
    D2I(0x8e, 0, Instruction::new),
    D2L(0x8f, 0, Instruction::new),
    DADD(0x63, 0, Instruction::new),
    DALOAD(0x31, 0, Instruction::new),
    DASTORE(0x52, 0, Instruction::new),
    DCMPG(0x98, 0, Instruction::new),
    DCMPL(0x97, 0, Instruction::new),
    DCONST_0(0x0e, 0, Instruction::new),
    DCONST_1(0x0f, 0, Instruction::new),
    DDIV(0x6f, 0, Instruction::new),
    DLOAD(0x18, 1, ShortIndexInstruction::new),
    DLOAD_0(0x26, 0, Instruction::new),
    DLOAD_1(0x27, 0, Instruction::new),
    DLOAD_2(0x28, 0, Instruction::new),
    DLOAD_3(0x29, 0, Instruction::new),
    DMUL(0x6b, 0, Instruction::new),
    DNEG(0x77, 0, Instruction::new),
    DREM(0x73, 0, Instruction::new),
    DRETURN(0xaf, 0, Instruction::new),
    DSTORE(0x39, 1, Instruction::new),
    DSTORE_0(0x47, 0, Instruction::new),
    DSTORE_1(0x48, 0, Instruction::new),
    DSTORE_2(0x49, 0, Instruction::new),
    DSTORE_3(0x4a, 0, Instruction::new),
    DSUB(0x67, 0, Instruction::new),
    DUP(0x59, 0, Instruction::new),
    DUP_X1(0x5a, 0, Instruction::new),
    DUP_X2(0x5b, 0, Instruction::new),
    DUP2(0x5c, 0, Instruction::new),
    DUP2_X1(0x5d, 0, Instruction::new),
    DUP2_X2(0x5e, 0, Instruction::new),
    F2D(0x8d, 0, Instruction::new),
    F2I(0x8b, 0, Instruction::new),
    F2L(0x8c, 0, Instruction::new),
    FADD(0x62, 0, Instruction::new),
    FALOAD(0x30, 0, Instruction::new),
    FASTORE(0x51, 0, Instruction::new),
    FCMPG(0x96, 0, Instruction::new),
    FCMPL(0x95, 0, Instruction::new),
    FCONST_0(0x0b, 0, Instruction::new),
    FCONST_1(0x0c, 0, Instruction::new),
    FCONST_2(0x0d, 0, Instruction::new),
    FDIV(0x6e, 0, Instruction::new),
    FLOAD(0x17, 1, ShortIndexInstruction::new),
    FLOAD_0(0x22, 0, Instruction::new),
    FLOAD_1(0x23, 0, Instruction::new),
    FLOAD_2(0x24, 0, Instruction::new),
    FLOAD_3(0x25, 0, Instruction::new),
    FMUL(0x6a, 0, Instruction::new),
    FNEG(0x76, 0, Instruction::new),
    FREM(0x72, 0, Instruction::new),
    FRETURN(0xae, 0, Instruction::new),
    FSTORE(0x39, 1, Instruction::new),
    FSTORE_0(0x43, 0, Instruction::new),
    FSTORE_1(0x44, 0, Instruction::new),
    FSTORE_2(0x45, 0, Instruction::new),
    FSTORE_3(0x46, 0, Instruction::new),
    FSUB(0x66, 0, Instruction::new),
    GETFIELD(0xb4, 2, WideIndexInstruction::new),
    GETSTATIC(0xb2, 2, WideIndexInstruction::new),
    GOTO(0xa7, 2, WideIndexInstruction::new),
    GOTO_W(0xc8, 4, Instruction::new),
    I2B(0x91, 0, Instruction::new),
    I2C(0x92, 0, Instruction::new),
    I2D(0x87, 0, Instruction::new),
    I2F(0x86, 0, Instruction::new),
    I2L(0x85, 0, Instruction::new),
    I2S(0x93, 0, Instruction::new),
    IADD(0x60, 0, Instruction::new),
    IALOAD(0x2e, 0, Instruction::new),
    IAND(0x7e, 0, Instruction::new),
    IASTORE(0x4f, 0, Instruction::new),
    ICONST_M1(0x02, 0, Instruction::new),
    ICONST_0(0x03, 0, Instruction::new),
    ICONST_1(0x04, 0, Instruction::new),
    ICONST_2(0x05, 0, Instruction::new),
    ICONST_3(0x06, 0, Instruction::new),
    ICONST_4(0x07, 0, Instruction::new),
    ICONST_5(0x08, 0, Instruction::new),
    IDIV(0x6c, 0, Instruction::new),
    IF_ACMPEQ(0xa5, 2, WideIndexInstruction::new),
    IF_ACMPNE(0xa6, 2, WideIndexInstruction::new),
    IF_ICMPEQ(0x9f, 2, WideIndexInstruction::new),
    IF_ICMPGE(0xa2, 2, WideIndexInstruction::new),
    IF_ICMPGT(0xa3, 2, WideIndexInstruction::new),
    IF_ICMPLE(0xa5, 2, WideIndexInstruction::new),
    IF_ICMPLT(0xa1, 2, WideIndexInstruction::new),
    IF_ICMPNE(0xa0, 2, WideIndexInstruction::new),
    IFEQ(0x99, 2, WideIndexInstruction::new),
    IFGE(0x9c, 2, WideIndexInstruction::new),
    IFGT(0x9d, 2, WideIndexInstruction::new),
    IFLE(0x9e, 2, WideIndexInstruction::new),
    IFLT(0x9b, 2, WideIndexInstruction::new),
    IFNE(0x9a, 2, WideIndexInstruction::new),
    IFNONNULL(0xc7, 2, WideIndexInstruction::new),
    IFNULL(0xc6, 2, WideIndexInstruction::new),
    IINC(0x84, 2, Instruction::new),
    ILOAD(0x15, 1, ShortIndexInstruction::new),
    ILOAD_0(0x1a, 0, Instruction::new),
    ILOAD_1(0x1b, 0, Instruction::new),
    ILOAD_2(0x1c, 0, Instruction::new),
    ILOAD_3(0x1d, 0, Instruction::new),
    IMPDEP1(0xfe, 0, Instruction::new),
    IMPDEP2(0xff, 0, Instruction::new),
    IMUL(0x68, 0, Instruction::new),
    INEG(0x74, 0, Instruction::new),
    INSTANCEOF(0xc1, 2, Instruction::new),
    INVOKEDYNAMIC(0xba, 4, Instruction::new),
    INVOKEINTERFACE(0xb9, 4, Instruction::new),
    INVOKESPECIAL(0xb7, 2, WideIndexInstruction::new),
    INVOKESTATIC(0xb8, 2, WideIndexInstruction::new),
    INVOKEVIRTUAL(0xb6, 2, WideIndexInstruction::new),
    IOR(0x80, 0, Instruction::new),
    IREM(0x70, 0, Instruction::new),
    IRETURN(0xac, 0, Instruction::new),
    ISHL(0x78, 0, Instruction::new),
    ISHR(0x7a, 0, Instruction::new),
    ISTORE(0x36, 1, Instruction::new),
    ISTORE_0(0x3b, 0, Instruction::new),
    ISTORE_1(0x3c, 0, Instruction::new),
    ISTORE_2(0x3d, 0, Instruction::new),
    ISTORE_3(0x3e, 0, Instruction::new),
    ISUB(0x64, 0, Instruction::new),
    IUSHR(0x7c, 0, Instruction::new),
    IXOR(0x82, 0, Instruction::new),
    JSR(0xa8, 2, Instruction::new),
    JSR_W(0xc9, 4, Instruction::new),
    L2D(0x8a, 0, Instruction::new),
    L2F(0x89, 0, Instruction::new),
    L2I(0x88, 0, Instruction::new),
    LADD(0x61, 0, Instruction::new),
    LALOAD(0x2f, 0, Instruction::new),
    LAND(0x7f, 0, Instruction::new),
    LASTORE(0x50, 0, Instruction::new),
    LCMP(0x94, 0, Instruction::new),
    LCONST_0(0x09, 0, Instruction::new),
    LCONST_1(0x0a, 0, Instruction::new),
    LDC(0x12, 1, ShortOrWideIndexInstruction::new),
    LDC_W(0x13, 2, ShortOrWideIndexInstruction::new),
    LDC2_W(0x14, 2, ShortOrWideIndexInstruction::new),
    LDIV(0x6d, 0, Instruction::new),
    LLOAD(0x16, 1, ShortIndexInstruction::new),
    LLOAD_0(0x1e, 0, Instruction::new),
    LLOAD_1(0x1f, 0, Instruction::new),
    LLOAD_2(0x20, 0, Instruction::new),
    LLOAD_3(0x21, 0, Instruction::new),
    LMUL(0x69, 0, Instruction::new),
    LNEG(0x75, 0, Instruction::new),
    LOOKUPSWITCH(0xab, -1, Instruction::new),
    LOR(0x81, 0, Instruction::new),
    LREM(0x71, 0, Instruction::new),
    LRETURN(0xad, 0, Instruction::new),
    LSHL(0x79, 0, Instruction::new),
    LSHR(0x7b, 0, Instruction::new),
    LSTORE(0x37, 1, Instruction::new),
    LSTORE_0(0x3f, 0, Instruction::new),
    LSTORE_1(0x40, 0, Instruction::new),
    LSTORE_2(0x41, 0, Instruction::new),
    LSTORE_3(0x42, 0, Instruction::new),
    LSUB(0x65, 0, Instruction::new),
    LUSHR(0x7d, 0, Instruction::new),
    LXOR(0x83, 0, Instruction::new),
    MONITORENTER(0xc2, 0, Instruction::new),
    MONITOREXIT(0xc3, 0, Instruction::new),
    MULTIANEWARRAY(0xc5, 3, Instruction::new),
    NEW(0xbb, 2, WideIndexInstruction::new),
    NEWARRAY(0xbc, 1, Instruction::new),
    NOP(0x00, 0, Instruction::new),
    POP(0x57, 0, Instruction::new),
    POP2(0x58, 0, Instruction::new),
    PUTFIELD(0xb5, 2, WideIndexInstruction::new),
    PUTSTATIC(0xb3, 2, WideIndexInstruction::new),
    RET(0xa9, 1, Instruction::new),
    RETURN(0xb1, 0, Instruction::new),
    SALOAD(0x35, 0, Instruction::new),
    SASTORE(0x56, 0, Instruction::new),
    SIPUSH(0x11, 2, Instruction::new),
    SWAP(0x5f, 0, Instruction::new),
    TABLESWITCH(0xaa, -1, Instruction::new),
    WIDE(0xc4, -1, Instruction::new);

    int code;
    int length;
    Instruction.Constructor constructor;

    public byte[] readBytes(ByteBuffer bb) {
        switch (this) {
            default: {
                byte[] bytes = new byte[length];
                bb.get(bytes);
                return bytes;
            }
            case LOOKUPSWITCH: {
                while (bb.position() % 4 != 0)
                    bb.position(bb.position() + 1);
                bb.position(bb.position() + 4);
                int npairs = bb.getInt();
                bb.position(bb.position() - 8);
                byte[] bytes = new byte[8 + (8 * npairs)];
                bb.get(bytes);
                return bytes;
            }
            case TABLESWITCH: {
                while (bb.position() % 4 != 0)
                    bb.position(bb.position() + 1);
                bb.position(bb.position() + 4);
                int low = bb.getInt();
                int high = bb.getInt();
                bb.position(bb.position() - 12);
                byte[] bytes = new byte[12 + ((high - low + 1) * 4)];
                bb.get(bytes);
                return bytes;
            }
            case WIDE: {
                int code = ByteCodeHelper.readUnsignedByte(bb);
                bb.position(bb.position() - 1);
                byte[] bytes = new byte[code == 0x84 ? 5 : 3];
                bb.get(bytes);
                return bytes;
            }
        }
    }

    public static OpCode fromCode(int code) {
        for(OpCode c : values()) {
            if(c.code == code)
                return c;
        }
        return null;
    }

}

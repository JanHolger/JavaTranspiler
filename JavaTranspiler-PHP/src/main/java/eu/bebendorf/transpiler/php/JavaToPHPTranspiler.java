package eu.bebendorf.transpiler.php;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import eu.bebendorf.bytecodemanipulator.ClassFile;
import eu.bebendorf.bytecodemanipulator.MethodDescriptor;
import eu.bebendorf.bytecodemanipulator.attribute.AnnotationInfo;
import eu.bebendorf.bytecodemanipulator.attribute.AnnotationsAttribute;
import eu.bebendorf.bytecodemanipulator.attribute.CodeAttribute;
import eu.bebendorf.bytecodemanipulator.attribute.ExceptionTableEntry;
import eu.bebendorf.bytecodemanipulator.constant.*;
import eu.bebendorf.bytecodemanipulator.instruction.*;
import eu.bebendorf.transpiler.php.generator.PHPArray;
import eu.bebendorf.transpiler.php.generator.PHPExpression;
import eu.bebendorf.transpiler.php.generator.PHPFunction;
import eu.bebendorf.transpiler.php.generator.PHPValue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaToPHPTranspiler {
    public static PHPFunction toPHP(List<ClassFile> files) {
        PHPFunction fn = new PHPFunction("v");
        files.forEach(cf -> fn.getCode().add("$v->classes[\""+cf.getClassName()+"\"] = " + toPHP(cf).toPHP() + ";"));
        return fn;
    }

    private static PHPArray toPHP(ClassFile file) {
        PHPArray ct = new PHPArray()
                .setString("name", file.getClassName())
                .setString("super", file.getSuperClassName())
                .set("flags", new PHPArray()
                        .setBoolean("protected", file.getFlags().isProtected())
                        .setBoolean("public", file.getFlags().isPublic())
                        .setBoolean("private", file.getFlags().isPrivate())
                        .setBoolean("static", file.getFlags().isStatic())
                        .setBoolean("native", file.getFlags().isNative())
                );
        PHPArray methodsArray = new PHPArray();
        file.getMethods().forEach(m -> {
            String name = m.getName();
            String descriptor = m.getDescriptor();
            PHPArray mot = (PHPArray) methodsArray.get(name);
            if (mot == null) {
                mot = new PHPArray();
                methodsArray.set(name, mot);
            }
            PHPArray mt = new PHPArray()
                    .setString("name", name)
                    .setString("descriptor", descriptor)
                    .set("flags", new PHPArray()
                            .setBoolean("protected", m.getFlags().isProtected())
                            .setBoolean("public", m.getFlags().isPublic())
                            .setBoolean("private", m.getFlags().isPrivate())
                            .setBoolean("static", m.getFlags().isStatic())
                            .setBoolean("native", m.getFlags().isNative())
                    );
            m.getAttributes().forEach(a -> {
                String aName = a.getName();
                if(aName.equalsIgnoreCase("code"))
                    mt.set("code", codeToPHP(file, a.asCode()));
                if(aName.equalsIgnoreCase("runtimevisibleannotations")) {
                    // Add Annotations for later reflection
                }
                if(aName.equalsIgnoreCase("runtimeinvisibleannotations")) {
                    AnnotationsAttribute attr = AnnotationsAttribute.fromData(file, a.getData());
                    for(AnnotationInfo an : attr.getAnnotations()) {
                        if(an.getClassName().equals("Leu/bebendorf/transpiler/interop/PHPImpl;")) {
                            PHPFunction code = new PHPFunction("v", "t", "l", "s");
                            code.getCode().addAll(Stream.of(((AnnotationInfo.ArrayValue) an.getResolvedValues().get("value")).getArray()).map(e -> ((AnnotationInfo.StringValue) e).getString()).collect(Collectors.toList()));
                            mt.set("code", code);
                        }
                    }
                }
            });
            mot.set(descriptor, mt);
        });
        ct.set("methods", methodsArray);
        return ct;
    }

    private static PHPFunction codeToPHP(ClassFile cf, CodeAttribute attr) {
        PHPFunction fn = new PHPFunction("&v", "&t", "&l", "&s");
        List<Instruction> instructions = CodeParser.parse(attr.getCode());
        for(int i=0; i<instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            if(ins.getCode() == OpCode.NOP)
                continue;
            fn.getCode().add("ins" + ins.getAddress() + ":");
            switch (ins.getCode()) {
                case RETURN:
                    fn.getCode().add("array_unshift($s, NULL);");
                    fn.getCode().add("goto ret;");
                    break;
                case ARETURN:
                case IRETURN:
                case LRETURN:
                case DRETURN:
                case FRETURN:
                    fn.getCode().add("goto ret;");
                    break;
                case GETSTATIC: {
                    FieldRefConstant fr = (FieldRefConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("array_unshift($s,$v->getstatic($t, \"" + fr.getClassName(cf) + "\",\"" + fr.getName(cf) + "\"));");
                    break;
                }
                case LDC:
                case LDC_W:
                case LDC2_W: {
                    Constant c = cf.getConstantPool().getConstant(((ShortOrWideIndexInstruction) ins).getIndex());
                    PHPValue value;
                    switch (c.getTag()) {
                        case LONG:
                            value = new PHPArray().setString("type", "J").setNumber("value", ((LongConstant) c).getValue());
                            break;
                        case INTEGER:
                            value = new PHPArray().setString("type", "I").setNumber("value", ((IntegerConstant) c).getValue());
                            break;
                        case DOUBLE:
                            value = new PHPArray().setString("type", "D").setNumber("value", ((DoubleConstant) c).getValue());
                            break;
                        case FLOAT:
                            value = new PHPArray().setString("type", "F").setNumber("value", ((FloatConstant) c).getValue());
                            break;
                        case STRING:
                            value = new PHPArray().setString("type", "Ljava/lang/String;").setString("value", ((StringConstant) c).getString(cf));
                            break;
                        default:
                            throw new RuntimeException("Unknown PHP constant type: " + c.getTag().name());
                    }
                    fn.getCode().add("array_unshift($s," + value.toPHP() + ");");
                    break;
                }
                case ILOAD:
                case ILOAD_0:
                case ILOAD_1:
                case ILOAD_2:
                case ILOAD_3:
                case LLOAD:
                case LLOAD_0:
                case LLOAD_1:
                case LLOAD_2:
                case LLOAD_3:
                case DLOAD:
                case DLOAD_0:
                case DLOAD_1:
                case DLOAD_2:
                case DLOAD_3:
                case FLOAD:
                case FLOAD_0:
                case FLOAD_1:
                case FLOAD_2:
                case FLOAD_3:
                case ALOAD:
                case ALOAD_0:
                case ALOAD_1:
                case ALOAD_2:
                case ALOAD_3: {
                    int index;
                    if(ins.getCode().name().contains("_"))
                        index = Integer.parseInt(ins.getCode().name().substring(6, 7));
                    else
                        index = ((ShortIndexInstruction) ins).getIndex();
                    fn.getCode().add("array_unshift($s,$l[" + index + "]);");
                    break;
                }
                case ISTORE:
                case ISTORE_0:
                case ISTORE_1:
                case ISTORE_2:
                case ISTORE_3:
                case LSTORE:
                case LSTORE_0:
                case LSTORE_1:
                case LSTORE_2:
                case LSTORE_3:
                case DSTORE:
                case DSTORE_0:
                case DSTORE_1:
                case DSTORE_2:
                case DSTORE_3:
                case FSTORE:
                case FSTORE_0:
                case FSTORE_1:
                case FSTORE_2:
                case FSTORE_3:
                case ASTORE:
                case ASTORE_0:
                case ASTORE_1:
                case ASTORE_2:
                case ASTORE_3: {
                    int index;
                    if(ins.getCode().name().contains("_"))
                        index = Integer.parseInt(ins.getCode().name().substring(7, 8));
                    else
                        index = ((ShortIndexInstruction) ins).getIndex();
                    fn.getCode().add("$l[" + index + "] = array_shift($s);");
                    break;
                }
                case LUSHR:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] >> array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case IUSHR:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] >> array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case LADD:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] + array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case IADD:
                    fn.getCode().add("array_unshift($s,"+ new PHPArray().setString("type", "I").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] + array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case DADD:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "D").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] + array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case FADD:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "F").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] + array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case LSUB:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] - array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case ISUB:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] - array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case DSUB:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "D").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] - array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case FSUB:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "F").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] - array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case LMUL:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] * array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case IMUL:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] * array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case DMUL:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "D").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] * array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case FMUL:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "F").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] * array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case LDIV:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").set("value", new PHPExpression("floor(array_splice($s,1,1)[0]['value'] / array_splice($s,0,1)[0]['value'])")).toPHP() + ");");
                    break;
                case IDIV:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").set("value", new PHPExpression("floor(array_splice($s,1,1)[0]['value'] / array_splice($s,0,1)[0]['value'])")).toPHP() + ");");
                    break;
                case DDIV:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "D").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] / array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case FDIV:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "F").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] / array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case LREM:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] % array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case IREM:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] % array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case DREM:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "D").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] % array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case FREM:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "F").set("value", new PHPExpression("array_splice($s,1,1)[0]['value'] % array_splice($s,0,1)[0]['value']")).toPHP() + ");");
                    break;
                case INVOKESPECIAL:
                case INVOKESTATIC:
                case INVOKEVIRTUAL: {
                    MethodRefConstant mr = cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex()).asMethod();
                    String descriptor = mr.getDescriptor(cf);
                    MethodDescriptor md = new MethodDescriptor(descriptor);
                    int params = new MethodDescriptor(descriptor).getParameterTypes().size() + (ins.getCode() == OpCode.INVOKESTATIC ? 0 : 1);
                    fn.getCode().add("array_unshift($s,$v->invoke($t, \"" + mr.getClassName(cf) + "\",\"" + mr.getName(cf) + "\",\"" + descriptor + "\",[" + IntStream.range(0, params).mapToObj(ind -> "array_splice($s," + (params - ind - 1) + ",1)[0]").collect(Collectors.joining(",")) + "]));");
                    List<ExceptionTableEntry> exceptionTable = attr.getExceptionTable().stream().filter(e -> e.getStartPC() <= (ins.getAddress() - 4) && e.getEndPC() > (ins.getAddress() - 4)).collect(Collectors.toList());
                    fn.getCode().add("if ($t['exception'] != NULL) {");
                    if (exceptionTable.size() > 0) {
                        exceptionTable.forEach(e -> {
                            fn.getCode().add("if ($v->instanceof($t,$t['exception'],\"L" + e.getCatchTypeName(cf) + ";\")) {");
                            fn.getCode().add("array_shift($s);");
                            fn.getCode().add("array_unshift($s, $t['exception']);");
                            fn.getCode().add("$t['exception'] = NULL;");
                            fn.getCode().add("goto ins" + (e.getHandlerPC() + 4) + ";");
                            fn.getCode().add("}");
                        });
                    }
                    fn.getCode().add("goto ret;");
                    fn.getCode().add("}");
                    if(md.getReturnType().equals("V"))
                        fn.getCode().add("array_shift($s);");
                    break;
                }
                case ICONST_M1:
                case ICONST_0:
                case ICONST_1:
                case ICONST_2:
                case ICONST_3:
                case ICONST_4:
                case ICONST_5: {
                    int value = Integer.parseInt(ins.getCode().name().substring(7).replace('M', '-'));
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").setNumber("value", value).toPHP() + ");");
                    break;
                }
                case LCONST_0:
                case LCONST_1: {
                    long value = Long.parseLong(ins.getCode().name().substring(7));
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "J").setNumber("value", value).toPHP() + ");");
                    break;
                }
                case DCONST_0:
                case DCONST_1: {
                    double value = Double.parseDouble(ins.getCode().name().substring(7));
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "D").setNumber("value", value).toPHP() + ");");
                    break;
                }
                case FCONST_0:
                case FCONST_1:
                case FCONST_2: {
                    float value = Float.parseFloat(ins.getCode().name().substring(7));
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "F").setNumber("value", value).toPHP() + ");");
                    break;
                }
                case ACONST_NULL:
                    fn.getCode().add("array_unshift($s, NULL);");
                    break;
                case BIPUSH:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").setNumber("value", ins.getBytes()[0]).toPHP() + ");");
                    break;
                case SIPUSH:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "I").setNumber("value", ByteCodeHelper.toSignedShort(ins.getBytes())).toPHP() + ");");
                    break;
                case NEW:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "L" + cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex()).asClass().getName(cf) + ";").set("fields", new PHPArray()).toPHP() + ");");
                    break;
                case DUP:
                    fn.getCode().add("array_unshift($s, $s[0]);");
                    break;
                case PUTSTATIC: {
                    FieldRefConstant fr = (FieldRefConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("$v->setstatic($t, \"" + fr.getClassName(cf) + "\",\"" + fr.getName(cf) + "\",array_unshift($s));");
                    break;
                }
                case ARRAYLENGTH:
                    fn.getCode().add("array_unshift($s,count(array_shift($s)[\"values\"]));");
                    break;
                case AALOAD:
                    fn.getCode().add("array_unshift($s,array_splice($s,1,1)[0][\"values\"][array_shift($s)['value']]);");
                    break;
                case AASTORE:
                    fn.getCode().add("array_splice($s,1,1)[0][\"values\"][array_shift($s)['value']] = array_shift($s);");
                    break;
                case IFEQ:
                    fn.getCode().add("if (array_shift($s) == 0) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFNE:
                    fn.getCode().add("if (array_shift($s) != 0) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFNULL:
                    fn.getCode().add("if (array_shift($s) == NULL) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFNONNULL:
                    fn.getCode().add("if (array_shift($s) != NULL) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFGE:
                    fn.getCode().add("if (array_shift($s) >= 0) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFGT:
                    fn.getCode().add("if (array_shift($s) > 0) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFLE:
                    fn.getCode().add("if (array_shift($s) <= 0) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IFLT:
                    fn.getCode().add("if (array_shift($s) < 0) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ACMPEQ:
                    fn.getCode().add("if (array_splice($s,1,1)[0] == array_shift($s)) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ACMPNE:
                    fn.getCode().add("if (array_splice($s,1,1)[0] != array_shift($s)) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ICMPEQ:
                    fn.getCode().add("if (array_splice($s,1,1)[0]['value'] == array_shift($s)['value']) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ICMPGE:
                    fn.getCode().add("if (array_splice($s,1,1)[0]['value'] >= array_shift($s)['value']) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ICMPGT:
                    fn.getCode().add("if (array_splice($s,1,1)[0]['value'] > array_shift($s)['value']) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ICMPLE:
                    fn.getCode().add("if (array_splice($s,1,1)[0]['value'] <= array_shift($s)['value']) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ICMPLT:
                    fn.getCode().add("if (array_splice($s,1,1)[0]['value'] < array_shift($s)['value']) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case IF_ICMPNE:
                    fn.getCode().add("if (array_splice($s,1,1)[0]['value'] != array_shift($s)['value']) goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case GOTO:
                    fn.getCode().add("goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getAddress()) + ";");
                    break;
                case ATHROW:
                    fn.getCode().add("$t[\"exception\"] = array_shift($s);");
                    fn.getCode().add("array_unshift($s, NULL);");
                    fn.getCode().add("goto ret;");
                    break;
                case GETFIELD: {
                    FieldRefConstant fr = (FieldRefConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("array_unshift($s,$v->getfield($t,\"" + fr.getClassName(cf) + "\",\"" + fr.getName(cf) + "\",array_shift($s)));");
                    break;
                }
                case PUTFIELD: {
                    FieldRefConstant fr = (FieldRefConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("$v->setfield($t,\"" + fr.getClassName(cf) + "\",\"" + fr.getName(cf) + "\",array_splice($s,1,1)[0],array_shift($s));");
                    break;
                }
                case ANEWARRAY: {
                    ClassConstant cc = (ClassConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", "[L" + cc.getName(cf) + ";").set("length", new PHPExpression("array_shift($s)")).set("values", new PHPArray()).toPHP() + ");");
                }
                case D2I:
                case D2L:
                case F2I:
                case F2L:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", ins.getCode().name().substring(2,3)).set("value", new PHPExpression("floor(array_shift($s)['value'])")).toPHP() + ");");
                    break;
                case D2F:
                case F2D:
                case I2L:
                case L2I:
                case L2D:
                case L2F:
                case I2D:
                case I2F:
                case I2B:
                case I2S:
                case I2C:
                    fn.getCode().add("array_unshift($s," + new PHPArray().setString("type", ins.getCode().name().substring(2,3)).set("value", new PHPExpression("array_shift($s)['value']")).toPHP() + ");");
                    break;
                case POP:
                    fn.getCode().add("array_shift($s);");
                    break;
                case POP2:
                    fn.getCode().add("if (s[1] == nil || ($s[0]['type'] != \"D\" && $s[0]['type'] != \"L\") array_shift($s);");
                    fn.getCode().add("array_shift($s);");
                case INSTANCEOF: {
                    ClassConstant cc = (ClassConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("array_unshift($s,$v->instanceof($t,array_shift($s),\"L" + cc.getName(cf) + ";\"));");
                    break;
                }
                default:
                    fn.getCode().add("// " + ins.getCode().name());
                    break;
            }
        }
        fn.getCode().add("ret:");
        fn.getCode().add("return array_unshift($s);");
        return fn;
    }
}

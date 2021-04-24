package eu.bebendorf.transpiler.lua;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import eu.bebendorf.bytecodemanipulator.MethodDescriptor;
import eu.bebendorf.bytecodemanipulator.attribute.AnnotationInfo;
import eu.bebendorf.bytecodemanipulator.attribute.AnnotationsAttribute;
import eu.bebendorf.bytecodemanipulator.constant.*;
import eu.bebendorf.bytecodemanipulator.instruction.*;
import eu.bebendorf.transpiler.lua.generator.LuaExpression;
import eu.bebendorf.transpiler.lua.generator.LuaFunction;
import eu.bebendorf.transpiler.lua.generator.LuaTable;
import eu.bebendorf.transpiler.lua.generator.LuaValue;
import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaToLuaTranspiler {

    public static LuaFunction toLua(List<ClassFile> files) {
        LuaFunction fn = new LuaFunction("v");
        files.forEach(cf -> fn.getCode().add("v.classes[\""+cf.getClassName()+"\"] = " + toLua(cf).toLua()));
        return fn;
    }

    private static LuaTable toLua(ClassFile file) {
        LuaTable ct = new LuaTable()
                .setString("name", file.getClassName())
                .setString("super", file.getSuperClassName())
                .set("flags", new LuaTable()
                        .setBoolean("protected", file.getFlags().isProtected())
                        .setBoolean("public", file.getFlags().isPublic())
                        .setBoolean("private", file.getFlags().isPrivate())
                        .setBoolean("static", file.getFlags().isStatic())
                        .setBoolean("native", file.getFlags().isNative())
                );
        LuaTable methodsTable = new LuaTable();
        file.getMethods().forEach(m -> {
            String name = m.getName();
            String descriptor = m.getDescriptor();
            LuaTable mot = (LuaTable) methodsTable.get(name);
            if(mot == null) {
                mot = new LuaTable();
                methodsTable.set(name, mot);
            }
            LuaTable mt = new LuaTable()
                    .setString("name", name)
                    .setString("descriptor", descriptor)
                    .set("flags", new LuaTable()
                            .setBoolean("protected", m.getFlags().isProtected())
                            .setBoolean("public", m.getFlags().isPublic())
                            .setBoolean("private", m.getFlags().isPrivate())
                            .setBoolean("static", m.getFlags().isStatic())
                            .setBoolean("native", m.getFlags().isNative())
                    );
            m.getAttributes().forEach(a -> {
                String aName = a.getName();
                if(aName.equalsIgnoreCase("code"))
                    mt.set("code", codeToLua(file, a.asCode().getCode()));
                if(aName.equalsIgnoreCase("runtimevisibleannotations")) {
                    // Add Annotations for later reflection
                }
                if(aName.equalsIgnoreCase("runtimeinvisibleannotations")) {
                    AnnotationsAttribute attr = AnnotationsAttribute.fromData(file, a.getData());
                    for(AnnotationInfo an : attr.getAnnotations()) {
                        if(an.getClassName().equals("Leu/bebendorf/transpiler/interop/LuaImpl;")) {
                            LuaFunction code = new LuaFunction("v", "l", "s");
                            code.getCode().addAll(Stream.of(((AnnotationInfo.ArrayValue) an.getResolvedValues().get("value")).getArray()).map(e -> ((AnnotationInfo.StringValue) e).getString()).collect(Collectors.toList()));
                            mt.set("code", code);
                        }
                    }
                }
            });
            mot.set(descriptor, mt);
        });
        ct.set("methods", methodsTable);
        return ct;
    }

    private static LuaFunction codeToLua(ClassFile cf, byte[] code) {
        LuaFunction fn = new LuaFunction("v", "l", "s");
        List<Instruction> instructions = CodeParser.parse(code);
        for(int i=0; i<instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            if(ins.getCode() == OpCode.NOP)
                continue;
            fn.getCode().add("::ins" + ins.getOffset() + "::");
            switch (ins.getCode()) {
                case RETURN:
                    fn.getCode().add("table.insert(s,1,nil)");
                    fn.getCode().add("goto ret");
                    break;
                case ARETURN:
                case IRETURN:
                case LRETURN:
                case DRETURN:
                case FRETURN:
                    fn.getCode().add("goto ret");
                    break;
                case GETSTATIC: {
                    FieldRefConstant fr = (FieldRefConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("table.insert(s,1,v.getstatic(\"" + fr.getClassName(cf) + "\",\"" + fr.getName(cf) + "\"))");
                    break;
                }
                case LDC:
                case LDC_W:
                case LDC2_W: {
                    Constant c = cf.getConstantPool().getConstant(((ShortOrWideIndexInstruction) ins).getIndex());
                    LuaValue value;
                    switch (c.getTag()) {
                        case LONG:
                            value = new LuaTable().setString("class", "long").setNumber("value", ((LongConstant) c).getValue());
                            break;
                        case INTEGER:
                            value = new LuaTable().setString("class", "int").setNumber("value", ((IntegerConstant) c).getValue());
                            break;
                        case DOUBLE:
                            value = new LuaTable().setString("class", "double").setNumber("value", ((DoubleConstant) c).getValue());
                            break;
                        case FLOAT:
                            value = new LuaTable().setString("class", "float").setNumber("value", ((FloatConstant) c).getValue());
                            break;
                        case STRING:
                            value = new LuaTable().setString("class", "java/lang/String").setString("value", ((StringConstant) c).getString(cf));
                            break;
                        default:
                            throw new RuntimeException("Unknown lua constant type: " + c.getTag().name());
                    }
                    fn.getCode().add("table.insert(s,1," + value.toLua() + ")");
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
                    fn.getCode().add("table.insert(s,1,l[" + (index + 1) + "])");
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
                    fn.getCode().add("l[" + (index + 1) + "] = table.remove(s,1)");
                    break;
                }
                case LUSHR:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").set("value", new LuaExpression("table.remove(s,2).value >> table.remove(s,1).value")).toLua() + ")");
                    break;
                case IUSHR:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").set("value", new LuaExpression("table.remove(s,2).value >> table.remove(s,1).value")).toLua() + ")");
                    break;
                case LADD:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").set("value", new LuaExpression("table.remove(s,2).value + table.remove(s,1).value")).toLua() + ")");
                    break;
                case IADD:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").set("value", new LuaExpression("table.remove(s,2).value + table.remove(s,1).value")).toLua() + ")");
                    break;
                case DADD:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "double").set("value", new LuaExpression("table.remove(s,2).value + table.remove(s,1).value")).toLua() + ")");
                    break;
                case FADD:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "float").set("value", new LuaExpression("table.remove(s,2).value + table.remove(s,1).value")).toLua() + ")");
                    break;
                case LSUB:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").set("value", new LuaExpression("table.remove(s,2).value - table.remove(s,1).value")).toLua() + ")");
                    break;
                case ISUB:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").set("value", new LuaExpression("table.remove(s,2).value - table.remove(s,1).value")).toLua() + ")");
                    break;
                case DSUB:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "double").set("value", new LuaExpression("table.remove(s,2).value - table.remove(s,1).value")).toLua() + ")");
                    break;
                case FSUB:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "float").set("value", new LuaExpression("table.remove(s,2).value - table.remove(s,1).value")).toLua() + ")");
                    break;
                case LMUL:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").set("value", new LuaExpression("table.remove(s,2).value * table.remove(s,1).value")).toLua() + ")");
                    break;
                case IMUL:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").set("value", new LuaExpression("table.remove(s,2).value * table.remove(s,1).value")).toLua() + ")");
                    break;
                case DMUL:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "double").set("value", new LuaExpression("table.remove(s,2).value * table.remove(s,1).value")).toLua() + ")");
                    break;
                case FMUL:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "float").set("value", new LuaExpression("table.remove(s,2).value * table.remove(s,1).value")).toLua() + ")");
                    break;
                case LDIV:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").set("value", new LuaExpression("table.remove(s,2).value // table.remove(s,1).value")).toLua() + ")");
                    break;
                case IDIV:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").set("value", new LuaExpression("table.remove(s,2).value // table.remove(s,1).value")).toLua() + ")");
                    break;
                case DDIV:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "double").set("value", new LuaExpression("table.remove(s,2).value / table.remove(s,1).value")).toLua() + ")");
                    break;
                case FDIV:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "float").set("value", new LuaExpression("table.remove(s,2).value / table.remove(s,1).value")).toLua() + ")");
                    break;
                case LREM:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").set("value", new LuaExpression("table.remove(s,2).value % table.remove(s,1).value")).toLua() + ")");
                    break;
                case IREM:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").set("value", new LuaExpression("table.remove(s,2).value % table.remove(s,1).value")).toLua() + ")");
                    break;
                case DREM:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "double").set("value", new LuaExpression("table.remove(s,2).value % table.remove(s,1).value")).toLua() + ")");
                    break;
                case FREM:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "float").set("value", new LuaExpression("table.remove(s,2).value % table.remove(s,1).value")).toLua() + ")");
                    break;
                case INVOKESPECIAL:
                case INVOKESTATIC:
                case INVOKEVIRTUAL: {
                    MethodRefConstant mr = cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex()).asMethod();
                    String descriptor = mr.getDescriptor(cf);
                    int params = new MethodDescriptor(descriptor).getParameterTypes().size();
                    fn.getCode().add("table.insert(s,1,v.invoke(\"" + mr.getClassName(cf) + "\",\"" + mr.getName(cf) + "\",\"" + descriptor + "\",{" + IntStream.range(0, params + (ins.getCode() == OpCode.INVOKESTATIC ? 0 : 1)).mapToObj(ind -> "table.remove(s," + (params - ind + 1) + ")").collect(Collectors.joining(",")) + "}))");
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
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").setNumber("value", value).toLua() + ")");
                    break;
                }
                case LCONST_0:
                case LCONST_1: {
                    long value = Long.parseLong(ins.getCode().name().substring(7));
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "long").setNumber("value", value).toLua() + ")");
                    break;
                }
                case DCONST_0:
                case DCONST_1: {
                    double value = Double.parseDouble(ins.getCode().name().substring(7));
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "double").setNumber("value", value).toLua() + ")");
                    break;
                }
                case FCONST_0:
                case FCONST_1:
                case FCONST_2: {
                    float value = Float.parseFloat(ins.getCode().name().substring(7));
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "float").setNumber("value", value).toLua() + ")");
                    break;
                }
                case ACONST_NULL:
                    fn.getCode().add("table.insert(s,1,nil)");
                    break;
                case BIPUSH:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").setNumber("value", ins.getBytes()[0]).toLua() + ")");
                    break;
                case SIPUSH:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", "int").setNumber("value", ByteCodeHelper.toSignedShort(ins.getBytes())).toLua() + ")");
                    break;
                case NEW:
                    fn.getCode().add("table.insert(s,1," + new LuaTable().setString("class", cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex()).asClass().getName(cf)).set("fields", new LuaTable()).toLua() + ")");
                    break;
                case DUP:
                    fn.getCode().add("table.insert(s,1,s[1])");
                    break;
                case PUTSTATIC: {
                    FieldRefConstant fr = (FieldRefConstant) cf.getConstantPool().getConstant(((WideIndexInstruction) ins).getIndex());
                    fn.getCode().add("v.setstatic(\"" + fr.getClassName(cf) + "\",\"" + fr.getName(cf) + "\",table.remove(s,1))");
                    break;
                }
                case ARRAYLENGTH:
                    fn.getCode().add("table.insert(s,1,#table.remove(s,1).values)");
                    break;
                case AALOAD:
                    fn.getCode().add("table.insert(s,1,table.remove(s,2).values[table.remove(s,1).value+1])");
                    break;
                case AASTORE:
                    fn.getCode().add("table.remove(s,2).values[table.remove(s,1).value+1] = table.remove(s,1)");
                    break;
                case IFEQ:
                    fn.getCode().add("if table.remove(s,1) == 0 then goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getOffset()) + " end");
                    break;
                case IFNE:
                    fn.getCode().add("if table.remove(s,1) ~= 0 then goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getOffset()) + " end");
                    break;
                case IFNULL:
                    fn.getCode().add("if table.remove(s,1) == nil then goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getOffset()) + " end");
                    break;
                case IFNONNULL:
                    fn.getCode().add("if table.remove(s,1) ~= nil then goto ins" + (((WideIndexInstruction) ins).getIndex() + ins.getOffset()) + " end");
                    break;
                case ATHROW:
                    fn.getCode().add("error(table.remove(s,1))");
                    break;
                default:
                    fn.getCode().add("-- " + ins.getCode().name());
                    break;
            }
        }
        fn.getCode().add("::ret::");
        fn.getCode().add("return table.remove(s,1)");
        return fn;
    }

}

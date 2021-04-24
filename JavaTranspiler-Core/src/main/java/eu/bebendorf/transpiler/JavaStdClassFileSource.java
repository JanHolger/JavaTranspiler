package eu.bebendorf.transpiler;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import eu.bebendorf.bytecodemanipulator.MethodDescriptor;
import eu.bebendorf.bytecodemanipulator.constant.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JavaStdClassFileSource implements JavaClassFileSource {

    final JavaClassFileSource parent;

    public List<ClassFile> get() {
        return parent.get().stream().map(cf -> {
            for(Constant c : cf.getConstantPool().getConstants()) {
                if(c.getTag() == ConstantTag.CLASS) {
                    UTF8Constant uc = cf.getConstantPool().getConstant(((ClassConstant) c).getIndex()).asUTF();
                    uc.setString(translate(uc.getString()));
                }
                if(c.getTag() == ConstantTag.NAME_AND_TYPE) {
                    UTF8Constant uc = cf.getConstantPool().getConstant(((NameAndTypeConstant) c).getDescriptorIndex()).asUTF();
                    uc.setString(translate(uc.getString()));
                }
            }
            return cf;
        }).collect(Collectors.toList());
    }

    private static String translate(String source) {
        if(source.startsWith("(")) {
            MethodDescriptor descriptor = new MethodDescriptor(source);
            descriptor.setReturnType(translate(descriptor.getReturnType()));
            for(int i=0; i<descriptor.getParameterTypes().size(); i++)
                descriptor.getParameterTypes().set(i, translate(descriptor.getParameterTypes().get(i)));
            return descriptor.toString();
        }
        if(source.startsWith("["))
            return "[" + translate(source.substring(1));
        if(source.startsWith("std/"))
            return source.substring(4);
        return source;
    }

}

package eu.bebendorf.bytecodemanipulator;

import eu.bebendorf.bytecodemanipulator.constant.ConstantPool;
import eu.bebendorf.bytecodemanipulator.constant.UTF8Constant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClassFile {

    Version version;
    ConstantPool constantPool;
    Flags flags;
    int classIndex;
    int superClassIndex;
    List<Integer> interfaceClassIndexes = new ArrayList<>();
    List<FieldInfo> fields = new ArrayList<>();
    List<MethodInfo> methods = new ArrayList<>();
    List<AttributeInfo> attributes = new ArrayList<>();

    public ClassFile(Version version, ConstantPool constantPool, Flags flags, int classIndex, int superClassIndex) {
        this.version = version;
        this.constantPool = constantPool;
        this.flags = flags;
        this.classIndex = classIndex;
        this.superClassIndex = superClassIndex;
    }

    public UTF8Constant getClassNameConstant() {
        return constantPool.getConstant(constantPool.getConstant(classIndex).asClass().getIndex()).asUTF();
    }

    public String getClassName() {
        return getClassNameConstant().getString();
    }

    public UTF8Constant getSuperClassNameConstant() {
        return constantPool.getConstant(constantPool.getConstant(superClassIndex).asClass().getIndex()).asUTF();
    }

    public String getSuperClassName() {
        return getSuperClassNameConstant().asUTF().getString();
    }

    public List<String> getInterfaceClassNames() {
        return interfaceClassIndexes.stream()
                .map(index -> constantPool.getConstant(constantPool.getConstant(index).asClass().getIndex()).asUTF().getString())
                .collect(Collectors.toList());
    }

}

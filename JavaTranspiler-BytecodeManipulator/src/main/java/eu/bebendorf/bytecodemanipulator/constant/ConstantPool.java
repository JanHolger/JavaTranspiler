package eu.bebendorf.bytecodemanipulator.constant;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

    @Getter
    final List<Constant> constants;

    public ConstantPool(List<Constant> constants) {
        this.constants = constants;
    }

    public ConstantPool() {
        this(new ArrayList<>());
    }

    public Constant getConstant(int index) {
        if(index < 1 || index > constants.size())
            return null;
        return constants.get(index - 1);
    }

    public int addConstant(Constant constant) {
        constants.add(constant);
        return constants.size();
    }

    public int getUTF8Index(String value) {
        for(int i = 0; i < constants.size(); i++) {
            Constant c = constants.get(i);
            if(c.getTag() == ConstantTag.UTF8) {
                if(((UTF8Constant) c).getString().equals(value))
                    return i + 1;
            }
        }
        return 0;
    }

    public UTF8Constant getUTF8Constant(String value) {
        int index = getUTF8Index(value);
        if(index == 0)
            return null;
        return (UTF8Constant) getConstant(index);
    }

    public int makeUTF8(String value) {
        int index = getUTF8Index(value);
        if(index > 0)
            return index;
        return addConstant(new UTF8Constant(value));
    }

}

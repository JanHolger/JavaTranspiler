package eu.bebendorf.bytecodemanipulator;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class MethodDescriptor {

    @Setter
    String returnType;
    List<String> parameterTypes = new ArrayList<>();

    public MethodDescriptor(String descriptor) {
        descriptor = descriptor.substring(1);
        while (!descriptor.startsWith(")")) {
            parameterTypes.add(parseType(descriptor));
            descriptor = descriptor.substring(parameterTypes.get(parameterTypes.size()-1).length());
        }
        returnType = parseType(descriptor.substring(1));
    }

    public MethodDescriptor(String returnType, String... parameterTypes) {
        this.returnType = returnType;
        this.parameterTypes = new ArrayList<>(Arrays.asList(parameterTypes));
    }

    public String toString() {
        return "(" + String.join("", parameterTypes) + ")" + returnType;
    }

    private static String parseType(String source) {
        int s = 0;
        while (source.charAt(s) == '[')
            s++;
        if(source.charAt(s) == 'L') {
            return source.split(";", 2)[0] + ";";
        } else {
            return source.substring(0, s + 1);
        }
    }

}

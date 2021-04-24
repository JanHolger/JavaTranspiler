package eu.bebendorf.transpiler;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import eu.bebendorf.bytecodemanipulator.ClassFileParser;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class JavaSingleClassFileSource implements JavaClassFileSource {

    final File file;

    public List<ClassFile> get() {
        return Arrays.asList(ClassFileParser.parse(file));
    }

}

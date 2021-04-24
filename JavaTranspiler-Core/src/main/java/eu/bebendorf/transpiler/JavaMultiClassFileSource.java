package eu.bebendorf.transpiler;

import eu.bebendorf.bytecodemanipulator.ClassFile;

import java.util.ArrayList;
import java.util.List;

public class JavaMultiClassFileSource implements JavaClassFileSource {

    final JavaClassFileSource[] sources;

    public JavaMultiClassFileSource(JavaClassFileSource... sources) {
        this.sources = sources;
    }

    public List<ClassFile> get() {
        List<ClassFile> classFiles = new ArrayList<>();
        for(JavaClassFileSource s : sources)
            classFiles.addAll(s.get());
        return classFiles;
    }

}

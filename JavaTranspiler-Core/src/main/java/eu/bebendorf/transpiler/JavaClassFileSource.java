package eu.bebendorf.transpiler;

import eu.bebendorf.bytecodemanipulator.ClassFile;

import java.util.List;

public interface JavaClassFileSource {

    List<ClassFile> get();

}

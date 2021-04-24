package eu.bebendorf.transpiler;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import eu.bebendorf.bytecodemanipulator.ClassFileParser;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JavaDirectoryClassFileSource implements JavaClassFileSource {

    final File directory;

    public List<ClassFile> get() {
        return find(new ArrayList<>(), directory).stream().map(ClassFileParser::parse).collect(Collectors.toList());
    }

    private List<File> find(List<File> files, File file) {
        if(file.isDirectory()) {
            for(File f : file.listFiles())
                find(files, f);
        } else {
            if(file.getName().endsWith(".class"))
                files.add(file);
        }
        return files;
    }

}

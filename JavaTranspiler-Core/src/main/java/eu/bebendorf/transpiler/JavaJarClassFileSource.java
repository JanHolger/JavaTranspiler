package eu.bebendorf.transpiler;

import eu.bebendorf.bytecodemanipulator.ClassFile;
import eu.bebendorf.bytecodemanipulator.ClassFileParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class JavaJarClassFileSource implements JavaClassFileSource {

    final JarFile jf;

    public JavaJarClassFileSource(File jarFile) throws IOException {
        jf = new JarFile(jarFile);
    }

    public List<ClassFile> get() {
        return Collections.list(jf.entries()).stream().filter(ze -> ze.getName().endsWith(".class")).map(ze -> {
            try {
                InputStream is = jf.getInputStream(ze);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int r;
                byte[] buffer = new byte[1024];
                while ((r = is.read(buffer)) != -1)
                    baos.write(buffer, 0, r);
                is.close();
                return ClassFileParser.parse(baos.toByteArray());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

}

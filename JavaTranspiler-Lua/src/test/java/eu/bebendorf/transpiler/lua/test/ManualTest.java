package eu.bebendorf.transpiler.lua.test;

import eu.bebendorf.transpiler.JavaJarClassFileSource;
import eu.bebendorf.transpiler.JavaMultiClassFileSource;
import eu.bebendorf.transpiler.JavaSingleClassFileSource;
import eu.bebendorf.transpiler.JavaStdClassFileSource;
import eu.bebendorf.transpiler.lua.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ManualTest {

    public static void main(String[] args) throws IOException {
        writeFile(new File("JavaTranspiler-Lua/lua/system.lua"), "return " + JavaToLuaTranspiler.toLua(new JavaStdClassFileSource(new JavaJarClassFileSource(new File("JavaTranspiler-StdLib/target/JavaTranspiler-StdLib-1.0-SNAPSHOT.jar"))).get()).toLua());
        writeFile(new File("JavaTranspiler-Lua/lua/user.lua"), "return " + JavaToLuaTranspiler.toLua(new JavaMultiClassFileSource(
                new JavaSingleClassFileSource(new File("Test.class"))
        ).get()).toLua());
    }

    private static void writeFile(File file, String content) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes(StandardCharsets.UTF_8));
            fos.flush();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

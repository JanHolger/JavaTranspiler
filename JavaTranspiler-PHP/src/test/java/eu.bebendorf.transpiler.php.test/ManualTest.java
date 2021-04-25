package eu.bebendorf.transpiler.php.test;

import eu.bebendorf.transpiler.JavaJarClassFileSource;
import eu.bebendorf.transpiler.JavaMultiClassFileSource;
import eu.bebendorf.transpiler.JavaSingleClassFileSource;
import eu.bebendorf.transpiler.JavaStdClassFileSource;
import eu.bebendorf.transpiler.php.PHPToLuaTranspiler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ManualTest {

    public static void main(String[] args) throws IOException {
        writeFile(new File("JavaTranspiler-PHP/php/system.php"), "<?php\nfunction loadsystemclasses() { return " + PHPToLuaTranspiler.toPHP(new JavaStdClassFileSource(new JavaJarClassFileSource(new File("JavaTranspiler-StdLib/target/JavaTranspiler-StdLib-1.0-SNAPSHOT.jar"))).get()).toPHP() + ";}");
        writeFile(new File("JavaTranspiler-PHP/php/user.php"), "<?php\nfunction loaduser() { return " + PHPToLuaTranspiler.toPHP(new JavaMultiClassFileSource(
                new JavaSingleClassFileSource(new File("test/Test.class"))
        ).get()).toPHP() + ";}");
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

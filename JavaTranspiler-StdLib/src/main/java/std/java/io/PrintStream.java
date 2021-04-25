package std.java.io;

import eu.bebendorf.transpiler.interop.LuaImpl;
import eu.bebendorf.transpiler.interop.PHPImpl;

public class PrintStream extends FilterOutputStream {

    public void println(int value) {
        print(value);
        print("\n");
    }

    public void println(Object value) {
        print(value);
        print("\n");
    }

    public void println(String value) {
        print(value);
        print("\n");
    }

    public void print(int value) {
        print(String.valueOf(value));
    }

    public void print(long value) {
        print(String.valueOf(value));
    }

    public void print(double value) {
        print(String.valueOf(value));
    }

    public void print(float value) {
        print(String.valueOf(value));
    }

    public void print(boolean value) {
        print(String.valueOf(value));
    }

    public void print(char value) {
        print(String.valueOf(value));
    }

    public void print(Object value) {
        print(String.valueOf(value));
    }

    @LuaImpl("io.write(l[2].value)")
    @PHPImpl("echo $l[1][\"value\"];")
    public native void print(String value);

}

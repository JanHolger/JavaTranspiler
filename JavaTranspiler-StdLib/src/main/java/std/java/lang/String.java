package std.java.lang;

import eu.bebendorf.transpiler.interop.LuaImpl;
import eu.bebendorf.transpiler.interop.PHPImpl;

public class String {

    @LuaImpl("return v.newstring(tostring(l[1].value))")
    @PHPImpl("return $v->newstring(strval($l[0]['value']));")
    public static native String valueOf(int value);

    @LuaImpl("return v.newstring(tostring(l[1].value))")
    @PHPImpl("return $v->newstring(strval($l[0]['value']));")
    public static native String valueOf(long value);

    @LuaImpl("return v.newstring(tostring(l[1].value))")
    @PHPImpl("return $v->newstring(strval($l[0]['value']));")
    public static native String valueOf(double value);

    @LuaImpl("return v.newstring(tostring(l[1].value))")
    @PHPImpl("return $v->newstring(strval($l[0]['value']));")
    public static native String valueOf(float value);

}

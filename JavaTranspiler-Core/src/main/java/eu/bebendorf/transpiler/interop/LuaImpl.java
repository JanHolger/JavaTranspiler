package eu.bebendorf.transpiler.interop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface LuaImpl {
    String[] value();
}

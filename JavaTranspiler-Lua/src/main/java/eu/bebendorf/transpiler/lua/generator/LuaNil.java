package eu.bebendorf.transpiler.lua.generator;

public class LuaNil implements LuaValue {

    public static LuaNil INSTANCE = new LuaNil();

    private LuaNil() {}

    public String toLua() {
        return "nil";
    }

}

package eu.bebendorf.transpiler.lua.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LuaBoolean implements LuaValue {

    boolean value;

    public String toLua() {
        return String.valueOf(value);
    }

}

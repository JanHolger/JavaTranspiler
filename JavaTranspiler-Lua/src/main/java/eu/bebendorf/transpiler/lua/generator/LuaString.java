package eu.bebendorf.transpiler.lua.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LuaString implements LuaValue {

    String value;

    public String toLua() {
        return "\"" + value
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\"", "\\\"") + "\"";
    }

}

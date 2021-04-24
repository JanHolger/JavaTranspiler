package eu.bebendorf.transpiler.lua.generator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LuaFunction implements LuaValue {

    final String[] parameters;
    final List<String> code = new ArrayList<>();

    public LuaFunction(String... parameters) {
        this.parameters = parameters;
    }

    public String toLua() {
        return "function(" + String.join(",", parameters) + ")\n" + String.join("\n", code) + "\nend";
    }

}

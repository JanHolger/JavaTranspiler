package eu.bebendorf.transpiler.lua.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LuaExpression implements LuaValue {

    String expression;

    public String toLua() {
        return expression;
    }

}

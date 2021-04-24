package eu.bebendorf.transpiler.php.generator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PHPFunction implements PHPValue {

    final String[] parameters;
    final List<String> code = new ArrayList<>();

    public PHPFunction(String... parameters) {
        this.parameters = parameters;
    }

    public String toPHP() {
        return "function(" + String.join(",", parameters) + "){\n" + String.join("\n", code) + "\n}";
    }

}

package eu.bebendorf.transpiler.php.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PHPString implements PHPValue {

    String value;

    public String toPHP() {
        return "\"" + value
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\"", "\\\"") + "\"";
    }

}

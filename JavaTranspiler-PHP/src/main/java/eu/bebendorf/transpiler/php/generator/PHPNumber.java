package eu.bebendorf.transpiler.php.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PHPNumber implements PHPValue {

    Number value;

    public String toPHP() {
        return String.valueOf(value);
    }

}

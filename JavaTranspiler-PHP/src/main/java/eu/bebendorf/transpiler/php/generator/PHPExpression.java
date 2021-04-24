package eu.bebendorf.transpiler.php.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PHPExpression implements PHPValue {

    String expression;

    public String toPHP() {
        return expression;
    }

}

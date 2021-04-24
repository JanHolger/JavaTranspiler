package eu.bebendorf.transpiler.php.generator;

public class PHPNull implements PHPValue {

    public static PHPNull INSTANCE = new PHPNull();

    private PHPNull() {}

    public String toPHP() {
        return "NULL";
    }

}

package eu.bebendorf.transpiler.php.generator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PHPArray implements PHPValue {

    final Map<String, PHPValue> values = new HashMap<>();

    public boolean hasAt(int key) {
        return has(String.valueOf(key));
    }

    public boolean has(String key) {
        return values.get(key) != null;
    }

    public PHPArray setNumber(String key, Number value) {
        return set(key, new PHPNumber(value));
    }

    public PHPArray setNumberAt(int key, Number value) {
        return setAt(key, new PHPNumber(value));
    }

    public PHPArray setString(String key, String value) {
        return set(key, new PHPString(value));
    }

    public PHPArray setStringAt(int key, String value) {
        return setAt(key, new PHPString(value));
    }

    public PHPArray setBoolean(String key, boolean value) {
        return set(key, new PHPBoolean(value));
    }

    public PHPArray setBooleanAt(int key, boolean value) {
        return setAt(key, new PHPBoolean(value));
    }

    public PHPArray setAt(int key, PHPValue value) {
        return set(String.valueOf(key), value);
    }

    public PHPArray set(String key, PHPValue value) {
        if(value == null)
            values.remove(key);
        else
            values.put(key, value);
        return this;
    }

    public PHPArray addString(String value) {
        return add(new PHPString(value));
    }

    public PHPArray add(PHPValue value) {
        return setAt(len() + 1, value);
    }

    public int len() {
        int i = 0;
        while (hasAt(i + 1))
            i++;
        return i;
    }

    public PHPValue get(int key) {
        return get(String.valueOf(key));
    }

    public PHPValue get(String key) {
        return values.get(key);
    }

    public Set<String> keys() {
        return values.keySet();
    }

    public String toPHP() {
        return "[\n" + keys().stream().map(k -> toSafeKey(k) + " => " + get(k).toPHP()).collect(Collectors.joining(",\n")) + "\n]";
    }

    private static String toSafeKey(String key) {
        return "\"" + key.replace("\"", "\\\"") + "\"";
    }

}

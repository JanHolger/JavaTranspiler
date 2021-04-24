package eu.bebendorf.transpiler.lua.generator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LuaTable implements LuaValue {

    final Map<String, LuaValue> values = new HashMap<>();

    public boolean hasAt(int key) {
        return has(String.valueOf(key));
    }

    public boolean has(String key) {
        return values.get(key) != null;
    }

    public LuaTable setNumber(String key, Number value) {
        return set(key, new LuaNumber(value));
    }

    public LuaTable setNumberAt(int key, Number value) {
        return setAt(key, new LuaNumber(value));
    }

    public LuaTable setString(String key, String value) {
        return set(key, new LuaString(value));
    }

    public LuaTable setStringAt(int key, String value) {
        return setAt(key, new LuaString(value));
    }

    public LuaTable setBoolean(String key, boolean value) {
        return set(key, new LuaBoolean(value));
    }

    public LuaTable setBooleanAt(int key, boolean value) {
        return setAt(key, new LuaBoolean(value));
    }

    public LuaTable setAt(int key, LuaValue value) {
        return set(String.valueOf(key), value);
    }

    public LuaTable set(String key, LuaValue value) {
        if(value == null)
            values.remove(key);
        else
            values.put(key, value);
        return this;
    }

    public LuaTable addString(String value) {
        return add(new LuaString(value));
    }

    public LuaTable add(LuaValue value) {
        return setAt(len() + 1, value);
    }

    public int len() {
        int i = 0;
        while (hasAt(i + 1))
            i++;
        return i;
    }

    public LuaValue get(int key) {
        return get(String.valueOf(key));
    }

    public LuaValue get(String key) {
        return values.get(key);
    }

    public Set<String> keys() {
        return values.keySet();
    }

    public String toLua() {
        return "{\n" + keys().stream().map(k -> toSafeKey(k) + " = " + get(k).toLua()).collect(Collectors.joining(",\n")) + "\n}";
    }

    private static boolean isAlpha(char c) {
        String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        CHARSET += CHARSET.toLowerCase(Locale.ROOT);
        for(char c1 : CHARSET.toCharArray()) {
            if(c1 == c)
                return true;
        }
        return false;
    }

    private static boolean isAlphaNum(String s) {
        for(char c : s.toCharArray()) {
            if(!Character.isDigit(c) && ! isAlpha(c))
                return false;
        }
        return true;
    }

    private static boolean isNum(String s) {
        s = s.trim();
        if(s.startsWith("-"))
            s = s.substring(1).trim();
        for(char c : s.toCharArray()) {
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    private static String toSafeKey(String key) {
        if(isAlpha(key.charAt(0)))
            if(isAlphaNum(key))
                return key;
        if(isNum(key))
            return "[" + key + "]";
        return "[\"" + key.replace("\"", "\\\"") + "\"]";
    }

}

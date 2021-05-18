package std.java.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true)
public class StackTraceElement {

    java.lang.String className;
    java.lang.String methodName;
    java.lang.String fileName;
    int lineNumber;

    public boolean isNativeMethod() {
        return lineNumber == -2;
    }

    public java.lang.String toString() {
        java.lang.String s = "";
        s = s.isEmpty() ? className : s + "/" + className;
        return s + "." + methodName + "(" +
                (isNativeMethod() ? "Native Method)" :
                        (fileName != null && lineNumber >= 0 ?
                                fileName + ":" + lineNumber + ")" :
                                (fileName != null ?  ""+fileName+")" : "Unknown Source)")));
    }

}

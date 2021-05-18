package std.java.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Throwable {

    java.lang.String message;
    java.lang.Throwable cause;

    public Throwable(java.lang.String message) {
        this(message, null);
    }

    public Throwable(java.lang.Throwable cause) {
        this(null, cause);
    }

}

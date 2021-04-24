package std.java.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Throwable {

    String message;
    Throwable cause;

    public Throwable(String message) {
        this(message, null);
    }

    public Throwable(Throwable cause) {
        this(null, cause);
    }

}

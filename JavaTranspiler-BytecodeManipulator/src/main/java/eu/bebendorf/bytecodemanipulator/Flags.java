package eu.bebendorf.bytecodemanipulator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Modifier;

@AllArgsConstructor
@Getter
@Setter
public class Flags {

    short flags;

    public boolean isStatic() {
        return Modifier.isStatic(flags);
    }

    public boolean isNative() {
        return Modifier.isNative(flags);
    }

    public boolean isPublic() {
        return Modifier.isPublic(flags);
    }

    public boolean isPrivate() {
        return Modifier.isPrivate(flags);
    }

    public boolean isProtected() {
        return Modifier.isProtected(flags);
    }

}

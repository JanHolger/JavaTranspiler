package eu.bebendorf.bytecodemanipulator.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionTableEntry {

    int startPC;
    int endPC;
    int handlerPC;
    int catchType;

}

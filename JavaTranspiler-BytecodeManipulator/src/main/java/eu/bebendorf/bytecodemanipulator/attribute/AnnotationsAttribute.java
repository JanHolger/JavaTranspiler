package eu.bebendorf.bytecodemanipulator.attribute;

import eu.bebendorf.bytecodemanipulator.ByteCodeHelper;
import eu.bebendorf.bytecodemanipulator.ClassFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AnnotationsAttribute {

    ClassFile classFile;
    List<AnnotationInfo> annotations;

    public static AnnotationsAttribute fromData(ClassFile classFile, byte[] data) {
        ByteBuffer bb = ByteBuffer.wrap(data);
        List<AnnotationInfo> annotations = new ArrayList<>();
        int annotationCount = ByteCodeHelper.readUnsignedShort(bb);
        for(int i = 0; i < annotationCount; i++)
            annotations.add(AnnotationInfo.read(classFile, bb));
        return new AnnotationsAttribute(classFile, annotations);
    }

}

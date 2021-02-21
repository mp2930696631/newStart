package com.hz.v5.other;

import net.sf.cglib.core.ClassGenerator;
import net.sf.cglib.core.Constants;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.transform.ClassEmitterTransformer;
import net.sf.cglib.transform.TransformingClassGenerator;
import org.objectweb.asm.Type;

/**
 * @author zehua
 * @date 2021/2/18 13:31
 */
public class MyStrategy extends DefaultGeneratorStrategy {

    @Override
    protected ClassGenerator transform(ClassGenerator cg) throws Exception {
        ClassEmitterTransformer emitter = new ClassEmitterTransformer() {
            @Override
            public void end_class() {
                declare_field(Constants.ACC_PRIVATE, "$$iName", Type.getType(String.class), null);
                super.end_class();
            }
        };

        return new TransformingClassGenerator(cg, emitter);
    }
}

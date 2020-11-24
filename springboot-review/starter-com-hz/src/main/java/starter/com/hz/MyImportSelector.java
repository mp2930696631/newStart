package starter.com.hz;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import starter.com.hz.controller.StarterController;
import starter.com.hz.service.StarterService;

/**
 * @author zehua
 * @date 2020/11/24 20:14
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String service = StarterService.class.getName();
        System.out.println(service);

        return new String[]{service};
    }
}

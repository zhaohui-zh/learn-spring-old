package com.zh.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.function.Predicate;

/**
 * @author Zhaohui
 * @date 2020/10/9
 */
// 自定义逻辑，返回需要导入的组件全类名
public class MyImportSelector implements ImportSelector {
    // 返回值就是要导入到ioc容器的组件的全类名
    // AnnotationMetadata：当前标注@Import注解的类的所有注解信息
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // importingClassMetadata.getAllAnnotationAttributes()

        return new String[] {"com.zh.bean.Blue", "com.zh.bean.Yellow"};
    }

    @Override
    public Predicate<String> getExclusionFilter() {
        return null;
    }
}

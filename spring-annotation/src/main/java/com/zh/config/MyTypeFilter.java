package com.zh.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author Zhaohui
 * @date 2020/10/8
 */
public class MyTypeFilter implements TypeFilter {
    // metadataReader: the metadata reader for the target class 读取到当前正在扫描的类的信息
    // metadataReaderFactory: a factory for obtaining metadata readers for other classes
    // (such as superclasses and interfaces) 可以获取到其他类的信息
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的类信息(类型、实现接口等)
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类的资源(类的路径)
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println("-->" + className);
        if (className.contains("er")) {
            return true;
        }
        return false;
    }
}

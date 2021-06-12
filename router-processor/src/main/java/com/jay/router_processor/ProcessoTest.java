package com.jay.router_processor;

import com.google.auto.service.AutoService;
import com.jay.router_annotations.DemoAnnotation;
import com.jay.router_annotations.Destination;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @author jaydroid
 * @version 1.0
 * @date 5/26/21
 */
@AutoService(Processor.class)
public class ProcessoTest extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> hashSet = new HashSet<String>();
        hashSet.add(Destination.class.getCanonicalName());
        hashSet.add(DemoAnnotation.class.getCanonicalName());
        return hashSet;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("=============ProcessoTest==============");

        return false;
    }
}

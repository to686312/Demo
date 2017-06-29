package com.example;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

/**
 * Created by Shinelon on 2017/3/23.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class CokeProcessor extends AbstractProcessor {//AbstractProcessor两个最重要的方法
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment environment) {
        //Messager 用来输出。就像我们平时用的System.out.pringln()和Log.d。输出位置在编译器下方的Messages窗口。这里System.out也是可以用的哦~
        Messager messager = processingEnv.getMessager();
            //用for循环遍历所有的 GetMsg注解，然后进行处理
        for (Element element : environment.getElementsAnnotatedWith(GetMsg.class)) {
            PackageElement packageElement = (PackageElement) element
                    .getEnclosingElement();
            //获取该注解所在类的包名
            String packageName = packageElement.getQualifiedName().toString();
            TypeElement classElement = (TypeElement) element;
            //获取该注解所在类的类名
            String className = classElement.getSimpleName().toString();
            //获取该注解所在类的全类名
            String fullClassName = classElement.getQualifiedName().toString();
            VariableElement variableElement = (VariableElement) element.getEnclosingElement();
            //获取方法名
            String methodName = variableElement.getSimpleName().toString();
            //获取该注解的值
            int id = classElement.getAnnotation(GetMsg.class).id();//   获取int的 id
            String name = classElement.getAnnotation(GetMsg.class).name();//   获取name
            //Diagnostic.Kind.NOTE 类似于Log.d Log.e这样的等级
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : packageName = " + packageName);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : className = " + className);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : fullClassName = " + fullClassName);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : methodName = " + methodName);
            messager.printMessage(Diagnostic.Kind.NOTE,
                    "Annotation class : id = " + id + "  name = " + name);
        }
        //return true;表示该Process已经处理了，其他的Process不需要再处理了。
        return true;
    }

    /**
     *  getSupportedAnnotationTypes用来表示该Processor处理哪些注解。这里我们只有一个GetMsg注解需要处理
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(GetMsg.class.getCanonicalName());
        return types;
    }
}

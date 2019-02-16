package xb.dev.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xb.dev.feign.annotation.FeignComponent;
import xb.dev.feign.annotation.FeignInclude;
import xb.dev.feign.config.ConfigProperties;
import xb.dev.feign.constant.DefaultConstants;
import xb.dev.feign.exception.DefaultException;
import xb.dev.feign.model.MethodModel;
import xb.dev.feign.model.ServiceModel;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

/**
 * @author Created by huang xiao bao
 * @date 2019-01-30 15:12:11
 */
@SpringBootApplication
public class Main {
    private ConfigProperties configs;
    public static void main(String[] args) throws Exception{
        Main m = new Main();
        Map<String, Class<?>> classOfPackage = m.getClassOfPackage("xb.dev.feign.controller");
        m.filterNoFeignComponent(classOfPackage);
    }

    private void filterNoFeignComponent(Map<String,Class<?>> classMap){

        Set<Map.Entry<String, Class<?>>> entries = classMap.entrySet();
        for (Map.Entry<String, Class<?>> entry : entries) {
            ServiceModel serviceModel = new ServiceModel();
            Class<?> value = entry.getValue();
            RequestMapping annotation = value.getAnnotation(RequestMapping.class);
            if(annotation != null){
                serviceModel.setRequestMapping(annotation.value()[0]);
                if(serviceModel.getImports() == null){
                    serviceModel.setImports(new HashSet<>());
                }
                serviceModel.getImports().add(RequestMapping.class.getName());
            }
            String className =  value.getSimpleName();
            if(className.endsWith("Controller")){
                className = className.replace("Controller","Client");
            }
            serviceModel.setName(className);
            serviceModel.setFallback(className + "FallBack");

            Method[] declaredMethods = value.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.getAnnotation(FeignInclude.class) != null) {
                    MethodModel methodModel = new MethodModel();
                    Annotation[] declaredAnnotations = declaredMethod.getDeclaredAnnotations();
                    for (Annotation declaredAnnotation : declaredAnnotations) {
                        if(declaredAnnotation instanceof GetMapping){
                            serviceModel.getImports().add(GetMapping.class.getName());
                            methodModel.setName(declaredMethod.getName());
                            methodModel.setReturnType(declaredMethod.getReturnType().toGenericString());
                            Field[] declaredFields = declaredMethod.getReturnType().getDeclaredFields();

                            System.out.println();
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param basePackage
     * @return
     */
    private Map<String,Class<?>> getClassOfPackage(String basePackage){
        configs = new ConfigProperties();
        configs.setBasePackage(basePackage);

        ClassLoader  currentClassLoader = Thread.currentThread().getContextClassLoader();
        URL baseUrl = currentClassLoader.getResource(basePackage.replace(".","/"));
        if(baseUrl == null){
            throw new DefaultException("path not found");
        }
        File file = null;
        try {
            file = new File(baseUrl.toURI());
        } catch (URISyntaxException e) {

        }
        Map<String,Class<?>> classMap = new HashMap<>(16);
        readFile(file,classMap);
        return classMap;
    }

    /**
     *
     * @param source
     * @param classMap
     */
    private void readFile(File source,Map<String,Class<?>> classMap){
        File[] files = source.listFiles();
        for (File file : files) {
            if(file.isDirectory()){
                readFile(file,classMap);
            }else {
                putClass(file,classMap);
            }
        }
    }

    /**
     *
     * @param f
     * @param classMap
     */
    private void putClass(File f,Map<String,Class<?>> classMap){
        String name = f.getName();
        if(!name.endsWith(DefaultConstants.CLASS_SUFFIX)){
            return;
        }
        int len = name.length();
        //全类名
        String classAllName = configs.getBasePackage() + "." +name.substring(0, len - DefaultConstants.CLASS_SUFFIX.length());
        try {
            Class<?> cls = Class.forName(classAllName);
            if(cls.getAnnotation(FeignComponent.class) != null){
                classMap.put(classAllName,cls);
            }
        } catch (ClassNotFoundException e) {
            //
        }
    }
}

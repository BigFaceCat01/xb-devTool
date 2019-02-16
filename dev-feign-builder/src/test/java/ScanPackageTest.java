import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import xb.dev.feign.Main;
import xb.dev.feign.annotation.FeignInclude;
import xb.dev.feign.model.ContentModel;
import xb.dev.feign.model.ServiceModel;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-01 11:46:05
 */
@SpringBootTest(classes = Main.class)
@RunWith(SpringRunner.class)
public class ScanPackageTest {
    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    public void testScan() throws Exception{

        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources = resolver.getResources("classpath*:xb/dev/feign/controller/**/*.class");
        ContentModel contentModel = new ContentModel();
        contentModel.setAuthor("huang xiao bao");
        contentModel.setCreateTime(new Date());
        List<ServiceModel> serviceModels = new ArrayList<>();
        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
            String clsName = reader.getClassMetadata().getClassName();
            contentModel.setPackageName(clsName.substring(0,clsName.indexOf("controller")) + "provider");
            boolean b = annotationMetadata.hasAnnotatedMethods(FeignInclude.class.getName());
            if(b) {
                String allName = reader.getClassMetadata().getClassName();
                String simpleName = allName.substring(allName.lastIndexOf(".")+1);
                ServiceModel serviceModel = new ServiceModel();
                serviceModel.setName("xb-dev-provider");
                serviceModel.setFallback(simpleName.replace("Controller","ClientFallBack"));
                Class<?> cls = Class.forName(allName);
                serviceModel.setImports(annotationMetadata.getAnnotationTypes());
                serviceModels.add(serviceModel);
                contentModel.setImports(annotationMetadata.getAnnotationTypes());
                contentModel.setService(serviceModel);
                contentModel.setServices(serviceModels);
                Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(FeignInclude.class.getName());
//                for (MethodMetadata item : annotatedMethods) {
//                    Method declaredMethod = cls.getDeclaredMethod(item.getMethodName());
//                    Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
//                    for (Class<?> parameterType : parameterTypes) {
//
//                    }
//                }

            }
        }


        writeJava(contentModel);
    }

    private void writeJava(ContentModel contentModel) throws Exception{
        //1.0 创建配置对象
        //创建Configuration实例，指定FreeMarker的版本
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        //指定模板所在的目录
        File templatesDir = ResourceUtils.getFile("classpath:template");
        cfg.setDirectoryForTemplateLoading(templatesDir);
        //设置默认字符集
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
        //设置出现异常处理的方式，开发阶段可以设置为HTML_DEBUG_HANDLER
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        //2.0 创建数据模型
        Map<String, Object> root = new HashMap<>();

        root.put("content",contentModel);
        //3.0 获取模板
        Template template = cfg.getTemplate("ServiceClient.ftl");

        //4.0 给模板绑定数据模型
        Writer out = new OutputStreamWriter(System.out);
        template.process(root, out);
        //3.0 获取模板
        Template template2 = cfg.getTemplate("ServiceClient.ftl");
        Process git_status = Runtime.getRuntime().exec("git status");
        git_status.waitFor();
        if(git_status.exitValue() != 0){

        }
        try (InputStream is = git_status.getInputStream();
             BufferedReader bs = new BufferedReader(new InputStreamReader(is))) {
            String line = null;
            while ((line = bs.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println();
    }
}

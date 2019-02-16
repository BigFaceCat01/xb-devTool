package xb.dev.feign.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Created by huang xiao bao
 * @date 2019-01-30 14:54:23
 */
@Data
public class ServiceModel {
    /**
     *
     */
    private String requestMapping;
    /**
     * 引入的类
     */
    private Set<String> imports;
    /**
     * feign名称
     */
    private String name;
    /**
     * fallback名称
     */
    private String fallback;
    /**
     * 方法列表
     */
    private List<MethodModel> methods;
}

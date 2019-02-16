package xb.dev.feign.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Created by huang xiao bao
 * @date 2019-01-30 14:46:40
 */
@Data
public class ContentModel {
    private List<ServiceModel> services;
    private ServiceModel service;
    private Set<String> imports;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 作者
     */
    private String author;
    /**
     * 创建时间
     */
    private Date createTime;
}

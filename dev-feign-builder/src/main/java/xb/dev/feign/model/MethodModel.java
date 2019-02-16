package xb.dev.feign.model;

import lombok.Data;

import java.util.List;

/**
 * @author Created by huang xiao bao
 * @date 2019-01-30 14:55:04
 */
@Data
public class MethodModel {
    private String name;
    private String returnType;
    private List<String> params;
}

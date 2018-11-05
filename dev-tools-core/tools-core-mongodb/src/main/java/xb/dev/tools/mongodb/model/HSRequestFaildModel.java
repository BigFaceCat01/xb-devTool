package xb.dev.tools.mongodb.model;

import lombok.Data;

/**
 * @author Created by huang xiao bao
 * @date 2018-10-30 16:19:04
 */
@Data
public class HSRequestFaildModel {
    /**
     * 细类名称
     */
    private String categoryName;
    /**
     * 编码
     */
    private String number;
    /**
     * 请求失败的路径
     */
    private String url;

    public HSRequestFaildModel(String categoryName, String number, String url) {
        this.categoryName = categoryName;
        this.number = number;
        this.url = url;
    }

    public HSRequestFaildModel() {
    }
}

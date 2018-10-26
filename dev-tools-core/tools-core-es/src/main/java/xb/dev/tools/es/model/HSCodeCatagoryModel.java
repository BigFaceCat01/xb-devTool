package xb.dev.tools.es.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by huangxb
 * @date 2018-10-26 16:59:09
 */
@Data
public class HSCodeCatagoryModel implements Serializable {
    /**
     * 编码
     */
    private String number;
    /**
     * 细类名称
     */
    private String catatoryName;
    /**
     * 海关编码
     */
    private String hsCode;
}

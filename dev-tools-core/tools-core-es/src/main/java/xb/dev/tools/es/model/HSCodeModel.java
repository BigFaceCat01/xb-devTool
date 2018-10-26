package xb.dev.tools.es.model;

import lombok.Data;
import xb.dev.document.annotation.Document;
import xb.dev.document.annotation.Head;
import xb.dev.document.common.DocumentType;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Created by huangxb
 * @date 2018-10-25 09:31:52
 */
@Data
@Document(documentType = DocumentType.EXCEL)
public class HSCodeModel implements Serializable {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 编码
     */
    @Head(name = "编码")
    private String number;
    /**
     * 名称
     */
    @Head(name = "名称")
    private String name;
    /**
     * 许可证代码
     */
    @Head(name = "许可证代码")
    private String permissionCode;
    /**
     * 普通税率
     */
    @Head(name = "普通税率")
    private Double normalTaxRate;
    /**
     * 优惠税率
     */
    @Head(name = "优惠税率")
    private Double discountTaxRate;
    /**
     * 备注
     */
    @Head(name = "备注")
    private String remark;
    /**
     * 出口税率
     */
    @Head(name = "出口税率")
    private Double exportTaxRate;
    /**
     * 消费税率
     */
    @Head(name = "消费税率")
    private Double consumeTaxRate;
    /**
     * 增值税率
     */
    @Head(name = "增值税率")
    private Double appreciationTaxRate;
    /**
     * 第一法定单位
     */
    @Head(name = "第一法定单位")
    private String firstLegalUnit;
    /**
     * 第二法定单位
     */
    @Head(name = "第二法定单位")
    private String secondLegalUnit;
}

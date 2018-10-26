package xb.dev.document.model;

import lombok.Data;
import xb.dev.document.annotation.Document;
import xb.dev.document.annotation.Head;
import xb.dev.document.common.DocumentType;

/**
 * @author Created by huangxb
 * @date 2018-10-26 15:05:38
 */
@Data
@Document(documentType = DocumentType.EXCEL)
public class HSCatagoryModel {
    /**
     * 编码
     */
    @Head(name = "编码")
    private String number;
    /**
     * 细类名称
     */
    @Head(name = "细类名称")
    private String catagoryName;
}

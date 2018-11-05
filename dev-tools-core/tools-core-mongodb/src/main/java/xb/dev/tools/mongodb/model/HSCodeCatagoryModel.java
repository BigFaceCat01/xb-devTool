package xb.dev.tools.mongodb.model;

import lombok.Data;
import xb.dev.document.annotation.Document;
import xb.dev.document.annotation.Head;
import xb.dev.document.common.DocumentType;

import java.io.Serializable;

/**
 * @author Created by huangxb
 * @date 2018-10-26 16:59:09
 */
@Data
@Document(documentType = DocumentType.EXCEL)
public class HSCodeCatagoryModel implements Serializable {
    /**
     * 编码
     */
    @Head(name = "编码")
    private String number;
    /**
     * 细类名称
     */
    @Head(name = "细类名称")
    private String catatoryName;
    /**
     * 海关编码
     */
    @Head(name = "海关编码")
    private String hsCode;
}

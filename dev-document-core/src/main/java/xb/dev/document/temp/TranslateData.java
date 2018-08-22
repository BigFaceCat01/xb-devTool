package xb.dev.document.temp;

import lombok.Getter;
import lombok.Setter;
import xb.dev.document.annotation.Document;
import xb.dev.document.annotation.Head;
import xb.dev.document.common.DocumentType;

import java.io.Serializable;

/**
 * @Author: Created by huangxb on 2018-08-20 18:07:17
 * @Description:
 */
@Getter
@Setter
public class TranslateData implements Serializable {
    @TableHead(name = "译前（中文）")
    private String cn;
    @TableHead(name = "译后（英文）")
    private String en;
    @TableHead(name = "简称")
    private String brief;
}

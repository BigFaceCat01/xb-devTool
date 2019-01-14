package xb.dev.document.model;

import lombok.Data;
import xb.dev.document.annotation.Document;
import xb.dev.document.annotation.Head;
import xb.dev.document.common.DocumentType;
import xb.dev.document.temp.Excel;
import xb.dev.document.temp.TableHead;

import java.io.Serializable;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-26 10:32:35
 */
@Data
@Document(documentType = DocumentType.EXCEL)
public class NewAndOld implements Serializable {
    @Head(name = "旧四级英文")
    private String oldCategory;
    @Head(name = "新四级英文")
    private String newCategory;
}

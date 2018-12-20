package xb.dev.document.model;

import lombok.Data;

import java.util.List;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-19 09:57:54
 */
@Data
public class MallPropData {
    private String[] category;
    private List<MallGoodsProp> mallGoodsProp;
}

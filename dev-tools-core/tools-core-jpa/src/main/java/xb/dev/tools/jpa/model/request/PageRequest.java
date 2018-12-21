package xb.dev.tools.jpa.model.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Optional;

/**
 * 分页工具类
 *
 * @author huang xiao bao
 * @date 2018-12-12 10:17
 */
public class PageRequest implements Serializable {
	@ApiModelProperty("当前页")
	protected Integer pageNum = 1;
	@ApiModelProperty("每页显示数据条数")
	protected Integer pageSize = 10;

	public Integer getPageNum() {
		return Optional.ofNullable(pageNum).filter(item->item>0).orElse(1);
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

    public Integer getPageSize() {
        return Optional.ofNullable(pageSize).filter(item->item>0).orElse(10);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

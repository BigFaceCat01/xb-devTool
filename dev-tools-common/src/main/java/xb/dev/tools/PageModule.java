package xb.dev.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-02 15:28:29
 * @Description: 分页结果类，用于包装分页结果
 */
public class PageModule<E> {

    public PageModule(long l, Integer pageSize, Integer pageNum, List<E> data) {
        super();
        this.totalSize = l;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.data = data;
    }

    /**
     * 总条数
     */
    private long totalSize=0;
    /**
     * 总页数
     */
    private Integer totalPage=0;
    /**
     * 每页条数
     */
    private Integer pageSize=0;
    /**
     * 当前页
     */
    private Integer pageNum=1;
    /**
     * 数据
     */
    private List<E> data=new ArrayList<E>();

    public long getTotalSize() {
        if (totalSize < 0) {
            totalSize = 0;
        }
        return totalSize;
    }

    public Integer getTotalPage() {
        totalPage = (int) ((getTotalSize() - 1) / pageSize + 1);
        return totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNum() {
        if (pageNum == null || pageNum < 0) {
            pageNum = 1;
        }
        return pageNum;
    }

    public List<E> getData() {
        if (data == null) {
            data = Collections.emptyList();
        }
        return data;
    }


    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }


    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    public void setData(List<E> data) {
        this.data = data;
    }

    public PageModule() {
        super();
    }
}
package xb.dev.document.node;

/**
 * @Author: Created by huangxb on 2018-07-27 11:06:58
 * @Description:
 */
public abstract class Node {
    //.获得某节点的数据
    //.根据某节点指定名称的数据
    //.获得某节点数据类型

    protected Node child;


    public abstract Object getData();

    public abstract String belongField();
}

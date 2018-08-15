package xb.dev.document.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xb.dev.document.common.StringTool;
import xb.dev.document.config.ConfigurationData;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.document.node.Node;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Created by huangxb on 2018-07-27 10:01:39
 * @Description: 表格处理类
 * @param <T>
 */
public abstract class DocumentHandler<T> {

    private static final String DOCUMENT_NAME = "DOCUMENT";
    /**
     * 日志记录
     */
    protected Logger logger = LoggerFactory.getLogger(DocumentHandler.class);
    /**
     * 配置信息
     */
    protected ConfigurationData configurationData;
    /**
     * 文档流对象
     */
    protected InputStream is;
    /**
     * 文件路径
     */
    protected String path;

    //.获得文档的根节点
    public abstract Node getRootNode();

    //.获得某节点的子节点
    public abstract List<Node> getNextNodes();

    //.获得所有节点,即整颗文档树的所有节点
    public abstract boolean hasNext();


    public DocumentHandler() {

    }

    public DocumentHandler(String path, InputStream is, ConfigurationData configurationData) throws DocumentHandlerException {
        //构建配置信息
        this.configurationData = configurationData;
        this.is = is;
        this.path = path;
    }

    /**
     * 文档处理开始
     *
     * @return
     * @throws Exception
     */
    public Map<String, List<T>> parse() throws DocumentHandlerException {
        Map<String, List<T>> resultMap = new HashMap<String, List<T>>();

        boolean hasNext = hasNext();
        List<T> dataList = new ArrayList<>();
        while (hasNext) {
            List<Node> nodes = getNextNodes();
            try {
                T instance = (T) configurationData.getTarget().newInstance();
                for (Node node : nodes) {
                    setValue(instance, node.getData(), node.belongField());
                }
                if (nodes.size() > 0) {
                    dataList.add(instance);
                }
            } catch (Exception e) {
                logger.error("cause:" + e.getMessage(), e);
                throw new DocumentHandlerException("cause:" + e.getMessage(), e);
            }
            hasNext = hasNext();
        }
        //返回结果列表
        resultMap.put(DOCUMENT_NAME, dataList);
        return resultMap;
    }

    private void setValue(Object obj, Object value, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        //字段名为空，则不进行任何操作
        if (StringTool.isBlank(fieldName)) {
            return;
        }
        //根据定义属性名获得字段
        Field f = configurationData.getTarget().getDeclaredField(fieldName);

        //设置字段可更改为true，否则如果字段没有使用public修饰无法修改
        f.setAccessible(true);
        //进行赋值
        f.set(obj, value);

    }

    /**
     *
     * @return
     */
    public abstract InputStream outputDocument() throws DocumentHandlerException;
}

package xb.dev.document.config;

import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.document.handler.DocumentHandler;
import xb.dev.document.handler.impl.PoiHandler;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-07-28 09:42:32
 * @Description:
 */
public class DocumentHandlerBuilder {

    private ConfigurationData configurationData;

    public <T> DocumentHandlerBuilder buildConfig(Class<T> target) throws DocumentHandlerException {
        configurationData = new ConfigurationData(target);
        return this;
    }

    public DocumentHandler build(String path, InputStream is) throws DocumentHandlerException {
        switch (configurationData.getDocument().documentType()){
            case EXCEL:
                return new PoiHandler(path,is,configurationData);
            case XML:
                return null;

            default:return null;
        }
    }
    public DocumentHandler build(List data) throws DocumentHandlerException {
        switch (configurationData.getDocument().documentType()){
            case EXCEL:
                return new PoiHandler(data,configurationData);
            case XML:
                return null;

            default:return null;
        }
    }
}

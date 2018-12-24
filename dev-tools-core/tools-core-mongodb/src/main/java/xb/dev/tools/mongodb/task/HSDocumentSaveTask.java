package xb.dev.tools.mongodb.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import xb.dev.document.DocumentUtil;
import xb.dev.tools.mongodb.model.HSCodeCatagoryModel;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-10-29 09:35:51
 */
@Slf4j
@Component("hSDocumentSaveTask")
public class HSDocumentSaveTask implements Runnable {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run() {
                log.info("开始生成excel表格");
                int limit = 65535;
                int from = 1;
                    List<HSCodeCatagoryModel> lsit = mongoTemplate.find(new Query(),HSCodeCatagoryModel.class,"hsCode");

                    int s = lsit.size();
                    List<HSCodeCatagoryModel> temp = new ArrayList<>(66000);
                    for(int i=0;i<s;i++) {
                        temp.add(lsit.get(i));
                        if(i==0){
                            continue;
                        }
                        if(i%65534==0||i==(s-1)) {
                            byte[] buffer = new byte[2048];
                            try (InputStream is = DocumentUtil.createDocument(temp, HSCodeCatagoryModel.class);

                                 OutputStream os = new FileOutputStream("D:/hscode/excel/hs-code-excel-" + from + ".xslx")) {
                                log.info("D:/hscode/excel/hs-code-excel-" + from + ".xslx");
                                int len = 0;
                                while ((len = is.read(buffer)) != -1) {
                                    os.write(buffer, 0, len);
                                }
                                os.flush();
                                from ++;
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            temp.clear();
                        }
                    }



                log.info("表格生成结束");
    }
}

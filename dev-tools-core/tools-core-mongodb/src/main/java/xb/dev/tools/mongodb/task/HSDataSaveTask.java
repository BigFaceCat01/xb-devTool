package xb.dev.tools.mongodb.task;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import xb.dev.tools.mongodb.model.HSCodeCatagoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Created by huangxb
 * @date 2018-10-23 18:06:21
 */
@Slf4j
@Component(value = "hsDataSaveTask")
public class HSDataSaveTask implements Runnable{
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * 存储数据队列
     */
    private ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue;

    public HSDataSaveTask() {
    }

    public HSDataSaveTask(ArrayBlockingQueue queue){
        this.dataQueue = queue;
    }

    @Override
    public void run() {
        List<HSCodeCatagoryModel> r = new ArrayList<>(100);
        while(true){
            try {
                r.clear();
                for(int i=0;i<80;i++) {
                    r.add(dataQueue.take());
                }
                mongoTemplate.insert(r,"hsCode");
            } catch (InterruptedException e) {
                if(!r.isEmpty()){
                    mongoTemplate.insert(r,"hsCode");
                }
                log.info("************************数据抓取完毕**************************");
                break;
            }catch (Exception e){
                e.printStackTrace();
                log.error("存储{}失败",JSON.toJSONString(r));
            }
        }
    }

    public void setDataQueue(ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue) {
        this.dataQueue = dataQueue;
    }
}

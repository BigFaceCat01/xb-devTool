package xb.dev.tools.es.task;

import xb.dev.tools.es.dao.entity.EsNewsEntity;
import xb.dev.tools.es.model.HSCodeCatagoryModel;
import xb.dev.tools.es.service.EsNewsService;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Created by huangxb
 * @date 2018-10-23 18:06:21
 */
public class HSDataSaveTask implements Runnable{
    /**
     * 存储数据队列
     */
    private ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue;

    public HSDataSaveTask(ArrayBlockingQueue queue, EsNewsService esNewsService){
        this.dataQueue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                HSCodeCatagoryModel hsCodeCatagoryModel = dataQueue.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

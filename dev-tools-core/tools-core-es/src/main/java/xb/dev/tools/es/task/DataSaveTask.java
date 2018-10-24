package xb.dev.tools.es.task;

import xb.dev.tools.es.dao.entity.EsNewsEntity;
import xb.dev.tools.es.service.EsNewsService;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Created by huangxb
 * @date 2018-10-23 18:06:21
 */
public class DataSaveTask implements Runnable{

    private EsNewsService esNewsService;
    /**
     * 存储数据队列
     */
    private ArrayBlockingQueue<EsNewsEntity> dataQueue;

    public DataSaveTask(ArrayBlockingQueue queue, EsNewsService esNewsService){
        this.dataQueue = queue;
        this.esNewsService = esNewsService;
    }

    @Override
    public void run() {
        while(true){
            try {
                EsNewsEntity esNewsEntity = dataQueue.take();
                esNewsService.insert(esNewsEntity);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

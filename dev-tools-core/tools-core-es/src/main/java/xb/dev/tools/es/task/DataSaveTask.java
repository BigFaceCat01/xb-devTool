package xb.dev.tools.es.task;

import xb.dev.tools.es.dao.entity.EsNewsEntity;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Created by huangxb
 * @date 2018-10-23 18:06:21
 */
public class DataSaveTask implements Runnable{
    /**
     * 存储数据队列
     */
    private ConcurrentLinkedQueue<EsNewsEntity> dataQueue;

    public DataSaveTask(ConcurrentLinkedQueue queue){
        this.dataQueue = queue;
    }

    @Override
    public void run() {
        while(true){

        }
    }
}

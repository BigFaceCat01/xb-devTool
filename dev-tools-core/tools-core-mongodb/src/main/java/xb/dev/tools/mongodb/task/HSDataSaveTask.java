package xb.dev.tools.mongodb.task;

import xb.dev.tools.mongodb.model.HSCodeCatagoryModel;

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

    public HSDataSaveTask(ArrayBlockingQueue queue){
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

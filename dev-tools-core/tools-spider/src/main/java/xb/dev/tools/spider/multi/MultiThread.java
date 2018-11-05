package xb.dev.tools.spider.multi;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Created by huang xiao bao
 * @date 2018-11-01 14:19:18
 */
public class MultiThread {


    public static void main(String[] args){
        ExecutorService pool = new ThreadPoolExecutor(4,8,10,TimeUnit.SECONDS,new LinkedBlockingQueue<>(1024));
        Lock lock = new ReentrantLock();
        for(int i=0;i<4;i++) {
            pool.execute(new OrderedTask(lock,i+""));
        }
        pool.shutdown();
    }
}

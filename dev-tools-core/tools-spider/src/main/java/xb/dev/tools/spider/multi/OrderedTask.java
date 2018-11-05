package xb.dev.tools.spider.multi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Created by huang xiao bao
 * @date 2018-11-01 15:31:23
 */
public class OrderedTask implements Runnable {

    private Lock lock;

    private static Condition order;

    private Condition self;

    private String name;

    public OrderedTask(Lock lock, String name) {
        this.lock = lock;
        this.self = lock.newCondition();
        this.name = name;
    }

    @Override
    public void run() {

        int i=0;
        do {
            if(order == null){
                lock.lock();
                order = self;
                System.out.print(name);
                lock.unlock();
                try {
                    self.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }while (i++<5);
    }
}

package com.chuck.concurrencytest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by chuck on 21/04/2017.
 */
public class AsyncManager {

    private Queue<Request> asyncMgrQueue;

    private List<AsyncManagerWorkerThread> asyncManagerWorkerThreads;

    public AsyncManager() {
        this.asyncMgrQueue = new LinkedList<>();
        asyncManagerWorkerThreads = new ArrayList<>();
        for (int i = 0; i < Constants.WORKER_THREADS_NUM; i++)
            asyncManagerWorkerThreads.add(new AsyncManagerWorkerThread((i + 1), "Thread " + (i + 1), this));

        for (int i = 0; i < Constants.WORKER_THREADS_NUM; i++) {
            asyncManagerWorkerThreads.get(i).start();
        }
    }

    public synchronized void addRequest(Request req) {
        this.asyncMgrQueue.add(req);
        notifyAll();
    }

    public synchronized Request getRequest() {
        while (asyncMgrQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return asyncMgrQueue.remove();
    }

    public Queue<Request> getAsyncMgrQueue() {
        return asyncMgrQueue;
    }
}

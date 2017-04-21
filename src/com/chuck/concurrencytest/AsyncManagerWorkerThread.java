package com.chuck.concurrencytest;

/**
 * Created by chuck on 21/04/2017.
 */
public class AsyncManagerWorkerThread extends Thread {

    private int threadId;
    private String name;
    private AsyncManager asyncManager;
    private int processedRequests;

    public AsyncManagerWorkerThread(int threadId, String threadName, AsyncManager asyncManager) {
        this.threadId = threadId;
        this.name = threadName;
        this.asyncManager = asyncManager;
        this.processedRequests = 0;
    }

    @Override
    public void run() {
        try {
            processRequest();
            while (!asyncManager.getAsyncMgrQueue().isEmpty()) {
                processRequest();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processRequest() throws InterruptedException {
        if (Constants.UNIFORM_SLEEP_TIME)
            sleep(Constants.SLEEP_TIME);
        else
            sleep(100 * threadId);
        processedRequests++;
        asyncManager.getRequest().process(name, processedRequests);
    }
}

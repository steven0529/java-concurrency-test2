package com.chuck.concurrencytest;

/**
 * Created by chuck on 21/04/2017.
 */
public class Request {

    private Callback callback;
    private int a,b;

    public Request(int a, int b, Callback callback) {
        this.a = a;
        this.b = b;
        this.callback = callback;
    }

    public synchronized void process(String threadName, int processedReqs) {
        callback.result(threadName, processedReqs, a, b, a + b);
    }
}

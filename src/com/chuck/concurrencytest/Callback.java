package com.chuck.concurrencytest;

/**
 * Created by chuck on 21/04/2017.
 */
public interface Callback {

    void result(String threadName, int processedReqs, int num1, int num2, int sum);
}

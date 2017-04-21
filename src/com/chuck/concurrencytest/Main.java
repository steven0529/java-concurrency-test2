package com.chuck.concurrencytest;

import java.util.Random;

/**
 * Created by chuck on 21/04/2017.
 */
public class Main {

    public static void main(String[] args) {
        AsyncManager asyncManager = new AsyncManager();

        Random random = new Random();

        for (int i = 0; i < Constants.REQS_NUM; i++) {
            int a = random.nextInt(Constants.MAX_RANDOM_INT);
            int b = random.nextInt(Constants.MAX_RANDOM_INT);
            Request req = new Request(a, b, (name, processedReqs, num1, num2, sum) -> System.out.println(name
                    + ": Reqs processed (" + processedReqs + ")" + ": " + num1 + " + " + num2 + " = " + sum));
            asyncManager.addRequest(req);
        }
    }
}

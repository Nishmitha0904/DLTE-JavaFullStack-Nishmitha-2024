package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Schedule {
    public static void main(String[] args) {
        TransactionAnalysis transactionAnalysis = new TransactionAnalysis();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        ScheduledFuture scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(transactionAnalysis,2,5, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduledExecutorService.shutdown();
            }
        }, 30, TimeUnit.SECONDS);
    }
}
